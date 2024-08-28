package box;

import game.Game;
import player.Player;

public class LadderBox extends AbstractBox{
    private final int specialBoxDestination;

    public LadderBox(int boxNumber, int specialBoxDestination) {
        super(boxNumber);
        this.specialBoxDestination = specialBoxDestination;
    }

    public void act(Game game, Player player) {
        player.move(specialBoxDestination);
    }

    @Override
    public String getBoxType() {
        return "Ladder";
    }
}
