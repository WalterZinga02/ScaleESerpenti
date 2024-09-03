package box;

import game.Game;
import player.Player;

public class DiceBox extends AbstractBox{

    public DiceBox(int boxNumber) {
        super(boxNumber);
    }

    @Override
    public void act(Game game, Player player){
        System.out.println("the player " + player.getName() + " is on a dice box "+boxNumber);
        game.turn(player);
    }

    @Override
    public String getBoxType() {
        return "Dice";
    }

}
