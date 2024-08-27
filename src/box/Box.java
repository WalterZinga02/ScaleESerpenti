package box;

public class Box implements BoxFactory {

    @Override
    public AbstractBox createBox(int boxNumber, int boxType, int specialBoxDestination) {
        switch (boxType) {
            case 1:
                return new BasicBox(boxNumber);
            case 2:
                return new LadderBox(boxNumber, specialBoxDestination);
            case 3:
                return new SnakeBox(boxNumber, specialBoxDestination);
            case 4:
                return new BenchBox(boxNumber);
            case 5:
                return new InnBox(boxNumber);
            case 6:
                return new SpringBox(boxNumber);
            case 7:
                return new DicesBox(boxNumber);
            case 8:
                return new DrawACardBox(boxNumber);
            case 9:
                return new UnusableBox(boxNumber);
            default:
                throw new IllegalArgumentException("wrong box type");
        }
    }
}