package mahjong.yaku;

import mahjong.Player;
import mahjong.tile.Tile;

import java.util.List;
import java.util.stream.Collectors;

public class ThirteenOrphansYaku extends AbstractYaku {

    @Override
    public boolean isValid(Player player) {
        List<Tile> combined = player.getPlayArea().getCombineHandAndMelds();
        if (combined.stream().allMatch(t -> t.isTerminal() || t.isHonor())) {
            List<Tile> unique = combined.stream().filter(distinctByKey(t -> t.getNumber() + t.getSuit())).distinct().collect(Collectors.toList());
            if (unique.size() == 13) {
                List<Long> counts = unique.stream().map(t -> combined.stream().filter(i -> i.getSuit().equals(t.getSuit()) && i.getNumber() == t.getNumber()).count()).sorted().collect(Collectors.toList());
                if (counts.get(11) == 1) {
                    return counts.get(12) == 2;
                }
            }
        }
        return false;
    }
}
