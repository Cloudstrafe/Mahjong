package mahjong;

import mahjong.tile.Tile;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestDeck {
    Deck deck;

    @Before
    public void setup() {
        deck = new Deck();
    }

    @Test
    public void testDrawAndGetTotalTilesAndShuffle() {
        //Expect
        assertEquals(136, deck.getTotalTiles());
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            Tile t = deck.getWall().get(0);
            deck.draw();
            tiles.add(t);
        }
        assertEquals(tiles, deck.getDrawn());
        assertEquals(111, deck.getTotalTiles());
        deck.reset();
        assertEquals(136, deck.getTotalTiles());
        assertEquals(136, deck.getWall().size());
        assertEquals(0, deck.getDrawn().size());
    }

    @Test
    public void testDrawFromEmptyDeck() {
        //Given
        deck.getWall().clear();
        assertNull(deck.draw());
    }

    @Test
    public void testDeckConstructionFromList() {
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            Tile t = deck.getWall().get(0);
            tiles.add(t);
        }
        Deck test = new Deck(tiles);
        assertEquals(25, test.getWall().size());
        assertEquals(25, test.getTotalTiles());
        test.draw();
        test.draw();
        assertEquals(test.getTotalTiles(), test.getWall().size());
    }
}
