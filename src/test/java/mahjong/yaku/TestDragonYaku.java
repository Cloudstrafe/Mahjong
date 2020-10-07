package mahjong.yaku;

import mahjong.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestDragonYaku {
    DragonYaku dragonYaku;
    Player player;

    @Before
    public void setUp() {
        dragonYaku = new DragonYaku();
        player = new Player(SuitConstants.WEST_WIND, false, 4, "a");
    }

    @Test
    public void happyPathGetters() {
        //Expect
        assertFalse(dragonYaku.isMangan());
        assertEquals(1, dragonYaku.getClosedPoints());
        assertEquals(1, dragonYaku.getOpenPoints());
        assertEquals(0, dragonYaku.getCurrentPoints(player));
        assertTrue(dragonYaku.isStackable());
        assertFalse(dragonYaku.isYakuman());
        assertFalse(dragonYaku.isDoubleYakuman());
    }

    @Test
    public void happyPathGreenDragonIsValid() {
        //TODO: Figure out why this test is breaking
        //Given
        Tile t1 = new WindTile(SuitConstants.EAST_WIND);
        Tile t2 = new WindTile(SuitConstants.EAST_WIND);
        Tile t3 = new WindTile(SuitConstants.EAST_WIND);
        Tile t4 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t5 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t10 = new WindTile(SuitConstants.WEST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t13 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t14 = new NumberTile(8, SuitConstants.BAMBOO, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea();
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(dragonYaku.isValid(player));
    }

    @Test
    public void happyPathRedDragonIsValid() {
        //Given
        Tile t1 = new WindTile(SuitConstants.EAST_WIND);
        Tile t2 = new WindTile(SuitConstants.EAST_WIND);
        Tile t3 = new WindTile(SuitConstants.EAST_WIND);
        Tile t4 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t5 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t7 = new NumberTile(6, SuitConstants.CHARACTERS, false);
        Tile t8 = new NumberTile(6, SuitConstants.CHARACTERS, false);
        Tile t9 = new NumberTile(6, SuitConstants.CHARACTERS, false);
        Tile t10 = new WindTile(SuitConstants.WEST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t13 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t14 = new NumberTile(8, SuitConstants.BAMBOO, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea();
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(dragonYaku.isValid(player));
    }

    @Test
    public void happyPathWhiteDragonIsValid() {
        //Given
        Tile t1 = new WindTile(SuitConstants.EAST_WIND);
        Tile t2 = new WindTile(SuitConstants.EAST_WIND);
        Tile t3 = new WindTile(SuitConstants.EAST_WIND);
        Tile t4 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t5 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t7 = new NumberTile(6, SuitConstants.CHARACTERS, false);
        Tile t8 = new NumberTile(6, SuitConstants.CHARACTERS, false);
        Tile t9 = new NumberTile(6, SuitConstants.CHARACTERS, false);
        Tile t10 = new WindTile(SuitConstants.WEST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t13 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t14 = new NumberTile(8, SuitConstants.BAMBOO, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea();
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(dragonYaku.isValid(player));
    }

    @Test
    public void handWithNoDragonSetIsNotValid() {
        //Given
        Tile t1 = new WindTile(SuitConstants.EAST_WIND);
        Tile t2 = new WindTile(SuitConstants.EAST_WIND);
        Tile t3 = new WindTile(SuitConstants.EAST_WIND);
        Tile t4 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t5 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t6 = new NumberTile(6, SuitConstants.CHARACTERS, false);
        Tile t7 = new NumberTile(6, SuitConstants.CHARACTERS, false);
        Tile t8 = new NumberTile(6, SuitConstants.CHARACTERS, false);
        Tile t9 = new WindTile(SuitConstants.WEST_WIND);
        Tile t10 = new WindTile(SuitConstants.WEST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t13 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t14 = new NumberTile(8, SuitConstants.BAMBOO, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea();
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(dragonYaku.isValid(player));
    }
}
