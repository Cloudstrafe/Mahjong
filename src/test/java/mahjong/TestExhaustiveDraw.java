package mahjong;

import mahjong.tile.DragonTile;
import mahjong.tile.NumberTile;
import mahjong.tile.Tile;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static mahjong.SuitConstants.*;
import static org.junit.Assert.assertEquals;

public class TestExhaustiveDraw {
    Player playerOne;
    Player playerTwo;
    Player playerThree;
    Player playerFour;
    Queue<Player> turnQueue;
    List<Tile> validHand;
    List<Tile> invalidHand;

    @Before
    public void setup() {
        this.playerOne = new Player(EAST_WIND, true, 1);
        this.playerTwo = new Player(SOUTH_WIND, false, 2);
        this.playerThree = new Player(WEST_WIND, false, 3);
        this.playerFour = new Player(NORTH_WIND, false, 4);
        this.turnQueue = new LinkedList<>();
        turnQueue.add(playerOne);
        turnQueue.add(playerTwo);
        turnQueue.add(playerThree);
        turnQueue.add(playerFour);
        Tile t1 = new DragonTile(GREEN_DRAGON);
        Tile t2 = new DragonTile(GREEN_DRAGON);
        Tile t3 = new DragonTile(RED_DRAGON);
        Tile t4 = new NumberTile(2, BAMBOO, false);
        Tile t5 = new NumberTile(2, BAMBOO, false);
        Tile t6 = new NumberTile(3, BAMBOO, false);
        Tile t7 = new NumberTile(4, BAMBOO, false);
        Tile t8 = new NumberTile(5, BAMBOO, false);
        Tile t9 = new NumberTile(6, BAMBOO, false);
        Tile t10 = new NumberTile(7, BAMBOO, false);
        Tile t11 = new NumberTile(8, BAMBOO, false);
        Tile t12 = new NumberTile(8, BAMBOO, false);
        Tile t13 = new NumberTile(9, BAMBOO, false);
        Tile t14 = new NumberTile(9, BAMBOO, false);
        Tile t15 = new NumberTile(1, BAMBOO, false);
        Tile t16 = new DragonTile(GREEN_DRAGON);

        validHand = new ArrayList<>(Arrays.asList(t1, t2, t16, t15, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        invalidHand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
    }

    @Test
    public void testNoPlayersInTenpai() {
        //Given
        PlayArea playArea1 = new PlayArea(1);
        playArea1.setHand(invalidHand);
        playerOne.setPlayArea(playArea1);
        PlayArea playArea2 = new PlayArea(2);
        playArea2.setHand(invalidHand);
        playerTwo.setPlayArea(playArea2);
        PlayArea playArea3 = new PlayArea(3);
        playArea3.setHand(invalidHand);
        playerThree.setPlayArea(playArea3);
        PlayArea playArea4 = new PlayArea(4);
        playArea4.setHand(invalidHand);
        playerFour.setPlayArea(playArea4);

        //When
        ExhaustiveDraw.tenpaiPayout(turnQueue);

        //Then
        assertEquals(25000, playerOne.getPoints());
        assertEquals(25000, playerTwo.getPoints());
        assertEquals(25000, playerThree.getPoints());
        assertEquals(25000, playerFour.getPoints());
    }

    @Test
    public void testOnePlayerInTenpai() {
        //Given
        PlayArea playArea1 = new PlayArea(1);
        playArea1.setHand(validHand);
        playerOne.setPlayArea(playArea1);
        PlayArea playArea2 = new PlayArea(2);
        playArea2.setHand(invalidHand);
        playerTwo.setPlayArea(playArea2);
        PlayArea playArea3 = new PlayArea(3);
        playArea3.setHand(invalidHand);
        playerThree.setPlayArea(playArea3);
        PlayArea playArea4 = new PlayArea(4);
        playArea4.setHand(invalidHand);
        playerFour.setPlayArea(playArea4);

        //When
        ExhaustiveDraw.tenpaiPayout(turnQueue);

        //Then
        assertEquals(28000, playerOne.getPoints());
        assertEquals(24000, playerTwo.getPoints());
        assertEquals(24000, playerThree.getPoints());
        assertEquals(24000, playerFour.getPoints());
    }

    @Test
    public void testTwoPlayersInTenpai() {
        //Given
        PlayArea playArea1 = new PlayArea(1);
        playArea1.setHand(validHand);
        playerOne.setPlayArea(playArea1);
        PlayArea playArea2 = new PlayArea(2);
        playArea2.setHand(validHand);
        playerTwo.setPlayArea(playArea2);
        PlayArea playArea3 = new PlayArea(3);
        playArea3.setHand(invalidHand);
        playerThree.setPlayArea(playArea3);
        PlayArea playArea4 = new PlayArea(4);
        playArea4.setHand(invalidHand);
        playerFour.setPlayArea(playArea4);

        //When
        ExhaustiveDraw.tenpaiPayout(turnQueue);

        //Then
        assertEquals(26500, playerOne.getPoints());
        assertEquals(26500, playerTwo.getPoints());
        assertEquals(23500, playerThree.getPoints());
        assertEquals(23500, playerFour.getPoints());
    }

    @Test
    public void testThreePlayersInTenpai() {
        //Given
        PlayArea playArea1 = new PlayArea(1);
        playArea1.setHand(validHand);
        playerOne.setPlayArea(playArea1);
        PlayArea playArea2 = new PlayArea(2);
        playArea2.setHand(validHand);
        playerTwo.setPlayArea(playArea2);
        PlayArea playArea3 = new PlayArea(3);
        playArea3.setHand(invalidHand);
        playerThree.setPlayArea(playArea3);
        PlayArea playArea4 = new PlayArea(4);
        playArea4.setHand(validHand);
        playerFour.setPlayArea(playArea4);

        //When
        ExhaustiveDraw.tenpaiPayout(turnQueue);

        //Then
        assertEquals(26000, playerOne.getPoints());
        assertEquals(26000, playerTwo.getPoints());
        assertEquals(22000, playerThree.getPoints());
        assertEquals(26000, playerFour.getPoints());
    }

    @Test
    public void testFourPlayersInTenpai() {
        //Given
        PlayArea playArea1 = new PlayArea(1);
        playArea1.setHand(validHand);
        playerOne.setPlayArea(playArea1);
        PlayArea playArea2 = new PlayArea(2);
        playArea2.setHand(validHand);
        playerTwo.setPlayArea(playArea2);
        PlayArea playArea3 = new PlayArea(3);
        playArea3.setHand(validHand);
        playerThree.setPlayArea(playArea3);
        PlayArea playArea4 = new PlayArea(4);
        playArea4.setHand(validHand);
        playerFour.setPlayArea(playArea4);

        //When
        ExhaustiveDraw.tenpaiPayout(turnQueue);

        //Then
        assertEquals(25000, playerOne.getPoints());
        assertEquals(25000, playerTwo.getPoints());
        assertEquals(25000, playerThree.getPoints());
        assertEquals(25000, playerFour.getPoints());
    }
}
