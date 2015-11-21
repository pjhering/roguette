package roguette;

import java.util.Collection;

public interface Graph<T> {
    
    public Collection<Vertex<T>> adjacentEdges(Vertex<T> vertex);
    
    public boolean isGoal(Vertex<T> vertex);
}
