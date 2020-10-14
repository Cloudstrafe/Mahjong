package mahjong.gui;

import mahjong.Meld;
import mahjong.tile.Tile;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class MeldPanelHolder extends PanelHolder {
    private int currentRow;

    public MeldPanelHolder(int rows, int cols) {
        super(rows, cols);
        this.currentRow = 0;
        this.mainPanel.setBorder(new TitledBorder("Melds"));
    }

//TODO: kanning a pon

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

    @Override
    protected void clearAll() {
        super.clearAll();
        currentRow = 0;
    }

}
