package mahjong.yaku;

import mahjong.Player;
import mahjong.Tile;

import java.util.List;
import java.util.stream.Collectors;

public class AllTerminalsAndHonorsYaku extends Yaku{
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
        return 2;
    }

    @Override
    public int getCurrentPoints(Player player) {
        return 0;
    }

    @Override
    public boolean isValid(Player player) {
        List<Tile> combined = player.getPlayArea().getCombineHandAndMelds();
        if (combined.stream().allMatch(t -> t.isTerminal() || t.isHonor())) {
            List<Tile> unique = combined.stream().filter(distinctByKey(t -> t.getNumber() + t.getSuit())).distinct().collect(Collectors.toList());
            if (unique.size() == 5) {
                List<Long> counts = unique.stream().map(t -> combined.stream().filter(i -> i.getSuit().equals(t.getSuit()) && i.getNumber() == t.getNumber()).count()).sorted().collect(Collectors.toList());
                if (counts.get(0) == 2) {
                    return counts.get(1) > 2;
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
