package mahjong.tile;

public class NumberTile extends Tile {

    public NumberTile(int number, String suit, boolean isRed) {
        this.number = number;
        this.suit = suit;
        this.isRed = isRed;
        this.isDora = isRed;
        setImageFilePaths();
    }

    @Override
    public boolean isDragon() {
        return false;
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
        return true;
    }

    @Override
    public boolean isTerminal() {
        return number == 1 || number == 9;
    }

    @Override
    public boolean isHonor() {
        return false;
    }
}
