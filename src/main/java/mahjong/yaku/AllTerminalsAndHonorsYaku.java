package mahjong.yaku;

import mahjong.Player;

public class AllTerminalsAndHonorsYaku extends AbstractYaku {

    @Override
    public boolean isValid(Player player) {
        return player.getPlayArea().getCombineHandAndMelds().stream().allMatch(t -> t.isHonor() || t.isTerminal()) && YakuHandler.hasAPairAndFourSetsOrRuns(player);
    }
}
