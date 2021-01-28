package mahjong.yaku;

import mahjong.Player;

public class SeatWindYaku extends AbstractYaku {

    @Override
    public boolean isValid(Player player) {
        return player.getPlayArea().getCombineHandAndMelds().stream().filter(t -> player.getSeat().equals(t.getSuit())).count() > 2 && YakuHandler.hasAPairAndFourSetsOrRuns(player);
    }
}
