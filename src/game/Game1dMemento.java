package game;

import die.Die;
import playboard.Playboard;
import playboard.PlayboardMemento;
import player.ConcretePlayer;
import player.Player;
import player.PlayerMemento;

import java.util.ArrayList;

public class Game1dMemento {

    private final ArrayList<PlayerMemento> players;
    private final int currentPlayerIndex;
    private final PlayboardMemento playboardState;
    private final int finalPosition;

    public Game1dMemento(ArrayList<PlayerMemento> playersStates, int currentPlayerIndex, PlayboardMemento playboardState, int finalPosition) {
        this.players = new ArrayList<>(playersStates);
        this.currentPlayerIndex = currentPlayerIndex;
        this.playboardState = playboardState;
        this.finalPosition = finalPosition;
    }

    public ArrayList<PlayerMemento> getPlayersState() {
        return new ArrayList<>(players);
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public PlayboardMemento getPlayboardState() {
        return playboardState;
    }

    public int getFinalPosition() {
        return finalPosition;
    }
}

