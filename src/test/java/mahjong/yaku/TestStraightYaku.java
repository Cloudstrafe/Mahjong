package mahjong.yaku;

import mahjong.Meld;
import mahjong.PlayArea;
import mahjong.Player;
import mahjong.SuitConstants;
import mahjong.tile.DragonTile;
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

public class TestStraightYaku {
    StraightYaku straightYaku;
    Player player;

    @Before
    public void setUp() {
        straightYaku = new StraightYaku();
        player = new Player(SuitConstants.WEST_WIND, false, 4);
    }

    @Test
    public void happyPathIsValid() {
        //Given
        Tile t1 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t3 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t4 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t5 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t6 = new NumberTile(3, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(5, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t12 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t13 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t14 = new NumberTile(9, SuitConstants.BAMBOO, false);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(straightYaku.isValid(player));
    }

    @Test
    public void handWithMeldsIsValid() {
        //Given
        Tile t1 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t3 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t4 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t5 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t6 = new NumberTile(3, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(5, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t12 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t13 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t14 = new NumberTile(9, SuitConstants.BAMBOO, false);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        List<Tile> tiles = new ArrayList<>(Arrays.asList(t1, t2, t3));
        Meld meld = new Meld(tiles, true, false, -1);
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        playArea.setMelds(Collections.singletonList(meld));
        player.setPlayArea(playArea);

        //Expect
        assertTrue(straightYaku.isValid(player));
    }

    @Test
    public void handWithQuadInRunIsValid() {
        //Given
        Tile t1 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
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
        Tile t14 = new NumberTile(9, SuitConstants.BAMBOO, false);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(straightYaku.isValid(player));
    }

    @Test
    public void handWithPartOfStraightInKanIsNotValid() {
        //Given
        Tile t1 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
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
        Tile t14 = new NumberTile(9, SuitConstants.BAMBOO, false);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10));
        List<Tile> tiles = new ArrayList<>(Arrays.asList(t11, t12, t13, t14));
        Meld meld = new Meld(tiles, true, false, 3);
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        playArea.setMelds(Collections.singletonList(meld));
        player.setPlayArea(playArea);

        //Expect
        assertFalse(straightYaku.isValid(player));
    }
}
