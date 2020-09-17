package Mahjong;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Hand {
    private List<Tile> hand;
    private List<Meld> melds;
    private final int startingHandSize = 13;
    private List<Tile> discard;

    public Hand() {
        this.hand = new ArrayList<>();
        this.discard = new ArrayList<>();
        this.melds = new ArrayList<>();
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

    public void draw(Deck deck) {
        this.hand = this.hand.stream().sorted().collect(Collectors.toList());
        Tile tile = deck.draw();
        this.hand.add(tile);
        displayHandAndMelds();
        if (isKan(tile)) {
            draw(deck);
        }
    }

    public void initialDraw(Deck deck) {
        this.hand = this.hand.stream().sorted().collect(Collectors.toList());
        Tile tile = deck.draw();
        this.hand.add(tile);
    }

    private boolean isKan(Tile tile) {
        if (this.hand.stream().filter(t -> t.getNumber() == tile.getNumber() && t.getSuit().equals(tile.getSuit())).count() == 4) {
            while (true) {
                System.out.println("Kan? (Y/N)");
                Scanner myScanner = new Scanner(System.in);
                String input = myScanner.nextLine();
                if ("N".equals(input.toUpperCase())) {
                    return false;
                } else if ("Y".equals(input.toUpperCase())) {
                    meldKan(tile, false);
                    return true;
                }
            }
        }
        return false;
    }

    public void meldKan(Tile tile, boolean isOpen) {
        List<Tile> newMeld = this.hand.stream().filter(t -> t.getNumber() == tile.getNumber() && t.getSuit().equals(tile.getSuit())).collect(Collectors.toList());
        this.hand = this.hand.stream().filter(t -> t.getNumber() != tile.getNumber() || !t.getSuit().equals(tile.getSuit())).collect(Collectors.toList());
        newMeld.add(tile);
        this.melds.add(new Meld(newMeld, isOpen));
    }

    public void meldPon(Tile tile, boolean isOpen) {
        List<Tile> newMeld = this.hand.stream().filter(t -> t.getNumber() == tile.getNumber() && t.getSuit().equals(tile.getSuit())).collect(Collectors.toList());
        this.hand = this.hand.stream().filter(t -> t.getNumber() != tile.getNumber() || !t.getSuit().equals(tile.getSuit())).collect(Collectors.toList());
        if (newMeld.size() == 3) {
            this.hand.add(newMeld.remove(0));
        }
        newMeld.add(tile);
        this.melds.add(new Meld(newMeld, isOpen));
    }

    public void discard(int index) {
        Tile tile = this.hand.remove(index);
        this.discard.add(tile);
    }

    public boolean isOpenKan(Tile tile) {
        return this.hand.stream().filter(t -> t.getNumber() == tile.getNumber() && t.getSuit().equals(tile.getSuit())).count() == 3;
    }

    public boolean isOpenPon(Tile tile) {
        return this.hand.stream().filter(t -> t.getNumber() == tile.getNumber() && t.getSuit().equals(tile.getSuit())).count() >= 2;
    }

    public Tile getLastDiscard() {
        if (this.discard.isEmpty()) {
            return null;
        }
        return this.discard.get(this.discard.size() - 1);
    }

    public void removeLastDiscard() {
        discard.remove(discard.size() - 1);
    }

    public void takeTurn(Deck deck) {
        draw(deck);
        makeDiscardSelection(false);
    }

    public void makeDiscardSelection(boolean displayHand) {
        Scanner myScanner = new Scanner(System.in);
        if (displayHand) {
            displayHandAndMelds();
        }
        while (true) {
            System.out.println("Discard which tile? (1 - " + hand.size() + ")");
            String input = myScanner.nextLine();
            try {
                int value = Integer.parseInt(input);
                if (value >= 1 && value <= hand.size()) {
                    discard(value - 1);
                    break;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Input a valid tile");
            displayHandAndMelds();
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

    public String getMeldsAsString() {
        StringBuilder str = new StringBuilder();
        for (Meld meld : this.melds) {
            for (Tile tile : meld.getTiles()) {
                if (tile.getNumber() != 0) {
                    str.append(tile.getNumber());
                    str.append(" ");
                }
                str.append(tile.getSuit());
                str.append(", ");
            }
            int length = str.length();
            str.delete(length - 2, length);
            str.append("; ");
        }
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

    private void displayHandAndMelds() {
        System.out.println("Hand: " + getHandAsString());
        System.out.println("Melds: " + getMeldsAsString());
    }
}
