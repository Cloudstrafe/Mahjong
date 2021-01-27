package mahjong;

import mahjong.gui.GameWindow;
import mahjong.tile.Tile;
import mahjong.yaku.YakuHandler;

import javax.swing.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Player {
    private PlayArea playArea;
    private String seat;
    private int points;
    private boolean isDealer;
    private final int playerNumber;
    private boolean isInRiichi;
    private boolean isInDoubleRiichi;
    private boolean isIppatsu;
    private boolean isReplacementDraw;
    private boolean hasRiichiTileInDiscard;
    private boolean hasRiichiDeposit;
    private int sizeOfDiscardAfterRiichi;
    private List<Tile> waits;
    private boolean isInTemporaryFuriten;
    private boolean isInPermanentFuriten;
    private boolean isFirstTurn;

    public Player(String seat, boolean isDealer, int playerNumber) {
        playArea = new PlayArea(playerNumber);
        this.seat = seat;
        points = 25000;
        this.isDealer = isDealer;
        this.playerNumber = playerNumber;
        isInRiichi = false;
        isInDoubleRiichi = false;
        isIppatsu = false;
        isReplacementDraw = false;
        hasRiichiTileInDiscard = false;
        waits = new ArrayList<>();
        isInTemporaryFuriten = false;
        isInPermanentFuriten = false;
        isFirstTurn = true;
    }

    public Player(Player player) {
        this.playArea = new PlayArea(player.playArea);
        this.seat = player.seat;
        this.points = player.points;
        this.isDealer = player.isDealer;
        this.playerNumber = player.playerNumber;
        this.isInRiichi = player.isInRiichi;
        this.isInDoubleRiichi = player.isInDoubleRiichi;
        this.hasRiichiDeposit = player.hasRiichiDeposit;
        this.sizeOfDiscardAfterRiichi = player.sizeOfDiscardAfterRiichi;
        this.isIppatsu = player.isIppatsu;
        this.isReplacementDraw = player.isReplacementDraw;
        this.hasRiichiTileInDiscard = player.hasRiichiTileInDiscard;
        this.waits = player.waits;
        this.isInTemporaryFuriten = player.isInTemporaryFuriten;
        this.isInPermanentFuriten = player.isInPermanentFuriten;
        this.isFirstTurn = player.isFirstTurn;
    }

    public void takeTurn(Game game) {
        Tile drawnTile = playArea.draw(game, false);
        turnHandler(game.getWindow(), drawnTile, game);
    }

    public void takeTurnAfterKan(Game game) {
        Tile drawnTile = playArea.draw(game, true);
        isReplacementDraw = true;
        turnHandler(game.getWindow(), drawnTile, game);
    }

    private void turnHandler(GameWindow window, Tile drawnTile, Game game) {
        isInTemporaryFuriten = false;
        if (isInRiichi && !hasRiichiTileInDiscard) {
            hasRiichiTileInDiscard = sizeOfDiscardAfterRiichi == playArea.getDiscard().size();
        }
        if (YakuHandler.hasValidYaku(this) && window.isConfirmed(MessageFormat.format(MessageConstants.MSG_TSUMO, this.playerNumber))) {
            JOptionPane.showMessageDialog(window.getWindow(), MessageFormat.format(MessageConstants.MSG_WIN, this.playerNumber));
            game.getTurnQueue().add(this);
            game.endRound(this, drawnTile, null, false, true);
        }
        isReplacementDraw = false;
        isIppatsu = false;
        if (!isInRiichi) {
            Map<Tile, List<Tile>> riichiTiles = YakuHandler.getRiichiTiles(this);
            if (!riichiTiles.isEmpty() && points >= 1000 && window.isConfirmed(MessageFormat.format(MessageConstants.MSG_RIICHI, this.playerNumber))) {
                if (riichiTiles.size() > 1) {
                    Tile discardTile = window.getRiichiDiscardChoice(MessageFormat.format(MessageConstants.MSG_SELECT_RIICHI_DISCARD, this.playerNumber), riichiTiles);
                    waits = riichiTiles.get(discardTile);
                    playArea.discard(this.getPlayArea().getHand().indexOf(discardTile), true);
                } else {
                    waits = riichiTiles.get(new ArrayList<>(riichiTiles.keySet()).get(0));
                    playArea.discard(this.getPlayArea().getHand().indexOf(new ArrayList<>(riichiTiles.keySet()).get(0)), true);
                }
                if (isFirstTurn) {
                    isInDoubleRiichi = true;
                }
                isInRiichi = true;
                isIppatsu = true;
                sizeOfDiscardAfterRiichi = playArea.getDiscard().size();
                isInPermanentFuriten = isInFuriten();
            } else {
                playArea.makeDiscardSelection(false, window);
                isInTemporaryFuriten = isInFuriten();
                waits = YakuHandler.getWaitTiles(new Player(this));
            }
        } else {
            playArea.discard(playArea.getHand().indexOf(drawnTile), !hasRiichiTileInDiscard);
            isInTemporaryFuriten = isInFuriten();
            waits = YakuHandler.getWaitTiles(new Player(this));
        }
        isFirstTurn = false;
        playArea.displayHandAndMelds();
    }

    public boolean isInFuriten() {
        for (Tile wait : waits) {
            for (Tile discard : getPlayArea().getDiscard()) {
                if (discard.equals(wait)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void reset() {
        isInRiichi = false;
        isInDoubleRiichi = false;
        isIppatsu = false;
        isReplacementDraw = false;
        hasRiichiTileInDiscard = false;
        hasRiichiDeposit = false;
        sizeOfDiscardAfterRiichi = -1;
        waits.clear();
        isInTemporaryFuriten = false;
        isInPermanentFuriten = false;
        isFirstTurn = true;
    }

    public void newGame(String wind) {
        reset();
        points = 25000;
        isDealer = false;
        seat = wind;
        playArea.reset();
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

    public void setWaits(List<Tile> waits) {
        this.waits = waits;
    }

    public void setInTemporaryFuriten(boolean inTemporaryFuriten) {
        isInTemporaryFuriten = inTemporaryFuriten;
    }

    public void setInPermanentFuriten(boolean inPermanentFuriten) {
        isInPermanentFuriten = inPermanentFuriten;
    }

    public boolean isInTemporaryFuriten() {
        return isInTemporaryFuriten;
    }

    public boolean isInPermanentFuriten() {
        return isInPermanentFuriten;
    }

    public boolean hasRiichiDeposit() {
        return hasRiichiDeposit;
    }

    public void setInRiichi(boolean inRiichi) {
        isInRiichi = inRiichi;
    }

    public void setHasRiichiDeposit(boolean hasRiichiDeposit) {
        this.hasRiichiDeposit = hasRiichiDeposit;
    }

    public boolean isIppatsu() {
        return isIppatsu;
    }

    public void setIppatsu(boolean ippatsu) {
        isIppatsu = ippatsu;
    }

    public boolean isFirstTurn() {
        return isFirstTurn;
    }

    public void setFirstTurn(boolean firstTurn) {
        isFirstTurn = firstTurn;
    }

    public boolean isInDoubleRiichi() {
        return isInDoubleRiichi;
    }

    public void setInDoubleRiichi(boolean inDoubleRiichi) {
        isInDoubleRiichi = inDoubleRiichi;
    }

    public boolean isReplacementDraw() {
        return isReplacementDraw;
    }

    public void setReplacementDraw(boolean replacementDraw) {
        isReplacementDraw = replacementDraw;
    }
}
