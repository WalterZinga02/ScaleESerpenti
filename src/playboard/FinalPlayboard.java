package playboard;

import box.AbstractBox;

import java.util.Random;

public class FinalPlayboard extends AbstractPlayboard {

    public FinalPlayboard(int rowsNumber, int columnsNumber, boolean stopBoxes, boolean bonusBoxes, boolean drawACardBoxes) {
        super(rowsNumber, columnsNumber, stopBoxes, bonusBoxes, drawACardBoxes);
        initializeBoxes();
    }

    @Override
    public void initializeBoxes() {
        //initializes every box in the board as basic box
        for (int row = 0; row < rowsNumber; row++) {
            for (int column = 0; column < columnsNumber; column++) {
                int boxNumber = row * columnsNumber + column + 1;
                boxes[row][column] = boxFactory.createBox(boxNumber,1,0);
            }
        }

        // generates 2 snakes and 2 ladders for each new play board as a standard option
        int max = rowsNumber*columnsNumber;
        Random random = new Random();

        //initializes 2 ladders
        for (int i = 0; i < 2; i++) {
            int ladderStart = random.nextInt(max-1)+1; //generates a number between 1 and "max-1" (both included)
            int ladderEnd = random.nextInt(max-ladderStart)+ladderStart+1; //generates a number between "ladderStart"+1 and "max" (both included)
            while (!checkBox(ladderStart) || !checkBox(ladderEnd)) { //repeat the process untill the initialization is correct
                ladderStart = random.nextInt(max-1)+1;
                ladderEnd = random.nextInt(max-ladderStart)+ladderStart+1;
            }
            AbstractBox ladderBox = boxFactory.createBox(ladderStart, 2,ladderEnd); //creates a new LadderBox using the Factory Method
            AbstractBox unusableBox = boxFactory.createBox(ladderEnd, 9,0);
            setBox(ladderStart, ladderBox); //set the newly created box in the right position on the play board
            setBox(ladderEnd, unusableBox);
        }

        //initializes 2 snakes
        for (int i = 0; i < 2; i++) {
            int snakeTail = random.nextInt(max-2)+1; //generates a number between 1 and "max-2" (both included)
            int snakeTongue = random.nextInt(max-snakeTail-1)+snakeTail+1;//generates a number between "snakeTail"+1 and "max-1" (both included)
            while (!checkBox(snakeTongue) || !checkBox(snakeTail)) { //repeat the process untill the initialization is correct
                snakeTail = random.nextInt(max-2)+1;
                snakeTongue = random.nextInt(max-snakeTail-1)+snakeTail+1;
            }
            AbstractBox snakeBox = boxFactory.createBox(snakeTongue, 3, snakeTail); //creates a new SnakeBox using the Factory Method
            setBox(snakeTongue, snakeBox); //sets the newly created box in the right position on the play board

            AbstractBox unusableBox = boxFactory.createBox(snakeTail, 9,0);//creates a new unusable box using the Factory Method
            setBox(snakeTail, unusableBox);//sets the newly created box in the right position on the play board
        }

        //initializes special boxes if required
        if(stopBoxes){
            initializeStopBoxes(max, random);
        }
        if(bonusBoxes){
            initializeBonusBoxes(max, random);
        }
        if(drawACardBoxes){
            initializeDrawACardBoxes(max, random);
        }
    }

    //checks if the box related to the boxNumber is already used for a special box
    private boolean checkBox(int boxNumber) {
        AbstractBox toCheck= getBox(boxNumber);
        return "Basic".equals(toCheck.getBoxType());
    }

    //sets the box in the right position on the play board
    private void setBox(int boxNumber, AbstractBox box) {
        int row = (boxNumber-1) / columnsNumber;
        int column = (boxNumber-1) % columnsNumber;
        boxes[row][column] = box;
    }

    //initializes special boxes, one for each type (except snake and ladder)
    private void initializeStopBoxes(int max, Random random) {
        for (int i = 4; i <6; i++) {
            int stopBoxNumber = random.nextInt(max-2)+2;
            while (!checkBox(stopBoxNumber)) {stopBoxNumber = random.nextInt(max-2)+2;}
            AbstractBox box = boxFactory.createBox(stopBoxNumber, i,0);
            setBox(stopBoxNumber, box);
        }
    }

    private void initializeBonusBoxes(int max, Random random) {
        for (int i = 6; i <8; i++) {
            int stopBoxNumber = random.nextInt(max-2)+2;
            while (!checkBox(stopBoxNumber)) {stopBoxNumber = random.nextInt(max-2)+2;}
            AbstractBox box = boxFactory.createBox(stopBoxNumber, i,0);
            setBox(stopBoxNumber, box);
        }
    }

    private void initializeDrawACardBoxes(int max, Random random) {
        int stopBoxNumber = random.nextInt(max-2)+2;
        while (!checkBox(stopBoxNumber)) {stopBoxNumber = random.nextInt(max-2)+2;}
        AbstractBox box = boxFactory.createBox(stopBoxNumber, 8,0);
        setBox(stopBoxNumber, box);
    }

}