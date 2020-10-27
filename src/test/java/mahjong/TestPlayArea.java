package mahjong;

import mahjong.tile.DragonTile;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestPlayArea {
    @Test
    public void testCopyConstructor() {
        //Given
        PlayArea pa1 = new PlayArea(1);
        DragonTile t1 = new DragonTile(SuitConstants.RED_DRAGON);
        DragonTile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
        DragonTile t3 = new DragonTile(SuitConstants.GREEN_DRAGON);
        DragonTile t4 = new DragonTile(SuitConstants.GREEN_DRAGON);
        DragonTile t5 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Meld meld = new Meld(Arrays.asList(t3,t4,t5),true, true, 2);
        pa1.getMelds().add(meld);
        pa1.getHand().add(t1);
        pa1.getDiscard().add(t2);
        PlayArea pa2 = new PlayArea(pa1);

        //Expect
        assertNotEquals(pa1, pa2);
        assertEquals(pa1.getHand(), pa2.getHand());
        assertEquals(pa1.getDiscard(), pa2.getDiscard());
        assertNotEquals(pa1.getMelds(), pa2.getMelds());
        assertEquals(pa1.getHand().get(0).getSuit(), pa2.getHand().get(0).getSuit());
        assertEquals(pa1.getHandPanelHolder(), pa2.getHandPanelHolder());
        assertEquals(pa1.getDiscardPanelHolder(), pa2.getDiscardPanelHolder());
        assertEquals(pa1.getMeldPanelHolder(), pa2.getMeldPanelHolder());
        assertEquals(pa1.getPlayerNumber(), pa2.getPlayerNumber());
        assertEquals(pa1.isDiscardSelected(), pa2.isDiscardSelected());
        assertEquals(pa1.getDiscardIndex(), pa2.getDiscardIndex());
        assertEquals(pa1.isMyTurn(), pa2.isMyTurn());

        //Given
        pa2.reset();
        pa2.setDiscardSelected(true);
        pa2.setDiscardIndex(2);
        pa2.setMyTurn(true);

        //Expect
        assertNotEquals(pa1.getHand().size(), pa2.getHand().size());
        assertNotEquals(pa1.getDiscard().size(), pa2.getDiscard().size());
        assertNotEquals(pa1.getMelds().size(), pa2.getMelds().size());
        assertNotEquals(pa1.isMyTurn(), pa2.isMyTurn());
        assertNotEquals(pa1.getDiscardIndex(),pa2.getDiscardIndex());
    }
}
