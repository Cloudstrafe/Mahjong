package Mahjong;

import java.util.Scanner;

public class Player {
    private Hand hand;
    private String seat;
    private int points;
    private boolean isDealer;
    private String name;
    private int playerNumber;

    public Player() {
        this.hand = new Hand();
        this.points = 25000;
        this.isDealer = false;
    }

    public Player(String seat, boolean isDealer, int playerNumber) {
        this.hand = new Hand();
        this.seat = seat;
        this.points = 25000;
        this.isDealer = isDealer;
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Player " + playerNumber + "'s Name:");
        this.name = myScanner.nextLine();
        this.playerNumber = playerNumber;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isDealer() {
        return isDealer;
    }

    public void setDealer(boolean dealer) {
        isDealer = dealer;
    }

    public String getName() {
        return name;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

}
