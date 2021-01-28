package mahjong.yaku;

import mahjong.HandDetail;
import mahjong.Meld;
import mahjong.Player;
import mahjong.tile.Tile;

import java.util.List;

public class NoPointsYaku extends AbstractYaku {

    private static String roundWind;

    @Override
    public boolean isValid(Player player) {
        if (player.getPlayArea().getMelds().isEmpty()) {
            Tile lastTile = player.getPlayArea().getHand().remove(player.getPlayArea().getHand().size() - 1);
            List<Tile> waits = YakuHandler.getWaitTiles(player);
            player.getPlayArea().getHand().add(lastTile);
            if (hasValidWaits(waits)) {
                List<HandDetail> handDetails = YakuHandler.getHandDetails(player);
                for (HandDetail handDetail : handDetails) {
                    if (!player.getSeat().equals(handDetail.getPair().get(0).getSuit()) &&
                            !roundWind.equals(handDetail.getPair().get(0).getSuit()) &&
                            !handDetail.getPair().get(0).isDragon()) {
                        for (List<Meld> validHand : handDetail.getValidHands()) {
                            if (isPinfu(player, lastTile, handDetail, validHand)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isPinfu(Player player, Tile lastTile, HandDetail handDetail, List<Meld> validHand) {
        if (validHand.stream().allMatch(Meld::isRun)) {
            player.getPlayArea().getHand().remove(lastTile);
            if (player.getPlayArea().getHand().stream().filter(t -> handDetail.getPair().get(0).getSuit().equals(t.getSuit()) &&
                    handDetail.getPair().get(0).getNumber() == t.getNumber()).count() >= 2) {
                player.getPlayArea().getHand().add(lastTile);
                return true;
            }
            player.getPlayArea().getHand().add(lastTile);
        }
        return false;
    }

    private boolean hasValidWaits(List<Tile> waits) {
        if (waits.size() >= 2) {
            for (int i = 0; i < waits.size() - 1; i++) {
                Tile first = waits.get(i);
                for (int j = i + 1; j < waits.size(); j++) {
                    Tile second = waits.get(j);
                    if (first.getNumber() == second.getNumber() - 3 &&
                            first.getSuit().equals(second.getSuit())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void setRoundWind(String roundWind) {
        NoPointsYaku.roundWind = roundWind;
    }
}
