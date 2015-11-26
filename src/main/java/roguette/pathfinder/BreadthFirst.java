package roguette.pathfinder;

import java.util.HashSet;
import java.util.LinkedList;
import static java.util.Objects.requireNonNull;
import java.util.Queue;
import java.util.Set;
import roguette.mapgen.Vertex;

public class BreadthFirst<T> implements Search<T> {

    private final Graph<T> graph;

    public BreadthFirst(Graph<T> graph) {

        this.graph = requireNonNull(graph);
    }

    @Override
    public Vertex<T> search(Vertex<T> source) {
        
        Queue<Vertex<T>> queue = new LinkedList<>();
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
                
                queue.add(v);
            }
        }
        
        return null;
    }
}
