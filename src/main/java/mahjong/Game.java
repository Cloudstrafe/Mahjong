package mahjong;

import mahjong.gui.GameWindow;
import mahjong.tile.Tile;
import mahjong.yaku.RoundWindYaku;
import mahjong.yaku.YakuHandler;

import javax.swing.*;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.lang.System.exit;
import static mahjong.SuitConstants.*;

public class Game {
    private Player playerOne;
    private Player playerTwo;
    private Player playerThree;
    private Player playerFour;
    private Deck deck;
    private Queue<Player> turnQueue;
    private Deadwall deadwall;
    private GameWindow window;
    private boolean isRoundOver;
    private static final String S_HAND = "'s Hand";
    private static final String INPUT_VALID_CHOICE = "Please input a valid choice";
    private static final int KAN = 0;
    private static final int PON = 1;

    public Game() {
        this.window = new GameWindow();
        this.playerOne = new Player(EAST_WIND, true, 1);
        this.playerTwo = new Player(SOUTH_WIND, false, 2);
        this.playerThree = new Player(WEST_WIND, false, 3);
        this.playerFour = new Player(NORTH_WIND, false, 4);
        this.deck = new Deck();
        this.turnQueue = new LinkedList<>();
        this.isRoundOver = false;
        this.deadwall = new Deadwall();
        setupWindow();
        turnQueue.add(playerOne);
        turnQueue.add(playerTwo);
        turnQueue.add(playerThree);
        turnQueue.add(playerFour);
    }

    private void setupWindow() {
        window.addPanels(playerOne);
        window.addPanels(playerTwo);
        window.addPanels(playerThree);
        window.addPanels(playerFour);
    }

    private void setupRound() {
        RoundWindYaku.setRoundWind(EAST_WIND);
        this.deck.shuffle();
        this.playerOne.getPlayArea().setup(deck);
        this.playerTwo.getPlayArea().setup(deck);
        this.playerThree.getPlayArea().setup(deck);
        this.playerFour.getPlayArea().setup(deck);
        deadwall.setup(deck);
    }

    private void playRound() {
        while (!deck.getWall().isEmpty()) {
            Player currentPlayer = turnQueue.remove();
            System.out.println(currentPlayer.getPlayerNumber() + "'s turn, " + currentPlayer.getSeat() + ", Tiles in deck: " + deck.getTiles() + ", Dora: " + deadwall.getDoraAsString());
            currentPlayer.takeTurn(deck, deadwall, window);
            turnQueue.add(currentPlayer);
            checkRons(currentPlayer);
        }
        if (!isRoundOver) {
            JOptionPane.showMessageDialog(this.window.getWindow(), MessageConstants.MSG_EMPTY_DECK);
            beginNewRound();
        }
    }

    private void checkRons(Player currentPlayer) {
        Tile discarded = currentPlayer.getPlayArea().getLastDiscard();
        while (turnQueue.peek() != currentPlayer) {
            Player player = turnQueue.remove();
            player.getPlayArea().getHand().add(discarded);
            if (YakuHandler.hasValidYaku(player)) {
                player.getPlayArea().getHand().remove(discarded);
                player.getPlayArea().displayHandAndMelds();
                if (this.window.isCallConfirmed(MessageFormat.format(MessageConstants.MSG_RON, player.getPlayerNumber()))) {
                    player.getPlayArea().getHand().add(discarded);
                    JOptionPane.showMessageDialog(this.window.getWindow(), MessageFormat.format(MessageConstants.MSG_WIN, player.getPlayerNumber()));
                    exit(0);
                }
            } else {
                player.getPlayArea().getHand().remove(discarded);
            }
            turnQueue.add(player);
        }
        turnQueue.add(turnQueue.remove());
        checkOpenKansAndPons(currentPlayer);
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
        player.getPlayArea().displayHandAndMelds();
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
        return false;
    }

    private boolean ponOnly(Player currentPlayer, Tile discarded, Player player) {
        player.getPlayArea().displayHandAndMelds();
        if (this.window.isCallConfirmed(MessageFormat.format(MessageConstants.MSG_PON, player.getPlayerNumber()))) {
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
        nextPlayer.getPlayArea().displayHandAndMelds();
        if (this.window.isCallConfirmed(MessageFormat.format(MessageConstants.MSG_CHI, nextPlayer.getPlayerNumber()))) {
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
        System.out.println(callingPlayer.getPlayerNumber() + "'s turn, " + callingPlayer.getSeat() + ", Tiles in deck: " + deck.getTiles() + ", Dora: " + deadwall.getDoraAsString());
        if (!isKan) {
            callingPlayer.getPlayArea().makeDiscardSelection(true, window);
        }
        if (isKan) {
            callingPlayer.takeTurnAfterKan(deadwall, window);
            deadwall.setRevealed(deadwall.getRevealed() + 1);
        }
        checkRons(callingPlayer);
    }

    public void beginNewRound() {
        setupRound();
        playRound();
    }
}