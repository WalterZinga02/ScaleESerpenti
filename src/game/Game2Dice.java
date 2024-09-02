package game;

import box.AbstractBox;
import die.Die;
import die.SixSidedDie;
import gui.GameBoardGUI;
import playboard.Playboard;
import player.ConcretePlayer;
import player.Player;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Game2Dice implements Game {

    private final Playboard playboard;
    private final int playersNumber;
    private boolean doubleSix;
    private boolean twoDiceMod;

    private final Die die1;
    private final Die die2;
    private List<Player> players;

    private final int finalPosition;
    private final int gameModBoxNumber;

    private int currentPlayerIndex;

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
    }

    @Override
    public void startGame() {
        System.out.println("Starting game");
        boolean gameWon = false;

        while (!gameWon) {
            Player currentPlayer = players.get(currentPlayerIndex);

            turn(currentPlayer);

            if (currentPlayer.getPosition() == finalPosition) {
                gameWon = true;
                System.out.println("player "+currentPlayer.getName()+" won");
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            }
        }
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
            box.act(this, currentPlayer);
        }
        else {
            currentPlayer.decrementTurnsToSkip();
            System.out.println(currentPlayer.getName()+ " skips a turn in position "+currentPlayer.getPosition());
        }
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