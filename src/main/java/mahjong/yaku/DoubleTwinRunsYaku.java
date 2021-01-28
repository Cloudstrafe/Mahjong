package mahjong.yaku;

import mahjong.HandDetail;
import mahjong.Meld;
import mahjong.Player;

import java.util.List;

public class DoubleTwinRunsYaku extends AbstractYaku {

    @Override
    public boolean isValid(Player player) {
        if (player.getPlayArea().getMelds().isEmpty()) {
            List<HandDetail> handDetails = YakuHandler.getHandDetails(player);
            for (HandDetail handDetail : handDetails) {
                for (List<Meld> validHand : handDetail.getValidHands()) {
                    if (validHand.stream().allMatch(m1 -> validHand.stream().filter(m2 -> YakuHandler.areRunsIdentical(m1.getTiles(), m2.getTiles())).count() == 2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
