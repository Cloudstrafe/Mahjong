package mahjong.yaku;

import mahjong.Player;
import mahjong.SuitConstants;
import mahjong.tile.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StraightYaku extends AbstractYaku {

    @Override
    public boolean isValid(Player player) {
        List<Tile> combined = player.getPlayArea().getCombineHandAndMelds();
        List<String> suits = new ArrayList<>(Arrays.asList(SuitConstants.BAMBOO, SuitConstants.CHARACTERS, SuitConstants.DOTS));
        for (String suit : suits) {
            List<Tile> suitTiles = combined.stream().filter(t -> suit.equals(t.getSuit())).collect(Collectors.toList());
            if ((long) suitTiles.size() > 8) {
                for (int i = 1; i < 10; i++) {
                    int finalI = i;
                    if (suitTiles.stream().noneMatch(t -> t.getNumber() == finalI)) {
                        return false;
                    }
                }
                return YakuHandler.hasAPairAndFourSetsOrRuns(player);
            }
        }
        return false;
    }
}
