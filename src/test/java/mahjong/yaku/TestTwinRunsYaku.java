package mahjong.yaku;

import mahjong.Meld;
import mahjong.PlayArea;
import mahjong.Player;
import mahjong.SuitConstants;
import mahjong.tile.NumberTile;
import mahjong.tile.Tile;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestTwinRunsYaku {
    TwinRunsYaku twinRunsYaku;
    Player player;

    @Before
    public void setUp() {
        twinRunsYaku = new TwinRunsYaku();
        player = new Player(SuitConstants.WEST_WIND, false, 4);
    }

    @Test
    public void happyPathGetters() {
        //Expect
        assertFalse(twinRunsYaku.isMangan());
        assertEquals(1, twinRunsYaku.getClosedPoints());
        assertEquals(0, twinRunsYaku.getOpenPoints());
        assertEquals(0, twinRunsYaku.getCurrentPoints(player));
        assertFalse(twinRunsYaku.isStackable());
        assertFalse(twinRunsYaku.isYakuman());
        assertFalse(twinRunsYaku.isDoubleYakuman());
    }

    @Test
    public void happyPathIsValid() {
        //Given
        Tile t1 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(3, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t10 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t11 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t12 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t13 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t14 = new NumberTile(8, SuitConstants.BAMBOO, false);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));

        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(twinRunsYaku.isValid(player));
    }

    @Test
    public void handWithMeldsIsNotValid() {
        Tile t1 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(3, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(6, SuitConstants.DOTS, false);
        Tile t8 = new NumberTile(6, SuitConstants.DOTS, false);
        Tile t9 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t10 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t11 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t12 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t13 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t14 = new NumberTile(8, SuitConstants.BAMBOO, false);

        List<Tile> tiles = new ArrayList<>(Arrays.asList(t1, t2, t3));
        Meld meld = new Meld(tiles, true, false, -1);
        List<Tile> tilesMeld2 = new ArrayList<>(Arrays.asList(t4, t5, t6));
        Meld meld2 = new Meld(tilesMeld2, true, true, -1);
        List<Meld> melds = new ArrayList<>(Arrays.asList(meld, meld2));
        List<Tile> hand = new ArrayList<>(Arrays.asList(t7, t8, t9, t10, t11, t12, t13, t14));

        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        playArea.setMelds(melds);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(twinRunsYaku.isValid(player));
    }

    public void handWithoutTwinRunIsNotValid() {
        //Given
        Tile t1 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(3, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t4 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t5 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t6 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t10 = new NumberTile(3, SuitConstants.DOTS, false);
        Tile t11 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t12 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t13 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t14 = new NumberTile(4, SuitConstants.CHARACTERS, false);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));

        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(twinRunsYaku.isValid(player));
    }

    @Test
    public void handWithTwinRunButNotFourSetsOrRunsIsNotValid() {
        //Given
        Tile t1 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t4 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t5 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t6 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t10 = new NumberTile(3, SuitConstants.DOTS, false);
        Tile t11 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t12 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t13 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t14 = new NumberTile(4, SuitConstants.CHARACTERS, false);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));

        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(twinRunsYaku.isValid(player));
    }
}
