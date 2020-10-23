package mahjong.gui;

import mahjong.tile.Tile;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GameInfoPanel {
    private static final int PANEL_WIDTH = 220;
    private static final int PANEL_HEIGHT = 220;
    private static final int X_COORDINATE = 850;
    private static final int Y_COORDINATE = 400;
    private final JLabel tileIcon = new JLabel(new ImageIcon(Tile.getBackOfTileSmall()));
    private JPanel mainPanel;
    private JLabel playerOneScore;
    private JLabel playerTwoScore;
    private JLabel playerThreeScore;
    private JLabel playerFourScore;
    private JLabel playerOneSeat;
    private JLabel playerTwoSeat;
    private JLabel playerThreeSeat;
    private JLabel playerFourSeat;
    private JLabel deckTileCount;
    private JLabel currentRoundWind;
    private JLabel currentRoundNumber;

    public GameInfoPanel() {
        this.mainPanel = new JPanel();
        this.mainPanel.setBorder(new TitledBorder("Game Info"));
        this.mainPanel.setLayout(null);
        this.mainPanel.setBounds(X_COORDINATE, Y_COORDINATE, PANEL_WIDTH, PANEL_HEIGHT);
        this.playerOneScore = initializeLabel("P1Score",92,180);
        this.playerTwoScore = initializeLabel("P2Score",175,80);
        this.playerThreeScore = initializeLabel("P3Score",92,0);
        this.playerFourScore = initializeLabel("P4Score",10,80);
        this.playerOneSeat = initializeLabel("P1Seat",105,165);
        this.playerTwoSeat = initializeLabel("P2Seat",190,95);
        this.playerThreeSeat = initializeLabel("P3Seat",105,15);
        this.playerFourSeat = initializeLabel("P4Seat",25,95);
        this.deckTileCount = initializeLabel("DeckCount",100,85);
        tileIcon.setLocation(92, 85);
        tileIcon.setSize(37, 50);
        tileIcon.setVisible(true);
        mainPanel.add(tileIcon);
        this.currentRoundWind = initializeLabel("RoundWind",92,120);
        this.currentRoundNumber = initializeLabel("RoundNumber",120,120);
    }

    private JLabel initializeLabel(String label, int x, int y) {
        JLabel newLabel = new JLabel(label);
        newLabel.setLocation(x, y);
        newLabel.setSize(50,50);
        newLabel.setVisible(true);
        this.mainPanel.add(newLabel);
        return newLabel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JLabel getPlayerOneScore() {
        return playerOneScore;
    }

    public JLabel getPlayerTwoScore() {
        return playerTwoScore;
    }

    public JLabel getPlayerThreeScore() {
        return playerThreeScore;
    }

    public JLabel getPlayerFourScore() {
        return playerFourScore;
    }

    public JLabel getPlayerOneSeat() {
        return playerOneSeat;
    }

    public JLabel getPlayerTwoSeat() {
        return playerTwoSeat;
    }

    public JLabel getPlayerThreeSeat() {
        return playerThreeSeat;
    }

    public JLabel getPlayerFourSeat() {
        return playerFourSeat;
    }

    public JLabel getDeckTileCount() {
        return deckTileCount;
    }

    public JLabel getCurrentRoundWind() {
        return currentRoundWind;
    }

    public JLabel getCurrentRoundNumber() {
        return currentRoundNumber;
    }
}
