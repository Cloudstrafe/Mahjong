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
import java.util.List;

import static org.junit.Assert.*;

public class TestBigThreeDragonsYaku {
    BigThreeDragonsYaku bigThreeDragonsYaku;
    Player player;

    @Before
    public void setUp() {
        bigThreeDragonsYaku = new BigThreeDragonsYaku();
        player = new Player(SuitConstants.WEST_WIND, false, 1);
    }

    @Test
    public void happyPathGetters() {
        //Expect
        assertFalse(bigThreeDragonsYaku.isMangan());
        assertEquals(0, bigThreeDragonsYaku.getClosedPoints());
        assertEquals(0, bigThreeDragonsYaku.getOpenPoints());
        assertEquals(0, bigThreeDragonsYaku.getCurrentPoints(player));
        assertFalse(bigThreeDragonsYaku.isStackable());
        assertTrue(bigThreeDragonsYaku.isYakuman());
        assertFalse(bigThreeDragonsYaku.isDoubleYakuman());
    }

    @Test
    public void happyPathIsValid() {
        //Given
        Tile t1 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t3 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t4 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t5 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t7 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t8 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t9 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t10 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t11 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t12 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t13 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t14 = new NumberTile(3, SuitConstants.CHARACTERS, false);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t4, t5, t6, t7, t8, t9, t10, t11));
        List<Tile> tiles1 = new ArrayList<>(Arrays.asList(t1, t2, t3));
        Meld meld1 = new Meld(tiles1, true, false, 1);
        List<Tile> tiles2 = new ArrayList<>(Arrays.asList(t12, t13, t14));
        Meld meld2 = new Meld(tiles2, true, true, 1);
        List<Meld> melds = new ArrayList<>(Arrays.asList(meld1, meld2));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        playArea.setMelds(melds);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(bigThreeDragonsYaku.isValid(player));
    }

    @Test
    public void invalidHandWithThreeDragonTripletsIsNotValid() {
        //Given
        Tile t1 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t3 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t4 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t5 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t7 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t8 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t9 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t10 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t11 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t12 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t13 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t14 = new NumberTile(3, SuitConstants.CHARACTERS, false);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t4, t5, t6, t7, t8, t9, t10, t11));
        List<Tile> tiles1 = new ArrayList<>(Arrays.asList(t1, t2, t3));
        Meld meld1 = new Meld(tiles1, true, false, 1);
        List<Tile> tiles2 = new ArrayList<>(Arrays.asList(t12, t13, t14));
        Meld meld2 = new Meld(tiles2, true, true, 1);
        List<Meld> melds = new ArrayList<>(Arrays.asList(meld1, meld2));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        playArea.setMelds(melds);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(bigThreeDragonsYaku.isValid(player));
    }

    @Test
    public void handWithoutThreeDragonTripletsIsNotValid() {
        //Given
        Tile t1 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t3 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t4 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t5 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t7 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t8 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t9 = new NumberTile(1, SuitConstants.DOTS, false);
        Tile t10 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t11 = new NumberTile(3, SuitConstants.DOTS, false);
        Tile t12 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t13 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t14 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(bigThreeDragonsYaku.isValid(player));
    }
}
