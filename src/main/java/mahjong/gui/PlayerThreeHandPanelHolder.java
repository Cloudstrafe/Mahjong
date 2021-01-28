package mahjong.gui;

import mahjong.PlayArea;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class PlayerThreeHandPanelHolder extends AbstractHandPanelHolder {
    public PlayerThreeHandPanelHolder(PlayArea playArea) {
        super(1, 14, 3, 450, 0, playArea, 1020, 130);
    }

    @Override
    public void displayHand() {
        clearAll();
        for (int j = playArea.getHand().size() - 1; j >= 0; j--) {
            this.labels[0][j].setIcon(new ImageIcon(playArea.getHand().get(playArea.getHand().size() - j - 1).getMediumTileFacingUp()));
        }
    }

    @Override
    protected void onMouseClick(MouseEvent e) {
        if (playArea.isMyTurn()) {
            for (int j = 0; j < cols; j++) {
                if (e.getSource() == labels[0][j] && labels[0][j] != null) {
                    playArea.setMyTurn(false);
                    labels[0][j].setIcon(null);
                    playArea.setDiscardIndex(playArea.getHand().size() - j - 1);
                    playArea.setDiscardSelected(true);
                }
            }
        }
    }
}
