package mahjong.gui;

import mahjong.PlayArea;

import javax.swing.*;

public class SampleWindow {
    private JFrame window = new JFrame("Mahjong");
    private JPanel handPanel = new JPanel();
    private HandPanelHolder playerHand;

    public SampleWindow() {
        playerHand = new HandPanelHolder(1, 14);
        window.add(playerHand.getMainPanel());
        window.setSize(1280, 720);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        window.setVisible(true);
    }

    public void displayHand(PlayArea playArea) {
        playerHand.displayHand(playArea);
    }
}
