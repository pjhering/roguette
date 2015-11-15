package roguette.mouse;

import roguette.Item;
import roguette.Creature;
import java.util.ArrayList;
import java.util.List;
import static roguette.mouse.Const.*;

public class Mouse extends Creature {

    private double health;
    private final List<Item> inventory;
    private int deposited;

    public Mouse() {

        super(MOUSE);

        health = 15;
        inventory = new ArrayList<>();
    }
    
    public void deposit() {
        
        deposited += 1;
    }
    
    public int countDeposited() {
        
        return deposited;
    }
    
    public int countFluff() {
        
        int count = 0;
        
        for(Item i : inventory) {
            
            count += (i.getType() == FLUFF)? 1 : 0;
        }
        
        return count;
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
