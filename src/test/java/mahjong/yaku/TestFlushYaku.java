package mahjong.yaku;

import mahjong.Meld;
import mahjong.PlayArea;
import mahjong.Player;
import mahjong.SuitConstants;
import mahjong.tile.NumberTile;
import mahjong.tile.Tile;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestFlushYaku {
    FlushYaku flushYaku;
    Player player;

    @Before
    public void setUp() {
        flushYaku = new FlushYaku();
        player = new Player(SuitConstants.WEST_WIND, false, 4);
    }

    @Test
    public void happyPathIsValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(3, SuitConstants.BAMBOO, false);
        Tile t4 = new NumberTile(5, SuitConstants.BAMBOO, false);
        Tile t5 = new NumberTile(5, SuitConstants.BAMBOO, false);
        Tile t6 = new NumberTile(5, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t12 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t13 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t14 = new NumberTile(9, SuitConstants.BAMBOO, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(flushYaku.isValid(player));
    }

    @Test
    public void handWithMeldsIsValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(3, SuitConstants.BAMBOO, false);
        Tile t4 = new NumberTile(5, SuitConstants.BAMBOO, false);
        Tile t5 = new NumberTile(5, SuitConstants.BAMBOO, false);
        Tile t6 = new NumberTile(5, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t12 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t13 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t14 = new NumberTile(9, SuitConstants.BAMBOO, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        List<Tile> tiles = new ArrayList<>(Arrays.asList(t1, t2, t3));
        Meld meld = new Meld(tiles, true, false, 2);
        PlayArea playArea = new PlayArea(1);
        playArea.setMelds(Collections.singletonList(meld));
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(flushYaku.isValid(player));
    }

    @Test
    public void handWithTwoSuitsIsNotValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t2 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t3 = new NumberTile(3, SuitConstants.DOTS, false);
        Tile t4 = new NumberTile(5, SuitConstants.BAMBOO, false);
        Tile t5 = new NumberTile(5, SuitConstants.BAMBOO, false);
        Tile t6 = new NumberTile(5, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t12 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t13 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t14 = new NumberTile(9, SuitConstants.BAMBOO, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        List<Tile> tiles = new ArrayList<>(Arrays.asList(t1, t2, t3));
        Meld meld = new Meld(tiles, true, false, 2);
        PlayArea playArea = new PlayArea(1);
        playArea.setMelds(Collections.singletonList(meld));
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(flushYaku.isValid(player));
    }

    @Test
    public void handWithoutFourSetsOrRunsIsNotValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t4 = new NumberTile(5, SuitConstants.BAMBOO, false);
        Tile t5 = new NumberTile(5, SuitConstants.BAMBOO, false);
        Tile t6 = new NumberTile(5, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t12 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t13 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t14 = new NumberTile(9, SuitConstants.BAMBOO, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(flushYaku.isValid(player));
    }
}
