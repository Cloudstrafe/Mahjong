package mahjong.gui;

import mahjong.PlayArea;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HandPanelHolder extends PanelHolder {
    private static final int PANEL_WIDTH = 1120;
    private static final int PANEL_HEIGHT = 130;
    private PlayArea playArea;
    private final Border myTurnBorder;
    private final Border notMyTurnBorder;
    private TitledBorder handBorder;

    public HandPanelHolder(int rows, int cols, int playerNumber, int x, int y, PlayArea playArea) {
        super(rows, cols, playerNumber);
        this.myTurnBorder = new LineBorder(Color.GREEN, 4, true);
        this.notMyTurnBorder = new LineBorder(Color.BLACK, 4, true);
        this.handBorder = new TitledBorder(notMyTurnBorder, "Player " + playerNumber + "'s Hand");
        this.mainPanel.setBorder(handBorder);
        this.mainPanel.setLayout(new GridLayout(rows, cols));
        this.mainPanel.setBounds(x, y, PANEL_WIDTH, PANEL_HEIGHT);
        this.playArea = playArea;
        GUIListener guiListener = new GUIListener();
        for (int j = 0; j < cols; j++) {
            this.labels[0][j].addMouseListener(guiListener);
        }
    }

    public void displayMyTurnBorder() {
        this.handBorder.setBorder(myTurnBorder);
    }

    public void displayNotMyTurnBorder() {
        this.handBorder.setBorder(notMyTurnBorder);
    }

    public void displayHand(PlayArea playArea) {
        clearAll();
        for (int j = 0; j < playArea.getHand().size(); j++) {
            this.labels[0][j].setIcon(new ImageIcon(playArea.getHand().get(j).getMediumTileFacingDown()));
        }
    }

    private class GUIListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            for (int j = 0; j < cols; j ++) {
                if (playArea.isMyTurn()) {
                    if (e.getSource() == labels[0][j]) {
                        if (labels[0][j] != null) {
                            playArea.setMyTurn(false);
                            labels[0][j].setIcon(null);
                            playArea.setDiscardIndex(j);
                            playArea.setDiscardSelected(true);
                        }
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //no action implemented
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //no action implemented
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //no action implemented
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //no action implemented
        }
    }
}
