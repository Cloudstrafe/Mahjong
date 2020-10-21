package mahjong;

import mahjong.tile.Tile;
import mahjong.tile.WindTile;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
}
