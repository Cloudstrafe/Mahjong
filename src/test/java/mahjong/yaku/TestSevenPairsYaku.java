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

public class TestSevenPairsYaku {
    SevenPairsYaku sevenPairsYaku;
    Player player;

    @Before
    public void setUp() {
        sevenPairsYaku = new SevenPairsYaku();
        player = new Player(SuitConstants.WEST_WIND, false, 4, "a");
    }

    @Test
    public void happyPathGetters() {
        //Expect
        assertFalse(sevenPairsYaku.isMangan());
        assertEquals(2, sevenPairsYaku.getClosedPoints());
        assertEquals(0, sevenPairsYaku.getOpenPoints());
        assertEquals(0, sevenPairsYaku.getCurrentPoints(player));
        assertTrue(sevenPairsYaku.isStackable());
        assertFalse(sevenPairsYaku.isYakuman());
        assertFalse(sevenPairsYaku.isDoubleYakuman());
    }

    @Test
    public void happyPathFourPointsGetCurrentPoints() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t4 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t5 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t7 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t8 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t9 = new WindTile(SuitConstants.EAST_WIND);
        Tile t10 = new WindTile(SuitConstants.EAST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new WindTile(SuitConstants.WEST_WIND);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertEquals(4,sevenPairsYaku.getCurrentPoints(player));
    }

    @Test
    public void happyPathTwoPointsGetCurrentPoints() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t4 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t5 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t7 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t8 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t9 = new WindTile(SuitConstants.EAST_WIND);
        Tile t10 = new WindTile(SuitConstants.EAST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new WindTile(SuitConstants.WEST_WIND);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertEquals(2,sevenPairsYaku.getCurrentPoints(player));
    }

    @Test
    public void handWithMeldsIsNotValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t4 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t5 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t7 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t8 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t9 = new WindTile(SuitConstants.EAST_WIND);
        Tile t10 = new WindTile(SuitConstants.EAST_WIND);
        Tile t11 = new WindTile(SuitConstants.EAST_WIND);
        Tile t12 = new WindTile(SuitConstants.WEST_WIND);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        List<Tile> meldTiles = new ArrayList<>(Arrays.asList(t9, t10, t11));
        Meld meld = new Meld(meldTiles, true,false, -1);
        playArea.setHand(hand);
        playArea.getMelds().add(meld);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(sevenPairsYaku.isValid(player));
    }

    @Test
    public void handWithoutSevenPairsIsNotValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t4 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t5 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t7 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t8 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t9 = new WindTile(SuitConstants.EAST_WIND);
        Tile t10 = new WindTile(SuitConstants.EAST_WIND);
        Tile t11 = new WindTile(SuitConstants.EAST_WIND);
        Tile t12 = new WindTile(SuitConstants.WEST_WIND);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(sevenPairsYaku.isValid(player));
    }
}
