package mahjong.yaku;

import mahjong.Player;
import mahjong.tile.Tile;

public class NineGatesYaku extends AbstractYaku {

    @Override
    public boolean isValid(Player player) {
        if (player.getPlayArea().getMelds().isEmpty() && player.getPlayArea().getHand().stream().map(Tile::getSuit).distinct().count() == 1) {
            return player.getPlayArea().getHand().stream().filter(t -> t.getNumber() == 1).count() >= 3 &&
                    player.getPlayArea().getHand().stream().filter(t -> t.getNumber() == 2).count() >= 1 &&
                    player.getPlayArea().getHand().stream().filter(t -> t.getNumber() == 3).count() >= 1 &&
                    player.getPlayArea().getHand().stream().filter(t -> t.getNumber() == 4).count() >= 1 &&
                    player.getPlayArea().getHand().stream().filter(t -> t.getNumber() == 5).count() >= 1 &&
                    player.getPlayArea().getHand().stream().filter(t -> t.getNumber() == 6).count() >= 1 &&
                    player.getPlayArea().getHand().stream().filter(t -> t.getNumber() == 7).count() >= 1 &&
                    player.getPlayArea().getHand().stream().filter(t -> t.getNumber() == 8).count() >= 1 &&
                    player.getPlayArea().getHand().stream().filter(t -> t.getNumber() == 9).count() >= 3 &&
                    YakuHandler.hasAPairAndFourSetsOrRuns(player);
        }
        return false;
    }
}
