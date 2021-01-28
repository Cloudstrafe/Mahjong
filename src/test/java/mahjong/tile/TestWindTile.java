package mahjong.tile;

import mahjong.SuitConstants;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestWindTile {

    @Test
    public void happyPathGetters() {
        //Given
        Tile tile = new WindTile(SuitConstants.NORTH_WIND);

        //Expect
        assertFalse(tile.isDragon());
        assertTrue(tile.isWind());
        assertFalse(tile.isNumber());
        assertFalse(tile.isTerminal());
        assertTrue(tile.isHonor());
        assertFalse(tile.isDora);
        assertFalse(tile.isRed);
        assertEquals(0, tile.number);
        assertEquals(SuitConstants.NORTH_WIND, tile.suit);
    }
}
