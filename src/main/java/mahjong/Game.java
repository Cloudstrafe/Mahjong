package mahjong;

import mahjong.gui.SampleWindow;
import mahjong.tile.Tile;
import mahjong.yaku.RoundWindYaku;
import mahjong.yaku.YakuHandler;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

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
    private SampleWindow window;
    private boolean isRoundOver;
    private static final String S_HAND = "'s Hand";
    private static final String INPUT_VALID_CHOICE = "Please input a valid choice";

    public Game() {
        this.window = new SampleWindow();
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
        this.playerOne.getPlayArea().reset();
        this.playerTwo.getPlayArea().reset();
        this.playerThree.getPlayArea().reset();
        this.playerFour.getPlayArea().reset();
        for (int i = 0; i < playerOne.getPlayArea().getStartingHandSize(); i++) {
            playerOne.getPlayArea().initialDraw(deck);
            playerTwo.getPlayArea().initialDraw(deck);
            playerThree.getPlayArea().initialDraw(deck);
            playerFour.getPlayArea().initialDraw(deck);
        }
        deadwall.setup(deck);
    }

    private void playRound() {
        while (!deck.getWall().isEmpty()) {
            Player currentPlayer = turnQueue.remove();
            System.out.println(currentPlayer.getName() + "'s turn, " + currentPlayer.getSeat() + ", Tiles in deck: " + deck.getTiles() + ", Dora: " + deadwall.getDoraAsString());
            currentPlayer.takeTurn(deck, deadwall, window);
            turnQueue.add(currentPlayer);
            checkRons(currentPlayer);
        }
        if (!isRoundOver) {
            System.out.println("Deck empty, starting new hand");
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
                String response = "";
                while (!"Y".equalsIgnoreCase(response) && !"N".equalsIgnoreCase(response)) {
                    System.out.println(player.getName() + S_HAND);
                    player.getPlayArea().displayHandAndMelds(window);
                    System.out.println(player.getName() + ", would you like to Ron the " + discarded.getTileAsString() + "? (Y, N)");
                    Scanner myScanner = new Scanner(System.in);
                    response = myScanner.nextLine();
                    if ("Y".equalsIgnoreCase(response)) {
                        player.getPlayArea().getHand().add(discarded);
                        System.out.println("You Win!!");
                        exit(0);
                    } else if ("N".equalsIgnoreCase(response)) {
                        break;
                    } else {
                        System.out.println(INPUT_VALID_CHOICE);
                    }
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
        String response = "";
        while (!"K".equalsIgnoreCase(response) && !"P".equalsIgnoreCase(response.toUpperCase()) && !"N".equalsIgnoreCase(response)) {
            System.out.println(player.getName() + S_HAND);
            player.getPlayArea().displayHandAndMelds(window);
            System.out.println(player.getName() + ", would you like to Kan or Pon the " + discarded.getTileAsString() + "? (K, P, N)");
            Scanner myScanner = new Scanner(System.in);
            response = myScanner.nextLine();
            if ("K".equalsIgnoreCase(response)) {
                player.getPlayArea().meldKan(discarded, true);
                if (deadwall.isRoundOver()) {
                    beginNewRound();
                }
                callHandler(currentPlayer, player, true);
                return true;
            } else if ("P".equalsIgnoreCase(response)) {
                player.getPlayArea().meldPon(discarded, true);
                callHandler(currentPlayer, player, false);
                return true;
            } else if ("N".equalsIgnoreCase(response)) {
                break;
            } else {
                System.out.println(INPUT_VALID_CHOICE);
            }
        }
        return false;
    }

    private boolean ponOnly(Player currentPlayer, Tile discarded, Player player) {
        String response = "";
        while (!"Y".equalsIgnoreCase(response) && !"N".equalsIgnoreCase(response)) {
            System.out.println(player.getName() + S_HAND);
            player.getPlayArea().displayHandAndMelds(window);
            System.out.println(player.getName() + ", would you like to Pon the " + discarded.getTileAsString() + "? (Y, N)");
            Scanner myScanner = new Scanner(System.in);
            response = myScanner.nextLine();
            if ("Y".equalsIgnoreCase(response)) {
                player.getPlayArea().meldPon(discarded, true);
                callHandler(currentPlayer, player, false);
                return true;
            } else if ("N".equalsIgnoreCase(response)) {
                break;
            } else {
                System.out.println(INPUT_VALID_CHOICE);
            }
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
        String response = "";
        while (!"Y".equalsIgnoreCase(response) && !"N".equalsIgnoreCase(response)) {
            System.out.println(nextPlayer.getName() + S_HAND);
            nextPlayer.getPlayArea().displayHandAndMelds(window);
            System.out.println(nextPlayer.getName() + ", would you like to Chi the " + discarded.getTileAsString() + "? (Y, N)");
            Scanner myScanner = new Scanner(System.in);
            response = myScanner.nextLine();
            if ("Y".equalsIgnoreCase(response)) {
                turnQueue.remove();
                if (possibleChi.size() > 1) {
                    while (true) {
                        System.out.println("Which tiles would you like to use? (1 - " + possibleChi.size() + ")");
                        printChiOptions(possibleChi);
                        response = myScanner.nextLine();
                        try {
                            int value = Integer.parseInt(response);
                            if (value >= 1 && value <= possibleChi.size()) {
                                nextPlayer.getPlayArea().meldChi(discarded, possibleChi.get(value - 1), true);
                                callHandler(currentPlayer, nextPlayer, false);
                                return;
                            }
                        } catch (NumberFormatException ignored) {
                            continue;
                        }
                        System.out.println(INPUT_VALID_CHOICE);
                    }
                } else {
                    nextPlayer.getPlayArea().meldChi(discarded, possibleChi.get(0), true);
                    callHandler(currentPlayer, nextPlayer, false);
                    return;
                }
            } else if ("N".equalsIgnoreCase(response)) {
                break;
            } else {
                System.out.println(INPUT_VALID_CHOICE);
            }
        }
    }

    private void printChiOptions(List<List<Tile>> combinations) {
        for (List<Tile> combination : combinations) {
            StringBuilder str = new StringBuilder();
            for (Tile tile : combination) {
                str.append(tile.getTileAsString());
                str.append(", ");
            }
            int length = str.length();
            str.delete(length - 2, length);
            System.out.println(str.toString());
        }
    }

    private void callHandler(Player currentPlayer, Player callingPlayer, boolean isKan) {
        currentPlayer.getPlayArea().removeLastDiscard();
        turnQueue.add(callingPlayer);
        System.out.println(callingPlayer.getName() + "'s turn, " + callingPlayer.getSeat() + ", Tiles in deck: " + deck.getTiles() + ", Dora: " + deadwall.getDoraAsString());
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