package mahjong.yaku;

import mahjong.Player;
import mahjong.tile.Tile;

public class FlushYaku extends AbstractYaku {

    @Override
    public boolean isValid(Player player) {
        return player.getPlayArea().getCombineHandAndMelds().stream().map(Tile::getSuit).distinct().count() == 1 && YakuHandler.hasAPairAndFourSetsOrRuns(player);
    }
}
