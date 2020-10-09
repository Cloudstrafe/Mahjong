package mahjong.yaku;

import mahjong.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestAllSimplesYaku {
    AllSimplesYaku allSimplesYaku;
    Player player;

    @Before
    public void setUp() {
        allSimplesYaku = new AllSimplesYaku();
        player = new Player(SuitConstants.WEST_WIND, false, 4, "a");
    }

    @Test
    public void happyPathGetters() {
        //Expect
        assertFalse(allSimplesYaku.isMangan());
        assertEquals(1, allSimplesYaku.getClosedPoints());
        assertEquals(1, allSimplesYaku.getOpenPoints());
        assertEquals(0, allSimplesYaku.getCurrentPoints(player));
        assertTrue(allSimplesYaku.isStackable());
        assertFalse(allSimplesYaku.isYakuman());
        assertFalse(allSimplesYaku.isDoubleYakuman());
    }

    @Test
    public void happyPathIsValid() {
        //Given
        Tile t1 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(2, SuitConstants.BAMBOO, false);
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

        List<Tile> tiles = new ArrayList<>(Arrays.asList(t1, t2, t3));
        Meld meld = new Meld(tiles, true, false);
        List<Tile> tilesMeld2 = new ArrayList<>(Arrays.asList(t4, t5, t6));
        Meld meld2 = new Meld(tilesMeld2, true, true);
        List<Meld> melds = new ArrayList<>(Arrays.asList(meld, meld2));
        List<Tile> hand = new ArrayList<>(Arrays.asList(t7, t8, t9, t10, t11, t12, t13, t14));

        PlayArea playArea = new PlayArea();
        playArea.setHand(hand);
        playArea.setMelds(melds);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(allSimplesYaku.isValid(player));
    }

    @Test
    public void handWithNonSimplesIsNotValid() {
        //Given
        Tile t1 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t4 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t5 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t7 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t8 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t9 = new WindTile(SuitConstants.WEST_WIND);
        Tile t10 = new WindTile(SuitConstants.WEST_WIND);
        Tile t11 = new WindTile(SuitConstants.WEST_WIND);
        Tile t12 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea();
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(allSimplesYaku.isValid(player));
    }

    @Test
    public void meldWithNonSimplesIsNotValid() {
        //Given
        Tile t1 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(3, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t10 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t11 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t12 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t13 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t14 = new NumberTile(1, SuitConstants.CHARACTERS, false);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11));
        List<Tile> meldTiles = new ArrayList<>(Arrays.asList(t12, t13, t14));
        Meld meld = new Meld(meldTiles, true,false);
        PlayArea playArea = new PlayArea();
        playArea.setHand(hand);
        playArea.getMelds().add(meld);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(allSimplesYaku.isValid(player));
    }

    @Test
    public void handWithoutAPairIsNotValid() {
        //Given
        Tile t1 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(3, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(4, SuitConstants.DOTS, false);
        List<Tile> tiles = new ArrayList<>(Arrays.asList(t1, t2, t3));
        List<Tile> tilesMeld2 = new ArrayList<>(Arrays.asList(t4, t5, t6));
        Meld meld = new Meld(tiles, true, false);
        Meld meld2 = new Meld(tilesMeld2, true, true);
        List<Meld> melds = new ArrayList<>(Arrays.asList(meld, meld2));

        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t10 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t11 = new NumberTile(4, SuitConstants.CHARACTERS, false);
        Tile t12 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t13 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t14 = new NumberTile(8, SuitConstants.BAMBOO, false);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t7, t8, t9, t10, t11, t12, t13, t14));

        PlayArea playArea = new PlayArea();
        playArea.setHand(hand);
        playArea.setMelds(melds);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(allSimplesYaku.isValid(player));
    }
}
