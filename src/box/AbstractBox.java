package box;

import game.Game;
import player.Player;

public abstract class AbstractBox {
    protected int boxNumber;

    public AbstractBox(int boxNumber) {
        this.boxNumber = boxNumber;
    }
    public abstract void act(Game game, Player player);
    public abstract String getBoxType();
    public int getBoxNumber() {return boxNumber;}
    public abstract AbstractBox copy();
}
