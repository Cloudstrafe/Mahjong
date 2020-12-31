package mahjong;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestPlayer {
    @Test
    public void testCopyConstructor() {
        //Given
        Player player1 = new Player(SuitConstants.EAST_WIND, false, 1);
        Player player2 = new Player(player1);

        //Expect
        assertEquals(player1.getPoints(), player2.getPoints());
        assertEquals(player1.getSeat(), player2.getSeat());
        assertEquals(player1.isDealer(), player2.isDealer());
        assertEquals(player1.getPlayerNumber(), player2.getPlayerNumber());
        assertEquals(player1.getPlayArea().isMyTurn(), player2.getPlayArea().isMyTurn());
        assertNotEquals(player1.getPlayArea(), player2.getPlayArea());

        //Given
        player2.setDealer(true);
        player2.setSeat(SuitConstants.WEST_WIND);
        player2.setPoints(0);
        player2.getPlayArea().setMyTurn(true);

        //Expect
        assertNotEquals(player1.getPoints(), player2.getPoints());
        assertNotEquals(player1.isDealer(), player2.isDealer());
        assertNotEquals(player1.getSeat(), player2.getSeat());
        assertNotEquals(player1.getPlayArea().isMyTurn(), player2.getPlayArea().isMyTurn());
    }
}
