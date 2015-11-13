package roguette.mouse;

import java.util.ArrayList;
import java.util.List;

public class Mouse extends Creature {

    private double health;
    private final List<Item> inventory;

    public Mouse() {

        super(MOUSE);

        health = 15;
        inventory = new ArrayList<>();
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
}
