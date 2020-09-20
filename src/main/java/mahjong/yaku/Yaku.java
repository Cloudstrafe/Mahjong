package mahjong.yaku;

import mahjong.Player;

public abstract class Yaku {

    private int closedPoints;
    private int openPoints;
    private boolean isValid;
    private boolean isStackable;
    private boolean isMangan;

    public abstract boolean isMangan();

    public abstract int getClosedPoints();

    public abstract int getOpenPoints();

    public abstract int getCurrentPoints(Player player);

    public abstract boolean isValid(Player player);

    public abstract boolean isStackable();
}
