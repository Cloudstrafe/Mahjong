package mahjong;

import com.google.gson.Gson;
import mahjong.tile.Tile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScoringHelper {

    private static final List<String> HONORS_LIST = new ArrayList<>(Arrays.asList(
            SuitConstants.EAST_WIND,
            SuitConstants.SOUTH_WIND,
            SuitConstants.WEST_WIND,
            SuitConstants.NORTH_WIND,
            SuitConstants.WHITE_DRAGON,
            SuitConstants.GREEN_DRAGON,
            SuitConstants.RED_DRAGON));

    private static final int WIND_OFFSET = 27;

    private ScoringHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static ScoringResult scoreRound(Deadwall deadwall, Deck deck, String roundWind, Tile winningTile, Player winningPlayer, boolean isTsumo, int riichiSticks, int tsumiSticks, boolean robbedKan) {
        try {
            StringBuilder pythonScript = new StringBuilder();
            String tiles = getTilesString(winningPlayer);
            String win_tile = getWinTileString(winningTile);
            String melds = getMeldsString(winningPlayer);
            String dora_indicators = getDoraIndicatorsString(deadwall);
            String config = getConfigString(roundWind, deck, winningPlayer, isTsumo, robbedKan);
            pythonScript.append("import json\n");
            pythonScript.append("import os\n");
            pythonScript.append("from mahjong.hand_calculating.hand import HandCalculator\n");
            pythonScript.append("from mahjong.hand_calculating.hand_config import HandConfig\n");
            pythonScript.append("from mahjong.meld import Meld\n");
            pythonScript.append("from mahjong.tile import TilesConverter\n");
            pythonScript.append("handCalculator = HandCalculator()\n");
            pythonScript.append("result = handCalculator.estimate_hand_value(").append(tiles).append(", ").append(win_tile).append(", ").append(melds).append(", ").append(dora_indicators).append(", ").append(config).append(")\n");
            pythonScript.append("resultDict = {}\n");
            pythonScript.append("resultDict[\"fu\"] = result.fu\n");
            pythonScript.append("resultDict[\"han\"] = result.han\n");
            pythonScript.append("resultDict[\"cost\"] = result.cost\n");
            pythonScript.append("resultDict[\"yaku\"] = [x.name for x in result.yaku]\n");
            pythonScript.append("jsonResult = json.dumps(resultDict, indent = 2)\n");
            pythonScript.append("dirname = os.path.dirname(__file__)\n");
            pythonScript.append("filename = os.path.join(dirname, 'result.json')\n");
            pythonScript.append("with open(filename, \"w\") as f:\n");
            pythonScript.append("    f.write(jsonResult)\n");
            File pythonFile = new File("src/main/java/mahjong/main.py");
            pythonFile.createNewFile(); // if file already exists will do nothing
            FileOutputStream oFile = new FileOutputStream(pythonFile, false);
            oFile.write(pythonScript.toString().getBytes(StandardCharsets.UTF_8));
            oFile.close();
            ProcessBuilder processBuilder = new ProcessBuilder("python", "src/main/java/mahjong/main.py");
            Process process = processBuilder.start();
            process.waitFor();
            String jsonContents = Files.readString(Paths.get("src/main/java/mahjong/result.json"));
            Gson gson = new Gson();
            ScoringResult scoringResult = gson.fromJson(jsonContents, ScoringResult.class);
            scoringResult.getCost().setKyoutakuBonus(riichiSticks);
            scoringResult.getCost().setTsumiBonuses(tsumiSticks);
            scoringResult.getCost().setTotal();
            return scoringResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void adjustScores(ScoringResult scoringResult, Game game, Player winningPlayer, Player discardingPlayer) {
        winningPlayer.setPoints(winningPlayer.getPoints() + scoringResult.getCost().getTotal());
        if (discardingPlayer == null) {
            for (int i = 0; i < 3; i ++) {
                Player player = game.getTurnQueue().remove();
                if (i != 1) {
                    player.setPoints(player.getPoints() - scoringResult.getCost().getAdditionalPayment());
                } else {
                    player.setPoints(player.getPoints() - scoringResult.getCost().getMainPayment());
                }
                game.getTurnQueue().add(player);
            }
            game.getTurnQueue().add(game.getTurnQueue().remove());
        } else {
            discardingPlayer.setPoints(discardingPlayer.getPoints() - scoringResult.getCost().getMainPayment());
        }
    }



    public static String getWinTileString(Tile winningTile) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("win_tile = TilesConverter.string_to_136_array(");
        if (winningTile.isNumber()) {
            stringBuilder.append(winningTile.getSuit()).append("='");
        } else if (winningTile.isHonor()) {
            stringBuilder.append("honors='");
        }
        convertTileToScoringString(stringBuilder, winningTile);
        stringBuilder.append("')[0]");
        return stringBuilder.toString();
    }

    public static String getTilesString(Player winningPlayer) {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder man = new StringBuilder();
        StringBuilder pin = new StringBuilder();
        StringBuilder sou = new StringBuilder();
        StringBuilder honors = new StringBuilder();
        stringBuilder.append("tiles = TilesConverter.string_to_136_array(");
        man.append("man='");
        pin.append("pin='");
        sou.append("sou='");
        honors.append("honors='");
        for (Tile tile : winningPlayer.getPlayArea().getHand()) {
            if (SuitConstants.CHARACTERS.equals(tile.getSuit())) {
                convertTileToScoringString(man, tile);
            } else if (SuitConstants.DOTS.equals(tile.getSuit())) {
                convertTileToScoringString(pin, tile);
            } else if (SuitConstants.BAMBOO.equals(tile.getSuit())) {
                convertTileToScoringString(sou, tile);
            } else if (tile.isHonor()) {
                convertTileToScoringString(honors, tile);
            }
        }
        for (Meld meld : winningPlayer.getPlayArea().getMelds()) {
            for (int i = 0; i < 3; i++) {
                Tile tile = meld.getTiles().get(i);
                if (SuitConstants.CHARACTERS.equals(tile.getSuit())) {
                    convertTileToScoringString(man, tile);
                } else if (SuitConstants.DOTS.equals(tile.getSuit())) {
                    convertTileToScoringString(pin, tile);
                } else if (SuitConstants.BAMBOO.equals(tile.getSuit())) {
                    convertTileToScoringString(sou, tile);
                } else if (tile.isHonor()) {
                    convertTileToScoringString(honors, tile);
                }
            }
        }
        man.append("'");
        pin.append("'");
        sou.append("'");
        honors.append("'");
        if (!"man=''".equals(man.toString())) {
            stringBuilder.append(man.toString()).append(", ");
        }
        if (!"pin=''".equals(pin.toString())) {
            stringBuilder.append(pin.toString()).append(", ");
        }
        if (!"sou=''".equals(sou.toString())) {
            stringBuilder.append(sou.toString()).append(", ");
        }
        if (!"honors=''".equals(honors.toString())) {
            stringBuilder.append(honors.toString());
        } else {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public static String getMeldsString(Player winningPlayer) {
        if (winningPlayer.getPlayArea().getMelds().isEmpty()) {
            return "melds=None";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("melds = [");
        for (Meld meld : winningPlayer.getPlayArea().getMelds()) {
            stringBuilder.append("Meld(meld_type=Meld.");
            if (meld.isRun()) {
                stringBuilder.append("CHI, tiles=TilesConverter.string_to_136_array(");
            } else if (meld.getTiles().size() == 4) {
                stringBuilder.append("KAN, tiles=TilesConverter.string_to_136_array(");
            } else {
                stringBuilder.append("PON, tiles=TilesConverter.string_to_136_array(");
            }
            if (meld.getTiles().get(0).isNumber()) {
                stringBuilder.append(meld.getTiles().get(0).getSuit()).append("='");
            } else if (meld.getTiles().get(0).isHonor()) {
                stringBuilder.append("honors='");
            }
            for (Tile tile : meld.getTiles()) {
                convertTileToScoringString(stringBuilder, tile);
            }
            stringBuilder.append("'), ");
            if (meld.isOpen()) {
                stringBuilder.append("opened=True");
            } else {
                stringBuilder.append("opened=False");
            }
            stringBuilder.append("), ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static String getDoraIndicatorsString(Deadwall deadwall) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("dora_indicators = [");
        for (int i = 0; i < deadwall.getRevealed(); i++) {
            stringBuilder.append("TilesConverter.string_to_136_array(");
            if (deadwall.getDoraTiles().get(i).isNumber()) {
                stringBuilder.append(deadwall.getDoraTiles().get(i).getSuit()).append("='");
            } else if (deadwall.getDoraTiles().get(i).isHonor()) {
                stringBuilder.append("honors='");
            }
            convertTileToScoringString(stringBuilder, deadwall.getDoraTiles().get(i));
            stringBuilder.append("')[0], ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private static void convertTileToScoringString(StringBuilder stringBuilder, Tile tile) {
        if (tile.isHonor()) {
            stringBuilder.append(HONORS_LIST.indexOf(tile.getSuit()) + 1);
        } else if (tile.getIsRed()) {
            stringBuilder.append("r");
        } else {
            stringBuilder.append(tile.getNumber());
        }
    }

    public static String getConfigString(String roundWind, Deck deck, Player winningPlayer, boolean isTsumo, boolean robbedKan) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("config = HandConfig(");
        if (isTsumo) {
            stringBuilder.append("is_tsumo=True, ");
        }
        if (winningPlayer.isInRiichi()) {
            stringBuilder.append("is_riichi=True, ");
        }
        if (winningPlayer.isIppatsu()) {
            stringBuilder.append("is_ippatsu=True, ");
        }
        if (winningPlayer.isReplacementDraw() && isTsumo) {
            stringBuilder.append("is_rinshan=True, ");
        }
        if (robbedKan) {
            stringBuilder.append("is_chankan=True, ");
        }
        if (deck.getWall().isEmpty() && isTsumo) {
            stringBuilder.append("is_haitei=True, ");
        }
        if (deck.getWall().isEmpty() && !isTsumo) {
            stringBuilder.append("is_houtei=True, ");
        }
        if (winningPlayer.isInDoubleRiichi()) {
            stringBuilder.append("is_daburu_riichi=True, ");
        }
        if (winningPlayer.isFirstTurn() && isTsumo && winningPlayer.isDealer()) {
            stringBuilder.append("is_tenhou=True, ");
        }
        if (winningPlayer.isFirstTurn() && !isTsumo && !winningPlayer.isDealer()) {
            stringBuilder.append("is_renhou=True, ");
        }
        if (winningPlayer.isFirstTurn() && isTsumo && !winningPlayer.isDealer()) {
            stringBuilder.append("is_chiihou=True, ");
        }
        stringBuilder.append("player_wind=").append(HONORS_LIST.indexOf(winningPlayer.getSeat()) + WIND_OFFSET).append(", ");
        stringBuilder.append("round_wind=").append(HONORS_LIST.indexOf(roundWind) + WIND_OFFSET).append(")");
        return stringBuilder.toString();
    }
}
