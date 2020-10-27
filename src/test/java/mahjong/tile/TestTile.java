package mahjong.tile;

import mahjong.SuitConstants;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestTile {
    Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
    Tile t2 = new NumberTile(2, SuitConstants.BAMBOO, false);
    Tile t3 = new NumberTile(1, SuitConstants.BAMBOO, false);
    Tile t4 = new DragonTile(SuitConstants.GREEN_DRAGON);
    Tile t5 = new DragonTile(SuitConstants.WHITE_DRAGON);
    Tile t6 = new WindTile(SuitConstants.WEST_WIND);
    Tile t7 = new DragonTile(SuitConstants.RED_DRAGON);
    Tile t8;

    @Test
    public void happyPathCompareTo() {
        assertTrue(t6.compareTo(t5) < 0);
        assertTrue(t2.compareTo(t1) > 0);
        assertEquals(0, t1.compareTo(t3));
        assertTrue(t1.compareTo(t2) < 0);
        assertTrue(t1.compareTo(t7) > 0);
    }

    @Test
    public void happyPathEquals() {
        assertEquals(t1, t3);
    }

    @Test
    public void tilesWithDifferentSuitsOrNumbersAreNotEqual() {
        assertNotEquals(t1, t6);
        assertNotEquals(t1, t2);
        assertNotEquals(t4, t5);
        assertNotEquals(t1, t8);
    }

    @Test
    public void testIsDragon() {
        assertTrue(t4.isDragon());
        assertFalse(t1.isDragon());
    }

    @Test
    public void testIsWind() {
        assertFalse(t1.isWind());
        assertTrue(t6.isWind());
    }

    @Test
    public void testIsTerminal() {
        assertTrue(t1.isTerminal());
        assertFalse(t6.isTerminal());
    }

    @Test
    public void testIsHonor() {
        assertFalse(t1.isHonor());
        assertFalse(t2.isHonor());
        assertTrue(t5.isHonor());
        assertTrue(t6.isHonor());
    }

    @Test
    public void testGetNumber() {
        assertEquals(1, t1.getNumber());
        assertEquals(2, t2.getNumber());
        assertEquals(0, t5.getNumber());
        assertEquals(0, t6.getNumber());
    }

    @Test
    public void testGetSuit() {
        assertEquals(SuitConstants.BAMBOO, t1.getSuit());
        assertEquals(SuitConstants.BAMBOO, t2.getSuit());
        assertEquals(SuitConstants.GREEN_DRAGON, t4.getSuit());
        assertEquals(SuitConstants.WEST_WIND, t6.getSuit());
    }

    @Test
    public void getTileAsString() {
        assertEquals("1 " + SuitConstants.BAMBOO, t1.getTileFileName());
        assertEquals("2 " + SuitConstants.BAMBOO, t2.getTileFileName());
        assertEquals(SuitConstants.GREEN_DRAGON, t4.getTileFileName());
        assertEquals(SuitConstants.WHITE_DRAGON, t5.getTileFileName());
        assertEquals(SuitConstants.WEST_WIND, t6.getTileFileName());
        assertEquals(SuitConstants.RED_DRAGON, t7.getTileFileName());
    }

    @Test
    public void testCopyConstructors() {
        //Given
        Tile t9 =new NumberTile((NumberTile) t1);

        //Expect
        assertEquals(t9.getNumber(), t1.getNumber());
        assertEquals(t9.getSuit(), t1.getSuit());
        assertEquals(t9.isRed, t1.isRed);
        assertEquals(t9.smallTilePath, t1.smallTilePath);
        assertEquals(t9.mediumTilePath, t1.mediumTilePath);
        assertEquals(t9.smallTileFacingDown, t1.smallTileFacingDown);
        assertEquals(t9.smallTileFacingLeft, t1.smallTileFacingLeft);
        assertEquals(t9.smallTileFacingUp, t1.smallTileFacingUp);
        assertEquals(t9.mediumTileFacingDown, t1.mediumTileFacingDown);
        assertEquals(t9.mediumTileFacingLeft, t1.mediumTileFacingLeft);
        assertEquals(t9.mediumTileFacingUp, t1.mediumTileFacingUp);
        assertEquals(t9.mediumTileFacingRight, t1.mediumTileFacingRight);
        assertEquals(t9, t1);

        //Given
        t9.setIsDora(true);

        //Expect
        assertNotEquals(t9.isDora, t1.isDora);

    }


}
