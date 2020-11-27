package mahjong.yaku;

import mahjong.HandDetail;
import mahjong.Meld;
import mahjong.Player;
import mahjong.tile.Tile;

import java.util.List;
import java.util.stream.Collectors;

public class ThreeColorStraightYaku extends AbstractYaku {
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
        List<HandDetail> handDetails = YakuHandler.getHandDetails(player);
        for (HandDetail handDetail : handDetails) {
            for (List<Meld> validHand : handDetail.getValidHands()) {
                if (validHand.stream().filter(Meld::isRun).count() > 2) {
                    List<Meld> runs = validHand.stream().filter(Meld::isRun).collect(Collectors.toList());
                    for (int i = 0; i < runs.size() - 2; i++) {
                        if (YakuHandler.areRunsSameNumber(runs.get(i).getTiles(), runs.get(i + 1).getTiles()) || YakuHandler.areRunsSameNumber(runs.get(i).getTiles(), runs.get(i + 2).getTiles())) {
                            List<Tile> first = runs.get(i).getTiles();
                            for (int j = i + 1; j < runs.size() - 1; j++) {
                                if (YakuHandler.areRunsSameNumber(runs.get(j).getTiles(), first) && !runs.get(j).getTiles().get(0).getSuit().equals(first.get(0).getSuit())) {
                                    List<Tile> second = runs.get(j).getTiles();
                                    for (int k = j + 1; k < runs.size(); k++) {
                                        if (YakuHandler.areRunsSameNumber(runs.get(k).getTiles(), second) && !runs.get(k).getTiles().get(0).getSuit().equals(first.get(0).getSuit()) &&
                                                !runs.get(k).getTiles().get(0).getSuit().equals(second.get(0).getSuit())) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
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
