package mahjong.tile;

public class DragonTile extends Tile {

    public DragonTile(String suit)  {
        this.number = 0;
        this.suit = suit;
        this.isRed = false;
        this.isDora = false;
        setImages();
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
}
