package mahjong.gui;

import mahjong.PlayArea;
import mahjong.tile.Tile;

import javax.swing.*;
import java.awt.*;

public class SampleWindow {
    private JFrame window = new JFrame("Mahjong");
    private JPanel handPanel = new JPanel();
    private JPanel[][] panelHolder;
    private JLabel[] handLabels;
    private JLabel[] meldLabels;
    private static final int ROWS = 2;
    private static final int COLS = 14;

    public SampleWindow() {
        handLabels = new JLabel[COLS];
        meldLabels = new JLabel[COLS];
        handPanel.setLayout(new GridLayout(ROWS, COLS));
        panelHolder = new JPanel[ROWS][COLS];
        for (int m = 0; m < ROWS; m++) {
            for (int n = 0; n < COLS; n++) {
                panelHolder[m][n] = new JPanel();
                handPanel.add(panelHolder[m][n]);
            }
        }
        for (int m = 0; m < COLS; m++) {
            handLabels[m] = new JLabel();
            meldLabels[m] = new JLabel();
            panelHolder[0][m].add(handLabels[m]);
            panelHolder[1][m].add(meldLabels[m]);
        }
        window.add(handPanel);
        window.setSize(1100, 250);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        window.setVisible(true);
    }

    public void displayHand(PlayArea playArea) {
        clearAll();
        for (int v = 0; v < playArea.getHand().size(); v++) {
            handLabels[v].setIcon(new ImageIcon(playArea.getHand().get(v).getMediumTileFacingDown()));
        }
        int k = 0;
        for (int v = 0; v < playArea.getMelds().size(); v++) {
            for (Tile tile : playArea.getMelds().get(v).getTiles()) {
                meldLabels[k].setIcon(new ImageIcon(tile.getMediumTileFacingDown()));
                k++;
            }
        }
    }

    private void clearAll() {
        for (int v = 0; v < COLS; v++) {
            handLabels[v].setIcon(null);
            meldLabels[v].setIcon(null);
        }
    }
}
