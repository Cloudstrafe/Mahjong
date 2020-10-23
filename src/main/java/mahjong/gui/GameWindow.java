package mahjong.gui;

import mahjong.Player;
import mahjong.tile.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static javax.swing.JOptionPane.YES_NO_OPTION;

public class GameWindow {
    private final JFrame window = new JFrame("Mahjong");
    private GameInfoPanel gameInfoPanel;
    private DoraPanelHolder doraPanelHolder;

    public GameWindow(Player playerOne, Player playerTwo, Player playerThree, Player playerFour) {
        gameInfoPanel = new GameInfoPanel();
        doraPanelHolder = new DoraPanelHolder();
        window.setSize(1920, 1080);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addPanels(playerOne);
        addPanels(playerTwo);
        addPanels(playerThree);
        addPanels(playerFour);
        window.add(gameInfoPanel.getMainPanel());
        window.add(doraPanelHolder.getMainPanel());
        window.add(new JPanel());
        window.setVisible(true);
    }

    private void addPanels(Player player) {
        window.add(player.getPlayArea().getHandPanelHolder().getMainPanel());
        window.add(player.getPlayArea().getMeldPanelHolder().getMainPanel());
        window.add(player.getPlayArea().getDiscardPanelHolder().getMainPanel());
    }

    public boolean isCallConfirmed(String message) {
        int response = JOptionPane.showConfirmDialog(this.window, message, UIManager.getString("OptionPane.titleText"), YES_NO_OPTION);
        return response == JOptionPane.YES_OPTION;
    }

    public int getChiCallChoice(String message, List<List<Tile>> combinations) {
        Object[] panelArray = new Object[combinations.size()];
        for (int i = 0; i < combinations.size(); i++) {
            panelArray[i] = new ImageIcon(joinBufferedImage(combinations.get(i).get(0).getMediumTileFacingDown(), combinations.get(i).get(1).getMediumTileFacingDown()));
        }
        int response = -1;
        while (response == -1) {
            response = JOptionPane.showOptionDialog(this.window, message, UIManager.getString("OptionPane.titleText"), JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, panelArray, panelArray[0]);
        }
        return response;
    }

    public int isKanOrPonCallConfirmed(String message) {
        String[] options = new String[]{"Kan", "Pon", "Pass"};
        return JOptionPane.showOptionDialog(this.window, message, UIManager.getString("OptionPane.titleText"), JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
    }

    public JFrame getWindow() {
        return window;
    }

    public GameInfoPanel getGameInfoPanel() {
        return gameInfoPanel;
    }

    public DoraPanelHolder getDoraPanelHolder() {
        return doraPanelHolder;
    }

    public static BufferedImage joinBufferedImage(BufferedImage img1, BufferedImage img2) {

        //do some calculate first
        int offset  = 5;
        int wid = img1.getWidth()+img2.getWidth()+offset;
        int height = Math.max(img1.getHeight(),img2.getHeight())+offset;
        //create a new buffer and draw two image into the new image
        BufferedImage newImage = new BufferedImage(wid,height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        //fill background
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, wid, height);
        //draw image
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, img1.getWidth()+offset, 0);
        g2.dispose();
        return newImage;
    }
}
