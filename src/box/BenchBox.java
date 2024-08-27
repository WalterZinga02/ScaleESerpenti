package box;

import game.Game;
import player.Player;

public class BenchBox extends AbstractBox{

    public BenchBox(int boxNumber) {
        super(boxNumber);
    }

    @Override
    public void act(Game game, Player player){
        player.setTurnsToSkip(1);
    }

    @Override
    public String getBoxType() {
        return "Bench";
    }
}
