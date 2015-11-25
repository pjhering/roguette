package roguette.pathfinder;

import roguette.mapgen.Vertex;

public interface Search<T> {
    
    Vertex<T> search(Vertex<T> vertex);
}
