package mahjong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PlayArea {
    private List<Tile> hand;
    private final List<Meld> melds;
    private static final int STARTING_HAND_SIZE = 13;
    private final List<Tile> discard;

    public PlayArea() {
        this.hand = new ArrayList<>();
        this.discard = new ArrayList<>();
        this.melds = new ArrayList<>();
    }

    public List<Tile> getHand() {
        return hand;
    }

    public int getStartingHandSize() {
        return STARTING_HAND_SIZE;
    }

    public List<Tile> getDiscard() {
        return discard;
    }

    public void draw(Deck deck, Deadwall deadwall) {
        this.hand = this.hand.stream().sorted().collect(Collectors.toList());
        Tile tile = deck.draw();
        this.hand.add(tile);
        displayHandAndMelds();
        if (isKan(tile)) {
            draw(deadwall.getDrawTiles(), deadwall);
        }
    }

    public void initialDraw(Deck deck) {
        Tile tile = deck.draw();
        this.hand.add(tile);
        this.hand = this.hand.stream().sorted().collect(Collectors.toList());
    }

    private boolean isKan(Tile tile) {
        if (this.hand.stream().filter(t -> t.getNumber() == tile.getNumber() && t.getSuit().equals(tile.getSuit())).count() == 4) {
            while (true) {
                System.out.println("Kan? (Y/N)");
                Scanner myScanner = new Scanner(System.in);
                String input = myScanner.nextLine();
                if ("N".equalsIgnoreCase(input)) {
                    return false;
                } else if ("Y".equalsIgnoreCase(input)) {
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

    public void meldChi(Tile discarded, List<Tile> newMeld, boolean isOpen) {
        this.hand.remove(newMeld.get(0));
        this.hand.remove(newMeld.get(1));
        newMeld.add(discarded);
        newMeld = newMeld.stream().sorted().collect(Collectors.toList());
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

    public List<List<Tile>> isOpenChi(Tile tile) {
        if (!tile.isHonor()) {
            List<List<Tile>> combinations = new ArrayList<>();
            List<Tile> minusTwoTiles = this.hand.stream().filter(t -> t.getNumber() == tile.getNumber() - 2 && t.getSuit().equals(tile.getSuit())).collect(Collectors.toList());
            List<Tile> minusOneTiles = this.hand.stream().filter(t -> t.getNumber() == tile.getNumber() - 1 && t.getSuit().equals(tile.getSuit())).collect(Collectors.toList());
            List<Tile> plusOneTiles = this.hand.stream().filter(t -> t.getNumber() == tile.getNumber() + 1 && t.getSuit().equals(tile.getSuit())).collect(Collectors.toList());
            List<Tile> plusTwoTiles = this.hand.stream().filter(t -> t.getNumber() == tile.getNumber() + 2 && t.getSuit().equals(tile.getSuit())).collect(Collectors.toList());
            List<List<Tile>> chiTiles = new ArrayList<>();
            chiTiles.add(minusTwoTiles);
            chiTiles.add(minusOneTiles);
            chiTiles.add(plusOneTiles);
            chiTiles.add(plusTwoTiles);
            for (int i = 0; i < 3; i++) {
                for (Tile firstTile : chiTiles.get(i)) {
                    for (Tile secondTile : chiTiles.get(i + 1)) {
                        List<Tile> newCombination = new ArrayList<>();
                        newCombination.add(firstTile);
                        newCombination.add(secondTile);
                        combinations.add(newCombination);
                    }
                }
            }
            return combinations.stream().distinct().collect(Collectors.toList());
        }
        return Collections.emptyList();
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

    public void takeTurn(Deck deck, Deadwall deadwall) {
        draw(deck, deadwall);
        makeDiscardSelection(false);
    }

    public void takeTurnAfterKan(Deadwall deadwall) {
        draw(deadwall.getDrawTiles(), deadwall);
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
                    return;
                }
            } catch (NumberFormatException ignored) {
                continue;
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