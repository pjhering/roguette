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
        
    }
    
    private boolean isBlocked(Cell cell) {
        
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