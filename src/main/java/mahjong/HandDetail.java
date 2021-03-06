package mahjong;

import mahjong.tile.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static mahjong.SuitConstants.*;

public class HandDetail {
    private final List<Tile> pair;
    private final List<Meld> runs;
    private final List<Meld> sets;
    private final List<Tile> remainder;
    private final int setsAndRunsLeft;
    private final List<List<Meld>> validHands;
    private final List<Meld> melds;

    public HandDetail(List<Tile> pair, List<Tile> remainder, int setsAndRunsLeft, List<Meld> melds) {
        this.pair = pair;
        this.remainder = remainder.stream().sorted().collect(Collectors.toList());
        this.setsAndRunsLeft = setsAndRunsLeft;
        this.melds = melds;
        this.runs = getAllRuns();
        this.sets = getAllSets();
        this.validHands = new ArrayList<>();
        generateValidHands();
    }

    public List<List<Meld>> getValidHands() {
        return validHands;
    }

    private void generateValidHands() {
        List<Meld> combined = new ArrayList<>();
        combined.addAll(runs);
        combined.addAll(sets);
        List<Meld> result = new ArrayList<>();
        getAllValidCombinations(combined, setsAndRunsLeft, 0, result);
    }

    private void getAllValidCombinations(List<Meld> combined, int setsAndRunsLeft, int startPosition, List<Meld> result) {
        if (setsAndRunsLeft == 0) {
            if (remainder.equals(result.stream().flatMap(m -> m.getTiles().stream()).sorted().collect(Collectors.toList()))) {
                result.addAll(melds);
                validHands.add(result);
            }
            return;
        }
        for (int i = startPosition; i <= combined.size() - setsAndRunsLeft; i++) {
            List<Meld> copyResult = new ArrayList<>(result);
            copyResult.add(combined.get(i));
            getAllValidCombinations(combined, setsAndRunsLeft - 1, i + 1, copyResult);
        }
    }

    private List<Meld> getAllRuns() {
        List<Meld> allRuns = new ArrayList<>();
        getAllRunsBySuit(remainder.stream().filter(t -> CHARACTERS.equals(t.getSuit())).collect(Collectors.toList()), allRuns);
        getAllRunsBySuit(remainder.stream().filter(t -> DOTS.equals(t.getSuit())).collect(Collectors.toList()), allRuns);
        getAllRunsBySuit(remainder.stream().filter(t -> BAMBOO.equals(t.getSuit())).collect(Collectors.toList()), allRuns);
        return allRuns;
    }

    private List<Meld> getAllSets() {
        List<Meld> allSets = new ArrayList<>();
        for (int i = 0; i < remainder.size() - 2; i++) {
            Tile first = remainder.get(i);
            for (int j = i + 1; j < remainder.size() - 1; j++) {
                Tile second = remainder.get(j);
                if (first.getNumber() == second.getNumber() &&
                        first.getSuit().equals(second.getSuit())) {
                    for (int k = j + 1; k < remainder.size(); k++) {
                        Tile third = remainder.get(k);
                        if (second.getNumber() == third.getNumber() &&
                                second.getSuit().equals(third.getSuit())) {
                            List<Tile> run = new ArrayList<>();
                            run.add(first);
                            run.add(second);
                            run.add(third);
                            Meld meld = new Meld(run, false, false, -1);
                            allSets.add(meld);
                        }
                    }
                }
            }
        }
        return allSets;
    }

    private void getAllRunsBySuit(List<Tile> numberTiles, List<Meld> allRuns) {
        for (int i = 0; i < numberTiles.size() - 2; i++) {
            Tile first = numberTiles.get(i);
            for (int j = i + 1; j < numberTiles.size() - 1; j++) {
                Tile second = numberTiles.get(j);
                if (first.getNumber() == second.getNumber() - 1 &&
                        first.getSuit().equals(second.getSuit())) {
                    for (int k = j + 1; k < numberTiles.size(); k++) {
                        Tile third = numberTiles.get(k);
                        if (second.getNumber() == third.getNumber() - 1 &&
                                second.getSuit().equals(third.getSuit())) {
                            List<Tile> run = new ArrayList<>();
                            run.add(first);
                            run.add(second);
                            run.add(third);
                            Meld meld = new Meld(run, false, true, -1);
                            allRuns.add(meld);
                        }
                    }
                }
            }
        }
    }

    public List<Tile> getPair() {
        return pair;
    }

    public List<Meld> getRuns() {
        return runs;
    }

    public List<Meld> getSets() {
        return sets;
    }

    public List<Tile> getRemainder() {
        return remainder;
    }

    public int getSetsAndRunsLeft() {
        return setsAndRunsLeft;
    }

    public List<Meld> getMelds() {
        return melds;
    }

}
