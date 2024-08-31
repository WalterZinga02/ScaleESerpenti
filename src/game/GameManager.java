package game;

import playboard.FinalPlayboard;
import playboard.Playboard;

public class GameManager {

    private int players;
    private int rows;
    private int columns;
    private boolean stopBoxes;
    private boolean bonusBoxes;
    private boolean dacBoxes;
    private boolean twoDice;
    private boolean doubleSix;
    private boolean twoDiceMod;

    // Singleton instance
    private static GameManager instance;

    // Strategy for the game mode
    private Game game;

    // Private constructor to prevent instantiation
    private GameManager(int players, int rows, int columns, boolean stopBoxes, boolean bonusBoxes, boolean dacBoxes, boolean twoDice,boolean doubleSix, boolean twoDiceMod) {
        this.players = players;
        this.rows = rows;
        this.columns = columns;
        this.stopBoxes = stopBoxes;
        this.bonusBoxes = bonusBoxes;
        this.dacBoxes = dacBoxes;
        this.twoDice = twoDice;
        this.doubleSix = doubleSix;
        this.twoDiceMod = twoDiceMod;
        setGame();
    }
    /*
    public static synchronized GameManager getInstanceFromFile() {
        GameSettings settings = new GameSettings();
        try {
            settings.loadFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new GameManager(settings);
    }

     */

    public static synchronized GameManager getInstance(int players, int rows, int columns, boolean stopBoxes, boolean bonusBoxes, boolean dacBoxes, boolean twoDice, boolean doubleSix, boolean twoDiceMod) {
        if (instance == null) {
            instance = new GameManager(players, rows, columns, stopBoxes, bonusBoxes, dacBoxes, twoDice, doubleSix, twoDiceMod);
        }
        return instance;
    }

    // Sets the strategy based on the game mode
    private void setGame() {
        if (players > 4 || players < 2) {
            throw new IllegalArgumentException("the number of players must be greater than 2 and less than 4.");
        }
        Playboard playboard = new FinalPlayboard(rows, columns, stopBoxes, bonusBoxes, dacBoxes);
        if (twoDice) {
            this.game = new Game2Dice(players, playboard, doubleSix, twoDiceMod);
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

    public GameSettings save(){
        GameSettings settings = new GameSettings(players, rows, columns, stopBoxes, bonusBoxes, dacBoxes, twoDice, doubleSix, twoDiceMod);
        return settings;
    }

    public void restore(GameSettings settings){
        this.players = settings.getPlayers();
        this.rows = settings.getRows();
        this.columns = settings.getColumns();
        this.stopBoxes = settings.isStopBoxes();
        this.bonusBoxes = settings.isBonusBoxes();
        this.dacBoxes = settings.isDacBoxes();
        this.twoDice = settings.isTwoDice();
        this.doubleSix = settings.isDoubleSix();
        this.twoDiceMod = settings.isTwoDiceMod();
        setGame();
    }
}
