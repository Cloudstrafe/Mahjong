package mahjong;

import mahjong.tile.Tile;

import java.util.List;

public class Meld {
    private final List<Tile> tiles;
    private final boolean isOpen;
    private final boolean isRun;
    private final int calledTileIndex;

    public Meld(List<Tile> tiles, boolean isOpen, boolean isRun, int calledTileIndex) {
        this.tiles = tiles;
        this.isOpen = isOpen;
        this.isRun = isRun;
        this.calledTileIndex = calledTileIndex;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public int getCalledTileIndex() {
        return calledTileIndex;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean isRun() {return isRun;}
}
