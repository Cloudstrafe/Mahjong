package mahjong.tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;

public abstract class Tile implements Comparable<Tile>{

    int number;
    String suit;
    boolean isRed;
    boolean isDora;
    String smallTilePath;
    String mediumTilePath;
    BufferedImage smallTileFacingDown;
    BufferedImage smallTileFacingRight;
    BufferedImage smallTileFacingUp;
    BufferedImage smallTileFacingLeft;
    BufferedImage mediumTileFacingDown;
    BufferedImage mediumTileFacingRight;
    BufferedImage mediumTileFacingUp;
    BufferedImage mediumTileFacingLeft;

    protected void setImages() {
        StringBuilder str = new StringBuilder();
        if (this.number != 0) {
            str.append(this.number);
        }
        str.append(this.suit);
        if (this.isRed) {
            str.append("red");
        }
        str.append(".png");
        this.smallTilePath = "src/main/java/mahjong/tile/images/international/small/" + str.toString();
        this.mediumTilePath = "src/main/java/mahjong/tile/images/international/medium/" + str.toString();
        try {
            this.smallTileFacingDown = ImageIO.read(new File(smallTilePath));
            this.mediumTileFacingDown = ImageIO.read(new File(mediumTilePath));
        } catch (IOException e) {
            System.out.println("Failed to load image " + str.toString());
        }
        this.smallTileFacingLeft = RotateTile.rotate(smallTileFacingDown,90.0d);
        this.smallTileFacingUp = RotateTile.rotate(smallTileFacingDown, 180.0d);
        this.smallTileFacingRight = RotateTile.rotate(smallTileFacingDown, 270.0d);
        this.mediumTileFacingLeft = RotateTile.rotate(mediumTileFacingDown,90.0d);
        this.mediumTileFacingUp = RotateTile.rotate(mediumTileFacingDown, 180.0d);
        this.mediumTileFacingRight = RotateTile.rotate(mediumTileFacingDown, 270.0d);
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

    public BufferedImage getSmallTileFacingDown() {
        return smallTileFacingDown;
    }

    public BufferedImage getSmallTileFacingRight() {
        return smallTileFacingRight;
    }

    public BufferedImage getSmallTileFacingUp() {
        return smallTileFacingUp;
    }

    public BufferedImage getSmallTileFacingLeft() {
        return smallTileFacingLeft;
    }

    public BufferedImage getMediumTileFacingDown() {
        return mediumTileFacingDown;
    }

    public BufferedImage getMediumTileFacingRight() {
        return mediumTileFacingRight;
    }

    public BufferedImage getMediumTileFacingUp() {
        return mediumTileFacingUp;
    }

    public BufferedImage getMediumTileFacingLeft() {
        return mediumTileFacingLeft;
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
