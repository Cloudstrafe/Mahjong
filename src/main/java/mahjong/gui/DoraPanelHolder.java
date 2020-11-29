package mahjong.gui;

import mahjong.tile.Tile;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class DoraPanelHolder extends TilePanelHolder {
    private int currentCol;
    private static final int COL_MAX = 5;
    private static final int PANEL_WIDTH = 210;
    private static final int PANEL_HEIGHT = 80;

    public DoraPanelHolder() {
        super(1, 5, 0);
        currentCol = 0;
        mainPanel.setBorder(new TitledBorder("Dora"));
        mainPanel.setLayout(new GridLayout(rows, cols));
        mainPanel.setBounds(300, 300, PANEL_WIDTH, PANEL_HEIGHT);
        reset();
    }

    public void displayDora(Tile tile) {
        labels[0][currentCol].setIcon(null);
        labels[0][currentCol].setIcon(new ImageIcon(tile.getSmallTileFacingDown()));
        currentCol++;
    }

    public void reset() {
        for (int j = 1; j < COL_MAX; j++) {
            labels[0][j].setIcon(new ImageIcon(Tile.getBackOfTileSmall()));
        }
        currentCol = 0;
    }

    public void clearAll() {
        super.clearAll();
        reset();
    }

}
