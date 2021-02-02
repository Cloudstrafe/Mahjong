package mahjong.yaku;

import mahjong.Player;
import mahjong.tile.Tile;

public class AllGreenYaku extends AbstractYaku {

    @Override
    public boolean isValid(Player player) {
        return player.getPlayArea().getCombineHandAndMelds().stream().allMatch(Tile::isGreen) && YakuHandler.hasAPairAndFourSetsOrRuns(player);
    }
}
