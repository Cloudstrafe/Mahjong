package mahjong.tile;

import mahjong.SuitConstants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestNumberTile {

    Tile t1;
    Tile t2;

    @Before
    public void setup() {
        t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        t2 = new NumberTile(5, SuitConstants.DOTS, true);
    }

    @Test
    public void happyPathGetters() {
        //Expect
        assertFalse(t1.isDragon());
        assertFalse(t1.isWind());
        assertTrue(t1.isNumber());
        assertTrue(t1.isTerminal());
        assertFalse(t1.isHonor());
        assertFalse(t1.isDora);
        assertFalse(t1.isRed);
        assertEquals(1, t1.number);
        assertEquals(SuitConstants.BAMBOO, t1.suit);

        assertFalse(t2.isDragon());
        assertFalse(t2.isWind());
        assertTrue(t2.isNumber());
        assertFalse(t2.isTerminal());
        assertFalse(t2.isHonor());
        assertTrue(t2.isDora);
        assertTrue(t2.isRed);
        assertEquals(5, t2.number);
        assertEquals(SuitConstants.DOTS, t2.suit);
    }
}
