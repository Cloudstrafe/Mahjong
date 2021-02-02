package mahjong.yaku;

import mahjong.HandDetail;
import mahjong.Meld;
import mahjong.Player;

import java.util.List;

public class TerminalOrHonorInEachSetYaku extends AbstractYaku {
    @Override
    public boolean isValid(Player player) {
        List<HandDetail> handDetails = YakuHandler.getHandDetails(player);
        for (HandDetail handDetail : handDetails) {
            if (handDetail.getPair().get(0).isHonor() ||
                    handDetail.getPair().get(0).isTerminal()) {
                for (List<Meld> validHand : handDetail.getValidHands()) {
                    if (hasTerminalOrHonorInEachSet(validHand)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean hasTerminalOrHonorInEachSet(List<Meld> validHand) {
        boolean valid = true;
        for (Meld meld : validHand) {
            if (meld.getTiles().stream().noneMatch(t -> t.isHonor() ||
                    t.isTerminal())) {
                valid = false;
                break;
            }
        }
        return valid;
    }
}
