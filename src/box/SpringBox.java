package box;

import game.Game;
import player.Player;

public class SpringBox extends AbstractBox{

    public SpringBox(int boxNumber) {
        super(boxNumber);
    }

    @Override
    public void act(Game game, Player player) {
        System.out.println("the player " + player.getName() + " is on a spring box "+boxNumber);
        game.moveAgain(player);
    }

    @Override
    public String getBoxType() {
        return "Spring";
    }

}
