package mahjong.gui;

import mahjong.tile.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class DoraPanelHolder extends TilePanelHolder {
    private int currentCol;
    private static final int COL_MAX = 5;
    private static final int PANEL_WIDTH = 400;
    private static final int PANEL_HEIGHT = 175;
    public static final String RIICHI_STICK_FILEPATH = "/mahjong/gui/riichiStick.png";
    private static final String TSUMI_STICK_FILEPATH = "/mahjong/gui/tsumiStick.png";
    private static Logger logger = Logger.getLogger(DoraPanelHolder.class.getName());

    public DoraPanelHolder() {
        super(3, 6, 0);
        mainPanel.removeAll();
        currentCol = 0;
        mainPanel.setBorder(new TitledBorder("Dora"));
        try {
            BufferedImage riichiStickImage = ImageIO.read(getClass().getResource(RIICHI_STICK_FILEPATH));
            BufferedImage tsumiStickImage = ImageIO.read(getClass().getResource(TSUMI_STICK_FILEPATH));
            labels[1][0].setIcon(new ImageIcon(riichiStickImage));
            labels[2][0].setIcon(new ImageIcon(tsumiStickImage));
        } catch (IOException e) {
            logger.log(new LogRecord(Level.SEVERE, "Failed to load riichi or tsumi stick image"));
        }
        labels[1][0].setText("x0");
        labels[2][0].setText("x0");
        reset();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
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
        gridBagConstraints.gridwidth = 6;
        mainPanel.add(panels[1][0], gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
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

    @Override
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
