package playboard;

import box.AbstractBox;

public class PlayboardMemento {

    private final AbstractBox[][] boxes;
    private final int rowsNumber;
    private final int columnsNumber;

    public PlayboardMemento(AbstractBox[][] boxes, int rowsNumber, int columnsNumber) {
        this.rowsNumber = rowsNumber;
        this.columnsNumber = columnsNumber;
        this.boxes = new AbstractBox[rowsNumber][columnsNumber];

        for (int i = 0; i < rowsNumber; i++) {
            for (int j = 0; j < columnsNumber; j++) {
                this.boxes[i][j] = boxes[i][j].copy();
            }
        }
    }

    public AbstractBox[][] getBoxes() {
        return boxes;
    }

    public int getRowsNumber() {
        return rowsNumber;
    }

    public int getColumnsNumber() {
        return columnsNumber;
    }
}
