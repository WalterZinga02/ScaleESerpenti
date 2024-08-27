package box;

import game.Game;
import player.Player;

public class DicesBox extends AbstractBox{

    public DicesBox(int boxNumber) {
        super(boxNumber);
    }

    @Override
    public void act(Game game, Player player){
        game.turn(player);
        }

    @Override
    public String getBoxType() {
        return "Dices";
    }
}
