package playboard;

import box.AbstractBox;
import box.Box;
import box.BoxFactory;

public abstract class AbstractPlayboard implements Playboard {
    protected int rowsNumber;
    protected int columnsNumber;
    protected boolean stopBoxes;
    protected boolean bonusBoxes;
    protected boolean drawACardBoxes;
    protected AbstractBox[][] boxes;
    protected BoxFactory boxFactory;

    public AbstractPlayboard(int rowsNumber, int columnsNumber, boolean stopBoxes, boolean bonusBoxes, boolean drawACardBoxes) {

        //checks if the size of the newly created play board is suitable for a balanced organization of the game boxes
        int totalBoxes = rowsNumber * columnsNumber;
        if (totalBoxes<25 || totalBoxes>100) {
            throw new IllegalArgumentException("the number of boxes must be greater than 25 and less than 100.");
        }

        this.rowsNumber = rowsNumber;
        this.columnsNumber = columnsNumber;
        this.stopBoxes = stopBoxes;
        this.bonusBoxes = bonusBoxes;
        this.drawACardBoxes = drawACardBoxes;
        this.boxFactory = new Box();
        this.boxes = new AbstractBox[rowsNumber][columnsNumber];
    }

    @Override
    public int getRowsNumber() {
        return rowsNumber;
    }

    @Override
    public int getColumnsNumber() {
        return columnsNumber;
    }

    @Override
    public abstract void initializeBoxes();

    //returns the box from the play board related to the box number
    @Override
    public AbstractBox getBox(int boxNumber) {
        if (boxNumber < 1 || boxNumber > rowsNumber * columnsNumber) {
            throw new IllegalArgumentException("Box number is out of bounds.");
        }
        return boxes[(boxNumber - 1) / columnsNumber][(boxNumber - 1) % columnsNumber];
    }

}