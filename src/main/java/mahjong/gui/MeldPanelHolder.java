package mahjong.gui;

import mahjong.Meld;
import mahjong.tile.Tile;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MeldPanelHolder extends TilePanelHolder {
    private int currentRow;
    private static final int PANEL_WIDTH = 200;
    private static final int PANEL_HEIGHT = 255;

    public MeldPanelHolder(int rows, int cols, int playerNumber, int x, int y) {
        super(rows, cols, playerNumber);
        this.currentRow = 0;
        this.mainPanel.setBorder(new TitledBorder("Player " + playerNumber + "'s Melds"));
        this.mainPanel.setLayout(new GridLayout(rows, cols));
        this.mainPanel.setBounds(x, y, PANEL_WIDTH, PANEL_HEIGHT);
    }

    public void displayMeld(Meld meld) {
        for (int j = 0; j < meld.getTiles().size(); j++) {
            if (!meld.isOpen() && (j == 0 || j == meld.getTiles().size() - 1)) {
                this.labels[currentRow][j].setIcon(new ImageIcon(Tile.getBackOfTileSmall()));
            } else if (meld.isRun() && j == meld.getCalledTileIndex()) {
                this.labels[currentRow][j].setIcon(new ImageIcon(meld.getTiles().get(j).getSmallTileFacingRight()));
            } else {
                this.labels[currentRow][j].setIcon(new ImageIcon(meld.getTiles().get(j).getSmallTileFacingDown()));
            }
        }
        this.currentRow++;
    }

    public void kanAPon(Tile tile, int row) {
        labels[row][3].setIcon(new ImageIcon(tile.getSmallTileFacingDown()));
    }

    @Override
    public void clearAll() {
        super.clearAll();
        currentRow = 0;
    }

}
