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
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestNineGatesYaku {
    NineGatesYaku nineGatesYaku;
    Player player;

    @Before
    public void setUp() {
        nineGatesYaku = new NineGatesYaku();
        player = new Player(SuitConstants.WEST_WIND, false, 1);
    }

    @Test
    public void happyPathIsValid() {
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t4 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t5 = new NumberTile(3, SuitConstants.BAMBOO, false);
        Tile t6 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(5, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t12 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t13 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t14 = new NumberTile(3, SuitConstants.BAMBOO, false);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(nineGatesYaku.isValid(player));
    }

    @Test
    public void handWithMeldsIsNotValid() {
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t4 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t5 = new NumberTile(3, SuitConstants.BAMBOO, false);
        Tile t6 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(5, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t12 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t13 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t14 = new NumberTile(3, SuitConstants.BAMBOO, false);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t6, t7, t8, t9, t10, t14));
        List<Tile> tiles1 = new ArrayList<>(Arrays.asList(t3, t4, t5));
        Meld meld1 = new Meld(tiles1, true, false, 1);
        List<Tile> tiles2 = new ArrayList<>(Arrays.asList(t11, t12, t13));
        Meld meld2 = new Meld(tiles2, false, true, -1);
        List<Meld> melds = new ArrayList<>(Arrays.asList(meld1, meld2));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        playArea.setMelds(melds);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(nineGatesYaku.isValid(player));
    }

    @Test
    public void handWithVaryingSuitIsNotValid() {
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t4 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t5 = new NumberTile(3, SuitConstants.BAMBOO, false);
        Tile t6 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(5, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t12 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t13 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t14 = new NumberTile(3, SuitConstants.BAMBOO, false);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(nineGatesYaku.isValid(player));
    }
}
