package player;

import die.Die;

public abstract class AbstractPlayer implements Player {

    protected String name;
    protected int position = 0;
    protected int turnsToSkip = 0;
    private int lastThrow;
    private int maxPosition;

    public AbstractPlayer(String name, int maxPosition) {
        this.name = name;
        this.maxPosition = maxPosition;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void move(int throwValue) {
        int newPosition = position+throwValue;
        if(newPosition > maxPosition){
            newPosition = maxPosition - (newPosition - maxPosition);
        }
        this.position = newPosition;
    }

    @Override
    public void moveToBox(int boxNumber) {
        this.position = boxNumber;
    }

    @Override
    public void setTurnsToSkip(int turns) {
        this.turnsToSkip = turns;
    }

    @Override
    public boolean hasTurnsToSkip() {
        return turnsToSkip > 0;
    }

    @Override
    public void decrementTurnsToSkip() {
        if (turnsToSkip > 0) {
            turnsToSkip--;
        }
    }

    @Override
    public int throw1Dice(Die die){
        lastThrow= die.throwTheDie();
        return lastThrow;
    }

    @Override
    public int throw2Dice(Die die1, Die die2){
        lastThrow = die1.throwTheDie()+die2.throwTheDie();
        return lastThrow;
    }

    @Override
    public int getLastThrow(){
        return lastThrow;
    }
}
