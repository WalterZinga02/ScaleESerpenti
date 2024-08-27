package game;

import die.Die;
import die.SixSidedDie;
import playboard.Playboard;

import java.util.ArrayList;
import java.util.List;

public class Game2Dice implements Game {

    private Playboard playboard;
    private int playersNumber;
    private boolean doubleSix;
    private boolean twoDiceMod;

    private Die die1;
    private Die die2;
    private List<Player> players;//TODO

    Game2Dice(int playersNumber, Playboard playboard, boolean doubleSix, boolean twoDiceMod) {
        this.playboard = playboard;
        this.doubleSix = doubleSix;
        this.twoDiceMod = twoDiceMod;
        this.playersNumber = playersNumber;

        this.die1 = new SixSidedDie();
        this.die2 = new SixSidedDie();
        this.players = new ArrayList<>();//TODO
    }

    @Override
    public void startGame() {
        boolean gameWon = false;
        int currentPlayerIndex = 0;
        int finalPosition = playboard.getColumnsNumber() * playboard.getRowsNumber();
        int gameModBoxNumber = finalPosition - 7;

        while (!gameWon) {
            Player currentPlayer = players.get(currentPlayerIndex);
            int move;

            //Throws the dice depending on the game modalities chosen
            if (twoDiceMod && currentPlayer.getPosition() < gameModBoxNumber) {
                move = currentPlayer.throw2Die(die1, die2);
                //check the box
                if (doubleSix && move == 12) {
                    move += currentPlayer.throw2Die(die1, die2);
                    //check the box
                }
            } else {
                move = currentPlayer.throw1Die(die1);
                //check the box
            }

            //Updates the position of the current player
            currentPlayer.move(move);

            if (currentPlayer.getPosition() == finalPosition) {
                gameWon = true;
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            }
        }
    }
}
