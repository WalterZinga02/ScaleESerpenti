package gui;

import box.AbstractBox;
import playboard.FinalPlayboard;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameBoardGUI extends JFrame {

    private JPanel boardPanel;
    private JLabel[][] boardLabels;
    private Map<Integer, JLabel> playerPositionLabels;

    public GameBoardGUI(int rows, int columns) {
        setTitle("Game Board");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for the board
        boardPanel = new JPanel(new GridLayout(rows, columns));
        boardLabels = new JLabel[rows][columns];
        playerPositionLabels = new HashMap<>();

        // Initialize board cells
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                JLabel cellLabel = new JLabel("", SwingConstants.CENTER);
                cellLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                boardLabels[row][col] = cellLabel;
                boardPanel.add(cellLabel);
            }
        }

        add(boardPanel, BorderLayout.CENTER);
    }

    // Method to update the board display with current game state
    public void updateBoard(FinalPlayboard playboard, ArrayList<Player> players) {
        // Reset board display
        for (int row = 0; row < playboard.getRowsNumber(); row++) {
            for (int col = 0; col < playboard.getColumnsNumber(); col++) {
                int boxNumber = row * playboard.getColumnsNumber() + col + 1;
                AbstractBox box = playboard.getBox(boxNumber);
                boardLabels[row][col].setText(getBoxRepresentation(box));
                boardLabels[row][col].setBackground(Color.WHITE);
                boardLabels[row][col].setOpaque(true);
            }
        }

        // Update player positions
        for (Player player : players) {
            int position = player.getPosition();
            JLabel playerLabel = getLabelForPosition(position, playboard);
            playerLabel.setText(playerLabel.getText() + " " + player.getName().charAt(0)+player.getName().charAt(player.getName().length()-1)); // Display the first and the last letters of the player's name
            playerLabel.setBackground(Color.CYAN);
            playerLabel.setOpaque(true);
        }

        repaint();
    }

    // Helper method to get the JLabel corresponding to a box number
    private JLabel getLabelForPosition(int boxNumber, FinalPlayboard playboard) {
        int row = (boxNumber-1) / playboard.getColumnsNumber();
        int col = (boxNumber-1) % playboard.getColumnsNumber();
        return boardLabels[row][col];
    }

    // Method to provide text representation of each box type
    private String getBoxRepresentation(AbstractBox box) {
        switch (box.getBoxType()) {
            case "Ladder":
                return box.getBoxNumber()+" Ladder Start";
            case "Snake":
                return box.getBoxNumber()+" Snake Tongue";
            case "Dice":
                return box.getBoxNumber()+" Dice ★";
            case "Spring":
                return box.getBoxNumber()+" Spring ★";
            case "Inn":
                return box.getBoxNumber()+" Inn ⏹";
            case "Bench":
                return box.getBoxNumber()+" Bench ⏹";
            case "DrawACard":
                return box.getBoxNumber()+" Draw Card";
            default:
                return box.getBoxNumber()+"";
        }
    }

    // This method is called when the game starts
    public void startGame() {
        setVisible(true);
    }
}