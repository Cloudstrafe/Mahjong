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

import static org.junit.Assert.*;

public class TestAllTerminalsAndHonorsYaku {
    AllTerminalsAndHonorsYaku allTerminalsAndHonorsYaku;
    Player player;

    @Before
    public void setUp() {
        allTerminalsAndHonorsYaku = new AllTerminalsAndHonorsYaku();
        player = new Player(SuitConstants.WEST_WIND, false, 4);
    }

    @Test
    public void happyPathGetters() {
        //Expect
        assertFalse(allTerminalsAndHonorsYaku.isMangan());
        assertEquals(2, allTerminalsAndHonorsYaku.getClosedPoints());
        assertEquals(2, allTerminalsAndHonorsYaku.getOpenPoints());
        assertEquals(0, allTerminalsAndHonorsYaku.getCurrentPoints(player));
        assertFalse(allTerminalsAndHonorsYaku.isStackable());
        assertFalse(allTerminalsAndHonorsYaku.isYakuman());
        assertFalse(allTerminalsAndHonorsYaku.isDoubleYakuman());
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
        Tile t7 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t8 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t9 = new WindTile(SuitConstants.WEST_WIND);
        Tile t10 = new WindTile(SuitConstants.WEST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(allTerminalsAndHonorsYaku.isValid(player));
    }

    @Test
    public void handWithSimplesIsNotValid() {
        //Given
        Tile t1 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t4 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t7 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t8 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t9 = new WindTile(SuitConstants.WEST_WIND);
        Tile t10 = new WindTile(SuitConstants.WEST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(allTerminalsAndHonorsYaku.isValid(player));
    }

    @Test
    public void handWithoutFiveDistinctTypesIsNotValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t4 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t7 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t8 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t9 = new WindTile(SuitConstants.WEST_WIND);
        Tile t10 = new WindTile(SuitConstants.WEST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(allTerminalsAndHonorsYaku.isValid(player));
    }

    @Test
    public void handWithoutPairIsNotValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t4 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t7 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t8 = new WindTile(SuitConstants.WEST_WIND);
        Tile t9 = new WindTile(SuitConstants.WEST_WIND);
        Tile t10 = new WindTile(SuitConstants.WEST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(allTerminalsAndHonorsYaku.isValid(player));
    }
}
