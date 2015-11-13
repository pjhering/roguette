package roguette.mouse;

public class Cat extends Creature {
    
    private int direction;

    public Cat() {
        
        super(CAT);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
