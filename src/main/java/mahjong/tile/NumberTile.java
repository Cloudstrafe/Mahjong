package mahjong.tile;

public class NumberTile extends Tile {

    public NumberTile(int number, String suit, boolean isRed)  {
        super(number, suit, isRed, isRed);
    }

    public NumberTile(NumberTile tile) {
        super(tile);
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

    public boolean isGreen() {
        return isGreen;
    }
}
