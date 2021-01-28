package mahjong.yaku;

import mahjong.Player;
import mahjong.tile.Tile;

public class AllHonorsYaku extends AbstractYaku {

    @Override
    public boolean isValid(Player player) {
        return player.getPlayArea().getCombineHandAndMelds().stream().allMatch(Tile::isHonor) && YakuHandler.hasAPairAndFourSetsOrRuns(player);
    }
}
