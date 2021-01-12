package mahjong;

import mahjong.tile.DragonTile;
import mahjong.tile.NumberTile;
import mahjong.tile.Tile;
import mahjong.tile.WindTile;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestScoringHelper {
    private Player player;

    @Before
    public void setUp() {
        player = new Player(SuitConstants.WEST_WIND, false, 1);
    }

    @Test
    public void testGetTilesStringWithHonorsAndBambooAndKan() {
        //Given
        Tile t1 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t2 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t3 = new DragonTile(SuitConstants.GREEN_DRAGON);
        Tile t4 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t5 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t6 = new DragonTile(SuitConstants.WHITE_DRAGON);
        Tile t15 = new DragonTile(SuitConstants.WHITE_DRAGON);
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
        List<Tile> tilesMeld2 = new ArrayList<>(Arrays.asList(t4, t5, t6, t15));
        Meld meld2 = new Meld(tilesMeld2, true, false, -1);
        List<Meld> melds = new ArrayList<>(Arrays.asList(meld, meld2));
        List<Tile> hand = new ArrayList<>(Arrays.asList(t7, t8, t9, t10, t11, t12, t13, t14));

        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        playArea.setMelds(melds);
        player.setPlayArea(playArea);

        //Expect
        assertEquals("tiles = TilesConverter.string_to_136_array(sou='66688999', honors='666555')", ScoringHelper.getTilesString(player));
    }

    @Test
    public void testGetTilesStringWithDotsAndCharacters() {
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
        assertEquals("tiles = TilesConverter.string_to_136_array(man='444', pin='234', sou='66678222')", ScoringHelper.getTilesString(player));
    }

    @Test
    public void testGetWinningTileStringWithBamboo() {
        //Given
        Tile t1 = new NumberTile(2, SuitConstants.BAMBOO, false);

        //Expect
        assertEquals("win_tile = TilesConverter.string_to_136_array(sou='2')[0]", ScoringHelper.getWinTileString(t1));
    }

    @Test
    public void testGetWinningTileStringWithDots() {
        //Given
        Tile t1 = new NumberTile(3, SuitConstants.DOTS, false);

        //Expect
        assertEquals("win_tile = TilesConverter.string_to_136_array(pin='3')[0]", ScoringHelper.getWinTileString(t1));
    }

    @Test
    public void testGetWinningTileStringWithCharacters() {
        //Given
        Tile t1 = new NumberTile(3, SuitConstants.CHARACTERS, false);

        //Expect
        assertEquals("win_tile = TilesConverter.string_to_136_array(man='3')[0]", ScoringHelper.getWinTileString(t1));
    }

    @Test
    public void testGetWinningTileStringWithRed() {
        //Given
        Tile t1 = new NumberTile(5, SuitConstants.DOTS, true);

        //Expect
        assertEquals("win_tile = TilesConverter.string_to_136_array(pin='r')[0]", ScoringHelper.getWinTileString(t1));
    }

    @Test
    public void testGetWinningTileStringWithHonor() {
        //Given
        Tile t1 = new DragonTile(SuitConstants.RED_DRAGON);

        //Expect
        assertEquals("win_tile = TilesConverter.string_to_136_array(honors='7')[0]", ScoringHelper.getWinTileString(t1));
    }

    @Test
    public void testGetMeldsStringWithFourMelds() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t16 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t12 = new WindTile(SuitConstants.EAST_WIND);
        Tile t13 = new WindTile(SuitConstants.EAST_WIND);
        Tile t14 = new WindTile(SuitConstants.EAST_WIND);
        Tile t15 = new WindTile(SuitConstants.EAST_WIND);

        List<Tile> tiles = new ArrayList<>(Arrays.asList(t1, t2, t3));
        Meld meld = new Meld(tiles, true, true, 1);
        List<Tile> tilesMeld2 = new ArrayList<>(Arrays.asList(t4, t5, t6));
        Meld meld2 = new Meld(tilesMeld2, true, false, 2);
        List<Tile> tilesMeld3 = new ArrayList<>(Arrays.asList(t9, t10, t11, t16));
        Meld meld3 = new Meld(tilesMeld3, false, false, -1);
        List<Tile> tilesMeld4 = new ArrayList<>(Arrays.asList(t12, t13, t14, t15));
        Meld meld4 = new Meld(tilesMeld4, false, false, -1);
        List<Meld> melds = new ArrayList<>(Arrays.asList(meld, meld2, meld3, meld4));
        List<Tile> hand = new ArrayList<>(Arrays.asList(t7, t8));

        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        playArea.setMelds(melds);
        player.setPlayArea(playArea);

        //Expect
        assertEquals("melds = [Meld(meld_type=Meld.CHI, tiles=TilesConverter.string_to_136_array(man='123'), opened=True), " +
                "Meld(meld_type=Meld.PON, tiles=TilesConverter.string_to_136_array(pin='222'), opened=True), " +
                "Meld(meld_type=Meld.KAN, tiles=TilesConverter.string_to_136_array(sou='4444'), opened=False), " +
                "Meld(meld_type=Meld.KAN, tiles=TilesConverter.string_to_136_array(honors='1111'), opened=False)]", ScoringHelper.getMeldsString(player));
    }

    @Test
    public void testGetDoraIndicatorsString() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t12 = new WindTile(SuitConstants.EAST_WIND);
        Tile t13 = new WindTile(SuitConstants.EAST_WIND);
        Tile t14 = new WindTile(SuitConstants.EAST_WIND);
        Deck deck = new Deck(new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14)));
        Deadwall deadwall = new Deadwall(deck);
        deadwall.setup(deck);
        deadwall.setRevealed(3);

        //Expect
        assertEquals("dora_indicators = [" +
                "TilesConverter.string_to_136_array(pin='2')[0], " +
                "TilesConverter.string_to_136_array(pin='2')[0], " +
                "TilesConverter.string_to_136_array(sou='6')[0]]", ScoringHelper.getDoraIndicatorsString(deadwall));
    }

    @Test
    public void testScoreRound() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t12 = new WindTile(SuitConstants.EAST_WIND);
        Tile t13 = new WindTile(SuitConstants.EAST_WIND);
        Tile t14 = new WindTile(SuitConstants.EAST_WIND);
        Deck deadwallDeck = new Deck(new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14)));
        Deadwall deadwall = new Deadwall(deadwallDeck);
        deadwall.setup(deadwallDeck);
        deadwall.setRevealed(3);

        Deck deck = new Deck();
        String roundWind = SuitConstants.EAST_WIND;

        Tile t15 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t16 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t17 = new DragonTile(SuitConstants.RED_DRAGON);
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
        List<Tile> tiles = new ArrayList<>(Arrays.asList(t15, t16, t17));
        Meld meld = new Meld(tiles, true, false, 1);
        List<Tile> tilesMeld2 = new ArrayList<>(Arrays.asList(t18, t19, t20));
        Meld meld2 = new Meld(tilesMeld2, true, true, 2);
        List<Meld> melds = new ArrayList<>(Arrays.asList(meld, meld2));
        List<Tile> hand = new ArrayList<>(Arrays.asList(t21, t22, t23, t24, t25, t26, t27, t28));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        playArea.setMelds(melds);
        player.setPlayArea(playArea);
        player.setFirstTurn(false);

        //When
        ScoringResult scoringResult = ScoringHelper.scoreRound(deadwall, deck, roundWind, t26, player, true, 0, 0, false);

        //Then
        assertNotNull(scoringResult);
        assertEquals(40, scoringResult.getFu());
        assertEquals(4, scoringResult.getHan());
        assertEquals(4000, scoringResult.getCost().getMain());
        assertEquals(4000, scoringResult.getCost().getMainPayment());
        assertEquals(2000, scoringResult.getCost().getAdditional());
        assertEquals(2000, scoringResult.getCost().getAdditionalPayment());
        assertEquals(0, scoringResult.getCost().getMainBonus());
        assertEquals(0, scoringResult.getCost().getAdditionalBonus());
        assertEquals(0, scoringResult.getCost().getKyoutakuBonus());
        assertEquals(8000, scoringResult.getCost().getTotal());
        assertEquals("", scoringResult.getCost().getYakuLevel());
        assertEquals("Yakuhai (chun)", scoringResult.getYaku().get(0));
        assertEquals("Dora", scoringResult.getYaku().get(1));
    }

    @Test
    public void testAdjustScoresWithTsumo() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t12 = new WindTile(SuitConstants.EAST_WIND);
        Tile t13 = new WindTile(SuitConstants.EAST_WIND);
        Tile t14 = new WindTile(SuitConstants.EAST_WIND);
        Deck deadwallDeck = new Deck(new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14)));
        Deadwall deadwall = new Deadwall(deadwallDeck);
        deadwall.setup(deadwallDeck);
        deadwall.setRevealed(3);

        Game game = new Game(null);

        Tile t15 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t16 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t17 = new DragonTile(SuitConstants.RED_DRAGON);
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
        List<Tile> tiles = new ArrayList<>(Arrays.asList(t15, t16, t17));
        Meld meld = new Meld(tiles, true, false, 1);
        List<Tile> tilesMeld2 = new ArrayList<>(Arrays.asList(t18, t19, t20));
        Meld meld2 = new Meld(tilesMeld2, true, true, 2);
        List<Meld> melds = new ArrayList<>(Arrays.asList(meld, meld2));
        List<Tile> hand = new ArrayList<>(Arrays.asList(t21, t22, t23, t24, t25, t26, t27, t28));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        playArea.setMelds(melds);
        Player p1 = game.getTurnQueue().remove();
        p1.setPlayArea(playArea);
        p1.setFirstTurn(false);
        game.getTurnQueue().add(p1);
        game.setDeadwall(deadwall);

        //When
        ScoringResult scoringResult = ScoringHelper.scoreRound(game.getDeadwall(), game.getDeck(), game.getRoundWind(), t26, p1, true, 0, 0, false);
        ScoringHelper.adjustScores(scoringResult, game, p1, null);

        //Then
        assertEquals(21000, game.getTurnQueue().remove().getPoints());
        assertEquals(21000, game.getTurnQueue().remove().getPoints());
        assertEquals(21000, game.getTurnQueue().remove().getPoints());
        assertEquals(37000, game.getTurnQueue().remove().getPoints());
    }

    @Test
    public void testAdjustScoresWithRon() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t12 = new WindTile(SuitConstants.EAST_WIND);
        Tile t13 = new WindTile(SuitConstants.EAST_WIND);
        Tile t14 = new WindTile(SuitConstants.EAST_WIND);
        Deck deadwallDeck = new Deck(new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14)));
        Deadwall deadwall = new Deadwall(deadwallDeck);
        deadwall.setup(deadwallDeck);
        deadwall.setRevealed(3);

        Game game = new Game(null);

        Tile t15 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t16 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t17 = new DragonTile(SuitConstants.RED_DRAGON);
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
        List<Tile> tiles = new ArrayList<>(Arrays.asList(t15, t16, t17));
        Meld meld = new Meld(tiles, true, false, 1);
        List<Tile> tilesMeld2 = new ArrayList<>(Arrays.asList(t18, t19, t20));
        Meld meld2 = new Meld(tilesMeld2, true, true, 2);
        List<Meld> melds = new ArrayList<>(Arrays.asList(meld, meld2));
        List<Tile> hand = new ArrayList<>(Arrays.asList(t21, t22, t23, t24, t25, t26, t27, t28));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        playArea.setMelds(melds);
        Player p1 = game.getTurnQueue().remove();
        p1.setPlayArea(playArea);
        p1.setFirstTurn(false);
        game.getTurnQueue().add(p1);
        Player p2 = game.getTurnQueue().remove();
        game.getTurnQueue().add(p2);
        game.getTurnQueue().add(game.getTurnQueue().remove());
        game.getTurnQueue().add(game.getTurnQueue().remove());
        game.getTurnQueue().add(game.getTurnQueue().remove());
        game.setDeadwall(deadwall);

        //When
        ScoringResult scoringResult = ScoringHelper.scoreRound(game.getDeadwall(), game.getDeck(), game.getRoundWind(), t26, p1, false, 0, 0, false);
        ScoringHelper.adjustScores(scoringResult, game, p1, p2);

        //Then
        assertEquals(13400, game.getTurnQueue().remove().getPoints());
        assertEquals(25000, game.getTurnQueue().remove().getPoints());
        assertEquals(25000, game.getTurnQueue().remove().getPoints());
        assertEquals(36600, game.getTurnQueue().remove().getPoints());
    }

    @Test
    public void testAdjustScoresWithRiichiBonus() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t12 = new WindTile(SuitConstants.EAST_WIND);
        Tile t13 = new WindTile(SuitConstants.EAST_WIND);
        Tile t14 = new WindTile(SuitConstants.EAST_WIND);
        Deck deadwallDeck = new Deck(new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14)));
        Deadwall deadwall = new Deadwall(deadwallDeck);
        deadwall.setup(deadwallDeck);
        deadwall.setRevealed(3);

        Game game = new Game(null);

        Tile t15 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t16 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t17 = new DragonTile(SuitConstants.RED_DRAGON);
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
        List<Tile> tiles = new ArrayList<>(Arrays.asList(t15, t16, t17));
        Meld meld = new Meld(tiles, true, false, 1);
        List<Tile> tilesMeld2 = new ArrayList<>(Arrays.asList(t18, t19, t20));
        Meld meld2 = new Meld(tilesMeld2, true, true, 2);
        List<Meld> melds = new ArrayList<>(Arrays.asList(meld, meld2));
        List<Tile> hand = new ArrayList<>(Arrays.asList(t21, t22, t23, t24, t25, t26, t27, t28));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        playArea.setMelds(melds);
        Player p1 = game.getTurnQueue().remove();
        p1.setPlayArea(playArea);
        p1.setFirstTurn(false);
        game.getTurnQueue().add(p1);
        game.setDeadwall(deadwall);

        //When
        ScoringResult scoringResult = ScoringHelper.scoreRound(game.getDeadwall(), game.getDeck(), game.getRoundWind(), t26, p1, true, 3, 0, false);
        ScoringHelper.adjustScores(scoringResult, game, p1, null);

        //Then
        assertEquals(21000, game.getTurnQueue().remove().getPoints());
        assertEquals(21000, game.getTurnQueue().remove().getPoints());
        assertEquals(21000, game.getTurnQueue().remove().getPoints());
        assertEquals(40000, game.getTurnQueue().remove().getPoints());
    }

    @Test
    public void testAdjustScoresWithTsumiBonusRon() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t12 = new WindTile(SuitConstants.EAST_WIND);
        Tile t13 = new WindTile(SuitConstants.EAST_WIND);
        Tile t14 = new WindTile(SuitConstants.EAST_WIND);
        Deck deadwallDeck = new Deck(new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14)));
        Deadwall deadwall = new Deadwall(deadwallDeck);
        deadwall.setup(deadwallDeck);
        deadwall.setRevealed(3);

        Game game = new Game(null);

        Tile t15 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t16 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t17 = new DragonTile(SuitConstants.RED_DRAGON);
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
        List<Tile> tiles = new ArrayList<>(Arrays.asList(t15, t16, t17));
        Meld meld = new Meld(tiles, true, false, 1);
        List<Tile> tilesMeld2 = new ArrayList<>(Arrays.asList(t18, t19, t20));
        Meld meld2 = new Meld(tilesMeld2, true, true, 2);
        List<Meld> melds = new ArrayList<>(Arrays.asList(meld, meld2));
        List<Tile> hand = new ArrayList<>(Arrays.asList(t21, t22, t23, t24, t25, t26, t27, t28));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        playArea.setMelds(melds);
        Player p1 = game.getTurnQueue().remove();
        p1.setPlayArea(playArea);
        p1.setFirstTurn(false);
        Player p2 = game.getTurnQueue().peek();
        game.getTurnQueue().add(p1);
        game.setDeadwall(deadwall);

        //When
        ScoringResult scoringResult = ScoringHelper.scoreRound(game.getDeadwall(), game.getDeck(), game.getRoundWind(), t26, p1, false, 0, 2, false);
        ScoringHelper.adjustScores(scoringResult, game, p1, p2);

        //Then
        assertEquals(12800, game.getTurnQueue().remove().getPoints());
        assertEquals(25000, game.getTurnQueue().remove().getPoints());
        assertEquals(25000, game.getTurnQueue().remove().getPoints());
        assertEquals(37200, game.getTurnQueue().remove().getPoints());
    }

    @Test
    public void testAdjustScoresWithTsumiBonusTsumo() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t12 = new WindTile(SuitConstants.EAST_WIND);
        Tile t13 = new WindTile(SuitConstants.EAST_WIND);
        Tile t14 = new WindTile(SuitConstants.EAST_WIND);
        Deck deadwallDeck = new Deck(new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14)));
        Deadwall deadwall = new Deadwall(deadwallDeck);
        deadwall.setup(deadwallDeck);
        deadwall.setRevealed(3);

        Game game = new Game(null);

        Tile t15 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t16 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t17 = new DragonTile(SuitConstants.RED_DRAGON);
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
        List<Tile> tiles = new ArrayList<>(Arrays.asList(t15, t16, t17));
        Meld meld = new Meld(tiles, true, false, 1);
        List<Tile> tilesMeld2 = new ArrayList<>(Arrays.asList(t18, t19, t20));
        Meld meld2 = new Meld(tilesMeld2, true, true, 2);
        List<Meld> melds = new ArrayList<>(Arrays.asList(meld, meld2));
        List<Tile> hand = new ArrayList<>(Arrays.asList(t21, t22, t23, t24, t25, t26, t27, t28));
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        playArea.setMelds(melds);
        Player p1 = game.getTurnQueue().remove();
        p1.setPlayArea(playArea);
        p1.setFirstTurn(false);
        game.getTurnQueue().add(p1);
        game.setDeadwall(deadwall);

        //When
        ScoringResult scoringResult = ScoringHelper.scoreRound(game.getDeadwall(), game.getDeck(), game.getRoundWind(), t26, p1, true, 0, 2, false);
        ScoringHelper.adjustScores(scoringResult, game, p1, null);

        //Then
        assertEquals(20800, game.getTurnQueue().remove().getPoints());
        assertEquals(20800, game.getTurnQueue().remove().getPoints());
        assertEquals(20800, game.getTurnQueue().remove().getPoints());
        assertEquals(37600, game.getTurnQueue().remove().getPoints());
    }

    @Test
    public void testAdjustScoresWithWinnerInRiichi() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t12 = new WindTile(SuitConstants.EAST_WIND);
        Tile t13 = new WindTile(SuitConstants.EAST_WIND);
        Tile t14 = new WindTile(SuitConstants.EAST_WIND);
        Deck deadwallDeck = new Deck(new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14)));
        Deadwall deadwall = new Deadwall(deadwallDeck);
        deadwall.setup(deadwallDeck);
        deadwall.setRevealed(3);

        Game game = new Game(null);

        Tile t15 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t16 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t17 = new DragonTile(SuitConstants.RED_DRAGON);
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
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        Player p1 = game.getTurnQueue().remove();
        p1.setPlayArea(playArea);
        p1.setFirstTurn(false);
        game.getTurnQueue().add(p1);
        game.setDeadwall(deadwall);
        p1.setInRiichi(true);

        //When
        ScoringResult scoringResult = ScoringHelper.scoreRound(game.getDeadwall(), game.getDeck(), game.getRoundWind(), t26, p1, true, 0, 0, false);
        ScoringHelper.adjustScores(scoringResult, game, p1, null);

        //Then
        assertEquals(19000, game.getTurnQueue().remove().getPoints());
        assertEquals(19000, game.getTurnQueue().remove().getPoints());
        assertEquals(19000, game.getTurnQueue().remove().getPoints());
        assertEquals(43000, game.getTurnQueue().remove().getPoints());
    }

    @Test
    public void testAdjustScoresWithHotei() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t12 = new WindTile(SuitConstants.EAST_WIND);
        Tile t13 = new WindTile(SuitConstants.EAST_WIND);
        Tile t14 = new WindTile(SuitConstants.EAST_WIND);
        Deck deadwallDeck = new Deck(new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14)));
        Deadwall deadwall = new Deadwall(deadwallDeck);
        deadwall.setup(deadwallDeck);
        deadwall.setRevealed(3);

        Game game = new Game(null);

        Tile t15 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t16 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t17 = new DragonTile(SuitConstants.RED_DRAGON);
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
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        Player p1 = game.getTurnQueue().remove();
        p1.setPlayArea(playArea);
        p1.setFirstTurn(false);
        game.getTurnQueue().add(p1);
        Player p2 = game.getTurnQueue().peek();
        game.setDeck(new Deck(new ArrayList<>()));
        game.setDeadwall(deadwall);

        //When
        ScoringResult scoringResult = ScoringHelper.scoreRound(game.getDeadwall(), game.getDeck(), game.getRoundWind(), t26, p1, false, 0, 0, false);
        ScoringHelper.adjustScores(scoringResult, game, p1, p2);

        //Then
        assertEquals(13000, game.getTurnQueue().remove().getPoints());
        assertEquals(25000, game.getTurnQueue().remove().getPoints());
        assertEquals(25000, game.getTurnQueue().remove().getPoints());
        assertEquals(37000, game.getTurnQueue().remove().getPoints());
    }

    @Test
    public void testAdjustScoresWithHaitei() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t12 = new WindTile(SuitConstants.EAST_WIND);
        Tile t13 = new WindTile(SuitConstants.EAST_WIND);
        Tile t14 = new WindTile(SuitConstants.EAST_WIND);
        Deck deadwallDeck = new Deck(new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14)));
        Deadwall deadwall = new Deadwall(deadwallDeck);
        deadwall.setup(deadwallDeck);
        deadwall.setRevealed(3);

        Game game = new Game(null);

        Tile t15 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t16 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t17 = new DragonTile(SuitConstants.RED_DRAGON);
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
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        Player p1 = game.getTurnQueue().remove();
        p1.setPlayArea(playArea);
        p1.setFirstTurn(false);
        game.getTurnQueue().add(p1);
        game.setDeck(new Deck(new ArrayList<>()));
        game.setDeadwall(deadwall);

        //When
        ScoringResult scoringResult = ScoringHelper.scoreRound(game.getDeadwall(), game.getDeck(), game.getRoundWind(), t26, p1, true, 0, 0, false);
        ScoringHelper.adjustScores(scoringResult, game, p1, null);

        //Then
        assertEquals(19000, game.getTurnQueue().remove().getPoints());
        assertEquals(19000, game.getTurnQueue().remove().getPoints());
        assertEquals(19000, game.getTurnQueue().remove().getPoints());
        assertEquals(43000, game.getTurnQueue().remove().getPoints());
    }

    @Test
    public void testIppatsuBonus() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t12 = new WindTile(SuitConstants.EAST_WIND);
        Tile t13 = new WindTile(SuitConstants.EAST_WIND);
        Tile t14 = new WindTile(SuitConstants.EAST_WIND);
        Deck deadwallDeck = new Deck(new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14)));
        Deadwall deadwall = new Deadwall(deadwallDeck);
        deadwall.setup(deadwallDeck);
        deadwall.setRevealed(3);

        Game game = new Game(null);

        Tile t15 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t16 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t17 = new DragonTile(SuitConstants.RED_DRAGON);
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
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        Player p1 = game.getTurnQueue().remove();
        p1.setPlayArea(playArea);
        game.getTurnQueue().add(p1);
        p1.setInRiichi(true);
        p1.setIppatsu(true);
        p1.setFirstTurn(false);
        game.setDeadwall(deadwall);

        //When
        ScoringResult scoringResult = ScoringHelper.scoreRound(game.getDeadwall(), game.getDeck(), game.getRoundWind(), t26, p1, true, 0, 0, false);
        ScoringHelper.adjustScores(scoringResult, game, p1, null);

        //Then
        assertEquals(19000, game.getTurnQueue().remove().getPoints());
        assertEquals(19000, game.getTurnQueue().remove().getPoints());
        assertEquals(19000, game.getTurnQueue().remove().getPoints());
        assertEquals(43000, game.getTurnQueue().remove().getPoints());
    }

    @Test
    public void testRobbingAKan() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t12 = new WindTile(SuitConstants.EAST_WIND);
        Tile t13 = new WindTile(SuitConstants.EAST_WIND);
        Tile t14 = new WindTile(SuitConstants.EAST_WIND);
        Deck deadwallDeck = new Deck(new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14)));
        Deadwall deadwall = new Deadwall(deadwallDeck);
        deadwall.setup(deadwallDeck);
        deadwall.setRevealed(3);

        Game game = new Game(null);

        Tile t15 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t16 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t17 = new DragonTile(SuitConstants.RED_DRAGON);
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
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        Player p1 = game.getTurnQueue().remove();
        p1.setPlayArea(playArea);
        game.getTurnQueue().add(p1);
        Player p2 = game.getTurnQueue().peek();
        p1.setInRiichi(true);
        p1.setIppatsu(true);
        p1.setFirstTurn(false);
        game.setDeadwall(deadwall);

        //When
        ScoringResult scoringResult = ScoringHelper.scoreRound(game.getDeadwall(), game.getDeck(), game.getRoundWind(), t26, p1, false, 0, 0, true);
        ScoringHelper.adjustScores(scoringResult, game, p1, p2);

        //Then
        assertEquals(7000, game.getTurnQueue().remove().getPoints());
        assertEquals(25000, game.getTurnQueue().remove().getPoints());
        assertEquals(25000, game.getTurnQueue().remove().getPoints());
        assertEquals(43000, game.getTurnQueue().remove().getPoints());
    }

    @Test
    public void testTenhou() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t12 = new WindTile(SuitConstants.EAST_WIND);
        Tile t13 = new WindTile(SuitConstants.EAST_WIND);
        Tile t14 = new WindTile(SuitConstants.EAST_WIND);
        Deck deadwallDeck = new Deck(new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14)));
        Deadwall deadwall = new Deadwall(deadwallDeck);
        deadwall.setup(deadwallDeck);
        deadwall.setRevealed(3);

        Game game = new Game(null);

        Tile t15 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t16 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t17 = new DragonTile(SuitConstants.RED_DRAGON);
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
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        Player p1 = game.getTurnQueue().remove();
        p1.setPlayArea(playArea);
        Player p2 = game.getTurnQueue().remove();
        Player p3 = game.getTurnQueue().remove();
        Player p4 = game.getTurnQueue().remove();
        game.getTurnQueue().add(p2);
        game.getTurnQueue().add(p3);
        game.getTurnQueue().add(p4);
        game.getTurnQueue().add(p1);
        p1.setFirstTurn(true);
        game.setDeadwall(deadwall);

        //When
        ScoringResult scoringResult = ScoringHelper.scoreRound(game.getDeadwall(), game.getDeck(), game.getRoundWind(), t26, p1, true, 0, 0, false);
        ScoringHelper.adjustScores(scoringResult, game, p1, null);

        //Then
        assertEquals(9000, p2.getPoints());
        assertEquals(9000, p3.getPoints());
        assertEquals(9000, p4.getPoints());
        assertEquals(73000, p1.getPoints());

        //Given
        p1.setFirstTurn(false);
        p1.setPoints(25000);
        p2.setPoints(25000);
        p3.setPoints(25000);
        p4.setPoints(25000);

        //When
        scoringResult = ScoringHelper.scoreRound(game.getDeadwall(), game.getDeck(), game.getRoundWind(), t26, p1, true, 0, 0, false);
        ScoringHelper.adjustScores(scoringResult, game, p1, null);

        //Then
        assertEquals(21000, p2.getPoints());
        assertEquals(21000, p3.getPoints());
        assertEquals(21000, p4.getPoints());
        assertEquals(37000, p1.getPoints());

        //Given
        p1.setFirstTurn(true);
        p1.setDealer(false);
        p1.setPoints(25000);
        p2.setPoints(25000);
        p3.setPoints(25000);
        p4.setPoints(25000);

        //When
        scoringResult = ScoringHelper.scoreRound(game.getDeadwall(), game.getDeck(), game.getRoundWind(), t26, p1, true, 0, 0, false);
        ScoringHelper.adjustScores(scoringResult, game, p1, null);

        //Then
        assertEquals("config = HandConfig(is_tsumo=True, is_chiihou=True, player_wind=27, round_wind=27)",
                ScoringHelper.getConfigString(game.getRoundWind(), game.getDeck(), p1, true, false));
    }

    @Test
    public void testRenhou() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t12 = new WindTile(SuitConstants.EAST_WIND);
        Tile t13 = new WindTile(SuitConstants.EAST_WIND);
        Tile t14 = new WindTile(SuitConstants.EAST_WIND);
        Deck deadwallDeck = new Deck(new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14)));
        Deadwall deadwall = new Deadwall(deadwallDeck);
        deadwall.setup(deadwallDeck);
        deadwall.setRevealed(3);

        Game game = new Game(null);

        Tile t15 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t16 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t17 = new DragonTile(SuitConstants.RED_DRAGON);
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
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        Player p1 = game.getTurnQueue().remove();
        p1.setPlayArea(playArea);
        Player p2 = game.getTurnQueue().remove();
        Player p3 = game.getTurnQueue().remove();
        Player p4 = game.getTurnQueue().remove();
        game.getTurnQueue().add(p2);
        game.getTurnQueue().add(p3);
        game.getTurnQueue().add(p4);
        game.getTurnQueue().add(p1);
        p1.setFirstTurn(true);
        p1.setDealer(false);
        game.setDeadwall(deadwall);

        //When
        ScoringResult scoringResult = ScoringHelper.scoreRound(game.getDeadwall(), game.getDeck(), game.getRoundWind(), t26, p1, false, 0, 0, false);
        ScoringHelper.adjustScores(scoringResult, game, p1, p2);

        //Then
        assertEquals(1000, p2.getPoints());
        assertEquals(25000, p3.getPoints());
        assertEquals(25000, p4.getPoints());
        assertEquals(49000, p1.getPoints());

        //Given
        p1.setFirstTurn(false);
        p1.setPoints(25000);
        p2.setPoints(25000);

        //When
        scoringResult = ScoringHelper.scoreRound(game.getDeadwall(), game.getDeck(), game.getRoundWind(), t26, p1, false, 0, 0, false);
        ScoringHelper.adjustScores(scoringResult, game, p1, p2);

        //Then
        assertEquals(13000, p2.getPoints());
        assertEquals(25000, p3.getPoints());
        assertEquals(25000, p4.getPoints());
        assertEquals(37000, p1.getPoints());

        //Given
        p1.setDealer(true);
        p1.setFirstTurn(true);
        p1.setPoints(25000);
        p2.setPoints(25000);

        //When
        scoringResult = ScoringHelper.scoreRound(game.getDeadwall(), game.getDeck(), game.getRoundWind(), t26, p1, false, 0, 0, false);
        ScoringHelper.adjustScores(scoringResult, game, p1, p2);

        //Then
        assertEquals(13000, p2.getPoints());
        assertEquals(25000, p3.getPoints());
        assertEquals(25000, p4.getPoints());
        assertEquals(37000, p1.getPoints());
    }

    @Test
    public void testChiihou() {
        //Given
        Tile t1 = new NumberTile(1, SuitConstants.CHARACTERS, false);
        Tile t2 = new NumberTile(2, SuitConstants.CHARACTERS, false);
        Tile t3 = new NumberTile(3, SuitConstants.CHARACTERS, false);
        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t5 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t6 = new NumberTile(2, SuitConstants.DOTS, false);
        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
        Tile t9 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t10 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t11 = new NumberTile(4, SuitConstants.BAMBOO, false);
        Tile t12 = new WindTile(SuitConstants.EAST_WIND);
        Tile t13 = new WindTile(SuitConstants.EAST_WIND);
        Tile t14 = new WindTile(SuitConstants.EAST_WIND);
        Deck deadwallDeck = new Deck(new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14)));
        Deadwall deadwall = new Deadwall(deadwallDeck);
        deadwall.setup(deadwallDeck);
        deadwall.setRevealed(3);

        Game game = new Game(null);

        Tile t15 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t16 = new DragonTile(SuitConstants.RED_DRAGON);
        Tile t17 = new DragonTile(SuitConstants.RED_DRAGON);
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
        PlayArea playArea = new PlayArea(1);
        playArea.setHand(hand);
        Player p1 = game.getTurnQueue().remove();
        p1.setPlayArea(playArea);
        p1.setDealer(false);
        Player p2 = game.getTurnQueue().remove();
        Player p3 = game.getTurnQueue().remove();
        Player p4 = game.getTurnQueue().remove();
        game.getTurnQueue().add(p2);
        game.getTurnQueue().add(p3);
        game.getTurnQueue().add(p4);
        game.getTurnQueue().add(p1);
        p1.setFirstTurn(true);
        game.setDeadwall(deadwall);

        //When
        ScoringResult scoringResult = ScoringHelper.scoreRound(game.getDeadwall(), game.getDeck(), game.getRoundWind(), t26, p1, true, 0, 0, false);
        ScoringHelper.adjustScores(scoringResult, game, p1, null);

        //Then
        assertEquals(9000, p2.getPoints());
        assertEquals(9000, p3.getPoints());
        assertEquals(9000, p4.getPoints());
        assertEquals(73000, p1.getPoints());

        //Given
        p1.setFirstTurn(false);
        p1.setPoints(25000);
        p2.setPoints(25000);
        p3.setPoints(25000);
        p4.setPoints(25000);

        //When
        scoringResult = ScoringHelper.scoreRound(game.getDeadwall(), game.getDeck(), game.getRoundWind(), t26, p1, true, 0, 0, false);
        ScoringHelper.adjustScores(scoringResult, game, p1, null);

        //Then
        assertEquals(21000, p2.getPoints());
        assertEquals(21000, p3.getPoints());
        assertEquals(21000, p4.getPoints());
        assertEquals(37000, p1.getPoints());

        //Given
        p1.setFirstTurn(true);
        p1.setDealer(true);
        p1.setPoints(25000);
        p2.setPoints(25000);
        p3.setPoints(25000);
        p4.setPoints(25000);

        //When
        scoringResult = ScoringHelper.scoreRound(game.getDeadwall(), game.getDeck(), game.getRoundWind(), t26, p1, true, 0, 0, false);
        ScoringHelper.adjustScores(scoringResult, game, p1, null);

        //Then
        assertEquals("config = HandConfig(is_tsumo=True, is_tenhou=True, player_wind=27, round_wind=27)",
                ScoringHelper.getConfigString(game.getRoundWind(), game.getDeck(), p1, true, false));
    }
}
