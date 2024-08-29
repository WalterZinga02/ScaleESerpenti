package box;

import game.Game;
import player.Player;

public class InnBox extends AbstractBox{

    public InnBox(int boxNumber) {
        super(boxNumber);
    }

    public void act(Game game, Player player){
        System.out.println("the player " + player.getName() + " is on a Inn box "+ boxNumber);
        player.setTurnsToSkip(3);
    }

    @Override
    public String getBoxType() {
        return "Inn";
    }
}
