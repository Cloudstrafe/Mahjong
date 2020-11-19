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

import static org.junit.Assert.*;

public class TestHalfFlushYaku {
    HalfFlushYaku halfFlushYaku;
    Player player;

    @Before
    public void setUp() {
        halfFlushYaku = new HalfFlushYaku();
        player = new Player(SuitConstants.WEST_WIND, false, 4);
    }

    @Test
    public void happyPathGetters() {
        //Expect
        assertFalse(halfFlushYaku.isMangan());
        assertEquals(3, halfFlushYaku.getClosedPoints());
        assertEquals(2, halfFlushYaku.getOpenPoints());
        assertEquals(0, halfFlushYaku.getCurrentPoints(player));
        assertFalse(halfFlushYaku.isStackable());
        assertFalse(halfFlushYaku.isYakuman());
        assertFalse(halfFlushYaku.isDoubleYakuman());
    }

    @Test
    public void happyPathIsValid() {
        //Given
        Tile t1 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t3 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t4 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t5 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.WHITE_DRAGON);
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
        assertTrue(halfFlushYaku.isValid(player));
    }

    @Test
    public void handWithMeldsIsValid() {
        //Given
        Tile t1 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t3 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t4 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t5 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t12 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t13 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t14 = new NumberTile(9, SuitConstants.BAMBOO, false);

        List<Tile> tiles = new ArrayList<>(Arrays.asList(t1, t2, t3));
        Meld meld = new Meld(tiles, true, false, -1);
        List<Tile> tilesMeld2 = new ArrayList<>(Arrays.asList(t4, t5, t6));
        Meld meld2 = new Meld(tilesMeld2, true, false, -1);
        List<Meld> melds = new ArrayList<>(Arrays.asList(meld, meld2));
        List<Tile> hand = new ArrayList<>(Arrays.asList(t7, t8, t9, t10, t11, t12, t13, t14));

        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        playArea.setMelds(melds);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(halfFlushYaku.isValid(player));
    }

    @Test
    public void handWithMultipleNumberTileSuitsIsNotValid() {
        //Given
        Tile t1 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t3 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t4 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t5 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t7 = new NumberTile(6, SuitConstants.DOTS, false);
        Tile t8 = new NumberTile(6, SuitConstants.DOTS, false);
        Tile t9 = new NumberTile(6, SuitConstants.DOTS, false);
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
        assertFalse(halfFlushYaku.isValid(player));
    }

    @Test
    public void handWithoutFourRunsOrSetsIsNotValid() {
        //Given
        Tile t1 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t3 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t4 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t5 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t7 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(5, SuitConstants.BAMBOO, false);
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
        assertFalse(halfFlushYaku.isValid(player));
    }

    @Test
    public void handWithOnlyHonorsIsNotValid() {
        //Given
        Tile t1 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t3 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t4 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t5 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t7 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t8 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t9 = new WindTile(SuitConstants.EAST_WIND);
        Tile t10 = new WindTile(SuitConstants.EAST_WIND);
        Tile t11 = new WindTile(SuitConstants.EAST_WIND);
        Tile t12 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t13 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t14 = new WindTile(SuitConstants.SOUTH_WIND);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(halfFlushYaku.isValid(player));
    }
}
