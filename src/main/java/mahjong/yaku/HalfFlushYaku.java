package mahjong.yaku;

import mahjong.Player;
import mahjong.tile.Tile;

public class HalfFlushYaku extends AbstractYaku {
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
}
