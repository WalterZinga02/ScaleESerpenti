package box;

import game.Game;
import player.Player;

public class SpringBox extends AbstractBox{

    public SpringBox(int boxNumber) {
        super(boxNumber);
    }

    public void act(Game game, Player player) {
        game.moveAgain(player);
    }

    @Override
    public String getBoxType() {
        return "Sping";
    }
}
