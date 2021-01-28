package mahjong.yaku;

import mahjong.Player;

import static mahjong.SuitConstants.*;

public class DragonYaku extends AbstractYaku {

    @Override
    public boolean isValid(Player player) {
        return (player.getPlayArea().getCombineHandAndMelds().stream().filter(t -> GREEN_DRAGON.equals(t.getSuit())).count() > 2 ||
                player.getPlayArea().getCombineHandAndMelds().stream().filter(t -> WHITE_DRAGON.equals(t.getSuit())).count() > 2 ||
                player.getPlayArea().getCombineHandAndMelds().stream().filter(t -> RED_DRAGON.equals(t.getSuit())).count() > 2) &&
                YakuHandler.hasAPairAndFourSetsOrRuns(player);
    }
}
