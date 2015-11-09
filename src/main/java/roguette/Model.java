package roguette;

public abstract class Model {
    
    public Size size;
    public Occupant player;
    
    public Iterable<Occupant> occupants(int type) {
        return null;
    }

    public Occupant occupant(Location location, int direction) {
        return null;
    }

    public void move(Occupant player) {
    }

    public Items items(Location location) {
        return null;
    }
}
