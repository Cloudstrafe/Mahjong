package mahjong.gui;

import mahjong.tile.Tile;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class DiscardPanelHolder extends TilePanelHolder {
    private int currentRow;
    private int currentCol;
    private static final int ROW_MAX = 4;
    private static final int COL_MAX = 6;
    private static final int PANEL_WIDTH = 252;
    private static final int PANEL_HEIGHT = 220;

    public DiscardPanelHolder(int rows, int cols, int x, int y, int playerNumber) {
        super(rows, cols, playerNumber);
        this.currentRow = 0;
        this.currentCol = 0;
        this.mainPanel.setBorder(new TitledBorder("Player " + playerNumber + "'s Discards"));
        this.mainPanel.setLayout(new GridLayout(rows, cols));
        this.mainPanel.setBounds(x, y, PANEL_WIDTH, PANEL_HEIGHT);
    }

    public void displayDiscard(Tile tile, boolean discardSideways) {
        if (!discardSideways) {
            this.labels[currentRow][currentCol].setIcon(new ImageIcon(tile.getSmallTileFacingDown()));
        } else {
            this.labels[currentRow][currentCol].setIcon(new ImageIcon(tile.getSmallTileFacingRight()));
        }
        currentCol++;
        if (currentCol == COL_MAX) {
            currentCol = 0;
            currentRow++;
        }
    }

    @Override
    public void clearAll() {
        super.clearAll();
        currentRow = 0;
        currentCol = 0;
    }

    public void removeLastDiscard() {
        if (currentCol == 0) {
            currentRow--;
            currentCol = COL_MAX;
        }
        currentCol--;
        this.labels[currentRow][currentCol].setIcon(null);
    }
}
