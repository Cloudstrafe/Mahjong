package mahjong.yaku;

import mahjong.Meld;
import mahjong.PlayArea;
import mahjong.Player;
import mahjong.SuitConstants;
import mahjong.tile.DragonTile;
import mahjong.tile.NumberTile;
import mahjong.tile.Tile;
import mahjong.tile.WindTile;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class TestYakuHandler {
    Player player;
    PlayArea pa;

    @Before
    public void setup() {
        player = new Player(SuitConstants.WEST_WIND, false, 1);
        pa = new PlayArea(1);
        player.setPlayArea(pa);
        RoundWindYaku.setRoundWind(SuitConstants.EAST_WIND);
    }

    @Test
    public void handWithMeldsIsNotValid() {
        //Given
        Tile t1 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t2 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t3 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t4 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(3, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t8 = new NumberTile(5, SuitConstants.DOTS, false);
        Tile t9 = new NumberTile(6, SuitConstants.DOTS, false);
        Tile t10 = new NumberTile(7, SuitConstants.DOTS, false);
        Tile t11 = new NumberTile(8, SuitConstants.DOTS, false);
        Tile t12 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t13 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t14 = new NumberTile(8, SuitConstants.BAMBOO, false);
        player.getPlayArea().setHand(Arrays.asList(t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        List<Tile> meldTiles = new ArrayList<>(Arrays.asList(t1, t2, t3));
        Map<Tile, List<Tile>> expected = new HashMap<>();
        player.getPlayArea().getMelds().add(new Meld(meldTiles, false, false, -1));

        //Expect
        assertEquals(expected, YakuHandler.getRiichiTiles(player));
    }

    @Test
    public void testOneDiscardFewWaits() {
        //Given
        Tile t1 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t2 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t3 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t4 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(3, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t8 = new NumberTile(5, SuitConstants.DOTS, false);
        Tile t9 = new NumberTile(6, SuitConstants.DOTS, false);
        Tile t10 = new NumberTile(7, SuitConstants.DOTS, false);
        Tile t11 = new NumberTile(8, SuitConstants.DOTS, false);
        Tile t12 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t13 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t14 = new NumberTile(8, SuitConstants.BAMBOO, false);
        player.getPlayArea().setHand(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        Map<Tile, List<Tile>> expected = new HashMap<>();
        expected.put(t14, new ArrayList<>(Arrays.asList(t6, t9, new NumberTile(9, SuitConstants.DOTS, false))));

        //When
        Map<Tile, List<Tile>> actual = YakuHandler.getRiichiTiles(player);

        //Then
        compareMaps(expected, actual);
    }

    @Test
    public void testMultipleDiscardsSingleWait() {
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t4 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t5 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t7 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t8 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t9 = new WindTile(SuitConstants.EAST_WIND);
        Tile t10 = new WindTile(SuitConstants.EAST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new WindTile(SuitConstants.WEST_WIND);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new WindTile(SuitConstants.SOUTH_WIND);
        player.getPlayArea().setHand(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        Map<Tile, List<Tile>> expected = new HashMap<>();
        expected.put(t13, Collections.singletonList(t14));
        expected.put(t14, Collections.singletonList(t13));

        //When
        Map<Tile, List<Tile>> actual = YakuHandler.getRiichiTiles(player);

        //Then
        compareMaps(expected, actual);
    }

    @Test
    public void testOneDiscardManyWeights() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        Tile t5 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t7 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t8 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t9 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t10 = new WindTile(SuitConstants.EAST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t13 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t14 = new NumberTile(5, SuitConstants.DOTS, false);
        player.getPlayArea().setHand(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        Map<Tile, List<Tile>> expected = new HashMap<>();
        expected.put(t14, Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13));

        //When
        Map<Tile, List<Tile>> actual = YakuHandler.getRiichiTiles(player);

        //Then
        compareMaps(expected, actual);
    }

    @Test
    public void testNoValidRiichiReturnsEmptyMap() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        Tile t5 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t7 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t8 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t9 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t10 = new WindTile(SuitConstants.EAST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t13 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t14 = new NumberTile(5, SuitConstants.DOTS, false);
        player.getPlayArea().setHand(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        Map<Tile, List<Tile>> expected = new HashMap<>();

        //When
        Map<Tile, List<Tile>> actual = YakuHandler.getRiichiTiles(player);

        //Then
        compareMaps(expected, actual);
    }

    private void compareMaps(Map<Tile, List<Tile>> expected, Map<Tile, List<Tile>> actual) {
        for (Entry<Tile, List<Tile>> entry : expected.entrySet()) {
            List<Tile> actualValue = actual.get(entry.getKey()).stream().sorted().collect(Collectors.toList());
            assertEquals(entry.getValue().size(), actualValue.size());
            entry.getValue().stream().sorted().collect(Collectors.toList());
            for (int i = 0; i < entry.getValue().size(); i++) {
                assertEquals(entry.getValue().stream().sorted().collect(Collectors.toList()).get(i), actualValue.get(i));
            }
        }
    }
}
