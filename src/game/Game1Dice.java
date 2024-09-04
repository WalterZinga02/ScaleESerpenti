package game;

import box.AbstractBox;
import die.Die;
import die.SixSidedDie;
import gui.GameBoardGUI;
import playboard.FinalPlayboard;
import playboard.Playboard;
import player.ConcretePlayer;
import player.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Game1Dice implements Game {

    private Playboard playboard;
    private final int playersNumber;

    private Die die;
    private ArrayList<Player> players;

    private int finalPosition;
    private int currentPlayerIndex;

    private GameBoardGUI gui;

    Game1Dice(int playersNumber, Playboard playboard) {
        this.playboard = playboard;
        this.playersNumber = playersNumber;

        this.die = new SixSidedDie();
        this.finalPosition = playboard.getColumnsNumber() * playboard.getRowsNumber();
        this.players = new ArrayList<Player>(playersNumber);
        initializePlayers();

        this.currentPlayerIndex = 0;
        this.gui = new GameBoardGUI(playboard.getRowsNumber(), playboard.getColumnsNumber());
    }

    public void startGame() {
        gui.startGame();
        gui.updateBoard((FinalPlayboard) playboard, players);
        System.out.println("Game started correctly");

        ActionListener turnListener = new ActionListener() {

            private boolean gameWon = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameWon) {
                    Player currentPlayer = players.get(currentPlayerIndex);

                    turn(currentPlayer);
                    gui.updateBoard((FinalPlayboard) playboard, players);

                    // checks if the game is won by the current player
                    if (currentPlayer.getPosition() == finalPosition) {
                        gameWon = true;
                        ((Timer) e.getSource()).stop();
                        JOptionPane.showMessageDialog(gui, currentPlayer.getName() + " won!");
                        System.exit(0);
                    } else {
                        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                    }
                }
            }
        };

        Timer turnTimer = new Timer(1500, turnListener);
        turnTimer.start();
    }

    public synchronized void turn(Player currentPlayer){
        int move;
        System.out.println("Turn " + currentPlayer.getName());
        if(!currentPlayer.hasTurnsToSkip()){
            //Throws the dice
            move = currentPlayer.throw1Dice(die);

            //System.out.println("Move " + move); //debug

            //Updates the position of the current player
            currentPlayer.move(move);

            //checks the box in the new position
            AbstractBox box = playboard.getBox(currentPlayer.getPosition());
            gui.showPopupMessage(box, currentPlayer.getName());
            box.act(this, currentPlayer);
        }
        else {
            currentPlayer.decrementTurnsToSkip();
            System.out.println(currentPlayer.getName() + " skips a turn in position "+currentPlayer.getPosition());
        }

        // Updates the board GUI
        SwingUtilities.invokeLater(() -> {
            GameBoardGUI gui = new GameBoardGUI(playboard.getRowsNumber(), playboard.getColumnsNumber());
            gui.updateBoard((FinalPlayboard) playboard, players);
            gui.repaint();
            gui.revalidate();
        });
    }

    public void moveAgain(Player currentPlayer) {
        int move = currentPlayer.getLastThrow();
        currentPlayer.move(move);

        AbstractBox box = playboard.getBox(currentPlayer.getPosition());
        box.act(this, currentPlayer);
    }

    private void initializePlayers() {
        for (int i = 0; i < playersNumber; i++) {
            if (players != null) {
                players.add(new ConcretePlayer("player " + i, this.finalPosition));
            }
        }
    }
}