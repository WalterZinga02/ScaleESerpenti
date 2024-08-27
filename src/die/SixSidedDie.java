package die;

public class SixSidedDie extends AbstractDie {

    public SixSidedDie() {
        super(6);
    }

    @Override
    public int throwTheDie() {
        return random.nextInt(sides) + 1; //generates a random number between 1 and 6 (both included) simulating the thrown of the die
    }
}