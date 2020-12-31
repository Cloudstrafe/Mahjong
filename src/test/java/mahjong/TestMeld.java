package mahjong;

import mahjong.tile.Tile;
import mahjong.tile.WindTile;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestMeld {
    @Test
    public void happyPathGetters() {
        //Given
        Tile t1 = new WindTile(SuitConstants.EAST_WIND);
        Tile t2 = new WindTile(SuitConstants.EAST_WIND);
        Tile t3 = new WindTile(SuitConstants.EAST_WIND);
        List<Tile> tiles = new ArrayList<>(Arrays.asList(t1, t2, t3));
        Meld meld = new Meld(tiles, false, false, 2);

        //Expect
        assertEquals(tiles, meld.getTiles());
        assertFalse(meld.isRun());
        assertFalse(meld.isOpen());
        assertEquals(2, meld.getCalledTileIndex());
    }

    @Test
    public void testCopyConstructor() {
        //Given
        Tile t1 = new WindTile(SuitConstants.EAST_WIND);
        Tile t2 = new WindTile(SuitConstants.EAST_WIND);
        Tile t3 = new WindTile(SuitConstants.EAST_WIND);
        List<Tile> tiles = new ArrayList<>(Arrays.asList(t1, t2, t3));
        Meld meld = new Meld(tiles, false, false, 2);
        Meld meld2 = new Meld(meld);

        //Expect
        assertNotEquals(meld, meld2);
        assertEquals(meld.isOpen(), meld2.isOpen());
        assertEquals(meld.isRun(), meld2.isRun());
        assertEquals(meld.getCalledTileIndex(), meld2.getCalledTileIndex());
        assertEquals(meld.getTiles().get(0).getSuit(), meld2.getTiles().get(0).getSuit());
        assertEquals(meld.getTiles().get(0).getNumber(), meld2.getTiles().get(0).getNumber());
    }
}
