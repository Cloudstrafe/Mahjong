package mahjong.gui;

import mahjong.PlayArea;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class PlayerFourHandPanelHolder extends AbstractHandPanelHolder {
    public PlayerFourHandPanelHolder(PlayArea playArea) {
        super(14, 1, 4, 10, 0, playArea, 130, 1120);
    }

    @Override
    public void displayHand() {
        clearAll();
        for (int j = 0; j < playArea.getHand().size(); j++) {
            this.labels[j][0].setIcon(new ImageIcon(playArea.getHand().get(j).getMediumTileFacingLeft()));
        }
    }

    @Override
    protected void onMouseClick(MouseEvent e) {
        if (playArea.isMyTurn()) {
            for (int j = 0; j < rows; j++) {
                if (e.getSource() == labels[j][0]) {
                    if (labels[j][0] != null) {
                        playArea.setMyTurn(false);
                        labels[j][0].setIcon(null);
                        playArea.setDiscardIndex(j);
                        playArea.setDiscardSelected(true);
                    }
                }
            }
        }
    }
}
