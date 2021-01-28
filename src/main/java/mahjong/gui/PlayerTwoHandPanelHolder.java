package mahjong.gui;

import mahjong.PlayArea;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class PlayerTwoHandPanelHolder extends AbstractHandPanelHolder {
    public PlayerTwoHandPanelHolder(PlayArea playArea) {
        super(14, 1, 2, 1790, 0, playArea, 130, 1020);
    }

    @Override
    public void displayHand() {
        clearAll();
        for (int j = playArea.getHand().size() - 1; j >= 0; j--) {
            this.labels[j][0].setIcon(new ImageIcon(playArea.getHand().get(playArea.getHand().size() - j - 1).getMediumTileFacingRight()));
        }
    }

    @Override
    protected void onMouseClick(MouseEvent e) {
        if (playArea.isMyTurn()) {
            for (int j = 0; j < rows; j++) {
                if (e.getSource() == labels[j][0] && labels[j][0] != null) {
                    playArea.setMyTurn(false);
                    labels[j][0].setIcon(null);
                    playArea.setDiscardIndex(playArea.getHand().size() - j - 1);
                    playArea.setDiscardSelected(true);
                }
            }
        }
    }
}
