package mahjong.yaku;

import mahjong.HandDetail;
import mahjong.Player;
import mahjong.tile.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class YakuHandler {

    public static boolean hasValidYaku(Player player) {
        AllHonorsYaku allHonorsYaku = new AllHonorsYaku();
        AllSimplesYaku allSimplesYaku = new AllSimplesYaku();
        AllTerminalsAndHonorsYaku allTerminalsAndHonorsYaku = new AllTerminalsAndHonorsYaku();
        AllTerminalsYaku allTerminalsYaku = new AllTerminalsYaku();
        AllTripletsYaku allTripletsYaku = new AllTripletsYaku();
        BigFourWindsYaku bigFourWindsYaku = new BigFourWindsYaku();
        DiscardYaku discardYaku = new DiscardYaku();
        DragonYaku dragonYaku = new DragonYaku();
        FourConcealedTripletsYaku fourConcealedTripletsYaku = new FourConcealedTripletsYaku();
        RoundWindYaku roundWindYaku = new RoundWindYaku();
        SeatWindYaku seatWindYaku = new SeatWindYaku();
        SevenPairsYaku sevenPairsYaku = new SevenPairsYaku();
        ThirteenOrphansYaku thirteenOrphansYaku = new ThirteenOrphansYaku();
        List<AbstractYaku> yaku = new ArrayList<>(Arrays.asList(
                allHonorsYaku,
                allSimplesYaku,
                allTerminalsAndHonorsYaku,
                allTerminalsYaku,
                allTripletsYaku,
                bigFourWindsYaku,
                discardYaku,
                dragonYaku,
                fourConcealedTripletsYaku,
                roundWindYaku,
                seatWindYaku,
                sevenPairsYaku,
                thirteenOrphansYaku));
        return yaku.stream().anyMatch(y -> y.isValid(player));
    }

//    public static Map<Tile, List <Tile>> getRiichiTiles(Player player) {
//        Player copyPlayer = new Player()
//    }

    public static boolean hasAPairAndFourSetsOrRuns(Player player) {
        List<HandDetail> handDetails = getHandDetails(player);
        return !handDetails.isEmpty() && handDetails.stream().anyMatch(h -> !h.getValidHands().isEmpty());
    }

    public static List<HandDetail> getHandDetails(Player player) {
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
