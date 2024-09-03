package box;

import game.Game;
import player.Player;

public class SnakeBox extends AbstractBox{
    private final int specialBoxDestination;

    public SnakeBox(int boxNumber, int specialBoxDestination) {
        super(boxNumber);
        this.specialBoxDestination = specialBoxDestination;
    }

    @Override
    public void act(Game game, Player player) {
        System.out.println("the player " + player.getName() + " is on a snake box " + boxNumber);
        player.moveToBox(specialBoxDestination);
        System.out.println("the player is moved to " + specialBoxDestination);
    }

    @Override
    public String getBoxType() {
        return "Snake";
    }

    public int getSpecialBoxDestination() {return specialBoxDestination;}
}
