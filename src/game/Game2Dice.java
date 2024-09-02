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

public class Game2Dice implements Game {

    private final Playboard playboard;
    private final int playersNumber;
    private boolean doubleSix;
    private boolean twoDiceMod;

    private final Die die1;
    private final Die die2;
    private ArrayList<Player> players;

    private final int finalPosition;
    private final int gameModBoxNumber;

    private int currentPlayerIndex;
    private GameBoardGUI gui;

    Game2Dice(int playersNumber, Playboard playboard, boolean doubleSix, boolean twoDiceMod) {
        this.playboard = playboard;
        this.doubleSix = doubleSix;
        this.twoDiceMod = twoDiceMod;
        this.playersNumber = playersNumber;

        this.die1 = new SixSidedDie();
        this.die2 = new SixSidedDie();
        this.finalPosition = playboard.getColumnsNumber() * playboard.getRowsNumber();
        this.gameModBoxNumber = finalPosition - 7;
        this.players = new ArrayList<Player>(playersNumber);
        initializePlayers();

        this.currentPlayerIndex = 0;
        this.gui=new GameBoardGUI(playboard.getRowsNumber(), playboard.getColumnsNumber());
    }

    @Override
    public void startGame() {
        gui.startGame();
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
                        JOptionPane.showMessageDialog(gui, "Player " + currentPlayer.getName() + " won!");
                        System.exit(0);
                    } else {
                        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                    }
                }
            }
        };
        Timer turnTimer = new Timer(0, turnListener);
        turnTimer.start();
    }

    @Override
    public synchronized void turn(Player currentPlayer) {
        int move;
        System.out.println("Turn " + currentPlayer.getName());
        if(!currentPlayer.hasTurnsToSkip()) {
            //Throws the dice depending on the game modalities chosen
            if(twoDiceMod){
                if(currentPlayer.getPosition() < gameModBoxNumber) {
                    move = currentPlayer.throw2Dice(die1, die2);
                } else {
                    System.out.println("you are near the victory! you throw only one die");
                    move = currentPlayer.throw1Dice(die1);
                }
            }
            else{
                move = currentPlayer.throw2Dice(die1, die2);
            }

            //controls the double six mod
            if (doubleSix && move == 12) {
                System.out.println("Double six dice");
                move += currentPlayer.throw2Dice(die1, die2);
            }

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
            System.out.println(currentPlayer.getName()+ " skips a turn in position "+currentPlayer.getPosition());
        }

        // Updates the board GUI
        SwingUtilities.invokeLater(() -> {
            GameBoardGUI gui = new GameBoardGUI(playboard.getRowsNumber(), playboard.getColumnsNumber());
            gui.updateBoard((FinalPlayboard) playboard, players);
            gui.repaint();
            gui.revalidate();
        });

        try {
            sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
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