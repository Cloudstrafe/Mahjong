package mahjong;

import mahjong.gui.GameWindow;
import mahjong.tile.Tile;
import mahjong.yaku.YakuHandler;

import javax.swing.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.System.exit;

public class Player {
    private PlayArea playArea;
    private String seat;
    private int points;
    private boolean isDealer;
    private final int playerNumber;
    private boolean isInRiichi;
    private boolean hasRiichiTileInDiscard;
    private int sizeOfDiscardAfterRiichi;

    public Player(String seat, boolean isDealer, int playerNumber) {
        this.playArea = new PlayArea(playerNumber);
        this.seat = seat;
        this.points = 25000;
        this.isDealer = isDealer;
        this.playerNumber = playerNumber;
        this.isInRiichi = false;
        hasRiichiTileInDiscard = false;
    }

    public Player(Player player) {
        this.playArea = new PlayArea(player.playArea);
        this.seat = player.seat;
        this.points = player.points;
        this.isDealer = player.isDealer;
        this.playerNumber = player.playerNumber;
        this.isInRiichi = false;
        hasRiichiTileInDiscard = false;
    }

    public void takeTurn(Deck deck, Deadwall deadwall, GameWindow window) {
        Tile drawnTile = playArea.draw(deck, deadwall, window);
        turnHandler(window, drawnTile);
    }

    public void takeTurnAfterKan(Deadwall deadwall, GameWindow window) {
        Tile drawnTile = playArea.draw(deadwall.getDrawTiles(), deadwall, window);
        turnHandler(window, drawnTile);
    }

    private void turnHandler(GameWindow window, Tile drawnTile) {
        if (isInRiichi && !hasRiichiTileInDiscard) {
            hasRiichiTileInDiscard = sizeOfDiscardAfterRiichi == playArea.getDiscard().size();
        }
        if (YakuHandler.hasValidYaku(this) && window.isCallConfirmed(MessageFormat.format(MessageConstants.MSG_TSUMO, this.playerNumber))) {
            JOptionPane.showMessageDialog(window.getWindow(), MessageFormat.format(MessageConstants.MSG_WIN, this.playerNumber));
            exit(0);
        }
        if (!isInRiichi) {
            Map<Tile, List<Tile>> riichiTiles = YakuHandler.getRiichiTiles(this);
            if (!riichiTiles.isEmpty() && window.isCallConfirmed(MessageFormat.format(MessageConstants.MSG_RIICHI, this.playerNumber))) {
                if (riichiTiles.size() > 1) {
                    Tile discardTile = window.getRiichiDiscardChoice(MessageFormat.format(MessageConstants.MSG_SELECT_RIICHI_DISCARD, this.playerNumber), riichiTiles);
                    isInRiichi = true;
                    playArea.discard(this.getPlayArea().getHand().indexOf(discardTile), true);
                    sizeOfDiscardAfterRiichi = playArea.getDiscard().size();
                } else {
                    isInRiichi = true;
                    playArea.discard(this.getPlayArea().getHand().indexOf(new ArrayList<>(riichiTiles.keySet()).get(0)), true);
                    sizeOfDiscardAfterRiichi = playArea.getDiscard().size();
                }
            } else {
                playArea.makeDiscardSelection(false, window);
            }
        } else {
            playArea.discard(playArea.getHand().indexOf(drawnTile), !hasRiichiTileInDiscard);
        }
    }

    public boolean isInRiichi() {
        return isInRiichi;
    }

    public PlayArea getPlayArea() {
        return playArea;
    }

    public void setPlayArea(PlayArea playArea) {
        this.playArea = playArea;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isDealer() {
        return isDealer;
    }

    public void setDealer(boolean dealer) {
        isDealer = dealer;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

}
