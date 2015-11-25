package roguette.pathfinder;

import java.util.Comparator;
import java.util.HashSet;
import static java.util.Objects.requireNonNull;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import roguette.mapgen.Vertex;

public abstract class AStar<T> implements Search<T> {

    private final Graph<T> graph;
    private final Comparator<Vertex<T>> best;

    public AStar(Graph<T> graph) {

        this.graph = requireNonNull(graph);
        best = (Vertex<T> a, Vertex<T> b) -> {
            double aa = a.getCost() + heuristic(a);
            double bb = b.getCost() + heuristic(b);
            
            if(aa < bb) return -1;
            
            if(aa > bb) return 1;
            
            return 0;
        };
    }
    
    public abstract double heuristic(Vertex<T> v);

    @Override
    public Vertex<T> search(Vertex<T> source) {
        
        Queue<Vertex<T>> queue = new PriorityQueue<>(best);
        Set<Vertex<T>> visited = new HashSet<>();
        
        queue.add(source);
        
        while(!queue.isEmpty()) {
            
            Vertex<T> current = queue.remove();
            
            if(graph.isGoal(current)) {
                
                return current;
            }
            
            if(visited.contains(current)) {
                
                continue;
            }
            
            visited.add(current);
            
            for(Vertex<T> v : graph.adjacentEdges(current)) {
                
                v.setCost(current.getCost() + 1);
                queue.add(v);
            }
        }
        
        return null;
    }
}
