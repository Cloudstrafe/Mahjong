package mahjong.yaku;

import mahjong.Player;

public class DiscardYaku extends AbstractYaku {

    @Override
    public boolean isValid(Player player) {
        return player.getPlayArea().getDiscard().stream().allMatch(t -> t.isHonor() || t.isTerminal()) && YakuHandler.hasAPairAndFourSetsOrRuns(player);
    }
}
