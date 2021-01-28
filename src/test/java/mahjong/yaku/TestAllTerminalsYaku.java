package mahjong.yaku;

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
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestAllTerminalsYaku {
    AllTerminalsYaku allTerminalsYaku;
    Player player;

    @Before
    public void setUp() {
        allTerminalsYaku = new AllTerminalsYaku();
        player = new Player(SuitConstants.WEST_WIND, false, 4);
    }

    @Test
    public void happyPathIsValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t4 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t8 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t9 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t10 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t12 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t13 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        Tile t14 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(allTerminalsYaku.isValid(player));
    }

    @Test
    public void handWithNonTerminalTilesIsNotValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t4 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t8 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t9 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t10 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t12 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(allTerminalsYaku.isValid(player));
    }

    @Test
    public void allTerminalsButNotFiveUniqueTileTypesIsNotValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t4 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t8 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t9 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t10 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t12 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t13 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t14 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(allTerminalsYaku.isValid(player));
    }

    @Test
    public void allTerminalsButNoPairIsNotValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t4 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t8 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t9 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t10 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t12 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t13 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t14 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(allTerminalsYaku.isValid(player));
    }
}
