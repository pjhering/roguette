package roguette.mouse;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    private int type;
    private Creature occupant;
    private List<Item> items;

    Cell(int type) {

        this.type = type;
        this.items = new ArrayList<>();
    }

    void setType(int type) {
        this.type = type;
    }

    int getType() {
        return type;
    }

    Creature getOccupant() {
        return occupant;
    }

    List<Item> getItems() {
        return items;
    }

    void setOccupant(Creature occupant) {
        this.occupant = occupant;
    }
}
