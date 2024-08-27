package game;

import player.Player;

public interface Game {
    void startGame();
    void turn(Player player);
    void moveAgain(Player player);
}
