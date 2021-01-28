package mahjong.yaku;

import mahjong.Player;
import mahjong.SuitConstants;
import mahjong.tile.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ThreeColorTripletsYaku extends AbstractYaku {

    @Override
    public boolean isValid(Player player) {
        List<Tile> combined = player.getPlayArea().getCombineHandAndMelds();
        for (Tile tile : combined) {
            if (tile.getNumber() != 0) {
                List<Tile> tilesWithSameNumber = combined.stream().filter(t -> t.getNumber() == tile.getNumber()).collect(Collectors.toList());
                if (tilesWithSameNumber.size() > 8) {
                    List<String> suits = new ArrayList<>(Arrays.asList(SuitConstants.BAMBOO, SuitConstants.CHARACTERS, SuitConstants.DOTS));
                    for (String suit : suits) {
                        if (tilesWithSameNumber.stream().filter(t -> suit.equals(t.getSuit())).count() < 3) {
                            return false;
                        }
                    }
                    return YakuHandler.hasAPairAndFourSetsOrRuns(player);
                }
            }
        }
        return false;
    }
}
