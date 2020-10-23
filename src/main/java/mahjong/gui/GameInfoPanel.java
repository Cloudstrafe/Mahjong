package mahjong.gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GameInfoPanel {
    private static final int PANEL_WIDTH = 220;
    private static final int PANEL_HEIGHT = 220;
    private static final int X_COORDINATE = 850;
    private static final int Y_COORDINATE = 400;
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
        this.playerOneScore = initializeLabel("P1Score",90,180);
        this.playerTwoScore = initializeLabel("P2Score",160,90);
        this.playerThreeScore = initializeLabel("P3Score",90,0);
        this.playerFourScore = initializeLabel("P4Score",5,90);
        this.playerOneSeat = initializeLabel("P1Seat",30,180);
        this.playerTwoSeat = initializeLabel("P2Seat",170,110);
        this.playerThreeSeat = initializeLabel("P3Seat",150,0);
        this.playerFourSeat = initializeLabel("P4Seat",5,70);
        this.deckTileCount = initializeLabel("DeckCount",90,90);
        this.currentRoundWind = initializeLabel("RoundWind",70,110);
        this.currentRoundNumber = initializeLabel("RoundNumber",110,110);
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

    public void setPlayerOneScore(JLabel playerOneScore) {
        this.playerOneScore = playerOneScore;
    }

    public void setPlayerTwoScore(JLabel playerTwoScore) {
        this.playerTwoScore = playerTwoScore;
    }

    public void setPlayerThreeScore(JLabel playerThreeScore) {
        this.playerThreeScore = playerThreeScore;
    }

    public void setPlayerFourScore(JLabel playerFourScore) {
        this.playerFourScore = playerFourScore;
    }

    public void setPlayerOneSeat(JLabel playerOneSeat) {
        this.playerOneSeat = playerOneSeat;
    }

    public void setPlayerTwoSeat(JLabel playerTwoSeat) {
        this.playerTwoSeat = playerTwoSeat;
    }

    public void setPlayerThreeSeat(JLabel playerThreeSeat) {
        this.playerThreeSeat = playerThreeSeat;
    }

    public void setPlayerFourSeat(JLabel playerFourSeat) {
        this.playerFourSeat = playerFourSeat;
    }

    public void setDeckTileCount(JLabel deckTileCount) {
        this.deckTileCount = deckTileCount;
    }

    public void setCurrentRoundWind(JLabel currentRoundWind) {
        this.currentRoundWind = currentRoundWind;
    }

    public void setCurrentRoundNumber(JLabel currentRoundNumber) {
        this.currentRoundNumber = currentRoundNumber;
    }
}
