package box;

import game.Game;
import player.Player;

public class BenchBox extends AbstractBox{

    public BenchBox(int boxNumber) {
        super(boxNumber);
    }

    @Override
    public void act(Game game, Player player){
        System.out.println("the player " + player.getName() + " is on a bench box "+boxNumber);
        player.setTurnsToSkip(1);
    }

    @Override
    public String getBoxType() {
        return "Bench";
    }
}
