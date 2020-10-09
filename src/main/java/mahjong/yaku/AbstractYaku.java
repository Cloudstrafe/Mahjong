package mahjong.yaku;

import mahjong.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractYaku {

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
}
