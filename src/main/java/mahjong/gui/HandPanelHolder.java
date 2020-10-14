package mahjong.gui;

import mahjong.PlayArea;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class HandPanelHolder extends PanelHolder {

    public HandPanelHolder(int rows, int cols) {
        super(rows, cols);
        this.mainPanel.setBorder(new TitledBorder("Hand"));
    }

    public void displayHand(PlayArea playArea) {
        clearAll();
        for (int j = 0; j < playArea.getHand().size(); j++) {
            this.labels[0][j].setIcon(new ImageIcon(playArea.getHand().get(j).getMediumTileFacingDown()));
        }
    }
}
