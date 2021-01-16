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

    private static final AllGreenYaku allGreenYaku = new AllGreenYaku();
    private static final AllHonorsYaku allHonorsYaku = new AllHonorsYaku();
    private static final AllSimplesYaku allSimplesYaku = new AllSimplesYaku();
    private static final AllTerminalsAndHonorsYaku allTerminalsAndHonorsYaku = new AllTerminalsAndHonorsYaku();
    private static final AllTerminalsYaku allTerminalsYaku = new AllTerminalsYaku();
    private static final AllTripletsYaku allTripletsYaku = new AllTripletsYaku();
    private static final BigFourWindsYaku bigFourWindsYaku = new BigFourWindsYaku();
    private static final BigThreeDragonsYaku bigThreeDragonsYaku = new BigThreeDragonsYaku();
    private static final DiscardYaku discardYaku = new DiscardYaku();
    private static final DoubleTwinRunsYaku doubleTwinRunsYaku = new DoubleTwinRunsYaku();
    private static final DragonYaku dragonYaku = new DragonYaku();
    private static final FlushYaku flushYaku = new FlushYaku();
    private static final FourConcealedTripletsYaku fourConcealedTripletsYaku = new FourConcealedTripletsYaku();
    private static final HalfFlushYaku halfFlushYaku = new HalfFlushYaku();
    private static final LittleFourWindsYaku littleFourWindsYaku = new LittleFourWindsYaku();
    private static final NineGatesYaku nineGatesYaku = new NineGatesYaku();
    private static final NoPointsYaku noPointsYaku = new NoPointsYaku();
    private static final RoundWindYaku roundWindYaku = new RoundWindYaku();
    private static final SeatWindYaku seatWindYaku = new SeatWindYaku();
    private static final SevenPairsYaku sevenPairsYaku = new SevenPairsYaku();
    private static final StraightYaku straightYaku = new StraightYaku();
    private static final TerminalInEachSetYaku terminalInEachSetYaku = new TerminalInEachSetYaku();
    private static final TerminalOrHonorInEachSetYaku terminalOrHonorInEachSetYaku = new TerminalOrHonorInEachSetYaku();
    private static final ThirteenOrphansYaku thirteenOrphansYaku = new ThirteenOrphansYaku();
    private static final ThreeClosedTripletsYaku threeClosedTripletsYaku = new ThreeClosedTripletsYaku();
    private static final ThreeColorStraightYaku threeColorStraightYaku = new ThreeColorStraightYaku();
    private static final ThreeColorTripletsYaku threeColorTripletsYaku = new ThreeColorTripletsYaku();
    private static final ThreeKansYaku threeKansYaku = new ThreeKansYaku();
    private static final TwinRunsYaku twinRunsYaku = new TwinRunsYaku();
    private static final List<AbstractYaku> yaku = new ArrayList<>(Arrays.asList(
            allGreenYaku,
            allHonorsYaku,
            allSimplesYaku,
            allTerminalsAndHonorsYaku,
            allTerminalsYaku,
            allTripletsYaku,
            bigFourWindsYaku,
            bigThreeDragonsYaku,
            discardYaku,
            doubleTwinRunsYaku,
            dragonYaku,
            flushYaku,
            fourConcealedTripletsYaku,
            halfFlushYaku,
            littleFourWindsYaku,
            nineGatesYaku,
            noPointsYaku,
            roundWindYaku,
            seatWindYaku,
            sevenPairsYaku,
            straightYaku,
            terminalInEachSetYaku,
            terminalOrHonorInEachSetYaku,
            thirteenOrphansYaku,
            threeClosedTripletsYaku,
            threeColorStraightYaku,
            threeColorTripletsYaku,
            threeKansYaku,
            twinRunsYaku));

    //TODO: make static variable holding all the hand details for current player

    public static boolean hasValidYaku(Player player) {
        return yaku.stream().anyMatch(y -> y.isValid(new Player(player)));
    }

    public static Map<Tile, List<Tile>> getRiichiTiles(Player player) {
        if (!player.getPlayArea().getMelds().isEmpty()) {
            return new HashMap<>();
        }
        Player copyPlayer = new Player(player);
        Map<Tile, List<Tile>> riichiMap = new HashMap<>();
        List<Tile> copyHand = getCopyHand(copyPlayer);
        for (Tile discardTile : copyHand) {
            copyPlayer.getPlayArea().getHand().remove(discardTile);
            List<Tile> riichiTiles = getWaitTiles(copyPlayer);
            if (!riichiTiles.isEmpty()) {
                riichiMap.put(discardTile, riichiTiles);
            }
            copyPlayer.getPlayArea().getHand().add(discardTile);
        }
        return riichiMap;
    }

    public static List<Tile> getWaitTiles(Player player) {
        List<Tile> waits = new ArrayList<>();
        for (Tile uniqueTile : uniqueTiles) {
            player.getPlayArea().getHand().add(uniqueTile);
            if (hasAPairAndFourSetsOrRuns(player) || sevenPairsYaku.isValid(player) || thirteenOrphansYaku.isValid(player)) {  //potentially prevent riichi declaration for Yakuman hands
                waits.add(uniqueTile);
            }
            player.getPlayArea().getHand().remove(uniqueTile);
        }
        return waits;
    }

    public static boolean isInTenpai(Player player) {
        Player copyPlayer = new Player(player);
        List<Tile> copyHand = getCopyHand(copyPlayer);
        for (Tile discardTile : copyHand) {
            copyPlayer.getPlayArea().getHand().remove(discardTile);
            for (Tile uniqueTile : uniqueTiles) {
                copyPlayer.getPlayArea().getHand().add(uniqueTile);
                if (hasAPairAndFourSetsOrRuns(copyPlayer) || sevenPairsYaku.isValid(copyPlayer) || thirteenOrphansYaku.isValid(copyPlayer)) {
                    return true;
                }
                copyPlayer.getPlayArea().getHand().remove(uniqueTile);
            }
            copyPlayer.getPlayArea().getHand().add(discardTile);
        }
        return false;
    }

    private static List<Tile> getCopyHand(Player player) {
        return player.getPlayArea().getHand().stream().map(t -> {
            if (t instanceof NumberTile) {
                return new NumberTile((NumberTile) t);
            } else if (t instanceof DragonTile) {
                return new DragonTile((DragonTile) t);
            } else {
                return new WindTile((WindTile) t);
            }
        }).collect(Collectors.toList());
    }

    public static boolean hasAPairAndFourSetsOrRuns(Player player) {
        List<HandDetail> handDetails = getHandDetails(player);
        return !handDetails.isEmpty() && handDetails.stream().anyMatch(h -> !h.getValidHands().isEmpty());
    }

    public static boolean areRunsIdentical(List<Tile> runOne, List<Tile> runTwo) {
        return runOne.get(0).getSuit().equals(runTwo.get(0).getSuit()) &&
                runOne.get(0).getNumber() == runTwo.get(0).getNumber() &&
                runOne.get(1).getSuit().equals(runTwo.get(1).getSuit()) &&
                runOne.get(1).getNumber() == runTwo.get(1).getNumber() &&
                runOne.get(2).getSuit().equals(runTwo.get(2).getSuit()) &&
                runOne.get(2).getNumber() == runTwo.get(2).getNumber();
    }

    public static boolean areRunsSameNumber(List<Tile> runOne, List<Tile> runTwo) {
        return runOne.get(0).getNumber() == runTwo.get(0).getNumber() &&
                runOne.get(1).getNumber() == runTwo.get(1).getNumber() &&
                runOne.get(2).getNumber() == runTwo.get(2).getNumber();
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
