package mahjong.yaku;

import mahjong.Player;
import mahjong.tile.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SevenPairsYaku extends AbstractYaku{
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
        return 0;
    }

    @Override
    public int getCurrentPoints(Player player) {
        if (isValid(player)) {
            if (player.getPlayArea().getHand().stream().allMatch(t -> t.isTerminal() || t.isHonor())) {
                return 4;
            }
            return 2;
        }
        return 0;
    }

    @Override
    public boolean isValid(Player player) {
        if (!player.getPlayArea().getMelds().isEmpty()) {
            return false;
        }
        List<Tile> sortedHand = player.getPlayArea().getHand().stream().sorted().collect(Collectors.toList());
        List<Tile> unique = new ArrayList<>();
        for (int i = 0; i < sortedHand.size(); i += 2) {
            if (sortedHand.get(i).getSuit().equals(sortedHand.get(i+1).getSuit()) && (sortedHand.get(i).getNumber() == sortedHand.get(i+1).getNumber())) {
                unique.add(sortedHand.get(i));
                continue;
            }
            return false;
        }
        return unique.stream().map(t -> t.getNumber() + t.getSuit()).distinct().count() == 7;
    }

    @Override
    public boolean isStackable() {
        return true;
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
