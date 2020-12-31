package mahjong.yaku;

import mahjong.HandDetail;
import mahjong.Meld;
import mahjong.Player;

import java.util.List;

public class TerminalOrHonorInEachSetYaku extends AbstractYaku {
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
        return 1;
    }

    @Override
    public int getCurrentPoints(Player player) {
        return 0;
    }

    @Override
    public boolean isValid(Player player) {
        boolean valid = false;
        List<HandDetail> handDetails = YakuHandler.getHandDetails(player);
        for (HandDetail handDetail : handDetails) {
            if (handDetail.getPair().get(0).isHonor() || handDetail.getPair().get(0).isTerminal()) {
                for (List<Meld> validHand : handDetail.getValidHands()) {
                    valid = true;
                    for (Meld meld : validHand) {
                        if (meld.getTiles().stream().noneMatch(t -> t.isHonor() || t.isTerminal())) {
                            valid = false;
                            break;
                        }
                    }
                    if (valid) {
                        return true;
                    }
                }
            }
        }
        return false;
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
