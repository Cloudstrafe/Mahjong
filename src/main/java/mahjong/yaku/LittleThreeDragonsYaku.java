package mahjong.yaku;

import mahjong.Player;
import mahjong.tile.Tile;

import java.util.List;
import java.util.stream.Collectors;

public class LittleThreeDragonsYaku extends AbstractYaku {

    @Override
    public boolean isValid(Player player) {
        List<Tile> dragons = player.getPlayArea().getCombineHandAndMelds().stream().filter(Tile::isDragon).distinct().collect(Collectors.toList());
        if (dragons.size() == 3) {
            List<Long> counts = dragons.stream().map(t -> player.getPlayArea().getCombineHandAndMelds().stream().filter(i -> i.getSuit().equals(t.getSuit()) && i.getNumber() == t.getNumber()).count()).sorted().collect(Collectors.toList());
            if (counts.get(0) == 2) {
                return counts.get(1) > 2 && YakuHandler.hasAPairAndFourSetsOrRuns(player);
            }
        }
        return false;
    }
}
