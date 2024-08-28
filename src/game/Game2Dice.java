package game;

import box.AbstractBox;
import die.Die;
import die.SixSidedDie;
import playboard.Playboard;
import player.ConcretePlayer;
import player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game2Dice implements Game {

    private final Playboard playboard;
    private final int playersNumber;
    private final boolean doubleSix;
    private final boolean twoDiceMod;

    private final Die die1;
    private final Die die2;
    private List<Player> players;

    private final int finalPosition;
    private final int gameModBoxNumber;

    Game2Dice(int playersNumber, Playboard playboard, boolean doubleSix, boolean twoDiceMod) {
        this.playboard = playboard;
        this.doubleSix = doubleSix;
        this.twoDiceMod = twoDiceMod;
        this.playersNumber = playersNumber;

        this.die1 = new SixSidedDie();
        this.die2 = new SixSidedDie();
        this.players = new ArrayList<Player>(playersNumber);
        initializePlayers();

        this.finalPosition = playboard.getColumnsNumber() * playboard.getRowsNumber();
        this.gameModBoxNumber = finalPosition - 7;
    }

    @Override
    public void startGame() {
        boolean gameWon = false;
        int currentPlayerIndex = 0;

        while (!gameWon) {
            Player currentPlayer = players.get(currentPlayerIndex);

            turn(currentPlayer);

            if (currentPlayer.getPosition() == finalPosition) {
                gameWon = true;
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            }
        }
    }

    @Override
    public void turn(Player currentPlayer) {
        int move;

        //Throws the dice depending on the game modalities chosen
        if (twoDiceMod && currentPlayer.getPosition() < gameModBoxNumber) {
            move = currentPlayer.throw2Dice(die1, die2);
            if (doubleSix && move == 12) {
                move += currentPlayer.throw2Dice(die1, die2);
            }
        } else {
            move = currentPlayer.throw1Dice(die1);
        }

        //Updates the position of the current player
        currentPlayer.move(move);

        //checks the box in the new position
        AbstractBox box = playboard.getBox(currentPlayer.getPosition());
        box.act(this, currentPlayer);
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
                players.set(i, new ConcretePlayer("player " + i));
            }
        }
    }
}