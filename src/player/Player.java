package player;

import die.Die;

public interface Player {
    String getName();
    int getLastThrow();
    int getPosition();
    void setTurnsToSkip(int turns);
    boolean hasTurnsToSkip();
    void decrementTurnsToSkip();
    void move(int newPosition);
    int throw1Dice(Die die);
    int throw2Dice(Die die1, Die die2);
}
