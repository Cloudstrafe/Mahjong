package mahjong;

import mahjong.gui.GameWindow;
import mahjong.yaku.YakuHandler;

import javax.swing.*;
import java.text.MessageFormat;

import static java.lang.System.exit;

public class Player {
    private PlayArea playArea;
    private String seat;
    private int points;
    private boolean isDealer;
    private final int playerNumber;

    public Player(String seat, boolean isDealer, int playerNumber) {
        this.playArea = new PlayArea(playerNumber);
        this.seat = seat;
        this.points = 25000;
        this.isDealer = isDealer;
        this.playerNumber = playerNumber;
    }

    public Player (Player player) {
        this.playArea = new PlayArea(player.playArea);
        this.seat = player.seat;
        this.points = player.points;
        this.isDealer = player.isDealer;
        this.playerNumber = player.playerNumber;
    }

    public void takeTurn(Deck deck, Deadwall deadwall, GameWindow window) {
        playArea.draw(deck, deadwall, window);
        if (YakuHandler.hasValidYaku(this) && window.isCallConfirmed(MessageFormat.format(MessageConstants.MSG_TSUMO, this.playerNumber))) {
            JOptionPane.showMessageDialog(window.getWindow(), MessageFormat.format(MessageConstants.MSG_WIN, this.playerNumber));
            exit(0);
        }
        playArea.makeDiscardSelection(false, window);
    }

    public void takeTurnAfterKan(Deadwall deadwall, GameWindow window) {
        playArea.draw(deadwall.getDrawTiles(), deadwall, window);
        if (YakuHandler.hasValidYaku(this) && window.isCallConfirmed(MessageFormat.format(MessageConstants.MSG_TSUMO, this.playerNumber))) {
            JOptionPane.showMessageDialog(window.getWindow(), MessageFormat.format(MessageConstants.MSG_WIN, this.playerNumber));
            exit(0);
        }
        playArea.makeDiscardSelection(false, window);
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
