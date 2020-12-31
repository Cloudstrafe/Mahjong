package mahjong;

import java.util.Collections;
import java.util.List;

public class ScoringResult {
    private int fu = 0;
    private int han = 0;
    private Cost cost = null;
    private List<String> yaku = Collections.emptyList();

    public int getFu() {
        return fu;
    }

    public int getHan() {
        return han;
    }

    public Cost getCost() {
        return cost;
    }

    public List<String> getYaku() {
        return yaku;
    }
}
