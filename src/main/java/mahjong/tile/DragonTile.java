package mahjong.tile;

public class DragonTile extends Tile {

    public DragonTile(String suit)  {
        super(0, suit, false, false);
    }

    public DragonTile(DragonTile tile) {
        super(tile);
    }

    @Override
    public boolean isDragon() {
        return true;
    }

    @Override
    public boolean isWind() {
        return false;
    }

    @Override
    public boolean isPrevailingWind() {
        return false;
    }

    @Override
    public boolean isSeatWind() {
        return false;
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
