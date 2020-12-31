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

public class TestThreeKansYaku {
    ThreeKansYaku threeKansYaku;
    Player player;

    @Before
    public void setUp() {
        threeKansYaku = new ThreeKansYaku();
        player = new Player(SuitConstants.WEST_WIND, false, 4);
    }

    @Test
    public void happyPathGetters() {
        //Expect
        assertFalse(threeKansYaku.isMangan());
        assertEquals(2, threeKansYaku.getClosedPoints());
        assertEquals(2, threeKansYaku.getOpenPoints());
        assertEquals(0, threeKansYaku.getCurrentPoints(player));
        assertFalse(threeKansYaku.isStackable());
        assertFalse(threeKansYaku.isYakuman());
        assertFalse(threeKansYaku.isDoubleYakuman());
    }

    @Test
    public void happyPathIsValid() {
        //Given
        Tile t1 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t3 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t4 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t5 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t8 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t9 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        Tile t10 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        Tile t11 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        Tile t12 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        Tile t13 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t14 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t15 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t16 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t17 = new NumberTile(2, SuitConstants.BAMBOO, false);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t13, t14, t15, t16, t17));
        List<Tile> tiles1 = new ArrayList<>(Arrays.asList(t5, t6, t7, t8));
        Meld meld1 = new Meld(tiles1, false, false, -1);
        List<Tile> tiles2 = new ArrayList<>(Arrays.asList(t9, t10, t11, t12));
        Meld meld2 = new Meld(tiles2, false, false, -1);
        List<Tile> tiles3 = new ArrayList<>(Arrays.asList(t1, t2, t3, t4));
        Meld meld3 = new Meld(tiles3, true, false, 3);
        List<Meld> melds = new ArrayList<>(Arrays.asList(meld1, meld2, meld3));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        playArea.setMelds(melds);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(threeKansYaku.isValid(player));  //Failing due to kan in hand only beeing seen as a triplet
    }

    @Test
    public void KanInHandIsNotValid() {
        //Given
        Tile t1 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t3 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t4 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t5 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t8 = new NumberTile(9, SuitConstants.DOTS, false);
        Tile t9 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        Tile t10 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        Tile t11 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        Tile t12 = new NumberTile(9, SuitConstants.CHARACTERS, false);
        Tile t13 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t14 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t15 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t16 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t17 = new NumberTile(2, SuitConstants.BAMBOO, false);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t13, t14, t15, t16, t17));
        List<Tile> tiles1 = new ArrayList<>(Arrays.asList(t5, t6, t7, t8));
        Meld meld1 = new Meld(tiles1, false, false, -1);
        List<Tile> tiles2 = new ArrayList<>(Arrays.asList(t9, t10, t11, t12));
        Meld meld2 = new Meld(tiles2, false, false, -1);
        List<Meld> melds = new ArrayList<>(Arrays.asList(meld1, meld2));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        playArea.setMelds(melds);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(threeKansYaku.isValid(player));  //Failing due to kan in hand only beeing seen as a triplet
    }
}
