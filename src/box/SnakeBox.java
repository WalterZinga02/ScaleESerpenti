package box;

import game.Game;
import player.Player;

public class SnakeBox extends AbstractBox{
    private final int specialBoxDestination;

    public SnakeBox(int boxNumber, int specialBoxDestination) {
        super(boxNumber);
        this.specialBoxDestination = specialBoxDestination;
    }

    public void act(Game game, Player player) {
        player.move(specialBoxDestination);
    }

    @Override
    public String getBoxType() {
        return "Snake";
    }
}
