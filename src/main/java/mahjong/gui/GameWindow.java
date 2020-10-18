package mahjong.gui;

import mahjong.Player;

import javax.swing.*;

import static javax.swing.JOptionPane.YES_NO_OPTION;

public class GameWindow {
    private final JFrame window = new JFrame("Mahjong");

    public GameWindow() {
        window.setSize(1920, 1080);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public void addPanels(Player player) {
        window.add(player.getPlayArea().getHandPanelHolder().getMainPanel());
        window.add(player.getPlayArea().getMeldPanelHolder().getMainPanel());
        window.add(player.getPlayArea().getDiscardPanelHolder().getMainPanel());
        window.add(new JPanel());
    }

    public boolean isCallConfirmed(String message) {
        int response = JOptionPane.showConfirmDialog(this.window, message, UIManager.getString("OptionPane.titleText"), YES_NO_OPTION);
        return response == JOptionPane.YES_OPTION;
    }

//    public boolean isChiCallConfirmed() {
//        //TODO: implement using JOptionPane.showOptionDialog()
//    }

    public int isKanOrPonCallConfirmed(String message) {
        String[] options = new String[]{"Kan", "Pon", "Pass"};
        int response = JOptionPane.showOptionDialog(this.window, message, UIManager.getString("OptionPane.titleText"), JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
        System.out.println(response);
        return response;
    }

    public JFrame getWindow() {
        return window;
    }
}
