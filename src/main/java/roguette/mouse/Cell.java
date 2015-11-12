package roguette.mouse;

import java.util.ArrayList;
import java.util.List;

public class Cell implements Types {
    
    private final int type;
    private Creature occupant;
    private List<Item> items;

    public Cell(int type) {
        
        this.type = type;
        this.items = new ArrayList<>();
    }

    public int getType() {
        return type;
    }

    public Creature getOccupant() {
        return occupant;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setOccupant(Creature occupant) {
        this.occupant = occupant;
    }
}
