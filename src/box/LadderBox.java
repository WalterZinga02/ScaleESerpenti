package box;

import game.Game;
import player.Player;

import static java.lang.Thread.sleep;

public class LadderBox extends AbstractBox{
    private final int specialBoxDestination;

    public LadderBox(int boxNumber, int specialBoxDestination) {
        super(boxNumber);
        this.specialBoxDestination = specialBoxDestination;
    }

    @Override
    public void act(Game game, Player player) {
        System.out.println("the player " + player.getName() + " is on a ladder box " + boxNumber);
        player.moveToBox(specialBoxDestination);
        System.out.println("the player is moved to " + specialBoxDestination);
    }

    @Override
    public String getBoxType() {
        return "Ladder";
    }

    public int getSpecialBoxDestination() {return specialBoxDestination;}

    public AbstractBox copy() {
        BoxFactory factory = new Box();
        return factory.createBox(this.boxNumber, 2, this.specialBoxDestination);
    }
}
