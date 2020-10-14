package mahjong.gui;

import mahjong.Player;

import javax.swing.*;
import java.awt.*;

public class SampleWindow {
    private final JFrame window = new JFrame("Mahjong");

    public SampleWindow() {
        window.setSize(1920, 1080);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        window.setLayout(new GridLayout(12,1));
        window.setVisible(true);
    }

    public void addPanels(Player player) {
        window.add(player.getPlayArea().getHandPanelHolder().getMainPanel());
        window.add(player.getPlayArea().getMeldPanelHolder().getMainPanel());
        window.add(player.getPlayArea().getDiscardPanelHolder().getMainPanel());
    }
}
