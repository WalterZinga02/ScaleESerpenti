import game.GameManager;
import game.GameSettings;

public class Main {
    public static void main(String[] args){

        GameSettings settings = new GameSettings();

        // game settings
        settings.setPlayers(2);
        settings.setRows(5);
        settings.setColumns(5);
        settings.setStopBoxes(false);
        settings.setBonusBoxes(false);
        settings.setDacBoxes(false);
        settings.setTwoDice(false);
        settings.setDoubleSix(false);
        settings.setTwoDiceMod(false);

        System.out.println("Welcome to Ladders and Snakes");

        //starting the game
        GameManager gameManager = GameManager.getInstance(settings);
        gameManager.startGame();

    }
}
