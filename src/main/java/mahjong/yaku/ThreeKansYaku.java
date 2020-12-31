package mahjong.yaku;

import mahjong.Meld;
import mahjong.Player;

public class ThreeKansYaku extends AbstractYaku {

    @Override
    public boolean isMangan() {
        return false;
    }

    @Override
    public int getClosedPoints() {
        return 2;
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
        int count = 0;
        for (Meld meld : player.getPlayArea().getMelds()) {
            if (meld.getTiles().size() == 4) {
                count++;
            }
        }
        return count == 3 && YakuHandler.hasAPairAndFourSetsOrRuns(player);
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
