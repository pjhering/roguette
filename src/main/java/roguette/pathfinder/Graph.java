package roguette.pathfinder;

import java.util.Collection;
import roguette.mapgen.Vertex;

public interface Graph<T> {
    
    public Collection<Vertex<T>> adjacentEdges(Vertex<T> vertex);
    
    public boolean isGoal(Vertex<T> vertex);
}
