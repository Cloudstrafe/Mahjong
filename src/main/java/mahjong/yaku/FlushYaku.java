package mahjong.yaku;

import mahjong.Player;
import mahjong.tile.Tile;

public class FlushYaku extends  AbstractYaku {
    @Override
    public boolean isMangan() {
        return false;
    }

    @Override
    public int getClosedPoints() {
        return 6;
    }

    @Override
    public int getOpenPoints() {
        return 5;
    }

    @Override
    public int getCurrentPoints(Player player) {
        return 0;
    }

    @Override
    public boolean isValid(Player player) {
        String suit = "";
        for (Tile tile : player.getPlayArea().getCombineHandAndMelds()) {
            if (tile.isNumber()) {
                suit = tile.getSuit();
                break;
            }
        }
        String finalSuit = suit;
        return player.getPlayArea().getCombineHandAndMelds().stream().allMatch(t -> finalSuit.equals(t.getSuit())) && YakuHandler.hasAPairAndFourSetsOrRuns(player);
    }

    @Override
    public boolean isStackable() {
        return false;
    }

    @Override
    public boolean isYakuman() {
        return false;
    }

    @Override
    public boolean isDoubleYakuman() {
        return false;
    }
}
