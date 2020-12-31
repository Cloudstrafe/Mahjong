package mahjong.gui;

import mahjong.PlayArea;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class PlayerOneHandPanelHolder extends AbstractHandPanelHolder {
    public PlayerOneHandPanelHolder(PlayArea playArea) {
        super(1, 14, 1, 450, 888, playArea, 1020, 130);
    }

    @Override
    public void displayHand() {
        clearAll();
        for (int j = 0; j < playArea.getHand().size(); j++) {
            this.labels[0][j].setIcon(new ImageIcon(playArea.getHand().get(j).getMediumTileFacingDown()));
        }
    }

    @Override
    protected void onMouseClick(MouseEvent e) {
        if (playArea.isMyTurn()) {
            for (int j = 0; j < cols; j++) {
                if (e.getSource() == labels[0][j]) {
                    if (labels[0][j] != null) {
                        playArea.setMyTurn(false);
                        labels[0][j].setIcon(null);
                        playArea.setDiscardIndex(j);
                        playArea.setDiscardSelected(true);
                    }
                }
            }
        }
    }
}
