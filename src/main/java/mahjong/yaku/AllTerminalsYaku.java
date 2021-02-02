package mahjong.yaku;

import mahjong.Player;
import mahjong.tile.Tile;

public class AllTerminalsYaku extends AbstractYaku {

    @Override
    public boolean isValid(Player player) {
        return player.getPlayArea().getCombineHandAndMelds().stream().allMatch(Tile::isTerminal) && YakuHandler.hasAPairAndFourSetsOrRuns(player);
    }
}
