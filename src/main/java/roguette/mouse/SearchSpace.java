package roguette.mouse;

import java.util.Set;

public interface SearchSpace<T> {

    Set<T> getEdges(T node);
    
    double getCost(T from, T to);
    
    double getEstimate(T from, T to);
    
    boolean isGoal(T node);

    public T getOrigin();
}
