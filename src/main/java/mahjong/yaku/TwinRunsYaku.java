package mahjong.yaku;

import mahjong.HandDetail;
import mahjong.Meld;
import mahjong.Player;

import java.util.List;

public class TwinRunsYaku extends AbstractYaku {

    @Override
    public boolean isValid(Player player) {
        if (player.getPlayArea().getMelds().isEmpty()) {
            List<HandDetail> handDetails = YakuHandler.getHandDetails(player);
            for (HandDetail handDetail : handDetails) {
                for (List<Meld> validHand : handDetail.getValidHands()) {
                    if (hasTwinRun(validHand)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean hasTwinRun(List<Meld> validHand) {
        for (int i = 0; i < validHand.size(); i++) {
            for (int j = i + 1; j < validHand.size(); j++) {
                if (YakuHandler.areRunsIdentical(validHand.get(i).getTiles(), validHand.get(j).getTiles())) {
                    return true;
                }
            }
        }
        return false;
    }
}
