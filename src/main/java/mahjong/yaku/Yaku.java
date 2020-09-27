package mahjong.yaku;

import mahjong.HandDetail;
import mahjong.Player;
import mahjong.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class Yaku {

    public abstract boolean isMangan();

    public abstract int getClosedPoints();

    public abstract int getOpenPoints();

    public abstract int getCurrentPoints(Player player);

    public abstract boolean isValid(Player player);

    public abstract boolean isStackable();

    public abstract boolean isYakuman();

    public abstract boolean isDoubleYakuman();

    protected static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public boolean hasAPairAndFourSetsOrRuns(Player player) {
        List<HandDetail> handDetails = getHandDetails(player);
        return !handDetails.isEmpty() && !handDetails.stream().allMatch(h -> h.getValidHands().isEmpty());
    }

    private List<HandDetail> getHandDetails(Player player) {
        List<HandDetail> handDetails = new ArrayList<>();
        List<Tile> hand = player.getPlayArea().getHand();
        for (int i = 0; i < hand.size() - 1; i++) {
            for (int j = i + 1; j < hand.size(); j++) {
                if (hand.get(i).getSuit().equals(hand.get(j).getSuit()) && hand.get(i).getNumber() == hand.get(j).getNumber()) {
                    List<Tile> pair = new ArrayList<>();
                    List<Tile> rest = new ArrayList<>();
                    pair.add(hand.get(i));
                    pair.add(hand.get(j));
                    for (int k = 0; k < hand.size(); k++) {
                        if (k != i && k !=j) {
                            rest.add(hand.get(k));
                        }
                    }
                    handDetails.add(new HandDetail(pair, rest, 4 - player.getPlayArea().getMelds().size(), player.getPlayArea().getMelds()));
                }
            }
        }
        return handDetails;
    }
}
