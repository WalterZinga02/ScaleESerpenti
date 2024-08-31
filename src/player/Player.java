package player;

import die.Die;

public interface Player {
    String getName();
    int getLastThrow();
    int getPosition();
    void setTurnsToSkip(int turns);
    void setPosition(int position);
    void setName(String name);
    void setLastThrow(int lastThrow);
    boolean hasTurnsToSkip();
    void decrementTurnsToSkip();
    void move(int newPosition);
    void moveToBox(int boxNumber);
    int throw1Dice(Die die);
    int throw2Dice(Die die1, Die die2);
}
