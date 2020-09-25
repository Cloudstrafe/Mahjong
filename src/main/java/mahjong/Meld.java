package mahjong;

import java.util.List;

public class Meld {
    private final List<Tile> tiles;
    private final boolean isOpen;
    private final boolean isRun;

    public Meld(List<Tile> tiles, boolean isOpen, boolean isRun) {
        this.tiles = tiles;
        this.isOpen = isOpen;
        this.isRun = isRun;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean isRun() {return isRun;}
}
