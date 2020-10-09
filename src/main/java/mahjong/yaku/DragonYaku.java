package mahjong.yaku;

import mahjong.Player;

import static mahjong.SuitConstants.*;

public class DragonYaku extends AbstractYaku {

    @Override
    public boolean isMangan() {
        return false;
    }

    @Override
    public int getClosedPoints() {
        return 1;
    }

    @Override
    public int getOpenPoints() {
        return 1;
    }

    @Override
    public int getCurrentPoints(Player player) {
        int total = (int) player.getPlayArea().getMelds().stream().filter(m -> m.getTiles().get(0).isDragon()).count();
        total += player.getPlayArea().getHand().stream().filter(t -> RED_DRAGON.equals(t.getSuit())).count() > 2 ? 1 : 0;
        total += player.getPlayArea().getHand().stream().filter(t -> WHITE_DRAGON.equals(t.getSuit())).count() > 2 ? 1 : 0;
        total += player.getPlayArea().getHand().stream().filter(t -> GREEN_DRAGON.equals(t.getSuit())).count() > 2 ? 1 : 0;
        return total;
    }

    @Override
    public boolean isValid(Player player) {
        return getCurrentPoints(player) > 0 && YakuHandler.hasAPairAndFourSetsOrRuns(player);
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
