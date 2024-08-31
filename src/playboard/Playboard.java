package playboard;

import box.AbstractBox;

public interface Playboard {
    void initializeBoxes();
    int getRowsNumber();
    int getColumnsNumber();
    AbstractBox getBox(int boxNumber);
}
