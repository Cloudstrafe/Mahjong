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

public class TestBigFourWindsYaku {
    BigFourWindsYaku bigFourWindsYaku;
    Player player;

    @Before
    public void setUp() {
        bigFourWindsYaku = new BigFourWindsYaku();
        player = new Player(SuitConstants.WEST_WIND, false, 4);
    }

    @Test
    public void happyPathIsValid() {
        //Given
        Tile t1 = new WindTile(SuitConstants.WEST_WIND);
        Tile t2 = new WindTile(SuitConstants.WEST_WIND);
        Tile t3 = new WindTile(SuitConstants.WEST_WIND);
        Tile t4 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t5 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t6 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t7 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t8 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t9 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t10 = new WindTile(SuitConstants.EAST_WIND);
        Tile t11 = new WindTile(SuitConstants.EAST_WIND);
        Tile t12 = new WindTile(SuitConstants.EAST_WIND);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(bigFourWindsYaku.isValid(player));
    }

    @Test
    public void handMissingWindsIsNotValid() {
        //Given
        Tile t1 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t4 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t5 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t6 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t7 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t8 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t9 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t10 = new WindTile(SuitConstants.EAST_WIND);
        Tile t11 = new WindTile(SuitConstants.EAST_WIND);
        Tile t12 = new WindTile(SuitConstants.EAST_WIND);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(bigFourWindsYaku.isValid(player));
    }

    @Test
    public void handWithoutFiveUniqueTileTypesIsNotValid() {
        //Given
        Tile t1 = new WindTile(SuitConstants.WEST_WIND);
        Tile t2 = new WindTile(SuitConstants.WEST_WIND);
        Tile t3 = new WindTile(SuitConstants.WEST_WIND);
        Tile t4 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t5 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t6 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t7 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t8 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t9 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t10 = new WindTile(SuitConstants.EAST_WIND);
        Tile t11 = new WindTile(SuitConstants.EAST_WIND);
        Tile t12 = new WindTile(SuitConstants.EAST_WIND);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new NumberTile(2, SuitConstants.BAMBOO, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(bigFourWindsYaku.isValid(player));
    }

    @Test
    public void handWithNoPairIsNotValid() {
        //Given
        Tile t1 = new WindTile(SuitConstants.WEST_WIND);
        Tile t2 = new WindTile(SuitConstants.WEST_WIND);
        Tile t3 = new WindTile(SuitConstants.WEST_WIND);
        Tile t4 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t5 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t6 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t7 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t8 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t9 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t10 = new WindTile(SuitConstants.EAST_WIND);
        Tile t11 = new WindTile(SuitConstants.EAST_WIND);
        Tile t12 = new WindTile(SuitConstants.EAST_WIND);
        Tile t13 = new WindTile(SuitConstants.EAST_WIND);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(bigFourWindsYaku.isValid(player));
    }

    @Test
    public void handWithAPairOfWindsIsNotValid() {
        //Given
        Tile t1 = new WindTile(SuitConstants.WEST_WIND);
        Tile t2 = new WindTile(SuitConstants.WEST_WIND);
        Tile t3 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t4 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t5 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t6 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t7 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t8 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t9 = new WindTile(SuitConstants.EAST_WIND);
        Tile t10 = new WindTile(SuitConstants.EAST_WIND);
        Tile t11 = new WindTile(SuitConstants.EAST_WIND);
        Tile t12 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(bigFourWindsYaku.isValid(player));
    }
}
