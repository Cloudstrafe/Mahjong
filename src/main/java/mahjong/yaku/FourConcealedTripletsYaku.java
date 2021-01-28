package mahjong.yaku;

import mahjong.Player;
import mahjong.tile.Tile;

import java.util.List;
import java.util.stream.Collectors;

public class FourConcealedTripletsYaku extends AbstractYaku {

    @Override
    public boolean isValid(Player player) {
        if (player.getPlayArea().getMelds().isEmpty()) {
            List<Tile> unique = player.getPlayArea().getHand().stream()
                    .filter(distinctByKey(t -> t.getNumber() + t.getSuit()))
                    .distinct().collect(Collectors.toList());
            if (unique.size() == 5) {
                List<Long> counts = unique.stream()
                        .map(t -> player.getPlayArea().getHand().stream().filter(i -> i.getSuit().equals(t.getSuit()) && i.getNumber() == t.getNumber()).count())
                        .sorted().collect(Collectors.toList());
                if (counts.get(0) == 2) {
                    return counts.get(1) > 2 && YakuHandler.hasAPairAndFourSetsOrRuns(player);
                }
            }
        }
        return false;
    }
}
