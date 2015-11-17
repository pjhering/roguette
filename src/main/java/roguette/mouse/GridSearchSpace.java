package roguette.mouse;

import java.awt.Point;
import static java.lang.Math.abs;
import java.util.HashSet;
import static java.util.Objects.requireNonNull;
import java.util.Set;
import roguette.Cell;
import roguette.Creature;
import roguette.Grid;
import roguette.SearchSpace;
import static roguette.mouse.Const.MOUSE;
import static roguette.mouse.Const.WALL;

public class GridSearchSpace implements SearchSpace<Point> {

    private Grid grid;
    
    public GridSearchSpace(Grid grid) {
        
        this.grid = requireNonNull(grid);
    }

    @Override
    public Set<Point> getEdges(Point p) {
        
        Set<Point> edges = new HashSet<>();
        
        Point north = new Point(p.x, p.y - 1);
        Point south = new Point(p.x, p.y + 1);
        Point west = new Point(p.x - 1, p.y);
        Point east = new Point(p.x + 1, p.y);
        
        if(valid(grid.getCell(north.x, north.y))) {
            
            edges.add(north);
        }
        
        if(valid(grid.getCell(south.x, south.y))) {
            
            edges.add(south);
        }
        
        if(valid(grid.getCell(west.x, west.y))) {
            
            edges.add(west);
        }
        
        if(valid(grid.getCell(east.x, east.y))) {
            
            edges.add(east);
        }
        
        return edges;
    }
    
    private boolean valid(Cell cell) {
        
        return cell != null &&
                cell.getType() != WALL &&
                cell.getOccupant() == null;
    }

    @Override
    public double getCost(Point a, Point b) {
        
        return abs(a.x - b.x) + abs(a.y - b.y);
    }

    @Override
    public double getEstimate(Point a, Point b) {
        
        return abs(a.x - b.x) + abs(a.y - b.y);
    }

    @Override
    public boolean isGoal(Point p) {
        
        Cell cell = grid.getCell(p.x, p.y);
        
        if(cell == null) {
            
            return false;
            
        } else {
            
            Creature c = cell.getOccupant();
            
            if(c == null) {
                
                return false;
                
            } else {
                
                return c.getType() == MOUSE;
            }
        }
    }
}
