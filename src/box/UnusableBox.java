package box;

import game.Game;
import player.Player;

public class UnusableBox extends AbstractBox{

    public UnusableBox(int boxNumber) {
        super(boxNumber);
    }

    @Override
    public void act(Game game,Player player) {
        System.out.println("the player " + player.getName() + " is on a basic box " + boxNumber);
    }

    @Override
    public String getBoxType() {
        return "Unuseable";
    }

}
