package mahjong.tile;

import java.util.Comparator;

public abstract class Tile implements Comparable<Tile>{

    int number;
    String suit;
    boolean isRed;
    boolean isDora;
    String smallTilePath;
    String mediumTilePath;
    String largeTilePath;
    
    protected void setImageFilePaths() {
        StringBuilder str = new StringBuilder();
        if (this.number != 0) {
            str.append(this.number);
        }
        str.append(this.suit);
        if (this.isRed) {
            str.append("red");
        }
        str.append(".png");
        this.smallTilePath = "images/international/small/" + str.toString();
        this.mediumTilePath = "images/international/medium/" + str.toString();
        this.largeTilePath = "images/international/large/" + str.toString();    //TODO may have to escape these names
    }

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

    public String getSmallTilePath() {
        return smallTilePath;
    }

    public String getMediumTilePath() {
        return mediumTilePath;
    }

    public String getLargeTilePath() {
        return largeTilePath;
    }

    public String getTileAsString() {
        StringBuilder str = new StringBuilder();
        if (number != 0) {
            str.append(number).append(" ");
        }
        str.append(suit);
        return str.toString();
    }
}
