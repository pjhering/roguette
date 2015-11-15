package roguette.mouse;

import roguette.Item;
import roguette.Creature;
import java.util.ArrayList;
import java.util.List;
import static roguette.mouse.Const.MOUSE;

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
