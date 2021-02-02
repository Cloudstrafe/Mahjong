package mahjong.gui;

import javax.swing.*;

public class TilePanelHolder {
    protected int rows;
    protected int cols;
    protected int playerNumber;
    protected JLabel[][] labels;
    protected JPanel[][] panels;
    protected JPanel mainPanel;

    public TilePanelHolder(int rows, int cols, int playerNumber) {
        this.rows = rows;
        this.cols = cols;
        this.playerNumber = playerNumber;
        this.labels = new JLabel[rows][cols];
        this.mainPanel = new JPanel();
        this.panels = new JPanel[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.panels[i][j] = new JPanel();
                this.mainPanel.add(this.panels[i][j]);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.labels[i][j] = new JLabel();
                this.panels[i][j].add(this.labels[i][j]);
            }
        }
    }

    public void clearAll() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.labels[i][j].setIcon(null);
            }
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
