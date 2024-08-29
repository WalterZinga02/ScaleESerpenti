package player;

public class ConcretePlayer extends AbstractPlayer{

    public ConcretePlayer(String name, int maxPosition) {
        super(name, maxPosition);
    }

    //memento methods

    public PlayerMemento saveMemento() {
        return new PlayerMemento(name, position, turnsToSkip, lastThrow);
    }

    public void restoreFromMemento(PlayerMemento memento) {
        setName(memento.getName());
        setPosition(memento.getPosition());
        setTurnsToSkip(memento.getTurnsToSkip());
        setLastThrow(memento.getLastThrow());
    }
}
