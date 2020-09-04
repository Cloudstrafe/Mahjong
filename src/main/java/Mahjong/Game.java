package Mahjong;

import java.util.LinkedList;
import java.util.Queue;

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
        }
        if (!isRoundOver) {
            System.out.println("Deck empty, starting new hand");
            setupRound();
            playRound();
        }
    }

    public void startGame() {
        setupRound();
        playRound();
    }
}