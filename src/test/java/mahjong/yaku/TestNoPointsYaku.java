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
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestNoPointsYaku {
    private Player player;
    private PlayArea playArea;
    private NoPointsYaku noPointsYaku;

    @Before
    public void setup() {
        player = new Player(SuitConstants.EAST_WIND, true, 1);
        player.setFirstTurn(false);
        playArea = new PlayArea(1);
        noPointsYaku = new NoPointsYaku();
        NoPointsYaku.setRoundWind(SuitConstants.SOUTH_WIND);
    }

    @Test
    public void happyPathIsValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t5 = new NumberTile(3, SuitConstants.BAMBOO, false);
        Tile t6 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(5, SuitConstants.DOTS, false);
        Tile t11 = new NumberTile(6, SuitConstants.DOTS, false);
        Tile t12 = new NumberTile(7, SuitConstants.DOTS, false);
        Tile t13 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t14 = new WindTile(SuitConstants.NORTH_WIND);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t10, t11, t12, t13, t14, t9));
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(noPointsYaku.isValid(player));
    }

    @Test
    public void openHandIsNotValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t5 = new NumberTile(3, SuitConstants.BAMBOO, false);
        Tile t6 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(5, SuitConstants.DOTS, false);
        Tile t11 = new NumberTile(6, SuitConstants.DOTS, false);
        Tile t12 = new NumberTile(7, SuitConstants.DOTS, false);
        Tile t13 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t14 = new WindTile(SuitConstants.NORTH_WIND);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t13, t14, t9));
        List<Tile> meldTiles = new ArrayList<>(Arrays.asList(t10, t11, t12));
        Meld meld = new Meld(meldTiles, false, true, -1);
        playArea.setHand(hand);
        playArea.setMelds(Collections.singletonList(meld));
        player.setPlayArea(playArea);

        //Expect
        assertFalse(noPointsYaku.isValid(player));
    }

    @Test
    public void handWithDragonPairIsNotValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t5 = new NumberTile(3, SuitConstants.BAMBOO, false);
        Tile t6 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(5, SuitConstants.DOTS, false);
        Tile t11 = new NumberTile(6, SuitConstants.DOTS, false);
        Tile t12 = new NumberTile(7, SuitConstants.DOTS, false);
        Tile t13 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t14 = new DragonTile(SuitConstants.RED_DRAGON);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t10, t11, t12, t13, t14, t9));
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(noPointsYaku.isValid(player));
    }

    @Test
    public void handWithSeatWindPairIsNotValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t5 = new NumberTile(3, SuitConstants.BAMBOO, false);
        Tile t6 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(5, SuitConstants.DOTS, false);
        Tile t11 = new NumberTile(6, SuitConstants.DOTS, false);
        Tile t12 = new NumberTile(7, SuitConstants.DOTS, false);
        Tile t13 = new WindTile(SuitConstants.EAST_WIND);
        Tile t14 = new WindTile(SuitConstants.EAST_WIND);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t10, t11, t12, t13, t14, t9));
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(noPointsYaku.isValid(player));
    }

    @Test
    public void handWithRoundWindPairIsNotValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t5 = new NumberTile(3, SuitConstants.BAMBOO, false);
        Tile t6 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(5, SuitConstants.DOTS, false);
        Tile t11 = new NumberTile(6, SuitConstants.DOTS, false);
        Tile t12 = new NumberTile(7, SuitConstants.DOTS, false);
        Tile t13 = new WindTile(SuitConstants.SOUTH_WIND);
        Tile t14 = new WindTile(SuitConstants.SOUTH_WIND);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t10, t11, t12, t13, t14, t9));
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(noPointsYaku.isValid(player));
    }

    @Test
    public void handWithTripletIsNotValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t5 = new NumberTile(3, SuitConstants.BAMBOO, false);
        Tile t6 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(5, SuitConstants.DOTS, false);
        Tile t11 = new NumberTile(6, SuitConstants.DOTS, false);
        Tile t12 = new NumberTile(7, SuitConstants.DOTS, false);
        Tile t13 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t14 = new WindTile(SuitConstants.NORTH_WIND);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t4));
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(noPointsYaku.isValid(player));
    }

    @Test
    public void handWithPairWaitIsNotValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t5 = new NumberTile(3, SuitConstants.BAMBOO, false);
        Tile t6 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(5, SuitConstants.DOTS, false);
        Tile t11 = new NumberTile(6, SuitConstants.DOTS, false);
        Tile t12 = new NumberTile(7, SuitConstants.DOTS, false);
        Tile t13 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t14 = new WindTile(SuitConstants.NORTH_WIND);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(noPointsYaku.isValid(player));
    }

    @Test
    public void handWithMiddleWaitIsNotValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t5 = new NumberTile(3, SuitConstants.BAMBOO, false);
        Tile t6 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(7, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(8, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(9, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(5, SuitConstants.DOTS, false);
        Tile t11 = new NumberTile(6, SuitConstants.DOTS, false);
        Tile t12 = new NumberTile(7, SuitConstants.DOTS, false);
        Tile t13 = new WindTile(SuitConstants.NORTH_WIND);
        Tile t14 = new WindTile(SuitConstants.NORTH_WIND);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t2));
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertFalse(noPointsYaku.isValid(player));
    }

    @Test
    public void handWithPairOrOpenWaitIsValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t5 = new NumberTile(3, SuitConstants.BAMBOO, false);
        Tile t6 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(3, SuitConstants.DOTS, false);
        Tile t8 = new NumberTile(4, SuitConstants.DOTS, false);
        Tile t9 = new NumberTile(5, SuitConstants.DOTS, false);
        Tile t10 = new NumberTile(6, SuitConstants.DOTS, false);
        Tile t11 = new NumberTile(6, SuitConstants.DOTS, false);
        Tile t12 = new NumberTile(7, SuitConstants.DOTS, false);
        Tile t13 = new NumberTile(8, SuitConstants.DOTS, false);
        Tile t14 = new NumberTile(6, SuitConstants.DOTS, false);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(noPointsYaku.isValid(player));
    }

    @Test
    public void handWithFourOfOneTileIsValid() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.BAMBOO, false);
        Tile t5 = new NumberTile(3, SuitConstants.BAMBOO, false);
        Tile t6 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t7 = new NumberTile(6, SuitConstants.DOTS, false);
        Tile t8 = new NumberTile(6, SuitConstants.DOTS, false);
        Tile t9 = new NumberTile(7, SuitConstants.DOTS, false);
        Tile t10 = new NumberTile(7, SuitConstants.DOTS, false);
        Tile t11 = new NumberTile(8, SuitConstants.DOTS, false);
        Tile t12 = new NumberTile(8, SuitConstants.DOTS, false);
        Tile t13 = new NumberTile(6, SuitConstants.DOTS, false);
        Tile t14 = new NumberTile(6, SuitConstants.DOTS, false);

        List<Tile> hand = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14));
        playArea.setHand(hand);
        player.setPlayArea(playArea);

        //Expect
        assertTrue(noPointsYaku.isValid(player));
    }
}
