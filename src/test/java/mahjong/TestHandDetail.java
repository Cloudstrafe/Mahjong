package mahjong;

import mahjong.tile.DragonTile;
import mahjong.tile.NumberTile;
import mahjong.tile.Tile;
import mahjong.tile.WindTile;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class TestHandDetail {

    @Test
    public void happyPathNoMeldsMixOfRunsAndSets() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(1, SuitConstants.BAMBOO, false);
        List<Tile> pair = new ArrayList<>(Arrays.asList(t1, t2));

        Tile t3 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(3, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(5, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t9 = new WindTile(SuitConstants.WEST_WIND);
        Tile t10 = new WindTile(SuitConstants.WEST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        List<Tile> remainder = new ArrayList<>(Arrays.asList(t3, t4, t5, t6, t7, t8, t9, t10, t11));
        remainder = remainder.stream().sorted().collect(Collectors.toList());

        Tile t12 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);
        List<Tile> tiles = new ArrayList<>(Arrays.asList(t12, t13, t14));
        Meld meld = new Meld(tiles, true, false);
        List<Meld> melds = new ArrayList<>(Collections.singletonList(meld));

        //When
        HandDetail handDetail = new HandDetail(pair, remainder, 3, melds);

        //Then
        assertEquals(pair, handDetail.getPair());
        assertEquals(remainder, handDetail.getRemainder());
        assertEquals(3, handDetail.getSetsAndRunsLeft());
        assertEquals(melds, handDetail.getMelds());

        List<Tile> firstRun = new ArrayList<>(Arrays.asList(t3, t4, t5));
        List<Tile> secondRun = new ArrayList<>(Arrays.asList(t6, t7, t8));
        Meld meldR1 = new Meld(firstRun, false, true);
        Meld meldR2 = new Meld(secondRun, false, true);
        List<Meld> runs = new ArrayList<>(Arrays.asList(meldR1, meldR2));
        assertEquals(runs.size(), handDetail.getRuns().size());
        assertEquals(runs.get(0).getTiles(), handDetail.getRuns().get(0).getTiles());
        assertEquals(runs.get(1).getTiles(), handDetail.getRuns().get(1).getTiles());

        List<Tile> firstSet = new ArrayList<>(Arrays.asList(t9, t10, t11));
        Meld meldS1 = new Meld(firstSet, false, true);
        List<Meld> sets = new ArrayList<>(Collections.singletonList(meldS1));
        assertEquals(sets.size(), handDetail.getSets().size());
        assertEquals(sets.get(0).getTiles(), handDetail.getSets().get(0).getTiles());

        List<Meld> hand = new ArrayList<>();
        hand.addAll(runs);
        hand.addAll(sets);
        hand.addAll(melds);
        List<List<Meld>> validHands = new ArrayList<>(Collections.singletonList(hand));
        assertEquals(validHands.size(), handDetail.getValidHands().size());
        assertEquals(validHands.get(0).get(0).getTiles(), handDetail.getValidHands().get(0).get(0).getTiles());
        assertEquals(validHands.get(0).get(1).getTiles(), handDetail.getValidHands().get(0).get(1).getTiles());
        assertEquals(validHands.get(0).get(2).getTiles(), handDetail.getValidHands().get(0).get(2).getTiles());
        assertEquals(validHands.get(0).get(3).getTiles(), handDetail.getValidHands().get(0).get(3).getTiles());
    }
}
