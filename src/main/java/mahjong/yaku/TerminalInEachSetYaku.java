package mahjong.yaku;

import mahjong.HandDetail;
import mahjong.Player;
import mahjong.tile.Tile;

import java.util.List;

public class TerminalInEachSetYaku extends AbstractYaku {

    @Override
    public boolean isValid(Player player) {
        List<HandDetail> handDetails = YakuHandler.getHandDetails(player);
        for (HandDetail handDetail : handDetails) {
            if (handDetail.getPair().get(0).isTerminal() &&
                    handDetail.getValidHands().stream().anyMatch(
                            validHand -> validHand.stream().allMatch(
                                    meld -> meld.getTiles().stream().anyMatch(Tile::isTerminal)))) {
                return true;
            }
        }
        return false;
    }
}
