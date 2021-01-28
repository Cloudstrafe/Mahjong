package mahjong.yaku;

import mahjong.Player;
import mahjong.tile.Tile;

import java.util.List;
import java.util.stream.Collectors;

public class BigThreeDragonsYaku extends AbstractYaku {

    @Override
    public boolean isValid(Player player) {
        List<Tile> combined = player.getPlayArea().getCombineHandAndMelds();
        if (combined.stream().filter(Tile::isDragon).count() == combined.size() - 5) {
            List<Tile> unique = combined.stream().filter(distinctByKey(t -> t.getNumber() + t.getSuit())).distinct().collect(Collectors.toList());
            if (unique.size() == 5) {
                List<Long> counts = unique.stream().map(t -> combined.stream().filter(i -> i.getSuit().equals(t.getSuit()) && i.getNumber() == t.getNumber()).count()).sorted().collect(Collectors.toList());
                if (counts.get(0) == 2) {
                    return counts.get(1) > 2 && YakuHandler.hasAPairAndFourSetsOrRuns(player);
                }
            } else if (unique.size() == 7) {
                List<Long> counts = unique.stream().map(t -> combined.stream().filter(i -> i.getSuit().equals(t.getSuit()) && i.getNumber() == t.getNumber()).count()).sorted().collect(Collectors.toList());
                if (counts.get(3) == 2) {
                    return counts.get(4) > 2 && YakuHandler.hasAPairAndFourSetsOrRuns(player);
                }
            }
        }
        return false;
    }
}
