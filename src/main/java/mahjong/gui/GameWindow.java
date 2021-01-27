package mahjong.gui;

import mahjong.MessageConstants;
import mahjong.Player;
import mahjong.ScoringResult;
import mahjong.tile.RotateImage;
import mahjong.tile.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static javax.swing.JOptionPane.YES_NO_OPTION;

public class GameWindow {
    private final JFrame window = new JFrame("Mahjong");
    private final GameInfoPanel gameInfoPanel;
    private final DoraPanelHolder doraPanelHolder;
    private BufferedImage playerOneRiichiImage;
    private BufferedImage playerTwoRiichiImage;
    private BufferedImage playerThreeRiichiImage;
    private BufferedImage playerFourRiichiImage;
    private final JLabel playerOneRiichiIndicator;
    private final JLabel playerTwoRiichiIndicator;
    private final JLabel playerThreeRiichiIndicator;
    private final JLabel playerFourRiichiIndicator;
    private static final String OPTION_PANE_TITLE_TEXT = "OptionPane.titleText";
    private static final Logger logger = Logger.getLogger(GameWindow.class.getName());

    public GameWindow(Player playerOne, Player playerTwo, Player playerThree, Player playerFour) {
        gameInfoPanel = new GameInfoPanel();
        doraPanelHolder = new DoraPanelHolder();
        setImages();
        playerOneRiichiIndicator = new JLabel();
        playerOneRiichiIndicator.setIcon(new ImageIcon(playerOneRiichiImage));
        playerOneRiichiIndicator.setBounds(800, 850, 321, 32);
        playerOneRiichiIndicator.setVisible(false);
        playerTwoRiichiIndicator = new JLabel();
        playerTwoRiichiIndicator.setIcon(new ImageIcon(playerTwoRiichiImage));
        playerTwoRiichiIndicator.setBounds(1332, 350, 32, 321);
        playerTwoRiichiIndicator.setVisible(false);
        playerThreeRiichiIndicator = new JLabel();
        playerThreeRiichiIndicator.setIcon(new ImageIcon(playerThreeRiichiImage));
        playerThreeRiichiIndicator.setBounds(800, 138, 321, 32);
        playerThreeRiichiIndicator.setVisible(false);
        playerFourRiichiIndicator = new JLabel();
        playerFourRiichiIndicator.setIcon(new ImageIcon(playerFourRiichiImage));
        playerFourRiichiIndicator.setBounds(556, 350, 32, 321);
        playerFourRiichiIndicator.setVisible(false);
        window.setSize(1920, 1080);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addPanels(playerOne);
        addPanels(playerTwo);
        addPanels(playerThree);
        addPanels(playerFour);
        window.add(gameInfoPanel.getMainPanel());
        window.add(doraPanelHolder.getMainPanel());
        window.add(playerOneRiichiIndicator);
        window.add(playerTwoRiichiIndicator);
        window.add(playerThreeRiichiIndicator);
        window.add(playerFourRiichiIndicator);
        window.add(new JPanel());
        window.setVisible(true);
    }

    protected void setImages() {
        try {
            playerOneRiichiImage = ImageIO.read(new File(DoraPanelHolder.RIICHI_STICK_FILEPATH));
            playerTwoRiichiImage = ImageIO.read(new File(DoraPanelHolder.RIICHI_STICK_FILEPATH));
            playerThreeRiichiImage = ImageIO.read(new File(DoraPanelHolder.RIICHI_STICK_FILEPATH));
            playerFourRiichiImage = ImageIO.read(new File(DoraPanelHolder.RIICHI_STICK_FILEPATH));
        } catch (IOException e) {
            logger.log(new LogRecord(Level.SEVERE, "Failed to load image " + DoraPanelHolder.RIICHI_STICK_FILEPATH));
        }
        playerTwoRiichiImage = RotateImage.rotate(playerTwoRiichiImage, 90.0d);
        playerFourRiichiImage = RotateImage.rotate(playerFourRiichiImage, 90.0d);

    }

    private void addPanels(Player player) {
        window.add(player.getPlayArea().getHandPanelHolder().getMainPanel());
        window.add(player.getPlayArea().getMeldPanelHolder().getMainPanel());
        window.add(player.getPlayArea().getDiscardPanelHolder().getMainPanel());
    }

    public boolean isConfirmed(String message) {
        int response = JOptionPane.showConfirmDialog(this.window, message, UIManager.getString(OPTION_PANE_TITLE_TEXT), YES_NO_OPTION);
        return response == JOptionPane.YES_OPTION;
    }

    public int getChiCallChoice(String message, List<List<Tile>> combinations) {
        Object[] panelArray = new Object[combinations.size()];
        for (int i = 0; i < combinations.size(); i++) {
            panelArray[i] = new ImageIcon(joinBufferedImage(combinations.get(i).get(0).getMediumTileFacingDown(), combinations.get(i).get(1).getMediumTileFacingDown()));
        }
        int response = -1;
        while (response == -1) {
            response = JOptionPane.showOptionDialog(this.window, message, UIManager.getString(OPTION_PANE_TITLE_TEXT), JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, panelArray, panelArray[0]);
        }
        return response;
    }

    public String getScoreWindowMessage(ScoringResult scoringResult, Player player) {
        StringBuilder message = new StringBuilder();
        message.append(MessageFormat.format(MessageConstants.MSG_WIN, player.getPlayerNumber(), player.getPlayerNumber()));
        message.append("\n\n");
        for (String yaku : scoringResult.getYaku()) {
            message.append(yaku).append("\n");
        }
        message.append("\n").append(scoringResult.getHan()).append(" Han ").append(scoringResult.getFu()).append(" Fu\n");
        message.append(scoringResult.getCost().getTotal()).append(" Points");
        return message.toString();
    }

    public Tile getRiichiDiscardChoice(String message, Map<Tile, List<Tile>> options) {
        Object[] panelArray = new Object[options.size()];
        List<Tile> keys = new ArrayList<>(options.keySet());
        for (int i = 0; i < options.size(); i++) {
            WaitPanelHolder waitPanelHolder = new WaitPanelHolder(keys.get(i), options.get(keys.get(i)));
            panelArray[i] = waitPanelHolder.getMainPanel();
        }
        int response = -1;
        while (response == -1) {
            response = JOptionPane.showOptionDialog(this.window, message, UIManager.getString(OPTION_PANE_TITLE_TEXT), JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, panelArray, null);
        }
        return keys.get(response);
    }

    public int isKanOrPonCallConfirmed(String message) {
        String[] options = new String[]{"Kan", "Pon", "Pass"};
        return JOptionPane.showOptionDialog(this.window, message, UIManager.getString(OPTION_PANE_TITLE_TEXT), JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
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

    public JLabel getPlayerOneRiichiIndicator() {
        return playerOneRiichiIndicator;
    }

    public JLabel getPlayerTwoRiichiIndicator() {
        return playerTwoRiichiIndicator;
    }

    public JLabel getPlayerThreeRiichiIndicator() {
        return playerThreeRiichiIndicator;
    }

    public JLabel getPlayerFourRiichiIndicator() {
        return playerFourRiichiIndicator;
    }

    public static BufferedImage joinBufferedImage(BufferedImage img1, BufferedImage img2) {

        //do some calculate first
        int offset = 0;
        int wid = img1.getWidth() + img2.getWidth() + offset;
        int height = Math.max(img1.getHeight(), img2.getHeight()) + offset;
        //create a new buffer and draw two image into the new image
        BufferedImage newImage = new BufferedImage(wid, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        //fill background
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, wid, height);
        //draw image
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, img1.getWidth() + offset, 0);
        g2.dispose();
        return newImage;
    }
}
