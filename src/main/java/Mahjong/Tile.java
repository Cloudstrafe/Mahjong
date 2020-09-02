package Mahjong;

public abstract class Tile {

    int number;
    String suit;
    boolean isRed;
    boolean isDora;

    public abstract boolean isDragon();

    public abstract boolean isWind();

    public abstract boolean isPrevailingWind();

    public abstract boolean isSeatWind();

    public abstract boolean isNumber();

    public abstract boolean isTerminal();

    public abstract boolean isHonor();

    public boolean getIsDora() {
        return isDora;
    }

    public void setIsDora(boolean isDora) {
        this.isDora = isDora;
    }

    public int getNumber() {
        return number;
    }

    public String getSuit() {
        return suit;
    }

    public boolean getIsRed() {
        return isRed;
    }
}
