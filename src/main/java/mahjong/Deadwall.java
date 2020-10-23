package mahjong;

import mahjong.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class Deadwall {
    private List<Tile> doraTiles;
    private int revealed;
    private Deck drawTiles;
    private static final int DEADWALL_SIZE = 10;
    private static final int DEADWALL_DRAW_SIZE = 4;

    public Deadwall(Deck deck) {
        this.doraTiles = new ArrayList<>();
        List<Tile> deadwallDraw = new ArrayList<>();
        for (int i = 0; i < DEADWALL_DRAW_SIZE; i++) {
            deadwallDraw.add(deck.draw());
        }
        setDrawTiles(new Deck(deadwallDraw));
        this.revealed = 0;
    }

    public void setup(Deck deck) {
        clearAll();
        for (int i = 0; i < DEADWALL_SIZE; i++) {
            this.doraTiles.add(deck.draw());
        }
        List<Tile> deadwallDraw = new ArrayList<>();
        for (int i = 0; i < DEADWALL_DRAW_SIZE; i++) {
            deadwallDraw.add(deck.draw());
        }
        setDrawTiles(new Deck(deadwallDraw));
        setRevealed(1);
    }

    public String getDoraAsString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.revealed; i++) {
            str.append(doraTiles.get(i).getTileAsString());
            str.append(", ");
        }
        int length = str.length();
        str.delete(length - 2, length);
        return str.toString();
    }

    public Deck getDrawTiles() {
        return drawTiles;
    }

    public void setDrawTiles(Deck drawTiles) {
        this.drawTiles = drawTiles;
    }

    public boolean isRoundOver() {
        return revealed == 4;
    }

    public List<Tile> getDoraTiles() {
        return doraTiles;
    }

    public void clearAll() {
        drawTiles.getDrawn().clear();
        drawTiles.getWall().clear();
        doraTiles.clear();
    }

    public int getRevealed() {
        return revealed;
    }

    public void setRevealed(int revealed) {
        this.revealed = revealed;
    }
}
