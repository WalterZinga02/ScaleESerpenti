package gui;

import game.Caretaker;
import game.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameSettingsGUI extends JFrame {

    private Caretaker caretaker;
    private GameManager gameManager;

    // Components for new configuration
    private JComboBox<Integer> playerComboBox;
    private JComboBox<Integer> rowsComboBox;
    private JComboBox<Integer> columnsComboBox;
    private JCheckBox stopBoxesCheckBox;
    private JCheckBox bonusBoxesCheckBox;
    private JCheckBox dacBoxesCheckBox;
    private JCheckBox twoDiceCheckBox;
    private JCheckBox doubleSixCheckBox;
    private JCheckBox twoDiceModCheckBox;

    public GameSettingsGUI() {
        caretaker = new Caretaker();
        setTitle("Game Settings");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Main panel with buttons
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));

        // Button to load the last settings
        JButton loadButton = new JButton("Load the last settings");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadLastSettings();
            }
        });

        // Button to create a new configuration
        JButton newConfigButton = new JButton("Create a new Game");
        newConfigButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewConfigPanel();
            }
        });

        mainPanel.add(loadButton);
        mainPanel.add(newConfigButton);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void loadLastSettings() {
        gameManager = GameManager.getInstance(2, 10, 10, false, false, false, true, false, false); // default settings
        caretaker.undo(gameManager);
        JOptionPane.showMessageDialog(this, "Settings loaded correctly!");
        gameManager.startGame();
    }

    private void openNewConfigPanel() {
        JFrame configFrame = new JFrame("New configuration");
        configFrame.setSize(400, 500);
        configFrame.setLayout(new GridLayout(11, 2));
        configFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Components for the configuration
        configFrame.add(new JLabel("Players number:"));
        playerComboBox = new JComboBox<>(new Integer[]{2, 3, 4});
        configFrame.add(playerComboBox);

        configFrame.add(new JLabel("Rows number:"));
        rowsComboBox = new JComboBox<>(new Integer[]{5, 6, 7, 8, 9, 10});
        configFrame.add(rowsComboBox);

        configFrame.add(new JLabel("Columns number:"));
        columnsComboBox = new JComboBox<>(new Integer[]{5, 6, 7, 8, 9, 10});
        configFrame.add(columnsComboBox);

        configFrame.add(new JLabel("Stop boxes:"));
        stopBoxesCheckBox = new JCheckBox();
        configFrame.add(stopBoxesCheckBox);

        configFrame.add(new JLabel("Bonus boxes:"));
        bonusBoxesCheckBox = new JCheckBox();
        configFrame.add(bonusBoxesCheckBox);

        configFrame.add(new JLabel("Draw a Card box:"));
        dacBoxesCheckBox = new JCheckBox();
        configFrame.add(dacBoxesCheckBox);

        configFrame.add(new JLabel("Two dice:"));
        twoDiceCheckBox = new JCheckBox();
        configFrame.add(twoDiceCheckBox);

        //Double six and twoDiceMod can be checked only if two dices is checked.
        configFrame.add(new JLabel("Double six mod:"));
        doubleSixCheckBox = new JCheckBox();
        doubleSixCheckBox.setEnabled(false);
        configFrame.add(doubleSixCheckBox);

        configFrame.add(new JLabel("Mod 'one dice if near the last box':"));
        twoDiceModCheckBox = new JCheckBox();
        twoDiceModCheckBox.setEnabled(false);
        configFrame.add(twoDiceModCheckBox);

        twoDiceCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isTwoDiceSelected = twoDiceCheckBox.isSelected();
                doubleSixCheckBox.setEnabled(isTwoDiceSelected);
                twoDiceModCheckBox.setEnabled(isTwoDiceSelected);
            }
        });

        // Save button to apply settings
        JButton saveButton = new JButton("Start the game and save Configuration");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewGame();
                try {
                    caretaker.save(gameManager);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(configFrame, "Last configuration saved successfully!");
                configFrame.dispose();
            }
        });

        configFrame.add(saveButton);
        configFrame.setVisible(true);
    }

    private void createNewGame() {
        int players = (Integer) playerComboBox.getSelectedItem();
        int rows = (Integer) rowsComboBox.getSelectedItem();
        int columns = (Integer) columnsComboBox.getSelectedItem();
        boolean stopBoxes = stopBoxesCheckBox.isSelected();
        boolean bonusBoxes = bonusBoxesCheckBox.isSelected();
        boolean dacBoxes = dacBoxesCheckBox.isSelected();
        boolean twoDice = twoDiceCheckBox.isSelected();
        boolean doubleSix = doubleSixCheckBox.isSelected();
        boolean twoDiceMod = twoDiceModCheckBox.isSelected();

        gameManager = GameManager.getInstance(players, rows, columns, stopBoxes, bonusBoxes, dacBoxes, twoDice, doubleSix, twoDiceMod);
        gameManager.startGame();
    }
}
