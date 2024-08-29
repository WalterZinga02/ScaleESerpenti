package box;

import game.Game;
import player.Player;

public class InnBox extends AbstractBox{

    public InnBox(int boxNumber) {
        super(boxNumber);
    }

    @Override
    public void act(Game game, Player player){
        System.out.println("the player " + player.getName() + " is on a Inn box "+ boxNumber);
        player.setTurnsToSkip(3);
    }

    @Override
    public String getBoxType() {
        return "Inn";
    }

    @Override
    public AbstractBox copy() {
        BoxFactory factory = new Box();
        return factory.createBox(this.boxNumber, 5, 0);
    }
}
