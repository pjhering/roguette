package roguette.mouse;

import java.util.Random;

public class Cat extends Creature {
    
    public static final int PATROLLING = 0;
    public static final int PURSUING = 1;
    public static final int ATTACKING = 2;
    
    public static final int NORTH = 0;
    public static final int WEST = 1;
    public static final int SOUTH = 2;
    public static final int EAST = 3;
    
    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    
    private static final Random R = new Random();
    
    private int direction;
    private int state;
    private final int side;

    public Cat() {
        
        super(CAT);
        this.direction = R.nextInt(4);
        this.state = PATROLLING;
        this.side = R.nextInt(2);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getSide() {
        return side;
    }
}
