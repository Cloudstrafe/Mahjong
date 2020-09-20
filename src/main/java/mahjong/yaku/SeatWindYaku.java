package mahjong.yaku;

import mahjong.Player;

public class SeatWindYaku extends Yaku {

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
        int total = (int) player.getPlayArea().getMelds().stream().filter(m -> player.getSeat().equals(m.getTiles().get(0).getSuit())).count();
        total += player.getPlayArea().getHand().stream().filter(t -> player.getSeat().equals(t.getSuit())).count() > 2 ? 1 : 0;
        return total;
    }

    @Override
    public boolean isValid(Player player) {
        return getCurrentPoints(player) > 0;
    }

    @Override
    public boolean isStackable() {
        return true;
    }
}
