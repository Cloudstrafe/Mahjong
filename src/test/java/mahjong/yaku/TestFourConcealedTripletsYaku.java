package mahjong.yaku;

import mahjong.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestFourConcealedTripletsYaku {
    FourConcealedTripletsYaku fourConcealedTripletsYaku;
    Player player;

    @Before
    public void setUp() {
        fourConcealedTripletsYaku = new FourConcealedTripletsYaku();
        player = new Player(SuitConstants.WEST_WIND, false, 4, "a");
    }

    @Test
    public void happyPathGetters() {
        //Expect
        assertFalse(fourConcealedTripletsYaku.isMangan());
        assertEquals(0, fourConcealedTripletsYaku.getClosedPoints());
        assertEquals(0, fourConcealedTripletsYaku.getOpenPoints());
        assertEquals(0, fourConcealedTripletsYaku.getCurrentPoints(player));
        assertFalse(fourConcealedTripletsYaku.isStackable());
        assertTrue(fourConcealedTripletsYaku.isYakuman());
        assertFalse(fourConcealedTripletsYaku.isDoubleYakuman());
    }

    @Test
    public void happyPathIsValid() {
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
        assertTrue(fourConcealedTripletsYaku.isValid(player));
    }

    @Test
    public void handWithRunIsNotValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(3, SuitConstants.BAMBOO, false);
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
        assertFalse(fourConcealedTripletsYaku.isValid(player));
    }

    @Test
    public void handWithMeldIsNotValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(3, SuitConstants.BAMBOO, false);
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
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11));
        List<Tile> meldTiles = new ArrayList<>(Arrays.asList(t12, t13, t14));
        Meld meld = new Meld(meldTiles, true,false);
        PlayArea playArea = new PlayArea();
        playArea.setHand(hand);
        playArea.getMelds().add(meld);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(fourConcealedTripletsYaku.isValid(player));
    }

    @Test
    public void handWithoutPairIsNotValid() {
        //Given
        Tile t1 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t4 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t5 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t7 = new DragonTile(SuitConstants.GREEN_DRAGON);
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
        assertFalse(fourConcealedTripletsYaku.isValid(player));
    }
}
