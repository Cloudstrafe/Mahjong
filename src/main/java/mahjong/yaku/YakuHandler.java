package mahjong.yaku;

import mahjong.HandDetail;
import mahjong.Player;
import mahjong.SuitConstants;
import mahjong.tile.DragonTile;
import mahjong.tile.NumberTile;
import mahjong.tile.Tile;
import mahjong.tile.WindTile;

import java.util.*;
import java.util.stream.Collectors;

public class YakuHandler {
    private static final List<Tile> uniqueTiles = new ArrayList<>(Arrays.asList(
            new DragonTile(SuitConstants.RED_DRAGON),
            new DragonTile(SuitConstants.GREEN_DRAGON),
            new DragonTile(SuitConstants.WHITE_DRAGON),
            new WindTile(SuitConstants.SOUTH_WIND),
            new WindTile(SuitConstants.NORTH_WIND),
            new WindTile(SuitConstants.WEST_WIND),
            new WindTile(SuitConstants.EAST_WIND),
            new NumberTile(1, SuitConstants.BAMBOO, false),
            new NumberTile(2, SuitConstants.BAMBOO, false),
            new NumberTile(3, SuitConstants.BAMBOO, false),
            new NumberTile(4, SuitConstants.BAMBOO, false),
            new NumberTile(5, SuitConstants.BAMBOO, false),
            new NumberTile(6, SuitConstants.BAMBOO, false),
            new NumberTile(7, SuitConstants.BAMBOO, false),
            new NumberTile(8, SuitConstants.BAMBOO, false),
            new NumberTile(9, SuitConstants.BAMBOO, false),
            new NumberTile(1, SuitConstants.CHARACTERS, false),
            new NumberTile(2, SuitConstants.CHARACTERS, false),
            new NumberTile(3, SuitConstants.CHARACTERS, false),
            new NumberTile(4, SuitConstants.CHARACTERS, false),
            new NumberTile(5, SuitConstants.CHARACTERS, false),
            new NumberTile(6, SuitConstants.CHARACTERS, false),
            new NumberTile(7, SuitConstants.CHARACTERS, false),
            new NumberTile(8, SuitConstants.CHARACTERS, false),
            new NumberTile(9, SuitConstants.CHARACTERS, false),
            new NumberTile(1, SuitConstants.DOTS, false),
            new NumberTile(2, SuitConstants.DOTS, false),
            new NumberTile(3, SuitConstants.DOTS, false),
            new NumberTile(4, SuitConstants.DOTS, false),
            new NumberTile(5, SuitConstants.DOTS, false),
            new NumberTile(6, SuitConstants.DOTS, false),
            new NumberTile(7, SuitConstants.DOTS, false),
            new NumberTile(8, SuitConstants.DOTS, false),
            new NumberTile(9, SuitConstants.DOTS, false)
    ));

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

    public static Map<Tile, List<Tile>> getRiichiTiles(Player player) {
        if (!player.getPlayArea().getMelds().isEmpty()) {
            return new HashMap<>();
        }
        Player copyPlayer = new Player(player);
        Map<Tile, List<Tile>> riichiMap = new HashMap<>();
        List<Tile> copyHand = copyPlayer.getPlayArea().getHand().stream().map(t -> {
            if (t instanceof NumberTile) {
                return new NumberTile((NumberTile) t);
            } else if (t instanceof DragonTile) {
                return new DragonTile((DragonTile) t);
            } else {
                return new WindTile((WindTile) t);
            }
        }).collect(Collectors.toList());
        for (Tile discardTile : copyHand) {
            List<Tile> riichiTiles = new ArrayList<>();
            copyPlayer.getPlayArea().getHand().remove(discardTile);
            for (Tile uniqueTile : uniqueTiles) {
                copyPlayer.getPlayArea().getHand().add(uniqueTile);
                if (hasValidYaku(copyPlayer)) {
                    riichiTiles.add(uniqueTile);
                }
                copyPlayer.getPlayArea().getHand().remove(uniqueTile);
            }
            if (!riichiTiles.isEmpty()) {
                riichiMap.put(discardTile, riichiTiles);
            }
            copyPlayer.getPlayArea().getHand().add(discardTile);
        }
        return riichiMap;
    }

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
                        if (k != i && k != j) {
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
