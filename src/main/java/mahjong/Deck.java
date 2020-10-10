package mahjong;

import mahjong.tile.DragonTile;
import mahjong.tile.NumberTile;
import mahjong.tile.Tile;
import mahjong.tile.WindTile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Tile> wall;
    private List<Tile> drawn;
    private int tiles;

    public Deck() {
        this.wall = new ArrayList<>();
        this.drawn = new ArrayList<>();
        this.tiles = 136;
        buildDeck();
    }

    public Deck(List<Tile> tiles) {
        this.wall = tiles;
        this.drawn = new ArrayList<>();
        this.tiles = 4;
    }

    public List<Tile> getWall() {
        return this.wall;
    }

    public int getTiles() {
        return tiles;
    }

    public Tile draw() {
        if (!this.wall.isEmpty()) {
            Tile tile = this.wall.remove(0);
            this.drawn.add(tile);
            this.tiles--;
            return tile;
        }
        return null;
    }

    public void buildDeck() {
        createDragonTiles(this.wall);
        createWindTiles(this.wall);
        createNumberTiles(this.wall);
    }

    public void shuffle() {
        this.wall.addAll(this.drawn);
        this.drawn.clear();
        this.tiles = 136;
        Collections.shuffle(this.wall);
    }

    private void createDragonTiles(List<Tile> deck) {
        List<String> suits = new ArrayList<>();
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
        List<String> suits = new ArrayList<>();
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
        List<String> suits = new ArrayList<>();
        suits.add(SuitConstants.BAMBOO);
        suits.add(SuitConstants.CHARACTERS);
        suits.add(SuitConstants.DOTS);
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 9; j++) {
                for (String suit : suits) {
                    NumberTile tile;
                    if ((j == 5 && i == 0) || (j == 5 && i == 1 && SuitConstants.DOTS.equals(suit))) {
                        tile = new NumberTile(j, suit, true);
                    } else {
                        tile = new NumberTile(j, suit, false);
                    }
                    deck.add(tile);
                }
            }
        }
    }
}
