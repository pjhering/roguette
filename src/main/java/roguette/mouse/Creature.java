package roguette.mouse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Creature {
    
    public static final int MOUSE = 0;
    public static final int CAT = 1;
    
    private final int id;
    private final int type;
    private double health;
    private final List<Item> inventory;

    public Creature(int type) {
        
        this.id = UUID.randomUUID().hashCode();
        this.type = type;
        
        if(type == MOUSE) {
            
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
