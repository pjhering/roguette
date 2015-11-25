package roguette.mapgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static java.util.Objects.requireNonNull;

public class Vertex<T> {
    
    private final T value;
    private final Vertex<T> parent;
    private boolean discovered;
    private int cost;

    public Vertex(Vertex<T> parent, T value) {
        
        this.parent = parent;
        this.value = requireNonNull(value);
    }
    
    List<T> buildPath() {
        
        if(parent == null) {
            
            List<T> list = new ArrayList<>();
            list.add(value);
            return list;
            
        } else {
            
            List<T> list = parent.buildPath();
            list.add(value);
            return list;
        }
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

    public T getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        
        return this.value.toString();
    }
    
    @Override
    public int hashCode() {
        
        return this.value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vertex<?> other = (Vertex<?>) obj;
        return this.value.equals(other.value);
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Vertex<T> getParent() {
        return parent;
    }
}
