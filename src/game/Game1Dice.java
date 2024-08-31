package game;

import box.AbstractBox;
import die.Die;
import die.SixSidedDie;
import playboard.Playboard;
import player.ConcretePlayer;
import player.Player;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Game1Dice implements Game {

    private Playboard playboard;
    private final int playersNumber;

    private Die die;
    private ArrayList<Player> players;

    private int finalPosition;
    private int currentPlayerIndex;

    Game1Dice(int playersNumber, Playboard playboard) {
        this.playboard = playboard;
        this.playersNumber = playersNumber;

        this.die = new SixSidedDie();
        this.finalPosition = playboard.getColumnsNumber() * playboard.getRowsNumber();
        this.players = new ArrayList<Player>(playersNumber);
        initializePlayers();

        this.currentPlayerIndex = 0;
    }

    @Override
    public void startGame(){
        boolean gameWon = false;
        System.out.println("Game started correctly");

        while (!gameWon) {
            Player currentPlayer = players.get(currentPlayerIndex);

            turn(currentPlayer);

            //checks if the game is won by the current player
            if (currentPlayer.getPosition() == finalPosition) {
                gameWon = true;
                System.out.println("player "+ currentPlayer.getName() + " won");
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            }
        }
    }

    public synchronized void turn(Player currentPlayer){
        int move;
        System.out.println("Turn " + currentPlayer.getName());
        if(!currentPlayer.hasTurnsToSkip()){
            //Throws the dice
            move = currentPlayer.throw1Dice(die);

            //Updates the position of the current player
            currentPlayer.move(move);

            //checks the box in the new position
            AbstractBox box = playboard.getBox(currentPlayer.getPosition());
            box.act(this, currentPlayer);
        }
        else {
            currentPlayer.decrementTurnsToSkip();
            System.out.println(currentPlayer.getName() + " skips a turn in position "+currentPlayer.getPosition());
        }
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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