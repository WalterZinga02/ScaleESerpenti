package player;

public class PlayerMemento {
    private final String name;
    private final int position;
    private final int turnsToSkip;
    private final int lastThrow;

    public PlayerMemento(String name, int position, int turnsToSkip, int lastThrow) {
        this.name = name;
        this.position = position;
        this.turnsToSkip = turnsToSkip;
        this.lastThrow = lastThrow;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getTurnsToSkip() {
        return turnsToSkip;
    }

    public int getLastThrow() {
        return lastThrow;
    }
}
