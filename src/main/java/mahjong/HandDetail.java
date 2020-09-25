package mahjong;

import java.util.ArrayList;
import java.util.List;

public class HandDetail {
    private List<Tile> pair;
    private List<List<Tile>> runs;
    private List<List<Tile>> sets;
    private List<Tile> remainder;
    private int setsAndRunsLeft;

    public HandDetail(List<Tile> pair, List<Tile> remainder, int setsAndRunsLeft) {
        this.pair = pair;
        this.runs = new ArrayList<>();
        this.sets = new ArrayList<>();
        this.remainder = remainder;
        this.setsAndRunsLeft = setsAndRunsLeft;
    }
}
