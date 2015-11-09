package roguette;

import java.util.List;

public class Occupant {

    public final Attributes attributes;
    public boolean moved;
    public int direction;
    public Location location;

    public Occupant() {
        
        this.attributes = new Attributes();
    }

    public void takeAll(List<Item> remove) {
    }

    public void decrement(int HEALTH, int i) {
    }

    public List<Item> dropAll(int FLUFF) {
        return null;
    }
}
