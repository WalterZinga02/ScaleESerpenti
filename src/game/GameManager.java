package game;

import playboard.FinalPlayboard;
import playboard.Playboard;

import java.io.IOException;

public class GameManager {

    private GameSettings settings;

    // Singleton instance
    private static GameManager instance;

    // Strategy for the game mode
    private Game game;

    // Private constructor to prevent instantiation
    private GameManager(GameSettings settings) {
        this.settings = settings;
        setGame();
    }

    public static synchronized GameManager getInstanceFromFile() {
        GameSettings settings = new GameSettings();
        try {
            settings.loadFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new GameManager(settings);
    }

    public static synchronized GameManager getInstance(GameSettings settings) {
        if (instance == null) {
            instance = new GameManager(settings);
        }
        return instance;
    }

    // Sets the strategy based on the game mode
    private void setGame() {
        int players = settings.getPlayers();
        if (players > 4 || players < 2) {
            throw new IllegalArgumentException("the number of players must be greater than 2 and less than 4.");
        }
        Playboard playboard = new FinalPlayboard(settings.getRows(), settings.getColumns(), settings.isStopBoxes(), settings.isBonusBoxes(), settings.isDacBoxes());
        if (settings.isTwoDice()) {
            this.game = new Game2Dice(players, playboard, settings.isDoubleSix(), settings.isTwoDiceMod());
        } else {
            this.game = new Game1Dice(players, playboard);
        }
        System.out.println("Game settings done");
    }

    // Method to start the game
    public void startGame(){
        if (game == null) {
            throw new IllegalStateException("The game was not initialized correctly.");
        }
        game.startGame();
    }
}
