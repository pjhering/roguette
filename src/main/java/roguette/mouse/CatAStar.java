package roguette.mouse;

import java.awt.Point;
import roguette.pathfinder.AStar;
import roguette.Cell;
import roguette.Creature;
import roguette.Grid;
import roguette.mapgen.Vertex;

class CatAStar extends AStar<Point> {

    private final Grid grid;
    private Point goal;

    public CatAStar(Grid grid) {
        
        super(new CatGraph(grid));
        this.grid = grid;
    }

    @Override
    public double heuristic(Vertex<Point> v) {
        
        Point p = v.getValue();
        
        // an udpate is necessary when the goal is null
        // or the current occupant of the goal is not the mouse
        if(goal == null) {
            
            update();
            
        } else {
            
            Cell cell = grid.getCell(goal.x, goal.y);
            Creature occupant = cell.getOccupant();
            
            if(occupant == null || occupant.getType() != Const.MOUSE) {
                
                update();
            }
        }
        
        return p.distance(goal);
    }
    
    private void update() {
        
        for(int x = 0; x < grid.width; x++) {
            for(int y = 0; y < grid.height; y++) {
                
                Cell cell = grid.getCell(x, y);
                Creature occupant = cell.getOccupant();
                
                if(occupant != null && occupant.getType() == Const.MOUSE) {
                    
                    goal = new Point(x, y);
                    return;
                }
            }
        }
        
        throw new RuntimeException("Where's the MOUSE?");
    }
}
