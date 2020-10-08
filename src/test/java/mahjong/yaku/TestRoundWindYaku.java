package mahjong.yaku;

import mahjong.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestRoundWindYaku {
    RoundWindYaku roundWindYaku;
    Player player;

    @Before
    public void setUp() {
        roundWindYaku = new RoundWindYaku();
        player = new Player(SuitConstants.WEST_WIND, false, 4, "a");
        RoundWindYaku.setRoundWind(SuitConstants.EAST_WIND);
    }

    @Test
    public void happyPathGetters() {
        //Expect
        assertFalse(roundWindYaku.isMangan());
        assertEquals(1, roundWindYaku.getClosedPoints());
        assertEquals(1, roundWindYaku.getOpenPoints());
        assertEquals(0, roundWindYaku.getCurrentPoints(player));
        assertFalse(roundWindYaku.isStackable());
        assertFalse(roundWindYaku.isYakuman());
        assertFalse(roundWindYaku.isDoubleYakuman());
    }

    @Test
    public void happyPathSetInHandIsValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t4 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t5 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t7 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t8 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t9 = new WindTile(SuitConstants.EAST_WIND);
        Tile t10 = new WindTile(SuitConstants.EAST_WIND);
        Tile t11 = new WindTile(SuitConstants.EAST_WIND);
        Tile t12 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        PlayArea playArea = new PlayArea();
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(roundWindYaku.isValid(player));
    }

    @Test
    public void happyPathSetInMeldsIsValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t4 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t5 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t7 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t8 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t9 = new WindTile(SuitConstants.EAST_WIND);
        Tile t10 = new WindTile(SuitConstants.EAST_WIND);
        Tile t11 = new WindTile(SuitConstants.EAST_WIND);
        Tile t12 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t12, t13, t14));
        PlayArea playArea = new PlayArea();
        List<Tile> meldTiles = new ArrayList<>(Arrays.asList(t9, t10, t11));
        Meld meld = new Meld(meldTiles, true,false);
        playArea.setHand(hand);
        playArea.getMelds().add(meld);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(roundWindYaku.isValid(player));
    }

    @Test
    public void handOrMeldsWithoutSeatWindSetIsNotValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t2 = new NumberTile(1, SuitConstants.BAMBOO, false);
        Tile t3 = new NumberTile(1, SuitConstants.BAMBOO, false);
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
        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t12, t13, t14));
        PlayArea playArea = new PlayArea();
        List<Tile> meldTiles = new ArrayList<>(Arrays.asList(t9, t10, t11));
        Meld meld = new Meld(meldTiles, true,false);
        playArea.setHand(hand);
        playArea.getMelds().add(meld);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(roundWindYaku.isValid(player));
    }
}
