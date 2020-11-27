package mahjong.gui;

import mahjong.tile.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class WaitPanelHolder extends TilePanelHolder {
    public WaitPanelHolder(Tile key, List<Tile> waits) {
        super(2, 2, 1);
        mainPanel.setLayout(new GridLayout(3, 2));
        labels[0][0].setText("Discard:");
        labels[0][1].setText("Waits:");
        labels[1][0].setIcon(new ImageIcon(key.getMediumTileFacingDown()));
        labels[1][1].setIcon(new ImageIcon(combineWaitIcons(waits)));
        JButton button = new JButton("Select");
        button.addActionListener(e -> {
            JOptionPane pane = getOptionPane((JComponent) e.getSource());
            pane.setValue(mainPanel);
        });
        mainPanel.add(button);
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
