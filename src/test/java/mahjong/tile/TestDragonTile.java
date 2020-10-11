package mahjong.tile;

import mahjong.SuitConstants;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDragonTile {

    @Test
    public void happyPathGetters() {
        //Given
        Tile tile = new DragonTile(SuitConstants.GREEN_DRAGON);

        //Expect
        assertTrue(tile.isDragon());
        assertFalse(tile.isWind());
        assertFalse(tile.isPrevailingWind());
        assertFalse(tile.isSeatWind());
        assertFalse(tile.isNumber());
        assertFalse(tile.isTerminal());
        assertTrue(tile.isHonor());
        assertFalse(tile.isDora);
        assertFalse(tile.isRed);
        assertEquals(0, tile.number);
        assertEquals(SuitConstants.GREEN_DRAGON, tile.suit);
    }
}
