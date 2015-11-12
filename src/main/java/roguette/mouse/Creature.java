package roguette.mouse;

import java.util.ArrayList;
import java.util.List;
import static java.util.UUID.randomUUID;

public class Creature implements Types {

    private final int id;
    private final int type;
    private double health;
    private final List<Item> inventory;

    public Creature(int type) {

        this.id = randomUUID().hashCode();
        this.type = type;

        if (type == MOUSE) {

            health = 15;
            inventory = new ArrayList<>();

        } else {

            this.health = 0;
            this.inventory = null;
        }
    }

    public int getType() {
        return type;
    }

    public double getHealth() {
        return health;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public int getId() {
        return id;
    }
}
