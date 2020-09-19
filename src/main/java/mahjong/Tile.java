package mahjong;

import java.util.Comparator;

public abstract class Tile implements Comparable<Tile>{

    int number;
    String suit;
    boolean isRed;
    boolean isDora;

    @Override
    public int compareTo(Tile o) {
        return Comparator.comparing(Tile::getSuit)
                .thenComparingInt(Tile::getNumber)
                .compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        return Comparator.comparing(Tile::getSuit)
                .thenComparingInt(Tile::getNumber)
                .compare(this, (Tile) o) == 0;
    }

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

    public String getTileAsString() {
        StringBuilder str = new StringBuilder();
        if (number != 0) {
            str.append(number);
            str.append(" ");
        }
        str.append(suit);
        return str.toString();
    }
}
