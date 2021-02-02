package mahjong.yaku;

import mahjong.Player;

public class RoundWindYaku extends AbstractYaku {

    private static String roundWind;

    @Override
    public boolean isValid(Player player) {
        return player.getPlayArea().getCombineHandAndMelds().stream().filter(t -> roundWind.equals(t.getSuit())).count() > 2 &&
                YakuHandler.hasAPairAndFourSetsOrRuns(player);
    }

    public static void setRoundWind(String roundWind) {
        RoundWindYaku.roundWind = roundWind;
    }
}
