package mahjong.yaku;

import mahjong.HandDetail;
import mahjong.Meld;
import mahjong.Player;

import java.util.List;

public class ThreeClosedTripletsYaku extends AbstractYaku {

    @Override
    public boolean isValid(Player player) {
        List<HandDetail> handDetails = YakuHandler.getHandDetails(player);
        for (HandDetail handDetail : handDetails) {
            for (List<Meld> validHand : handDetail.getValidHands()) {
                if (hasThreeClosedTriplets(validHand)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasThreeClosedTriplets(List<Meld> validHand) {
        int totalClosedTriplets = 0;
        for (Meld meld : validHand) {
            if (meld.getTiles().get(0).getNumber() == meld.getTiles().get(1).getNumber() &&
                    meld.getTiles().get(0).getSuit().equals(meld.getTiles().get(1).getSuit()) &&
                    !meld.isOpen()) {
                totalClosedTriplets++;
                if (totalClosedTriplets == 3) {
                    return true;
                }
            }
        }
        return false;
    }
}
