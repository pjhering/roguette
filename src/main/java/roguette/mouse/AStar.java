package roguette.mouse;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import static java.lang.Math.abs;

public class AStar {
    
    private final Grid grid;
    private final int mouse;

    public AStar(Grid grid, int mouse) {
        
        this.grid = Objects.requireNonNull(grid);
        this.mouse = mouse;
    }
    
    List<Point> getPathFrom(int column, int row) {
        
        Set<Point> closed = new HashSet<>();
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        Point current = new Point(column, row);
        Point goal = grid.locateCreature(mouse);
        
        return null;
    }
    
    private double getHeuristic(Point a, Point b) {
        
        return abs(a.x - b.x) + abs(a.y - b.y);
    }
    
    private List<Point> getNeighbors(Point p) {
        return null;
    }
    
    static class Node implements Comparable<Node> {
        
        private final int priority;
        private final Point point;
        
        Node(int priority, Point point) {
            
            this.priority = priority;
            this.point = Objects.requireNonNull(point);
        }

        @Override
        public int compareTo(Node that) {
            
            if(this.priority < that.priority) {
                
                return 1;
                
            } else if(this.priority > that.priority) {
                
                return 1;
            }
            
            return 0;
        }
    }
}
