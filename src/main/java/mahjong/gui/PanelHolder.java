package mahjong.gui;

import javax.swing.*;

public class PanelHolder {
    protected int rows;
    protected int cols;
    protected JLabel[][] labels;
    protected JPanel[][] panels;
    protected JPanel mainPanel;

    public PanelHolder(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
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

    protected void clearAll() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.labels[i][j].setIcon(null);
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public JLabel[][] getLabels() {
        return labels;
    }

    public JPanel[][] getPanels() {
        return panels;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}