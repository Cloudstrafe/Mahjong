package Mahjong;

import java.util.ArrayList;
import java.util.List;

public class Meld {
    private List<Tile> tiles;
    private boolean isOpen;

    public Meld() {
        this.tiles = new ArrayList<>();
        this.isOpen = true;
    }

    public Meld(List<Tile> tiles, boolean isOpen) {
        this.tiles = tiles;
        this.isOpen = isOpen;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
}
