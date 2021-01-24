package mahjong.gui;

import mahjong.tile.Tile;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class DoraPanelHolder extends TilePanelHolder {
    private int currentCol;
    private static final int COL_MAX = 5;
    private static final int PANEL_WIDTH = 400;
    private static final int PANEL_HEIGHT = 175;
    public static final String RIICHI_STICK_FILEPATH = "src/main/java/mahjong/gui/riichiStick.png";
    private static final String TSUMI_STICK_FILEPATH = "src/main/java/mahjong/gui/tsumiStick.png";

    public DoraPanelHolder() {
        super(3, 6, 0);
        mainPanel.removeAll();
        currentCol = 0;
        mainPanel.setBorder(new TitledBorder("Dora"));
        labels[1][0].setIcon(new ImageIcon(RIICHI_STICK_FILEPATH));
        labels[1][0].setText("x0");
        labels[2][0].setText("x0");
        labels[2][0].setIcon(new ImageIcon(TSUMI_STICK_FILEPATH));
        reset();
        GridBagLayout gridBagLayout = new GridBagLayout();
//        gridBagLayout.columnWeights = new double[] {.2, .1, .5, .1, .1};
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
//        gridBagConstraints.weightx = .2;
        mainPanel.add(panels[0][0], gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        mainPanel.add(panels[0][1], gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        mainPanel.add(panels[0][2], gridBagConstraints);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        mainPanel.add(panels[0][3], gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        mainPanel.add(panels[0][4], gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
//        gridBagConstraints.weightx = .8;
        gridBagConstraints.gridwidth = 6;
        mainPanel.add(panels[1][0], gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
//        gridBagConstraints.weightx = .8;
        gridBagConstraints.gridwidth = 6;
        mainPanel.add(panels[2][0], gridBagConstraints);
        mainPanel.setBounds(350, 175, PANEL_WIDTH, PANEL_HEIGHT);
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
        labels[1][0].setIcon(new ImageIcon(RIICHI_STICK_FILEPATH));
        labels[2][0].setIcon(new ImageIcon(TSUMI_STICK_FILEPATH));
        reset();
    }

    public void setRiichiStickCount(int riichiStickCount) {
        labels[1][0].setText("x" + riichiStickCount);
    }

    public void setTsumiStickCount(int tsumiStickCount) {
        labels[2][0].setText("x" + tsumiStickCount);
    }
}
