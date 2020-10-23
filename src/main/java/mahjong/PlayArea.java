package mahjong;

import mahjong.gui.*;
import mahjong.tile.Tile;

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
    private AbstractHandPanelHolder handPanelHolder;
    private MeldPanelHolder meldPanelHolder;
    private DiscardPanelHolder discardPanelHolder;
    private volatile boolean isDiscardSelected;
    private volatile int discardIndex;
    private boolean isMyTurn;
    private int playerNumber;

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

    public void draw(Deck deck, Deadwall deadwall, GameWindow window) {
        this.hand = this.hand.stream().sorted().collect(Collectors.toList());
        Tile tile = deck.draw();
        this.hand.add(tile);
        displayHandAndMelds();
        if (isKan(tile, window)) {
            draw(deadwall.getDrawTiles(), deadwall, window);
        }
    }

    public void setup(Deck deck) {
        reset();
        for (int i = 0; i < getStartingHandSize(); i++) {
            initialDraw(deck);
        }
        handPanelHolder.displayHand();
    }

    private void initialDraw(Deck deck) {
        Tile tile = deck.draw();
        this.hand.add(tile);
        this.hand = this.hand.stream().sorted().collect(Collectors.toList());
    }

    private boolean isKan(Tile tile, GameWindow window) {
        if (this.hand.stream().filter(t -> t.getNumber() == tile.getNumber() && t.getSuit().equals(tile.getSuit())).count() == 4) {
            if (window.isCallConfirmed(MessageFormat.format(MessageConstants.MSG_KAN, this.playerNumber))) {
                meldKan(tile, false);
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

    public void discard(int index) {
        this.handPanelHolder.displayNotMyTurnBorder();
        this.handPanelHolder.getMainPanel().repaint();
        Tile tile = this.hand.remove(index);
        this.discard.add(tile);
        this.handPanelHolder.displayHand();
        this.discardPanelHolder.displayDiscard(tile);
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
            for (int i = 0; i < 3; i++) {
                for (Tile firstTile : chiTiles.get(i)) {
                    for (Tile secondTile : chiTiles.get(i + 1)) {
                        List<Tile> newCombination = new ArrayList<>();
                        newCombination.add(firstTile);
                        newCombination.add(secondTile);
                        combinations.add(newCombination);
                    }
                }
            }
            return combinations.stream().distinct().collect(Collectors.toList());
        }
        return Collections.emptyList();
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

    public void makeDiscardSelection(boolean displayHand, GameWindow window) {
        if (displayHand) {
            displayHandAndMelds();
        }
        isMyTurn = true;
        this.handPanelHolder.displayMyTurnBorder();
        this.handPanelHolder.getMainPanel().repaint();
        while (!isDiscardSelected) {
            //Waiting for the player to click on the tile they want to discard
        }
        discard(discardIndex);
    }

    public boolean isHandOpen() {
        return !this.melds.isEmpty();
    }

    public void reset() {
        this.hand.clear();
        this.discard.clear();
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

    public String getHandAsString() {
        StringBuilder str = new StringBuilder();
        int i = 1;
        for (Tile tile : this.hand) {
            str.append("(").append(i).append(") ");
            if (tile.getNumber() != 0) {
                str.append(tile.getNumber()).append(" ");
            }
            str.append(tile.getSuit()).append(", ");
            i++;
        }
        int length = str.length();
        str.delete(length - 2, length);
        return str.toString();
    }

    public String getMeldsAsString() {
        StringBuilder str = new StringBuilder();
        for (Meld meld : this.melds) {
            for (Tile tile : meld.getTiles()) {
                if (tile.getNumber() != 0) {
                    str.append(tile.getNumber()).append(" ");
                }
                str.append(tile.getSuit()).append(", ");
            }
            int length = str.length();
            str.delete(length - 2, length);
            str.append("; ");
        }
        return str.toString();
    }

    public String getDiscardAsString() {
        StringBuilder str = new StringBuilder();
        for (Tile tile : this.discard) {
            if (tile.getNumber() != 0) {
                str.append(tile.getNumber());
                str.append(" ");
            }
            str.append(tile.getSuit());
            str.append(", ");
        }
        int length = str.length();
        str.delete(length - 2, length);
        return str.toString();
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
}