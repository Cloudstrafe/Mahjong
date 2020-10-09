package mahjong.yaku;

import mahjong.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestAllTripletsYaku {
    AllTripletsYaku allTripletsYaku;
    Player player;

    @Before
    public void setUp() {
        allTripletsYaku = new AllTripletsYaku();
        player = new Player(SuitConstants.WEST_WIND, false, 4, "a");
    }

    @Test
    public void happyPathGetters() {
        //Expect
        assertFalse(allTripletsYaku.isMangan());
        assertEquals(2, allTripletsYaku.getClosedPoints());
        assertEquals(2, allTripletsYaku.getOpenPoints());
        assertEquals(0, allTripletsYaku.getCurrentPoints(player));
        assertTrue(allTripletsYaku.isStackable());
        assertFalse(allTripletsYaku.isYakuman());
        assertFalse(allTripletsYaku.isDoubleYakuman());
    }

    @Test
    public void happyPathIsValid() {
        //Given
        Tile t1 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t3 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t4 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t8 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t9 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t10 = new WindTile(SuitConstants.WEST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new WindTile(SuitConstants.WEST_WIND);
        Tile t13 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        Tile t14 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea();
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(allTripletsYaku.isValid(player));
    }

    @Test
    public void handWithRunIsNotValid() {
        //Given
        Tile t1 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t3 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(3, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t8 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t9 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t10 = new WindTile(SuitConstants.WEST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new WindTile(SuitConstants.WEST_WIND);
        Tile t13 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        Tile t14 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea();
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(allTripletsYaku.isValid(player));
    }

    @Test
    public void allTripletsButNotFiveUniqueTileTypesIsNotValid() {
        //Given
        Tile t1 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t3 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t4 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t8 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t9 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t10 = new WindTile(SuitConstants.WEST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new WindTile(SuitConstants.WEST_WIND);
        Tile t13 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        Tile t14 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea();
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(allTripletsYaku.isValid(player));
    }

    @Test
    public void allTriplletsButNoPairIsNotValid() {
        //Given
        Tile t1 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t3 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t4 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t8 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t9 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t10 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new WindTile(SuitConstants.WEST_WIND);
        Tile t13 = new WindTile(SuitConstants.WEST_WIND);
        Tile t14 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea();
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(allTripletsYaku.isValid(player));
    }
}
