package mahjong;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Translation {
    protected static final Map<String, String> japaneseToEnglish;

    static {
        japaneseToEnglish = new HashMap<>();
        japaneseToEnglish.put("Aka Dora", "Red Five");
        japaneseToEnglish.put("Chankan", "Robbed A Kan");
        japaneseToEnglish.put("Chantai", "Half Outside Hand");
        japaneseToEnglish.put("Chiitoitsu", "Seven Pairs");
        japaneseToEnglish.put("Chinitsu", "Full Flush");
        japaneseToEnglish.put("Yakuhai (chun)", "Red Dragon");
        japaneseToEnglish.put("Double Riichi", "Double Riichi");
        japaneseToEnglish.put("Dora", "Dora");
        japaneseToEnglish.put("Yakuhai (east)", "East Round/Seat");
        japaneseToEnglish.put("Haitei Raoyue", "Win By Last Draw");
        japaneseToEnglish.put("Yakuhai (haku)", "White Dragon");
        japaneseToEnglish.put("Yakuhai (hatsu)", "Green Dragon");
        japaneseToEnglish.put("Honitsu", "Half Flush");
        japaneseToEnglish.put("Honroutou", "Terminals And Honors");
        japaneseToEnglish.put("Houtei Raoyui", "Win By Last Discard");
        japaneseToEnglish.put("Iipeiko", "Twin Run");
        japaneseToEnglish.put("Ippatsu", "First Turn After Riichi");
        japaneseToEnglish.put("Ittsu", "Straight");
        japaneseToEnglish.put("Junchan", "Fully Outside Hand");
        japaneseToEnglish.put("Nagashi Mangan", "Nagashi Mangan");
        japaneseToEnglish.put("Yakuhai (north)", "North Round/Seat");
        japaneseToEnglish.put("Pinfu", "No Fu Hand");
        japaneseToEnglish.put("Renhou", "Hand Of Man");
        japaneseToEnglish.put("Riichi", "Riichi");
        japaneseToEnglish.put("Rinshan Kaihou", "Dead Wall Draw");
        japaneseToEnglish.put("Ryanpeikou", "Double Twin Runs");
        japaneseToEnglish.put("San Ankou", "Three Closed Triplets");
        japaneseToEnglish.put("San Kantsu", "Three Kans");
        japaneseToEnglish.put("Sanshoku Doujun", "Mixed Triple Sequence");
        japaneseToEnglish.put("Sanshoku Doukou", "Three Color Triplets");
        japaneseToEnglish.put("Shou Sangen", "Little Three Dragons");
        japaneseToEnglish.put("Yakuhai (south)", "South Round/Seat");
        japaneseToEnglish.put("Tanyao", "All Simples");
        japaneseToEnglish.put("Toitoi", "All Triplets");
        japaneseToEnglish.put("Menzen Tsumo", "Self Draw");
        japaneseToEnglish.put("Yakuhai (west)", "West Round/Seat");
        japaneseToEnglish.put("Yakuhai (wind of place)", "Value Tiles (Seat)");
        japaneseToEnglish.put("Yakuhai (wind of round)", "Value Tiles (Round");
        japaneseToEnglish.put("Chiihou", "Hand Of Earth");
        japaneseToEnglish.put("Chinroutou", "All Terminals");
        japaneseToEnglish.put("Chuuren Poutou", "Nine Gates");
        japaneseToEnglish.put("Daburu Chuuren Poutou", "True Nine Gates");
        japaneseToEnglish.put("Kokushi Musou Juusanmen Matchi", "True Thirteen Orphans");
        japaneseToEnglish.put("Daisangen", "Big Three Dragons");
        japaneseToEnglish.put("Daisharin", "Big Wheels");
        japaneseToEnglish.put("Daisuurin", "Numerous Numbers");
        japaneseToEnglish.put("Daichikurin", "Bamboo Forest");
        japaneseToEnglish.put("Dai Suushii", "Big Four Winds");
        japaneseToEnglish.put("Kokushi Musou", "Thirteen Orphans");
        japaneseToEnglish.put("Renhou (yakuman)", "Hand Of Man (Yakuman)");
        japaneseToEnglish.put("Ryuuiisou", "All Green");
        japaneseToEnglish.put("Shousuushii", "Little Four Winds");
        japaneseToEnglish.put("Suu Ankou", "Four Closed Triplets");
        japaneseToEnglish.put("Suu Ankou Tanki", "Four Closedd Triplets (Single Wait)");
        japaneseToEnglish.put("Suu Kantsu", "Four Kans");
        japaneseToEnglish.put("Tenhou", "Hand Of Heaven");
        japaneseToEnglish.put("Tsuu Iisou", "All Honors");
    }

    private Translation() {
        throw new IllegalStateException("Utility Class");
    }

    public static List<String> translateYaku(List<String> yakuList) {
        List<String> translated = new ArrayList<>();
        for (String yaku : yakuList) {
            translated.add(japaneseToEnglish.get(yaku));
        }
        return translated;
    }
}
