package mahjong.yaku;

import mahjong.Meld;
import mahjong.Player;

public class ThreeKansYaku extends AbstractYaku {

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
}
