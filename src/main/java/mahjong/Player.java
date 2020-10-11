package mahjong;

import mahjong.gui.SampleWindow;
import mahjong.yaku.YakuHandler;

import java.util.Scanner;

import static java.lang.System.exit;

public class Player {
    private PlayArea playArea;
    private String seat;
    private int points;
    private boolean isDealer;
    private String name;
    private int playerNumber;

    public Player(String seat, boolean isDealer, int playerNumber) {
        this.playArea = new PlayArea();
        this.seat = seat;
        this.points = 25000;
        this.isDealer = isDealer;
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Player " + playerNumber + "'s Name:");
        this.name = myScanner.nextLine();
        this.playerNumber = playerNumber;
    }

    public Player(String seat, boolean isDealer, int playerNumber, String name) {
        this.playArea = new PlayArea();
        this.seat = seat;
        this.points = 25000;
        this.isDealer = isDealer;
        this.name = name;
        this.playerNumber = playerNumber;
    }

    public void takeTurn(Deck deck, Deadwall deadwall, SampleWindow window) {
        playArea.draw(deck, deadwall, window);
        if (YakuHandler.hasValidYaku(this)) {
            System.out.println(this.getName() + ", would you like to Tsumo? (Y, N)");
            Scanner myScanner = new Scanner(System.in);
            if ("Y".equalsIgnoreCase(myScanner.nextLine())) {
                System.out.println("You Win!!");
                exit(0);
            }
        }
        playArea.makeDiscardSelection(false, window);
    }

    public void takeTurnAfterKan(Deadwall deadwall, SampleWindow window) {
        playArea.draw(deadwall.getDrawTiles(), deadwall, window);
        if (YakuHandler.hasValidYaku(this)) {
            System.out.println(this.getName() + ", would you like to Tsumo? (Y, N)");
            Scanner myScanner = new Scanner(System.in);
            if ("Y".equalsIgnoreCase(myScanner.nextLine())) {
                System.out.println("You Win!!");
                exit(0);
            }
        }
        playArea.makeDiscardSelection(false, window);
    }

    public PlayArea getPlayArea() {
        return playArea;
    }

    public void setPlayArea(PlayArea playArea) {
        this.playArea = playArea;
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
