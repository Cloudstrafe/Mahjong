package mahjong.tile;

public class WindTile extends Tile {

    public WindTile(String suit)  {
        this.number = 0;
        this.suit = suit;
        this.isRed = false;
        this.isDora = false;
        setImages();
    }

    @Override
    public boolean isDragon() {
        return false;
    }

    @Override
    public boolean isWind() {
        return true;
    }

    @Override
    public boolean isPrevailingWind() {
        return false;
        //TODO return dealerWind == this.suit;
    }

    @Override
    public boolean isSeatWind() {
        return false;
        //TODO return thisSeatWind == this.suit;
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
