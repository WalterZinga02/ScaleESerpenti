package gui;

import box.AbstractBox;
import box.LadderBox;
import box.SnakeBox;
import playboard.FinalPlayboard;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameBoardGUI extends JFrame {

    private JPanel boardPanel;
    private JLabel[][] boardLabels;

    public GameBoardGUI(int rows, int columns) {
        setTitle("Game Board");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for the board
        boardPanel = new JPanel(new GridLayout(rows, columns));
        boardLabels = new JLabel[rows][columns];

        // Initializes board cells in a "zig zag" shape
        for (int row = rows - 1; row >= 0; row--) {
            if (row % 2 == 0) {
                for (int col = 0; col < columns; col++) {
                    JLabel cellLabel = new JLabel("", SwingConstants.CENTER);
                    cellLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    boardLabels[row][col] = cellLabel;
                    boardPanel.add(cellLabel);
                }
            } else {
                for (int col = columns - 1; col >= 0; col--) {
                    JLabel cellLabel = new JLabel("", SwingConstants.CENTER);
                    cellLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    boardLabels[row][col] = cellLabel;
                    boardPanel.add(cellLabel);
                }
            }
        }

        add(boardPanel, BorderLayout.CENTER);
    }

    public void updateBoard(FinalPlayboard playboard, ArrayList<Player> players) {
        //resets the playboard
        for (int row = 0; row < playboard.getRowsNumber(); row++) {
            for (int col = 0; col < playboard.getColumnsNumber(); col++) {
                int boxNumber = row * playboard.getColumnsNumber() + col + 1;
                AbstractBox box = playboard.getBox(boxNumber);
                boardLabels[row][col].setText(getBoxRepresentation(box));
                boardLabels[row][col].setBackground(Color.LIGHT_GRAY);
                boardLabels[row][col].setOpaque(true);
            }
        }
        //updates player positions
        for(Player player : players) {
            updatePlayerPosition(player, playboard);
        }
        repaint();
    }

    private void updatePlayerPosition(Player player, FinalPlayboard playboard) {
        int position = player.getPosition();
        JLabel playerLabel = getLabelForPosition(position, playboard);
        playerLabel.setText(playerLabel.getText() + " " + "👤" +player.getName().charAt(0)+player.getName().charAt(player.getName().length()-1));
        playerLabel.setBackground(getPlayerColor(player));
        playerLabel.setOpaque(true);
    }

    private Color getPlayerColor(Player player) {
        switch (player.getName()) {
            case "player 0": return Color.GREEN;
            case "player 1": return Color.ORANGE;
            case "player 2": return Color.CYAN;
            case "player 3": return Color.PINK;
            default: return Color.RED;
        }
    }

    private JLabel getLabelForPosition(int boxNumber, FinalPlayboard playboard) {
        int row = (boxNumber-1) / playboard.getColumnsNumber();
        int col = (boxNumber-1) % playboard.getColumnsNumber();
        return boardLabels[row][col];
    }

    // Box decorations
    private String getBoxRepresentation(AbstractBox box) {
        switch (box.getBoxType()) {
            case "Ladder":
                return "<html><center>" + box.getBoxNumber() + "<br>↗</center><br>LADDER</center></html>";
            case "Snake":
                return "<html><center>" + box.getBoxNumber() + "<br>🐍</center><br>SNAKE</center></html>";
            case "Dice":
                return "<html><center>" + box.getBoxNumber() + "<br>★</center><br>DICE</center></html>";
            case "Spring":
                return "<html><center>" + box.getBoxNumber() + "<br>★</center><br>SPRING</center></html>";
            case "Inn":
                return "<html><center>" + box.getBoxNumber() + "<br>⏹</center><br>INN</center></html>";
            case "Bench":
                return "<html><center>" + box.getBoxNumber() + "<br>⏹</center><br>BENCH</center></html>";
            case "DrawACard":
                return "<html><center>" + box.getBoxNumber() + "<br>?</center><br>DRAW A CARD</center></html>";
            default:
                return box.getBoxNumber()+"";
        }
    }

    public void showPopupMessage(AbstractBox box, String playerName) {
        switch (box.getBoxType()) {
            case "Ladder":
                JOptionPane.showMessageDialog(this, "GREAT! "+playerName+" found a Ladder. Moving to: "+((LadderBox)box).getSpecialBoxDestination());
                break;
            case "Snake":
                JOptionPane.showMessageDialog(this, "OH NO! "+playerName+" found a Snake. Moving to: "+((SnakeBox)box).getSpecialBoxDestination());
                break;
            case "Dice":
                JOptionPane.showMessageDialog(this, playerName+" found a Dice box!");
                break;
            case "Spring":
                JOptionPane.showMessageDialog(this, playerName+" found a Spring box!");
                break;
            case "Inn":
                JOptionPane.showMessageDialog(this, playerName+" found an Inn box!");
                break;
            case "Bench":
                JOptionPane.showMessageDialog(this, playerName+" found a Bench box!");
                break;
            case "DrawACard":
                JOptionPane.showMessageDialog(this, playerName+" found a Draw A Card box!");
                break;
            default:
        }
    }

    public void startGame() {
        setVisible(true);
    }
}