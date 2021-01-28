package mahjong.tile;

import mahjong.SuitConstants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

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
    protected static Map<String, Integer> rankMap = new HashMap<>();
    protected static Logger logger = Logger.getLogger(Tile.class.getName());

    static {
        BufferedImage backOfTileMedium1;
        BufferedImage backOfTileSmall1;
        try {
            backOfTileSmall1 = ImageIO.read(new File("src/main/java/mahjong/tile/images/international/small/back.png"));
            backOfTileMedium1 = ImageIO.read(new File("src/main/java/mahjong/tile/images/international/medium/back.png"));
        } catch (IOException e) {
            backOfTileSmall1 = null;
            backOfTileMedium1 = null;
        }
        BACK_OF_TILE_MEDIUM = backOfTileMedium1;
        BACK_OF_TILE_SMALL = backOfTileSmall1;
        rankMap.put(SuitConstants.CHARACTERS, 1);
        rankMap.put(SuitConstants.DOTS, 2);
        rankMap.put(SuitConstants.BAMBOO, 3);
        rankMap.put(SuitConstants.EAST_WIND, 4);
        rankMap.put(SuitConstants.SOUTH_WIND, 5);
        rankMap.put(SuitConstants.WEST_WIND, 6);
        rankMap.put(SuitConstants.NORTH_WIND, 7);
        rankMap.put(SuitConstants.WHITE_DRAGON, 8);
        rankMap.put(SuitConstants.GREEN_DRAGON, 9);
        rankMap.put(SuitConstants.RED_DRAGON, 10);
    }

    protected Tile(int number, String suit, boolean isRed, boolean isDora) {
        this.number = number;
        this.suit = suit;
        this.isRed = isRed;
        this.isDora = isDora;
        this.isGreen = (suit.equals(SuitConstants.BAMBOO) && (number == 2 || number == 3 || number == 4 || number == 6 || number == 8)) ||
                suit.equals(SuitConstants.GREEN_DRAGON);
        setImages();
    }

    protected Tile(Tile tile) {
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
            logger.log(new LogRecord(Level.SEVERE ,"Failed to load image " + fileName));
        }
        this.smallTileFacingLeft = RotateImage.rotate(smallTileFacingDown, 90.0d);
        this.smallTileFacingUp = RotateImage.rotate(smallTileFacingDown, 180.0d);
        this.smallTileFacingRight = RotateImage.rotate(smallTileFacingDown, 270.0d);
        this.mediumTileFacingLeft = RotateImage.rotate(mediumTileFacingDown, 90.0d);
        this.mediumTileFacingUp = RotateImage.rotate(mediumTileFacingDown, 180.0d);
        this.mediumTileFacingRight = RotateImage.rotate(mediumTileFacingDown, 270.0d);
    }

    protected int getSuitRank() {
        return rankMap.get(suit);
    }

    @Override
    public int compareTo(Tile o) {
        return Comparator.comparing(Tile::getSuitRank)
                .thenComparingInt(Tile::getNumber)
                .thenComparing(Tile::getIsRed)
                .compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        return Comparator.comparing(Tile::getSuitRank)
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

    public abstract boolean isNumber();

    public abstract boolean isTerminal();

    public abstract boolean isHonor();

    public abstract boolean isGreen();

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
