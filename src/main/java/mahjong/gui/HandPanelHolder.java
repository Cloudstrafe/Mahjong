package mahjong.gui;

import mahjong.PlayArea;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class HandPanelHolder extends PanelHolder {

    public HandPanelHolder(int rows, int cols, int playerNumber) {
        super(rows, cols, playerNumber);
        this.mainPanel.setBorder(new TitledBorder("Player " + playerNumber + "'s Hand"));
        this.mainPanel.setLayout(new GridLayout(rows, cols));
    }

    public void displayHand(PlayArea playArea) {
        clearAll();
        for (int j = 0; j < playArea.getHand().size(); j++) {
            this.labels[0][j].setIcon(new ImageIcon(playArea.getHand().get(j).getMediumTileFacingDown()));
        }
    }
}
