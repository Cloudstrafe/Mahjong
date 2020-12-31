package mahjong.gui;

import mahjong.PlayArea;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class AbstractHandPanelHolder extends TilePanelHolder {
    protected int panelWidth;
    protected int panelHeight;
    protected PlayArea playArea;
    private final Border myTurnBorder;
    private final Border notMyTurnBorder;
    private TitledBorder handBorder;

    public AbstractHandPanelHolder(int rows, int cols, int playerNumber, int x, int y, PlayArea playArea, int width, int height) {
        super(rows, cols, playerNumber);
        this.panelWidth = width;
        this.panelHeight = height;
        this.myTurnBorder = new LineBorder(Color.GREEN, 4, true);
        this.notMyTurnBorder = new LineBorder(Color.BLACK, 4, true);
        this.handBorder = new TitledBorder(notMyTurnBorder, "Player " + playerNumber + "'s Hand");
        this.mainPanel.setBorder(handBorder);
        this.mainPanel.setLayout(new GridLayout(rows, cols));
        this.mainPanel.setBounds(x, y, panelWidth, panelHeight);
        this.playArea = playArea;
        GUIListener guiListener = new GUIListener();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.labels[i][j].addMouseListener(guiListener);
            }
        }
    }

    public void displayMyTurnBorder() {
        this.handBorder.setBorder(myTurnBorder);
    }

    public void displayNotMyTurnBorder() {
        this.handBorder.setBorder(notMyTurnBorder);
    }

    public abstract void displayHand();

    @Override
    public void clearAll() {
        super.clearAll();
        handBorder.setBorder(notMyTurnBorder);
    }

    protected abstract void onMouseClick(MouseEvent e);

    protected class GUIListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            onMouseClick(e);
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
