package mahjong.yaku;

import mahjong.Meld;
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

public class TestLittleFourWindsYaku {
    LittleFourWindsYaku littleFourWindsYaku;
    Player player;

    @Before
    public void setUp() {
        littleFourWindsYaku = new LittleFourWindsYaku();
        player = new Player(SuitConstants.WEST_WIND, false, 1);
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
        Tile t12 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t4, t5, t6, t7, t8, t9, t10, t11));
        List<Tile> tiles1 = new ArrayList<>(Arrays.asList(t1, t2, t3));
        Meld meld1 = new Meld(tiles1, true, false, 1);
        List<Tile> tiles2 = new ArrayList<>(Arrays.asList(t12, t13, t14));
        Meld meld2 = new Meld(tiles2, false, true, -1);
        List<Meld> melds = new ArrayList<>(Arrays.asList(meld1, meld2));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        playArea.setMelds(melds);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(littleFourWindsYaku.isValid(player));
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
        assertFalse(littleFourWindsYaku.isValid(player));
    }

    @Test
    public void handWithFourBigWindsIsNotValid() {
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
        assertFalse(littleFourWindsYaku.isValid(player));
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
        Tile t12 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new NumberTile(2, SuitConstants.BAMBOO, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(littleFourWindsYaku.isValid(player));
    }
}
