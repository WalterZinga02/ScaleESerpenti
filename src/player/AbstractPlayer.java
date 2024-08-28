package player;

import die.Die;

public abstract class AbstractPlayer implements Player {

    protected String name;
    protected int position = 0;
    protected int turnsToSkip = 0;
    private int lastThrow;

    public AbstractPlayer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void move(int newPosition) {
        this.position = newPosition;
    }

    public void setTurnsToSkip(int turns) {
        this.turnsToSkip = turns;
    }

    public boolean hasTurnsToSkip() {
        return turnsToSkip > 0;
    }

    public void decrementTurnsToSkip() {
        if (turnsToSkip > 0) {
            turnsToSkip--;
        }
    }

    public int throw1Dice(Die die){
        if(hasTurnsToSkip()) {
            decrementTurnsToSkip();
            return 0;
        }
        lastThrow= die.throwTheDie();
        return lastThrow;
    }

    public int throw2Dice(Die die1, Die die2){
        if(hasTurnsToSkip()) {
            decrementTurnsToSkip();
            return 0;
        }
        lastThrow = die1.throwTheDie()+die2.throwTheDie();
        return lastThrow;
    }

    public int getLastThrow(){
        return lastThrow;
    }
}
