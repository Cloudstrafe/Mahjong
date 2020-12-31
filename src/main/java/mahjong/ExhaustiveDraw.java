package mahjong;

import mahjong.yaku.YakuHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ExhaustiveDraw {

    public static boolean tenpaiPayout(Queue<Player> turnQueue) {
        List<Player> inTenpai = new ArrayList<>();
        List<Player> notInTenpai = new ArrayList<>();
        boolean dealerInTenpai = false;
        for (Player player : turnQueue) {
            if (YakuHandler.isInTenpai(player)) {
                inTenpai.add(player);
                if (player.isDealer()) {
                    dealerInTenpai = true;
                }
            } else {
                notInTenpai.add(player);
            }
        }
        if (!(inTenpai.isEmpty() || inTenpai.size() == 4)) {
            for (Player player : inTenpai) {
                player.setPoints(player.getPoints() + (3000 / inTenpai.size()));
            }
            for (Player player : notInTenpai) {
                player.setPoints(player.getPoints() - (3000 / notInTenpai.size()));
            }
        }
        return dealerInTenpai;
    }
}
