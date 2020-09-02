package Mahjong;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hand {
    private List<Tile> hand;
    private final int startingHandSize = 13;
    private List<Tile> discard;

    public Hand() {
        this.hand = new ArrayList<>();
        this.discard = new ArrayList<>();
    }

    public List<Tile> getHand() {
        return hand;
    }

    public int getStartingHandSize() {
        return startingHandSize;
    }

    public List<Tile> getDiscard() {
        return discard;
    }

    public void draw(Tile tile) {
        this.hand.add(tile);
    }

    public void discard(int index) {
        Tile tile = this.hand.remove(index);
        this.discard.add(tile);
    }

    public void takeTurn(Tile tile) {
        draw(tile);
        Scanner myScanner = new Scanner(System.in);
        while (true) {
            System.out.println(getHandAsString());
            System.out.println("Discard which tile? (1 - 14)");
            String input = myScanner.nextLine();
            try {
                int value = Integer.parseInt(input);
                if (value >= 1 && value <= 14) {
                    discard(value - 1);
                    break;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Input a valid tile");
        }

    }

    public void reset() {
        this.hand.clear();
        this.discard.clear();
    }

    public String getHandAsString() {
        StringBuilder str = new StringBuilder();
        for (Tile tile : this.hand) {
            if (tile.getNumber() != 0) {
                str.append(tile.getNumber());
                str.append(" ");
            }
            str.append(tile.getSuit());
            str.append(", ");
        }
        int length = str.length();
        str.delete(length - 2, length);
        return str.toString();
    }

    public String getDiscardAsString() {
        StringBuilder str = new StringBuilder();
        for (Tile tile : this.discard) {
            if (tile.getNumber() != 0) {
                str.append(tile.getNumber());
                str.append(" ");
            }
            str.append(tile.getSuit());
            str.append(", ");
        }
        int length = str.length();
        str.delete(length - 2, length);
        return str.toString();
    }
}
