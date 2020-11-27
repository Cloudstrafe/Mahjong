package mahjong.tile;

import mahjong.SuitConstants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Objects;

public abstract class Tile implements Comparable<Tile> {

    protected int number;
    protected String suit;
    protected boolean isRed;
    protected boolean isDora;
    protected boolean isGreen;
    protected String smallTilePath;
    protected String mediumTilePath;
    protected BufferedImage smallTileFacingDown;
    protected BufferedImage smallTileFacingRight;
    protected BufferedImage smallTileFacingUp;
    protected BufferedImage smallTileFacingLeft;
    protected BufferedImage mediumTileFacingDown;
    protected BufferedImage mediumTileFacingRight;
    protected BufferedImage mediumTileFacingUp;
    protected BufferedImage mediumTileFacingLeft;
    protected static final BufferedImage BACK_OF_TILE_SMALL;
    protected static final BufferedImage BACK_OF_TILE_MEDIUM;

    static {
        BufferedImage BACK_OF_TILE_MEDIUM1;
        BufferedImage BACK_OF_TILE_SMALL1;
        try {
            BACK_OF_TILE_SMALL1 = ImageIO.read(new File("src/main/java/mahjong/tile/images/international/small/back.png"));
            BACK_OF_TILE_MEDIUM1 = ImageIO.read(new File("src/main/java/mahjong/tile/images/international/medium/back.png"));
        } catch (IOException e) {
            BACK_OF_TILE_SMALL1 = null;
            BACK_OF_TILE_MEDIUM1 = null;
        }
        BACK_OF_TILE_MEDIUM = BACK_OF_TILE_MEDIUM1;
        BACK_OF_TILE_SMALL = BACK_OF_TILE_SMALL1;
    }

    public Tile(int number, String suit, boolean isRed, boolean isDora) {
        this.number = number;
        this.suit = suit;
        this.isRed = isRed;
        this.isDora = isDora;
        this.isGreen = (suit.equals(SuitConstants.BAMBOO) && (number == 2 || number == 3 || number == 4 || number == 6 || number == 8)) ||
                suit.equals(SuitConstants.GREEN_DRAGON);
        setImages();
    }

    public Tile(Tile tile) {
        this.number = tile.number;
        this.suit = tile.suit;
        this.isRed = tile.isRed;
        this.isDora = tile.isDora;
        this.isGreen = tile.isGreen;
        this.smallTilePath = tile.smallTilePath;
        this.mediumTilePath = tile.mediumTilePath;
        this.smallTileFacingDown = tile.smallTileFacingDown;
        this.smallTileFacingRight = tile.smallTileFacingRight;
        this.smallTileFacingUp = tile.smallTileFacingUp;
        this.smallTileFacingLeft = tile.smallTileFacingLeft;
        this.mediumTileFacingDown = tile.mediumTileFacingDown;
        this.mediumTileFacingRight = tile.mediumTileFacingRight;
        this.mediumTileFacingUp = tile.mediumTileFacingUp;
        this.mediumTileFacingLeft = tile.mediumTileFacingLeft;
    }

    protected void setImages() {
        String fileName = getTileFileName();
        this.smallTilePath = "src/main/java/mahjong/tile/images/international/small/" + fileName;
        this.mediumTilePath = "src/main/java/mahjong/tile/images/international/medium/" + fileName;
        try {
            this.smallTileFacingDown = ImageIO.read(new File(smallTilePath));
            this.mediumTileFacingDown = ImageIO.read(new File(mediumTilePath));
        } catch (IOException e) {
            System.out.println("Failed to load image " + fileName);
        }
        this.smallTileFacingLeft = RotateTile.rotate(smallTileFacingDown, 90.0d);
        this.smallTileFacingUp = RotateTile.rotate(smallTileFacingDown, 180.0d);
        this.smallTileFacingRight = RotateTile.rotate(smallTileFacingDown, 270.0d);
        this.mediumTileFacingLeft = RotateTile.rotate(mediumTileFacingDown, 90.0d);
        this.mediumTileFacingUp = RotateTile.rotate(mediumTileFacingDown, 180.0d);
        this.mediumTileFacingRight = RotateTile.rotate(mediumTileFacingDown, 270.0d);
    }

    @Override
    public int compareTo(Tile o) {
        return Comparator.comparing(Tile::getSuit)
                .thenComparingInt(Tile::getNumber)
                .thenComparing(Tile::getIsRed)
                .compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        return Comparator.comparing(Tile::getSuit)
                .thenComparingInt(Tile::getNumber)
                .thenComparing(Tile::getIsRed)
                .compare(this, (Tile) o) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, suit, isRed);
    }

    public abstract boolean isDragon();

    public abstract boolean isWind();

    public abstract boolean isPrevailingWind();

    public abstract boolean isSeatWind();

    public abstract boolean isNumber();

    public abstract boolean isTerminal();

    public abstract boolean isHonor();

    public abstract boolean isGreen();

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

    public static BufferedImage getBackOfTileSmall() {
        return BACK_OF_TILE_SMALL;
    }

    public static BufferedImage getBackOfTileMedium() {
        return BACK_OF_TILE_MEDIUM;
    }

    public String getTileFileName() {
        StringBuilder str = new StringBuilder();
        if (number != 0) {
            str.append(number);
        }
        str.append(suit);
        if (isRed) {
            str.append("red");
        }
        str.append(".png");
        return str.toString();
    }
}
