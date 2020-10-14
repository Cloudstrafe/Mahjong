package mahjong.gui;

import mahjong.tile.Tile;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class DiscardPanelHolder extends PanelHolder {
    private int currentRow;
    private int currentCol;
    private static final int ROW_MAX = 4;
    private static final int COL_MAX = 6;

    public DiscardPanelHolder(int rows, int cols) {
        super(rows, cols);
        this.currentRow = 0;
        this.currentCol = 0;
        this.mainPanel.setBorder(new TitledBorder("Discards"));
    }

    public void displayDiscard(Tile tile) {
        this.labels[currentRow][currentCol].setIcon(new ImageIcon(tile.getSmallTileFacingDown()));
        currentCol++;
        if (currentCol == COL_MAX) {
            currentCol = 0;
            currentRow++;
        }
    }

    @Override
    protected void clearAll() {
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
