package mahjong.gui;

import mahjong.PlayArea;

import javax.swing.*;

public class HandPanelHolder extends PanelHolder {

    public HandPanelHolder(int rows, int cols) {
        super(rows, cols);
    }

    public void displayHand(PlayArea playArea) {
        clearAll();
        for (int j = 0; j < cols; j++) {
            this.labels[0][j].setIcon(new ImageIcon(playArea.getHand().get(j).getMediumTileFacingDown()));
        }
    }
}
