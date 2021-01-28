package mahjong;

import mahjong.gui.GameWindow;
import mahjong.tile.Tile;
import mahjong.yaku.NoPointsYaku;
import mahjong.yaku.RoundWindYaku;
import mahjong.yaku.YakuHandler;

import javax.swing.*;
import java.text.MessageFormat;
import java.util.*;

import static java.lang.System.exit;
import static mahjong.SuitConstants.*;

public class Game {
    private final Player playerOne;
    private final Player playerTwo;
    private final Player playerThree;
    private final Player playerFour;
    private Deck deck;
    private final Queue<Player> turnQueue;
    private Deadwall deadwall;
    private final GameWindow window;
    private int riichiSticks;
    private int tsumiSticks;
    private int roundNumber;
    private String roundWind;
    private static final List<String> windArray = new ArrayList<>(Arrays.asList(EAST_WIND, SOUTH_WIND, WEST_WIND, NORTH_WIND));
    private static final int KAN = 0;
    private static final int PON = 1;

    public Game() {
        this.playerOne = new Player(EAST_WIND, true, 1);
        this.playerTwo = new Player(SOUTH_WIND, false, 2);
        this.playerThree = new Player(WEST_WIND, false, 3);
        this.playerFour = new Player(NORTH_WIND, false, 4);
        this.deck = new Deck();
        this.turnQueue = new LinkedList<>();
        this.deadwall = new Deadwall(deck);
        this.roundNumber = 1;
        this.roundWind = EAST_WIND;
        turnQueue.add(playerOne);
        turnQueue.add(playerTwo);
        turnQueue.add(playerThree);
        turnQueue.add(playerFour);
        this.window = new GameWindow(playerOne, playerTwo, playerThree, playerFour);
    }

    //use for testing w/o dealing with UI elements causing issues, pass in null for the GameWindow
    public Game(GameWindow gameWindow) {
        this.playerOne = new Player(EAST_WIND, true, 1);
        this.playerTwo = new Player(SOUTH_WIND, false, 2);
        this.playerThree = new Player(WEST_WIND, false, 3);
        this.playerFour = new Player(NORTH_WIND, false, 4);
        this.deck = new Deck();
        this.turnQueue = new LinkedList<>();
        this.deadwall = new Deadwall(deck);
        this.roundNumber = 1;
        this.roundWind = EAST_WIND;
        turnQueue.add(playerOne);
        turnQueue.add(playerTwo);
        turnQueue.add(playerThree);
        turnQueue.add(playerFour);
        window = gameWindow;
    }

    private void setupRound() {
        RoundWindYaku.setRoundWind(roundWind);
        NoPointsYaku.setRoundWind(roundWind);
        this.deck.reset();
        playerOne.reset();
        playerTwo.reset();
        playerThree.reset();
        playerFour.reset();
        this.playerOne.getPlayArea().setup(deck);
        this.playerTwo.getPlayArea().setup(deck);
        this.playerThree.getPlayArea().setup(deck);
        this.playerFour.getPlayArea().setup(deck);
        this.deadwall.setup(deck);
        putDealerAtFrontOfPlayerQueue();
        window.getDoraPanelHolder().reset();
        window.getDoraPanelHolder().displayDora(deadwall.getDoraTiles().get(0));
        this.window.getGameInfoPanel().getCurrentRoundNumber().setText(Integer.toString(roundNumber));
        this.window.getGameInfoPanel().getCurrentRoundWind().setText(getFullWindName(roundWind));
        this.window.getGameInfoPanel().getPlayerOneScore().setText(Integer.toString(playerOne.getPoints()));
        this.window.getGameInfoPanel().getPlayerTwoScore().setText(Integer.toString(playerTwo.getPoints()));
        this.window.getGameInfoPanel().getPlayerThreeScore().setText(Integer.toString(playerThree.getPoints()));
        this.window.getGameInfoPanel().getPlayerFourScore().setText(Integer.toString(playerFour.getPoints()));
        this.window.getGameInfoPanel().getPlayerOneSeat().setText(playerOne.getSeat().toUpperCase());
        this.window.getGameInfoPanel().getPlayerTwoSeat().setText(playerTwo.getSeat().toUpperCase());
        this.window.getGameInfoPanel().getPlayerThreeSeat().setText(playerThree.getSeat().toUpperCase());
        this.window.getGameInfoPanel().getPlayerFourSeat().setText(playerFour.getSeat().toUpperCase());
        window.getPlayerOneRiichiIndicator().setVisible(false);
        window.getPlayerTwoRiichiIndicator().setVisible(false);
        window.getPlayerThreeRiichiIndicator().setVisible(false);
        window.getPlayerFourRiichiIndicator().setVisible(false);
    }

    private void putDealerAtFrontOfPlayerQueue() {
        while (!turnQueue.peek().isDealer()) {
            turnQueue.add(turnQueue.remove());
        }
    }

    private void playRound() {
        while (!deck.getWall().isEmpty()) {
            Player currentPlayer = turnQueue.remove();
            currentPlayer.takeTurn(this);
            turnQueue.add(currentPlayer);
            checkRons(currentPlayer);
        }
        JOptionPane.showMessageDialog(this.window.getWindow(), MessageConstants.MSG_EMPTY_DECK);
        if (!ExhaustiveDraw.tenpaiPayout(turnQueue)) {
            //potentially clear tsumiSticks here? unsure about ruling when dealer loses control during exhaustive draw
            advanceRound();
        } else {
            tsumiSticks++;
            window.getDoraPanelHolder().setTsumiStickCount(tsumiSticks);
        }
        beginNewRound();
    }

    private void checkRons(Player currentPlayer) {
        ronHandler(currentPlayer, currentPlayer.getPlayArea().getLastDiscard(), false);
        if (currentPlayer.isInRiichi() && !currentPlayer.hasRiichiDeposit()) {
            currentPlayer.setPoints(currentPlayer.getPoints() - 1000);
            updateScoreDisplay(currentPlayer);
            riichiSticks++;
            window.getDoraPanelHolder().setRiichiStickCount(riichiSticks);
            displayRiichiIndicator(currentPlayer);
            currentPlayer.setHasRiichiDeposit(true);
            if (playerOne.isInRiichi() &&
                    playerTwo.isInRiichi() &&
                    playerThree.isInRiichi() &&
                    playerFour.isInRiichi()) {
                beginNewRound();
            }
        }
        checkOpenKansAndPons(currentPlayer);
    }

    public void ronHandler(Player currentPlayer, Tile ronTile, boolean robbedKan) {
        if (robbedKan) {
            turnQueue.add(currentPlayer);
        }
        while (turnQueue.peek() != currentPlayer) {
            Player player = turnQueue.remove();
            player.getPlayArea().getHand().add(ronTile);
            if (!player.isInPermanentFuriten() &&
                    !player.isInTemporaryFuriten() &&
                    YakuHandler.hasValidYaku(player)) {
                player.getPlayArea().getHand().remove(ronTile);
                player.getPlayArea().displayHandAndMelds();
                if (this.window.isConfirmed(MessageFormat.format(MessageConstants.MSG_RON, player.getPlayerNumber()))) {
                    player.getPlayArea().getHand().add(ronTile);
                    turnQueue.add(player);
                    endRound(player, ronTile, currentPlayer, robbedKan, false);
                } else {
                    setFuritenState(player);
                }
            } else {
                player.getPlayArea().getHand().remove(ronTile);
            }
            turnQueue.add(player);
        }
        if (robbedKan) {
            turnQueue.remove();
        } else {
            turnQueue.add(turnQueue.remove());
        }
    }

    private void setFuritenState(Player player) {
        if (player.isInRiichi()) {
            player.setInPermanentFuriten(true);
        } else {
            player.setInTemporaryFuriten(true);
        }
    }

    private void updateScoreDisplay(Player currentPlayer) {
        if (currentPlayer.getPlayerNumber() == 1) {
            window.getGameInfoPanel().getPlayerOneScore().setText(Integer.toString(playerOne.getPoints()));
        } else if (currentPlayer.getPlayerNumber() == 2) {
            window.getGameInfoPanel().getPlayerTwoScore().setText(Integer.toString(playerTwo.getPoints()));
        } else if (currentPlayer.getPlayerNumber() == 3) {
            window.getGameInfoPanel().getPlayerThreeScore().setText(Integer.toString(playerThree.getPoints()));
        }
        if (currentPlayer.getPlayerNumber() == 4) {
            window.getGameInfoPanel().getPlayerFourScore().setText(Integer.toString(playerFour.getPoints()));
        }
    }

    public void endRound(Player winningPlayer, Tile winningTile, Player discardingPlayer, boolean robbedKan, boolean isTsumo) {
        //scoring stuff
        ScoringResult scoringResult = ScoringHelper.scoreRound(deadwall, deck, roundWind, winningTile, winningPlayer, isTsumo, riichiSticks, tsumiSticks, robbedKan);
        ScoringHelper.adjustScores(scoringResult, this, winningPlayer, discardingPlayer);
        JOptionPane.showMessageDialog(this.window.getWindow(), window.getScoreWindowMessage(scoringResult, winningPlayer));
        riichiSticks = 0;
        window.getDoraPanelHolder().setRiichiStickCount(riichiSticks);
        if (!winningPlayer.isDealer()) {
            tsumiSticks = 0;
            advanceRound();
        } else {
            tsumiSticks++;
        }
        window.getDoraPanelHolder().setTsumiStickCount(tsumiSticks);
        if (hasGameEnded()) {
            if (window.isConfirmed(MessageConstants.MSG_PLAY_AGAIN)) {
                beginNewGame();
            } else {
                exit(0);
            }
        }
        beginNewRound();
    }

    private void checkOpenKansAndPons(Player currentPlayer) {
        Tile discarded = currentPlayer.getPlayArea().getLastDiscard();
        while (turnQueue.peek() != currentPlayer) {
            Player player = turnQueue.remove();
            if (player.getPlayArea().isOpenPon(discarded)) {
                if (player.getPlayArea().isOpenKan(discarded)) {
                    if (kanOrPon(currentPlayer, discarded, player)) return;
                } else {
                    if (ponOnly(currentPlayer, discarded, player)) return;
                }
            }
            turnQueue.add(player);
        }
        turnQueue.add(turnQueue.remove());
        checkChi(currentPlayer);
    }

    private boolean kanOrPon(Player currentPlayer, Tile discarded, Player player) {
        if (!player.isInRiichi()) {
            int response = this.window.isKanOrPonCallConfirmed(MessageFormat.format(MessageConstants.MSG_KAN_OR_PON, player.getPlayerNumber()));
            if (response == KAN) {
                player.getPlayArea().meldKan(discarded, true);
                if (deadwall.isRoundOver()) {
                    beginNewRound();
                }
                callHandler(currentPlayer, player, true);
                return true;
            } else if (response == PON) {
                player.getPlayArea().meldPon(discarded, true);
                callHandler(currentPlayer, player, false);
                return true;
            }
        }
        return false;
    }

    private boolean ponOnly(Player currentPlayer, Tile discarded, Player player) {
        if (!player.isInRiichi() && this.window.isConfirmed(MessageFormat.format(MessageConstants.MSG_PON, player.getPlayerNumber()))) {
            player.getPlayArea().meldPon(discarded, true);
            callHandler(currentPlayer, player, false);
            return true;
        }
        return false;
    }

    private void checkChi(Player currentPlayer) {
        Player nextPlayer = turnQueue.peek();
        Tile discarded = currentPlayer.getPlayArea().getLastDiscard();
        List<List<Tile>> possibleChi = nextPlayer.getPlayArea().isOpenChi(discarded);
        if (!possibleChi.isEmpty()) {
            chi(currentPlayer, nextPlayer, discarded, possibleChi);
        }
    }

    private void chi(Player currentPlayer, Player nextPlayer, Tile discarded, List<List<Tile>> possibleChi) {
        if (!nextPlayer.isInRiichi() && this.window.isConfirmed(MessageFormat.format(MessageConstants.MSG_CHI, nextPlayer.getPlayerNumber()))) {
            turnQueue.remove();
            if (possibleChi.size() > 1) {
                int response = this.window.getChiCallChoice(MessageFormat.format(MessageConstants.MSG_SELECT_CHI_OPTION, nextPlayer.getPlayerNumber()), possibleChi);
                nextPlayer.getPlayArea().meldChi(discarded, possibleChi.get(response), true);
            } else {
                nextPlayer.getPlayArea().meldChi(discarded, possibleChi.get(0), true);
            }
            callHandler(currentPlayer, nextPlayer, false);
        }
    }

    private void callHandler(Player discardingPlayer, Player callingPlayer, boolean isKan) {
        discardingPlayer.getPlayArea().removeLastDiscard();
        turnQueue.add(callingPlayer);
        for (Player player : turnQueue) {
            player.setIppatsu(false);
            player.setFirstTurn(false);
        }
        if (!isKan) {
            callingPlayer.getPlayArea().makeDiscardSelection(true, window);
            callingPlayer.setWaits(YakuHandler.getWaitTiles(new Player(callingPlayer)));
            callingPlayer.setInTemporaryFuriten(callingPlayer.isInFuriten());
        } else {
            callingPlayer.takeTurnAfterKan(this);
            deadwall.setRevealed(deadwall.getRevealed() + 1);
            window.getDoraPanelHolder().displayDora(deadwall.getDoraTiles().get(deadwall.getRevealed()));
        }
        checkRons(callingPlayer);
    }

    private String getFullWindName(String wind) {
        if (EAST_WIND.equals(wind)) {
            return EAST;
        } else if (SOUTH_WIND.equals(wind)) {
            return SOUTH;
        } else if (WEST_WIND.equals(wind)) {
            return WEST;
        }
        return NORTH;
    }

    public void advanceRound() {
        if (roundNumber == 4) {
            roundWind = windArray.get(windArray.indexOf(roundWind) + 1);    //technically should check in case of going out of bounds, extremely unlikely to happen
            roundNumber = 1;
        } else {
            roundNumber++;
        }
        for (Player player : turnQueue) {
            int index = windArray.indexOf(player.getSeat()) - 1;
            if (index == -1) {
                index = 3;
            }
            player.setSeat(windArray.get(index));
            player.setDealer(index == 0);
        }
    }

    public boolean hasGameEnded() {
        boolean playerHas30000Points = false;
        for (Player player : turnQueue) {
            if (player.getPoints() >= 30000) {
                playerHas30000Points = true;
            }
            if (player.getPoints() < 0) {
                return true;
            }
        }
        return (WEST_WIND.equals(roundWind) || NORTH_WIND.equals(roundWind)) && playerHas30000Points;
    }

    public void beginNewRound() {
        setupRound();
        playRound();
    }

    public void beginNewGame() {
        playerOne.newGame(EAST_WIND);
        playerTwo.newGame(SOUTH_WIND);
        playerThree.newGame(WEST_WIND);
        playerFour.newGame(NORTH_WIND);
        playerOne.setDealer(true);
        roundWind = EAST_WIND;
        roundNumber = 1;
        beginNewRound();
    }

    private void displayRiichiIndicator(Player player) {
        if (player.getPlayerNumber() == 1) {
            window.getPlayerOneRiichiIndicator().setVisible(true);
        } else if (player.getPlayerNumber() == 2) {
            window.getPlayerTwoRiichiIndicator().setVisible(true);
        } else if (player.getPlayerNumber() == 3) {
            window.getPlayerThreeRiichiIndicator().setVisible(true);
        } else {
            window.getPlayerFourRiichiIndicator().setVisible(true);
        }

    }

    public Player getPlayerFromNumber(int playerNumber) {
        if (playerNumber == 1) {
            return playerOne;
        }
        if (playerNumber == 2) {
            return playerTwo;
        }
        if (playerNumber == 3) {
            return playerThree;
        }
        return playerFour;
    }

    public Queue<Player> getTurnQueue() {
        return turnQueue;
    }

    public Deadwall getDeadwall() {
        return deadwall;
    }

    public Deck getDeck() {
        return deck;
    }

    public String getRoundWind() {
        return roundWind;
    }

    public void setDeadwall(Deadwall deadwall) {
        this.deadwall = deadwall;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public GameWindow getWindow() {
        return window;
    }
}