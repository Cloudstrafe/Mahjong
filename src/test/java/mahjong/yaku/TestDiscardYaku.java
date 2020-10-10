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

public class TestDiscardYaku {
    DiscardYaku discardYaku;
    Player player;

    @Before
    public void setUp() {
        discardYaku = new DiscardYaku();
        player = new Player(SuitConstants.WEST_WIND, false, 4, "a");
    }

    @Test
    public void happyPathGetters() {
        //Expect
        assertTrue(discardYaku.isMangan());
        assertEquals(0, discardYaku.getClosedPoints());
        assertEquals(0, discardYaku.getOpenPoints());
        assertEquals(0, discardYaku.getCurrentPoints(player));
        assertFalse(discardYaku.isStackable());
        assertFalse(discardYaku.isYakuman());
        assertFalse(discardYaku.isDoubleYakuman());
    }

    @Test
    public void happyPathIsValid() {
        Tile t1 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t3 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t4 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t5 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t7 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t8 = new WindTile(SuitConstants.WEST_WIND);
        Tile t9 = new WindTile(SuitConstants.WEST_WIND);
        Tile t10 = new WindTile(SuitConstants.WEST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);
        List<Tile> discard = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea();
        playArea.setDiscard(discard);

        Tile t15 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t16 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t17 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t18 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t19 = new NumberTile(3, SuitConstants.DOTS, false);
        Tile t20 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t21 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t22 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t23 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t24 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t25 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t26 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t27 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t28 = new NumberTile(8, SuitConstants.BAMBOO, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28));
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(discardYaku.isValid(player));
    }

    @Test
    public void discardWithSimplesIsNotValid() {
        Tile t1 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t3 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t4 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t5 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t7 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t8 = new WindTile(SuitConstants.WEST_WIND);
        Tile t9 = new WindTile(SuitConstants.WEST_WIND);
        Tile t10 = new WindTile(SuitConstants.WEST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new NumberTile(2, SuitConstants.BAMBOO, false);
        List<Tile> discard = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea();
        playArea.setDiscard(discard);

        Tile t15 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t16 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t17 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t18 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t19 = new NumberTile(3, SuitConstants.DOTS, false);
        Tile t20 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t21 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t22 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t23 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t24 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t25 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t26 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t27 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t28 = new NumberTile(8, SuitConstants.BAMBOO, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28));
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(discardYaku.isValid(player));
    }
}
