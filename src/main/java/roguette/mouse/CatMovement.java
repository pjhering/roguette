package roguette.mouse;

import roguette.Grid;
import java.awt.Point;
import roguette.Cell;
import static roguette.mouse.Const.*;

public class CatMovement {
    
    /*
    wall following algorithm
    
    SEEKING:
    if left tile is blocked
       enter FOLLOWING state
    else if forward tile is blocked
       turn right
       enter FOLLOWING state
    else 
       move forward
    
    FOLLOWING:
    if forward tile is blocked
       turn right
       move forward
    else if left tile is blocked
       move forward
    else
       turn left
       move forward
    */
    void patrolRooms(Grid grid, Point p) {
        
        Cat cat = (Cat) grid.getCell(p.x, p.y).getOccupant();
        int facing = cat.getDirection();
        
        Point f = direction(p, facing, FORWARD);
        Cell forward = grid.getCell(f.x, f.y);
        
        Point l = direction(p, facing, LEFT);
        Cell left = grid.getCell(l.x, l.y);
        
        switch(cat.getState()) {
            
            case SEEKING_WALL:
                if(isBlocked(left)) {
                    
                    cat.setState(FOLLOWING_WALL);
                    patrolRooms(grid, p);
                    
                } else if(isBlocked(forward)) {
                    
                    int dir = turn(facing, RIGHT);
                    cat.setDirection(dir);
                    cat.setState(FOLLOWING_WALL);
                    patrolRooms(grid, p);
                    
                } else {
                    
                    grid.moveOccupant(p, f);
                }
                break;
                
            case FOLLOWING_WALL:
                if(isBlocked(forward)) {
                    
                    int dir = turn(facing, RIGHT);
                    cat.setDirection(dir);
                    
                } else if(isBlocked(left)) {
                    
                    grid.moveOccupant(p, f);
                    
                } else {
                    
                    int dir = turn(facing, LEFT);
                    cat.setDirection(dir);
                    cat.setState(SEEKING_WALL);
                }
                break;
        }
    }
    
    private int turn(int facing, int direction) {
        
        switch(facing) {
            
            case NORTH:
                if(direction == LEFT) return WEST;
                if(direction == RIGHT) return EAST;
                if(direction == FORWARD) return NORTH;
                return SOUTH;
                
            case SOUTH:
                if(direction == LEFT) return EAST;
                if(direction == RIGHT) return WEST;
                if(direction == FORWARD) return SOUTH;
                return NORTH;
                
            case WEST:
                if(direction == LEFT) return SOUTH;
                if(direction == RIGHT) return NORTH;
                if(direction == FORWARD) return WEST;
                return EAST;
                
            case EAST:
                if(direction == LEFT) return NORTH;
                if(direction == RIGHT) return SOUTH;
                if(direction == FORWARD) return EAST;
                return WEST;
        }
        
        return facing;
    }
    
    private boolean isBlocked(Cell cell) {
        
        if(cell == null) {
            
            return true;
        }
        
        int type = cell.getType();
        
        if(type == FLOOR || type == HOME) {
            
            return cell.getOccupant() != null;
        }
        
        return true;
    }
    
    private Point direction(Point p, int facing, int moving) {
        
        switch(facing) {
            
            case NORTH:
                if(moving == FORWARD) return new Point(p.x, p.y - 1);
                if(moving == LEFT) return new Point(p.x - 1, p.y);
                if(moving == RIGHT) return new Point(p.x + 1, p.y);
                break;
                
            case EAST:
                if(moving == FORWARD) return new Point(p.x + 1, p.y);
                if(moving == LEFT) return new Point(p.x, p.y - 1);
                if(moving == RIGHT) return new Point(p.x, p.y + 1);
                break;
                
            case SOUTH:
                if(moving == FORWARD) return new Point(p.x, p.y + 1);
                if(moving == LEFT) return new Point(p.x + 1, p.y);
                if(moving == RIGHT) return new Point(p.x - 1, p.y);
                break;
                
            case WEST:
                if(moving == FORWARD) return new Point(p.x - 1, p.y);
                if(moving == LEFT) return new Point(p.x, p.y + 1);
                if(moving == RIGHT) return new Point(p.x, p.y - 1);
                break;
        }
        
        return p;
    }
    
    void pursueMouse(Grid grid, Point p) {}
    
    void attackMouse(Grid grid, Point p) {}
}