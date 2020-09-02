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

    public Game() {
        this.playerOne = new Player("East", true, 1);
        this.playerTwo = new Player("South", false, 2);
        this.playerThree = new Player("West", false, 3);
        this.playerFour = new Player("North", false, 4);
        this.roundWind = "East";
        this.round = 1;
        this.deck = new Deck();
        this.turnQueue = new LinkedList<>();
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
            playerOne.getHand().draw(deck.draw());
            playerTwo.getHand().draw(deck.draw());
            playerThree.getHand().draw(deck.draw());
            playerFour.getHand().draw(deck.draw());
        }
    }

    private void playRound() {
        while (!deck.getDeck().isEmpty()) {
            Player currentPlayer = turnQueue.remove();
            System.out.println(currentPlayer.getName() + "'s turn");
            currentPlayer.getHand().takeTurn(deck.draw());
            turnQueue.add(currentPlayer);
        }
    }

    public void startGame() {
        setupRound();
        playRound();
    }
}
