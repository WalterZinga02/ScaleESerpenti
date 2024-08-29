package game;

import box.AbstractBox;
import die.Die;
import die.SixSidedDie;
import playboard.Playboard;
import playboard.PlayboardMemento;
import player.ConcretePlayer;
import player.Player;
import player.PlayerMemento;

import java.util.ArrayList;
import java.util.List;

public class Game2Dice implements Game {

    private final Playboard playboard;
    private final int playersNumber;
    private boolean doubleSix;
    private boolean twoDiceMod;

    private final Die die1;
    private final Die die2;
    private List<Player> players;

    private final int finalPosition;
    private final int gameModBoxNumber;

    private int currentPlayerIndex;

    Game2Dice(int playersNumber, Playboard playboard, boolean doubleSix, boolean twoDiceMod) {
        this.playboard = playboard;
        this.doubleSix = doubleSix;
        this.twoDiceMod = twoDiceMod;
        this.playersNumber = playersNumber;

        this.die1 = new SixSidedDie();
        this.die2 = new SixSidedDie();
        this.finalPosition = playboard.getColumnsNumber() * playboard.getRowsNumber();
        this.gameModBoxNumber = finalPosition - 7;
        this.players = new ArrayList<Player>(playersNumber);
        initializePlayers();

        this.currentPlayerIndex = 0;
    }

    @Override
    public void startGame() {
        System.out.println("Starting game");
        boolean gameWon = false;

        while (!gameWon) {
            Player currentPlayer = players.get(currentPlayerIndex);

            turn(currentPlayer);

            if (currentPlayer.getPosition() == finalPosition) {
                gameWon = true;
                System.out.println("player "+currentPlayer.getName()+" won");
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            }
        }
    }

    @Override
    public synchronized void turn(Player currentPlayer) {
        int move;
        System.out.println("Turn " + currentPlayer.getName());
        if(!currentPlayer.hasTurnsToSkip()) {
            //Throws the dice depending on the game modalities chosen
            if (twoDiceMod && currentPlayer.getPosition() < gameModBoxNumber) {
                move = currentPlayer.throw2Dice(die1, die2);
                if (doubleSix && move == 12) {
                    System.out.println("Double six dice");
                    move += currentPlayer.throw2Dice(die1, die2);
                }
            } else {
                System.out.println("you are near the victory! you throw only one die");
                move = currentPlayer.throw1Dice(die1);
            }

            //Updates the position of the current player
            currentPlayer.move(move);

            //checks the box in the new position
            AbstractBox box = playboard.getBox(currentPlayer.getPosition());
            box.act(this, currentPlayer);
        }
        else {
            currentPlayer.decrementTurnsToSkip();
            System.out.println(currentPlayer.getName()+ " skips a turn in position "+currentPlayer.getPosition());
        }
    }

    @Override
    public void moveAgain(Player currentPlayer) {
        int move = currentPlayer.getLastThrow();
        currentPlayer.move(move);

        AbstractBox box = playboard.getBox(currentPlayer.getPosition());
        box.act(this, currentPlayer);
    }

    private void initializePlayers() {
        for (int i = 0; i < playersNumber; i++) {
            if (players != null) {
                players.add(new ConcretePlayer("player " + i, this.finalPosition));
            }
        }
    }

    //memento methods

    public Game2dMemento saveMemento() {
        List<PlayerMemento> playerMementos = new ArrayList<>(players.size());
        for (Player player : players) {
            playerMementos.add(player.saveMemento());
        }

        PlayboardMemento playboardMemento = playboard.saveMemento();
        int currentPlayerIndex = players.indexOf(players.get(0));
        return new Game2dMemento(playerMementos, playboardMemento, currentPlayerIndex, doubleSix, twoDiceMod);
    }

    public void restoreFromMemento(Game2dMemento memento) {
        this.players = new ArrayList<>(memento.getPlayersState().size());
        for (PlayerMemento playerMemento : memento.getPlayersState()) {
            Player p = new ConcretePlayer("player", 0);
            p.restoreFromMemento(playerMemento);
            this.players.add(p);
        }

        this.playboard.restoreFromMemento(memento.getPlayboardState());
        this.currentPlayerIndex = memento.getCurrentPlayerIndex();

        this.doubleSix = memento.isDoubleSix();
        this.twoDiceMod = memento.isTwoDiceMod();
    }
}