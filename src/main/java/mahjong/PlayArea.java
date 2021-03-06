package mahjong;

import mahjong.gui.*;
import mahjong.tile.DragonTile;
import mahjong.tile.NumberTile;
import mahjong.tile.Tile;
import mahjong.tile.WindTile;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlayArea {
    private List<Tile> hand;
    private List<Meld> melds;
    private List<Tile> discard;
    private static final int STARTING_HAND_SIZE = 13;
    private final AbstractHandPanelHolder handPanelHolder;
    private final MeldPanelHolder meldPanelHolder;
    private final DiscardPanelHolder discardPanelHolder;
    private volatile boolean isDiscardSelected;
    private volatile int discardIndex;
    private boolean isMyTurn;
    private final int playerNumber;

    public PlayArea(int playerNumber) {
        this.hand = new ArrayList<>();
        this.discard = new ArrayList<>();
        this.melds = new ArrayList<>();
        this.isDiscardSelected = false;
        this.discardIndex = -1;
        this.playerNumber = playerNumber;
        this.handPanelHolder = getPlayerSpecificHandPanelHolder();
        this.meldPanelHolder = new MeldPanelHolder(4, 4, playerNumber, getMeldsXCoordinate(), getMeldsYCoordinate());
        this.discardPanelHolder = new DiscardPanelHolder(4, 6, getDiscardXCoordinate(), getDiscardYCoordinate(), playerNumber);
        this.isMyTurn = false;
    }

    public PlayArea(PlayArea playArea) {
        this.hand = playArea.hand.stream().map(t -> {
            if (t instanceof NumberTile) {
                return new NumberTile((NumberTile) t);
            } else if (t instanceof DragonTile) {
                return new DragonTile((DragonTile) t);
            } else {
                return new WindTile((WindTile) t);
            }
        }).collect(Collectors.toList());
        this.melds = playArea.melds.stream().map(Meld::new).collect(Collectors.toList());
        this.discard = playArea.discard.stream().map(t -> {
            if (t instanceof NumberTile) {
                return new NumberTile((NumberTile) t);
            } else if (t instanceof DragonTile) {
                return new DragonTile((DragonTile) t);
            } else {
                return new WindTile((WindTile) t);
            }
        }).collect(Collectors.toList());
        this.handPanelHolder = playArea.handPanelHolder;
        this.meldPanelHolder = playArea.meldPanelHolder;
        this.discardPanelHolder = playArea.discardPanelHolder;
        this.isDiscardSelected = playArea.isDiscardSelected;
        this.discardIndex = playArea.discardIndex;
        this.isMyTurn = playArea.isMyTurn;
        this.playerNumber = playArea.playerNumber;
    }

    private AbstractHandPanelHolder getPlayerSpecificHandPanelHolder() {
        if (this.playerNumber == 1) {
            return new PlayerOneHandPanelHolder(this);
        }
        if (this.playerNumber == 2) {
            return new PlayerTwoHandPanelHolder(this);
        }
        if (this.playerNumber == 3) {
            return new PlayerThreeHandPanelHolder(this);
        }
        return new PlayerFourHandPanelHolder(this);
    }

    private int getMeldsXCoordinate() {
        if (playerNumber == 1) {
            return 1480;
        } else if (playerNumber == 2) {
            return 1480;
        } else if (playerNumber == 3) {
            return 140;
        } else {
            return 140;
        }
    }

    private int getDiscardXCoordinate() {
        if (playerNumber == 1) {
            return 834;
        } else if (playerNumber == 2) {
            return 1070;
        } else if (playerNumber == 3) {
            return 834;
        } else {
            return 598;
        }
    }

    private int getMeldsYCoordinate() {
        if (playerNumber == 1) {
            return 750;
        } else if (playerNumber == 2) {
            return 0;
        } else if (playerNumber == 3) {
            return 0;
        } else {
            return 750;
        }
    }

    private int getDiscardYCoordinate() {
        if (playerNumber == 1) {
            return 620;
        } else if (playerNumber == 2) {
            return 400;
        } else if (playerNumber == 3) {
            return 180;
        } else {
            return 400;
        }
    }


    public List<Tile> getHand() {
        return hand;
    }

    public int getStartingHandSize() {
        return STARTING_HAND_SIZE;
    }

    public List<Tile> getDiscard() {
        return discard;
    }

    public Tile draw(Game game, boolean useDeadwall) {
        this.hand = this.hand.stream().sorted().collect(Collectors.toList());
        Tile tile;
        if (!useDeadwall) {
            tile = game.getDeck().draw();
            game.getWindow().getGameInfoPanel().getDeckTileCount().setText("x" + game.getDeck().getTotalTiles());
        } else {
            tile = game.getDeadwall().getDrawTiles().draw();
        }
        this.hand.add(tile);
        this.handPanelHolder.displayHand();
        if (isKan(tile, game)) {
            return draw(game, true);
        }
        return tile;
    }

    public void setup(Deck deck) {
        reset();
        for (int i = 0; i < getStartingHandSize(); i++) {
            initialDraw(deck);
        }
        handPanelHolder.displayHand();
    }

//    This is a debug method, set to put players in riichi instantly
//    public void setup(Deck deck) {
//        reset();
//        Tile t1 = new NumberTile(2, SuitConstants.BAMBOO, false);
//        Tile t2 = new NumberTile(2, SuitConstants.BAMBOO, false);
//        Tile t3 = new NumberTile(2, SuitConstants.BAMBOO, false);
//        Tile t4 = new NumberTile(2, SuitConstants.DOTS, false);
//        Tile t5 = new NumberTile(3, SuitConstants.DOTS, false);
//        Tile t6 = new NumberTile(4, SuitConstants.DOTS, false);
//        Tile t7 = new NumberTile(6, SuitConstants.BAMBOO, false);
//        Tile t8 = new NumberTile(6, SuitConstants.BAMBOO, false);
//        Tile t9 = new NumberTile(4, SuitConstants.CHARACTERS, false);
//        Tile t10 = new NumberTile(4, SuitConstants.CHARACTERS, false);
//        Tile t11 = new NumberTile(4, SuitConstants.CHARACTERS, false);
//        Tile t12 = new NumberTile(3, SuitConstants.BAMBOO, false);
//        Tile t13 = new NumberTile(4, SuitConstants.BAMBOO, false);
//        this.hand.add(t1);
//        this.hand.add(t2);
//        this.hand.add(t3);
//        this.hand.add(t4);
//        this.hand.add(t5);
//        this.hand.add(t6);
//        this.hand.add(t7);
//        this.hand.add(t8);
//        this.hand.add(t9);
//        this.hand.add(t10);
//        this.hand.add(t11);
//        this.hand.add(t12);
//        this.hand.add(t13);
//        this.hand = this.hand.stream().sorted().collect(Collectors.toList());
//    }

    private void initialDraw(Deck deck) {
        Tile tile = deck.draw();
        this.hand.add(tile);
        this.hand = this.hand.stream().sorted().collect(Collectors.toList());
    }

    private boolean isKan(Tile tile, Game game) {
        if (this.hand.stream().filter(t -> t.getNumber() == tile.getNumber() && t.getSuit().equals(tile.getSuit())).count() == 4) {
            if (game.getWindow().isConfirmed(MessageFormat.format(MessageConstants.MSG_KAN, this.playerNumber))) {
                meldKan(tile, false);
                return true;
            }
        } else {
            List<Meld> meld = melds.stream().filter(m -> m.getTiles().stream().allMatch(t -> t.getNumber() == tile.getNumber() && t.getSuit().equals(tile.getSuit()))).collect(Collectors.toList());
            if (!meld.isEmpty() && game.getWindow().isConfirmed(MessageFormat.format(MessageConstants.MSG_KAN, this.playerNumber))) {
                game.ronHandler(game.getPlayerFromNumber(playerNumber), tile, true);
                meld.get(0).getTiles().add(tile);
                hand.remove(tile);
                meldPanelHolder.kanAPon(tile, melds.indexOf(meld.get(0)));
                return true;
            }
        }
        return false;
    }

    public void meldKan(Tile discarded, boolean isOpen) {
        List<Tile> newMeld = this.hand.stream().filter(t -> t.getNumber() == discarded.getNumber() && t.getSuit().equals(discarded.getSuit())).collect(Collectors.toList());
        this.hand = this.hand.stream().filter(t -> t.getNumber() != discarded.getNumber() || !t.getSuit().equals(discarded.getSuit())).collect(Collectors.toList());
        if (isOpen) {
            newMeld.add(discarded);
        }
        int calledTileIndex = isOpen ? newMeld.indexOf(discarded) : -1;
        Meld meld = new Meld(newMeld, isOpen, false, calledTileIndex);
        this.melds.add(meld);
        this.meldPanelHolder.displayMeld(meld);
    }

    public void meldPon(Tile discarded, boolean isOpen) {
        List<Tile> newMeld = this.hand.stream().filter(t -> t.getNumber() == discarded.getNumber() && t.getSuit().equals(discarded.getSuit())).collect(Collectors.toList());
        this.hand = this.hand.stream().filter(t -> t.getNumber() != discarded.getNumber() || !t.getSuit().equals(discarded.getSuit())).collect(Collectors.toList());
        if (newMeld.size() == 3) {
            this.hand.add(newMeld.remove(0));
        }
        newMeld.add(discarded);
        int calledTileIndex = isOpen ? newMeld.indexOf(discarded) : -1;
        Meld meld = new Meld(newMeld, isOpen, false, calledTileIndex);
        this.melds.add(meld);
        this.meldPanelHolder.displayMeld(meld);
    }

    public void meldChi(Tile discarded, List<Tile> newMeld, boolean isOpen) {
        this.hand.remove(newMeld.get(0));
        this.hand.remove(newMeld.get(1));
        newMeld.add(discarded);
        newMeld = newMeld.stream().sorted().collect(Collectors.toList());
        int calledTileIndex = isOpen ? newMeld.indexOf(discarded) : -1;
        Meld meld = new Meld(newMeld, isOpen, true, calledTileIndex);
        this.melds.add(meld);
        this.meldPanelHolder.displayMeld(meld);
    }

    public void discard(int index, boolean discardSideways) {
        this.handPanelHolder.displayNotMyTurnBorder();
        this.handPanelHolder.getMainPanel().repaint();
        Tile tile = this.hand.remove(index);
        this.discard.add(tile);
        this.handPanelHolder.displayHand();
        this.discardPanelHolder.displayDiscard(tile, discardSideways);
        this.isDiscardSelected = false;
        this.setDiscardIndex(-1);
    }

    public boolean isOpenKan(Tile tile) {
        return this.hand.stream().filter(t -> t.getNumber() == tile.getNumber() && t.getSuit().equals(tile.getSuit())).count() == 3;
    }

    public boolean isOpenPon(Tile tile) {
        return this.hand.stream().filter(t -> t.getNumber() == tile.getNumber() && t.getSuit().equals(tile.getSuit())).count() >= 2;
    }

    public List<List<Tile>> isOpenChi(Tile tile) {
        if (!tile.isHonor()) {
            List<List<Tile>> combinations = new ArrayList<>();
            List<Tile> minusTwoTiles = this.hand.stream().filter(t -> t.getNumber() == tile.getNumber() - 2 && t.getSuit().equals(tile.getSuit())).collect(Collectors.toList());
            List<Tile> minusOneTiles = this.hand.stream().filter(t -> t.getNumber() == tile.getNumber() - 1 && t.getSuit().equals(tile.getSuit())).collect(Collectors.toList());
            List<Tile> plusOneTiles = this.hand.stream().filter(t -> t.getNumber() == tile.getNumber() + 1 && t.getSuit().equals(tile.getSuit())).collect(Collectors.toList());
            List<Tile> plusTwoTiles = this.hand.stream().filter(t -> t.getNumber() == tile.getNumber() + 2 && t.getSuit().equals(tile.getSuit())).collect(Collectors.toList());
            List<List<Tile>> chiTiles = new ArrayList<>();
            chiTiles.add(minusTwoTiles);
            chiTiles.add(minusOneTiles);
            chiTiles.add(plusOneTiles);
            chiTiles.add(plusTwoTiles);
            getChiCombinations(combinations, chiTiles);
            return combinations.stream().distinct().collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private void getChiCombinations(List<List<Tile>> combinations, List<List<Tile>> chiTiles) {
        for (int i = 0; i < 3; i++) {
            for (Tile firstTile : chiTiles.get(i)) {
                for (Tile secondTile : chiTiles.get(i + 1)) {
                    if (combinations.stream().noneMatch(c -> c.get(0).getTileFileName().equals(firstTile.getTileFileName())
                            && c.get(1).getTileFileName().equals(secondTile.getTileFileName()))) {
                        List<Tile> newCombination = new ArrayList<>();
                        newCombination.add(firstTile);
                        newCombination.add(secondTile);
                        combinations.add(newCombination);
                    }
                }
            }
        }
    }

    public Tile getLastDiscard() {
        if (this.discard.isEmpty()) {
            return null;
        }
        return this.discard.get(this.discard.size() - 1);
    }

    public void removeLastDiscard() {
        discard.remove(discard.size() - 1);
        discardPanelHolder.removeLastDiscard();
    }

    public void makeDiscardSelection(boolean displayHand) {
        if (displayHand) {
            displayHandAndMelds();
        }
        isMyTurn = true;
        this.handPanelHolder.displayMyTurnBorder();
        this.handPanelHolder.getMainPanel().repaint();
        while (!isDiscardSelected) {
            //Waiting for the player to click on the tile they want to discard
        }
        discard(discardIndex, false);
    }

    public void reset() {
        this.hand.clear();
        this.discard.clear();
        this.melds.clear();
        discardIndex = -1;
        handPanelHolder.clearAll();
        discardPanelHolder.clearAll();
        meldPanelHolder.clearAll();
        isMyTurn = false;
    }

    public void setDiscardSelected(boolean discardSelected) {
        isDiscardSelected = discardSelected;
    }

    public void setDiscardIndex(int discardIndex) {
        this.discardIndex = discardIndex;
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    public void setMyTurn(boolean myTurn) {
        isMyTurn = myTurn;
    }

    public void displayHandAndMelds() {
        this.hand = this.hand.stream().sorted().collect(Collectors.toList());
        this.handPanelHolder.displayHand();
    }

    public List<Tile> getCombineHandAndMelds() {
        List<Tile> combined = new ArrayList<>(hand);
        for (Meld m : melds) {
            combined.addAll(m.getTiles());
        }
        return combined;
    }

    public List<Meld> getMelds() {
        return melds;
    }

    public void setHand(List<Tile> hand) {
        this.hand = hand;
    }

    public void setMelds(List<Meld> melds) {
        this.melds = melds;
    }

    public void setDiscard(List<Tile> discard) {
        this.discard = discard;
    }

    public AbstractHandPanelHolder getHandPanelHolder() {
        return handPanelHolder;
    }

    public MeldPanelHolder getMeldPanelHolder() {
        return meldPanelHolder;
    }

    public DiscardPanelHolder getDiscardPanelHolder() {
        return discardPanelHolder;
    }

    public boolean isDiscardSelected() {
        return isDiscardSelected;
    }

    public int getDiscardIndex() {
        return discardIndex;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
}