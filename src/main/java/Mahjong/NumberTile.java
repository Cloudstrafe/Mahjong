package Mahjong;

public class NumberTile extends Tile {

    public NumberTile() {
        this.number = 0;
        this.suit = "";
        this.isRed = false;
        this.isDora = false;
    }

    public NumberTile(int number, String suit, boolean isRed) {
        this.number = number;
        this.suit = suit;
        this.isRed = isRed;
        this.isDora = isRed;
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
        return false;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    @Override
    public boolean isHonor() {
        return false;
    }
}
