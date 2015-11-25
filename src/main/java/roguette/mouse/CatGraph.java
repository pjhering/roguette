package roguette.mouse;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static java.util.Objects.requireNonNull;
import roguette.Cell;
import roguette.Creature;
import roguette.pathfinder.Graph;
import roguette.Grid;
import roguette.mapgen.Vertex;
import static roguette.mouse.Const.WALL;

public class CatGraph implements Graph<Point> {

    private final Grid grid;

    CatGraph(Grid grid) {

        this.grid = requireNonNull(grid);
    }

    @Override
    public Collection<Vertex<Point>> adjacentEdges(Vertex<Point> vertex) {

        List<Vertex<Point>> list = new ArrayList<>();
        Point p = vertex.getValue();

        Point n = new Point(p.x, p.y - 1);
        Point s = new Point(p.x, p.y + 1);
        Point w = new Point(p.x - 1, p.y);
        Point e = new Point(p.x + 1, p.y);

        if (valid(n)) {
            list.add(new Vertex(vertex, n));
        }
        if (valid(s)) {
            list.add(new Vertex(vertex, s));
        }
        if (valid(w)) {
            list.add(new Vertex(vertex, w));
        }
        if (valid(e)) {
            list.add(new Vertex(vertex, e));
        }

        return list;
    }

    private boolean valid(Point p) {
        
        if(p.x >= 0 && p.y >= 0 && p.x < grid.width && p.y < grid.height) {
            
            Cell cell = grid.getCell(p.x, p.y);
            
            if(cell.getType() == WALL) {
                
                return false;
            }
            
            Creature c = cell.getOccupant();
            
            return c == null || c.getType() == Const.MOUSE;
            
        } else {
            
            return false;
        }
    }

    @Override
    public boolean isGoal(Vertex<Point> vertex) {

        Point p = vertex.getValue();
        Cell cell = grid.getCell(p.x, p.y);
        Creature occupant = cell.getOccupant();

        return occupant != null && occupant.getType() == Const.MOUSE;
    }
}
