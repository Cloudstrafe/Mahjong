package mahjong.yaku;

import mahjong.Player;

public class DiscardYaku extends Yaku {
    @Override
    public boolean isMangan() {
        return true;
    }

    @Override
    public int getClosedPoints() {
        return 0;
    }

    @Override
    public int getOpenPoints() {
        return 0;
    }

    @Override
    public int getCurrentPoints(Player player) {
        return 0;
    }

    @Override
    public boolean isValid(Player player) {
        return player.getPlayArea().getDiscard().stream().allMatch(t -> t.isHonor() || t.isTerminal());
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
