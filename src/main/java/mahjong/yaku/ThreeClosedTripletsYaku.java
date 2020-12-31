package mahjong.yaku;

import mahjong.HandDetail;
import mahjong.Meld;
import mahjong.Player;

import java.util.List;

public class ThreeClosedTripletsYaku extends AbstractYaku {
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
        List<HandDetail> handDetails = YakuHandler.getHandDetails(player);
        int totalClosedTriplets = 0;
        for (HandDetail handDetail : handDetails) {
            for (List<Meld> validHand : handDetail.getValidHands()) {
                for (Meld meld : validHand) {
                    if (meld.getTiles().get(0).getNumber() == meld.getTiles().get(1).getNumber() && meld.getTiles().get(0).getSuit().equals(meld.getTiles().get(1).getSuit()) && !meld.isOpen()) {
                        totalClosedTriplets++;
                        if (totalClosedTriplets == 3) {
                            return true;
                        }
                    }
                }
                totalClosedTriplets = 0;
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
