package Mahjong;

public class DragonTile extends Tile {

    public DragonTile() {
        this.number = 0;
        this.suit = "";
        this.isRed = false;
        this.isDora = false;
    }

    public DragonTile(String suit) {
        this.number = 0;
        this.suit = suit;
        this.isRed = false;
        this.isDora = false;
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
