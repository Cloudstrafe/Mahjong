package Mahjong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Tile> deck;
    private List<Tile> drawn;

    public Deck() {
        this.deck = new ArrayList<>();
        this.drawn = new ArrayList<>();
        buildDeck();
    }

    public List<Tile> getDeck() {
        return this.deck;
    }

    public Tile draw() {
        if (!this.deck.isEmpty()) {
            Tile tile = this.deck.remove(0);
            this.drawn.add(tile);
            return tile;
        }
        return null;
    }

    public void buildDeck() {
        createDragonTiles(this.deck);
        createWindTiles(this.deck);
        createNumberTiles(this.deck);
    }

    public void shuffle() {
        this.deck.addAll(this.drawn);
        this.drawn.clear();
        Collections.shuffle(this.deck);
    }

    private void createDragonTiles(List<Tile> deck) {
        List<String> suits = new ArrayList<String>();
        suits.add(SuitConstants.RED_DRAGON);
        suits.add(SuitConstants.WHITE_DRAGON);
        suits.add(SuitConstants.GREEN_DRAGON);
        for (int i = 0; i < 4; i++) {
            for (String suit: suits) {
                DragonTile tile = new DragonTile(suit);
                deck.add(tile);
            }
        }
    }

    private void createWindTiles(List<Tile> deck) {
        List<String> suits = new ArrayList<String>();
        suits.add(SuitConstants.EAST_WIND);
        suits.add(SuitConstants.SOUTH_WIND);
        suits.add(SuitConstants.WEST_WIND);
        suits.add(SuitConstants.NORTH_WIND);
        for (int i = 0; i < 4; i++) {
            for (String suit : suits) {
                WindTile tile = new WindTile(suit);
                deck.add(tile);
            }
        }
    }

    private void createNumberTiles(List<Tile> deck) {
        List<String> suits = new ArrayList<String>();
        suits.add(SuitConstants.BAMBOO);
        suits.add(SuitConstants.CHARACTERS);
        suits.add(SuitConstants.DOTS);
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 9; j++) {
                for (String suit : suits) {
                    NumberTile tile = new NumberTile(j, suit, false);
                    deck.add(tile);
                }
            }
        }
    }
}
