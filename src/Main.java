import game.GameManager;

public class Main {
    public static void main(String[] args) {

        // game settings
        int players = 2;
        int rows = 5;
        int columns = 5;
        boolean stopBoxes = true;
        boolean bonusBoxes = true;
        boolean dacBoxes = true;
        boolean twoDice = true;
        boolean doubleSix = true;
        boolean twoDiceMod = true;

        System.out.println("Welcome to Ladders and Snakes");

        //starting the game
        GameManager gameManager = GameManager.getInstance(players, rows, columns, stopBoxes, bonusBoxes, dacBoxes, twoDice, doubleSix, twoDiceMod);
        gameManager.startGame();
    }
}
