package mahjong;

import mahjong.tile.Tile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDeadwall {
    Deadwall deadwall;

    @Before
    public void setup() {
        Deck deck = new Deck();
        deadwall = new Deadwall();
        deadwall.setup(deck);
    }

    @Test
    public void testGetters() {
        //Expect
        assertEquals(4, deadwall.getDrawTiles().getWall().size());
        assertEquals(1, deadwall.getRevealed());
        assertEquals(deadwall.getDoraTiles().get(0).getTileAsString(), deadwall.getDoraAsString());
        assertFalse(deadwall.isRoundOver());
    }

    @Test
    public void testWithFourRevealedTiles() {
        //Given
        deadwall.setRevealed(4);
        String doraString = deadwall.getDoraTiles().get(0).getTileAsString() + ", "
                + deadwall.getDoraTiles().get(1).getTileAsString() + ", "
                + deadwall.getDoraTiles().get(2).getTileAsString() + ", "
                + deadwall.getDoraTiles().get(3).getTileAsString();
        Tile toBeDrawn = deadwall.getDrawTiles().getWall().get(0);
        Tile drawn = deadwall.getDrawTiles().draw();

        //Expect
        assertEquals(doraString, deadwall.getDoraAsString());
        assertEquals(4, deadwall.getRevealed());
        assertEquals(3, deadwall.getDrawTiles().getWall().size());
        assertEquals(toBeDrawn, drawn);
        assertTrue(deadwall.isRoundOver());
    }
}
