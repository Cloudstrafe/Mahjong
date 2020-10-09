package mahjong.yaku;

import mahjong.Player;

public class AllSimplesYaku extends AbstractYaku {
    @Override
    public boolean isMangan() {
        return false;
    }

    @Override
    public int getClosedPoints() {
        return 1;
    }

    @Override
    public int getOpenPoints() {
        return 1;
    }

    @Override
    public int getCurrentPoints(Player player) {
        return 0;
    }

    @Override
    public boolean isValid(Player player) {
        return player.getPlayArea().getHand().stream().allMatch(t -> !t.isHonor() && !t.isTerminal()) &&
                player.getPlayArea().getMelds().stream().flatMap(m -> m.getTiles().stream()).allMatch(t -> !t.isHonor() && !t.isTerminal()) &&
                YakuHandler.hasAPairAndFourSetsOrRuns(player);
    }

    @Override
    public boolean isStackable() {
        return true;
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
