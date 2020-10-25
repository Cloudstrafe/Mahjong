package mahjong;

import mahjong.tile.DragonTile;
import mahjong.tile.NumberTile;
import mahjong.tile.Tile;
import mahjong.tile.WindTile;

import java.util.List;
import java.util.stream.Collectors;

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

    public Meld(Meld meld) {
        this.tiles = meld.tiles.stream().map(t -> {
            if (t instanceof NumberTile) {
                return new NumberTile((NumberTile) t);
            } else if (t instanceof DragonTile) {
                return new DragonTile((DragonTile) t);
            } else {
                return new WindTile((WindTile) t);
            }
        }).collect(Collectors.toList());
        this.isOpen = meld.isOpen;
        this.isRun = meld.isRun;
        this.calledTileIndex = meld.calledTileIndex;
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

    public boolean isRun() {
        return isRun;
    }
}
