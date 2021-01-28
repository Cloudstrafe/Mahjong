package mahjong.yaku;

import mahjong.Player;
import mahjong.tile.Tile;

import java.util.List;
import java.util.stream.Collectors;

public class LittleFourWindsYaku extends AbstractYaku {

    @Override
    public boolean isValid(Player player) {
        List<Tile> winds = player.getPlayArea().getCombineHandAndMelds().stream().filter(Tile::isWind).distinct().collect(Collectors.toList());
        if (winds.size() == 4) {
            List<Long> counts = winds.stream().map(t -> player.getPlayArea().getCombineHandAndMelds().stream().filter(i -> i.getSuit().equals(t.getSuit()) && i.getNumber() == t.getNumber()).count()).sorted().collect(Collectors.toList());
            if (counts.get(0) == 2) {
                return counts.get(1) > 2 && YakuHandler.hasAPairAndFourSetsOrRuns(player);
            }
        }
        return false;
    }
}
