package box;

public interface BoxFactory {

    AbstractBox createBox(int boxNumber, int boxType, int specialBoxDestination);

}
