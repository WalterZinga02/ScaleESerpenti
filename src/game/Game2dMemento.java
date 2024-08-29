package game;

import playboard.PlayboardMemento;
import player.PlayerMemento;

import java.util.List;

public class Game2dMemento {
    private final List<PlayerMemento> playersState;
    private final PlayboardMemento playboardState;
    private final int currentPlayerIndex;
    private final boolean doubleSix;
    private final boolean twoDiceMod;

    public Game2dMemento(List<PlayerMemento> playersState, PlayboardMemento playboardState, int currentPlayerIndex, boolean doubleSix, boolean twoDiceMod) {
        this.playersState = playersState;
        this.playboardState = playboardState;
        this.currentPlayerIndex = currentPlayerIndex;
        this.doubleSix = doubleSix;
        this.twoDiceMod = twoDiceMod;
    }

    public List<PlayerMemento> getPlayersState() {
        return playersState;
    }

    public PlayboardMemento getPlayboardState() {
        return playboardState;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public boolean isDoubleSix() {
        return doubleSix;
    }

    public boolean isTwoDiceMod() {
        return twoDiceMod;
    }
}