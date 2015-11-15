package roguette.mouse;

import roguette.Creature;
import java.awt.Point;
import java.util.List;
import static roguette.mouse.Const.CAT;
import static roguette.mouse.Const.SEEKING_WALL;
import static roguette.mouse.Const.RANDOM;

public class Cat extends Creature {
    
    private int direction;
    private int state;
    private final int side;
    private List<Point> path;
    private Point mouse;

    public Cat() {
        
        super(CAT);
        this.direction = RANDOM.nextInt(4);
        this.state = SEEKING_WALL;
        this.side = RANDOM.nextInt(2);
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

    public List<Point> getPath() {
        return path;
    }

    public Point getMouse() {
        return mouse;
    }

    public void setPath(List<Point> path) {
        this.path = path;
    }

    public void setMouse(Point mouse) {
        this.mouse = mouse;
    }
}
