package die;

import java.util.Random;

public abstract class AbstractDie implements Die {
    //Leaving the number of sides as a parameter in the abstract class allows for the expansion of the game
    //with new types of dice, creating the opportunity to publish new versions of the game.
    protected int sides;
    protected Random random;

    public AbstractDie(int sides) {
        if (sides < 2) {
            throw new IllegalArgumentException("The die must have at least 2 sides.");
        }
        this.sides = sides;
        this.random = new Random();
    }

    @Override
    public int getSidesNumber() {
        return sides;
    }

    @Override
    public abstract int throwTheDie();
}