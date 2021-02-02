package mahjong.yaku;

import mahjong.PlayArea;
import mahjong.Player;
import mahjong.SuitConstants;
import mahjong.tile.DragonTile;
import mahjong.tile.NumberTile;
import mahjong.tile.Tile;
import mahjong.tile.WindTile;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestThirteenOrphansYaku {
    ThirteenOrphansYaku thirteenOrphansYaku;
    Player player;

    @Before
    public void setUp() {
        thirteenOrphansYaku = new ThirteenOrphansYaku();
        player = new Player(SuitConstants.WEST_WIND, false, 4);
    }

    @Test
    public void happyPathIsValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        Tile t5 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t7 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t8 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t9 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t10 = new WindTile(SuitConstants.EAST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t13 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(thirteenOrphansYaku.isValid(player));
    }

    @Test
    public void handWithNonSimpleTileIsNotValid() {
        //Given
        Tile t1 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        Tile t5 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t7 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t8 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t9 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t10 = new WindTile(SuitConstants.EAST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t13 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(thirteenOrphansYaku.isValid(player));
    }

    @Test
    public void handWithoutPairIsNotValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        Tile t5 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t7 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t8 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t9 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t10 = new WindTile(SuitConstants.EAST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t13 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t14 = new NumberTile(2, SuitConstants.BAMBOO, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(thirteenOrphansYaku.isValid(player));
    }
}
