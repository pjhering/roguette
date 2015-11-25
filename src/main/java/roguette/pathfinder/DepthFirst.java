package roguette.pathfinder;

import java.util.HashSet;
import static java.util.Objects.requireNonNull;
import java.util.Set;
import java.util.Stack;
import roguette.mapgen.Vertex;

public class DepthFirst<T> implements Search<T> {

    private final Graph<T> graph;

    public DepthFirst(Graph<T> graph) {

        this.graph = requireNonNull(graph);
    }

    @Override
    public Vertex<T> search(Vertex<T> source) {
        
        Stack<Vertex<T>> stack = new Stack<>();
        Set<Vertex<T>> visited = new HashSet<>();
        
        stack.push(source);
        
        while(!stack.isEmpty()) {
            
            Vertex<T> current = stack.pop();
            
            if(graph.isGoal(current)) {
                
                return current;
            }
            
            if(visited.contains(current)) {
                
                continue;
            }
            
            visited.add(current);
            
            for(Vertex<T> v : graph.adjacentEdges(current)) {
                
                stack.push(v);
            }
        }
        
        return null;
    }
}
