package game;

import box.AbstractBox;
import die.Die;
import die.SixSidedDie;
import playboard.Playboard;
import player.ConcretePlayer;
import player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game1Dice implements Game {

    private final Playboard playboard;
    private final int playersNumber;

    private final Die die;
    private List<Player> players;

    private final int finalPosition;

    Game1Dice(int playersNumber, Playboard playboard) {
        this.playboard = playboard;
        this.playersNumber = playersNumber;

        this.die = new SixSidedDie();
        this.players = new ArrayList<Player>(playersNumber);
        initializePlayers();

        this.finalPosition = playboard.getColumnsNumber() * playboard.getRowsNumber();
    }

    @Override
    public void startGame() {
        boolean gameWon = false;
        int currentPlayerIndex = 0;

        //gestione turni
        while (!gameWon) {
            Player currentPlayer = players.get(currentPlayerIndex);

            turn(currentPlayer);

            //checks if the game is won by the current player after the
            if (currentPlayer.getPosition() == finalPosition) {
                gameWon = true;
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            }
        }
    }

    public void turn(Player currentPlayer) {
        int move;

        //Throws the dice
        move = currentPlayer.throw1Dice(die);

        //Updates the position of the current player
        currentPlayer.move(move);

        //checks the box in the new position
        AbstractBox box = playboard.getBox(currentPlayer.getPosition());
        box.act(this, currentPlayer);
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
                players.set(i, new ConcretePlayer("player " + i));
            }
        }
    }
}