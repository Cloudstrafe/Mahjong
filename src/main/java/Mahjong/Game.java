package Mahjong;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Game {
    private Player playerOne;
    private Player playerTwo;
    private Player playerThree;
    private Player playerFour;
    private String roundWind;
    private int round;
    private Deck deck;
    private Queue<Player> turnQueue;
    private boolean isRoundOver;

    public Game() {
        this.playerOne = new Player("East", true, 1);
        this.playerTwo = new Player("South", false, 2);
        this.playerThree = new Player("West", false, 3);
        this.playerFour = new Player("North", false, 4);
        this.roundWind = "East";
        this.round = 1;
        this.deck = new Deck();
        this.turnQueue = new LinkedList<>();
        this.isRoundOver = false;
        turnQueue.add(playerOne);
        turnQueue.add(playerTwo);
        turnQueue.add(playerThree);
        turnQueue.add(playerFour);
    }

    private void setupRound() {
        this.deck.shuffle();
        this.playerOne.getHand().reset();
        this.playerTwo.getHand().reset();
        this.playerThree.getHand().reset();
        this.playerFour.getHand().reset();
        for (int i = 0; i < playerOne.getHand().getStartingHandSize(); i++) {
            playerOne.getHand().initialDraw(deck);
            playerTwo.getHand().initialDraw(deck);
            playerThree.getHand().initialDraw(deck);
            playerFour.getHand().initialDraw(deck);
        }
    }

    private void playRound() {
        while (!deck.getDeck().isEmpty()) {
            Player currentPlayer = turnQueue.remove();
            System.out.println(currentPlayer.getName() + "'s turn, " + currentPlayer.getSeat() + ", Tiles in deck: " + deck.getTiles());
            currentPlayer.getHand().takeTurn(deck);
            turnQueue.add(currentPlayer);
            checkOpenKansAndPons(currentPlayer);
        }
        if (!isRoundOver) {
            System.out.println("Deck empty, starting new hand");
            setupRound();
            playRound();
        }
    }

    private void checkOpenKansAndPons(Player currentPlayer) {
        Tile discarded = currentPlayer.getHand().getLastDiscard();
        while (turnQueue.peek() != currentPlayer) {
            Player player = turnQueue.remove();
            if (player.getHand().isOpenPon(discarded)) {
                if (player.getHand().isOpenKan(discarded)) {
                    String response = "";
                    while (!"K".equals(response.toUpperCase()) && !"P".equals(response.toUpperCase()) && !"N".equals(response.toUpperCase())) {
                        System.out.println(player.getName() + "'s Hand");
                        System.out.println(player.getHand().getHandAsString());
                        System.out.println(player.getName() + ", would you like to Kan or Pon? (K, P, N)");
                        Scanner myScanner = new Scanner(System.in);
                        response = myScanner.nextLine();
                        if ("K".equals(response.toUpperCase())) {
                            player.getHand().meldKan(discarded, true);
                            callHandler(currentPlayer, player);
                            return;
                        } else if ("P".equals(response.toUpperCase())) {
                            player.getHand().meldPon(discarded, true);
                            callHandler(currentPlayer, player);
                            return;
                        } else if ("N".equals(response.toUpperCase())) {
                        } else {
                            System.out.println("Please input a valid choice");
                        }
                    }
                } else {
                    String response = "";
                    while (!"P".equals(response.toUpperCase()) && !"N".equals(response.toUpperCase())) {
                        System.out.println(player.getName() + "'s Hand");
                        System.out.println(player.getHand().getHandAsString());
                        System.out.println(player.getName() + ", would you like to Pon? (P, N)");
                        Scanner myScanner = new Scanner(System.in);
                        response = myScanner.nextLine();
                        if ("P".equals(response.toUpperCase())) {
                            player.getHand().meldPon(discarded, true);
                            callHandler(currentPlayer, player);
                            return;
                        } else if ("N".equals(response.toUpperCase())) {
                        } else {
                            System.out.println("Please input a valid choice");
                        }
                    }
                }
            }
            turnQueue.add(player);
        }
        turnQueue.add(turnQueue.remove());
    }

    private void callHandler(Player currentPlayer, Player callingPlayer) {
        currentPlayer.getHand().removeLastDiscard();
        turnQueue.add(callingPlayer);
        callingPlayer.getHand().makeDiscardSelection(true);
        checkOpenKansAndPons(callingPlayer);
        //TODO debug back to back kan/pons
    }

    public void startGame() {
        setupRound();
        playRound();
    }
}