package mahjong.gui;

import mahjong.tile.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class WaitPanelHolder extends TilePanelHolder {

    public WaitPanelHolder(Tile key, List<Tile> waits) {
        super(2, 2, 1);
        mainPanel.removeAll();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = .5;
        mainPanel.add(panels[0][0], gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        mainPanel.add(panels[0][1], gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = (double) 1 / (waits.size() + 1);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(panels[1][0], gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = (double) waits.size() / (waits.size() + 1);
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        mainPanel.add(panels[1][1], gridBagConstraints);
        labels[0][0].setText("Discard:");
        labels[0][1].setText("Waits:");
        labels[1][0].setIcon(new ImageIcon(key.getMediumTileFacingDown()));
        labels[1][1].setIcon(new ImageIcon(combineWaitIcons(waits)));
        JButton button = new JButton("Select");
        button.addActionListener(e -> {
            JOptionPane pane = getOptionPane((JComponent) e.getSource());
            pane.setValue(mainPanel);
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1.0;
        mainPanel.add(button, gridBagConstraints);
    }

    //TODO: Add an on hover to the discard options that displays waits

    private BufferedImage combineWaitIcons(List<Tile> waits) {
        if (!waits.isEmpty()) {
            BufferedImage finalImage = waits.get(0).getMediumTileFacingDown();
            for (int i = 1; i < waits.size(); i++) {
                finalImage = GameWindow.joinBufferedImage(finalImage, waits.get(i).getMediumTileFacingDown());
            }
            return finalImage;
        }
        return new BufferedImage(1, 1, 1);
    }

    protected JOptionPane getOptionPane(JComponent parent) {
        if (!(parent instanceof JOptionPane)) {
            return getOptionPane((JComponent) parent.getParent());
        } else {
            return (JOptionPane) parent;
        }
    }
}
