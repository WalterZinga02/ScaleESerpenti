package game;

import playboard.FinalPlayboard;
import playboard.Playboard;

public class GameManager {

    // Singleton instance
    private static GameManager instance;

    // Strategy for the game mode
    private Game game;

    // Private constructor to prevent instantiation
    private GameManager() {}

    // Factory method to create a specific game based on the dice type
    public static synchronized GameManager getInstance(int players, int rows, int columns, boolean stopBoxes, boolean bonusBoxes, boolean dacBoxes, boolean twoDice, boolean doubleSix, boolean twoDiceMod) {
        if (instance == null) {
            instance = new GameManager();
            instance.setGioco(players, rows, columns, stopBoxes, bonusBoxes, dacBoxes, twoDice, doubleSix, twoDiceMod);
        }
        return instance;
    }

    // Sets the strategy based on the game mode
    private void setGioco(int players, int rows, int columns, boolean stopBoxes, boolean bonusBoxes, boolean dacBoxes, boolean twoDice, boolean doubleSix, boolean twoDiceMod) {
        Playboard playboard = FinalPlayboard.getInstance(rows, columns, stopBoxes, bonusBoxes, dacBoxes);
        if (twoDice) {
            this.game = new Game2Dice(players, playboard, doubleSix, twoDiceMod);
        } else {
            this.game = new Game1Dice(players, playboard);
        }
        System.out.println("Game settings done");
    }

    // Method to start the game
    public void startGame() {
        if (game == null) {
            throw new IllegalStateException("The game was not initialized correctly.");
        }
        game.startGame();
    }
}
