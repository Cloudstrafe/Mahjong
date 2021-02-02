package mahjong.tile;

public class WindTile extends Tile {

    public WindTile(String suit) {
        super(0, suit, false, false);
    }

    public WindTile(WindTile tile) {
        super(tile);
    }

    @Override
    public boolean isDragon() {
        return false;
    }

    @Override
    public boolean isWind() {
        return true;
    }

    @Override
    public boolean isNumber() {
        return false;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    @Override
    public boolean isHonor() {
        return true;
    }

    @Override
    public boolean isGreen() {
        return isGreen;
    }
}
