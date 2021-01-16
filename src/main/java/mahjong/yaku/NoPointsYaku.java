package mahjong.yaku;

import mahjong.HandDetail;
import mahjong.Player;
import mahjong.tile.Tile;

import java.util.List;

public class NoPointsYaku extends AbstractYaku {

    private static String roundWind;

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
        return 0;
    }

    @Override
    public int getCurrentPoints(Player player) {
        return 0;
    }

    @Override
    public boolean isValid(Player player) {
        if (player.getPlayArea().getMelds().isEmpty()) {
            List<Tile> waits = YakuHandler.getWaitTiles(player);
            if (waits.size() == 2 && waits.get(0).getNumber() == waits.get(1).getNumber() - 3 &&
                    waits.get(0).getSuit().equals(waits.get(1).getSuit())) {
                List<HandDetail> handDetails = YakuHandler.getHandDetails(player);
                for (HandDetail handDetail : handDetails) {
                    if (handDetail.getSets().isEmpty() &&
                            !player.getSeat().equals(handDetail.getPair().get(0).getSuit()) &&
                            !roundWind.equals(handDetail.getPair().get(0).getSuit()) &&
                            !handDetail.getPair().get(0).isDragon()) {
                        return YakuHandler.hasAPairAndFourSetsOrRuns(player);
                    }
                }
            }
        }
        return false;
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

    public static void setRoundWind(String roundWind) {
        NoPointsYaku.roundWind = roundWind;
    }
}
