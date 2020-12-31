package mahjong.yaku;

import mahjong.Player;
import mahjong.tile.Tile;

public class HalfFlushYaku extends AbstractYaku {
    @Override
    public boolean isMangan() {
        return false;
    }

    @Override
    public int getClosedPoints() {
        return 3;
    }

    @Override
    public int getOpenPoints() {
        return 2;
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
        return !"".equals(suit) && player.getPlayArea().getCombineHandAndMelds().stream().allMatch(t -> finalSuit.equals(t.getSuit()) || t.isHonor()) && YakuHandler.hasAPairAndFourSetsOrRuns(player);
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
