package roguette.mouse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static java.util.Objects.requireNonNull;

public class Node<T> {

    private final Node<T> parent;
    private List<Node<T>> children;
    private final T value;
    private double cost;
    private boolean visited;

    public Node(T value) {

        this.parent = null;
        this.value = requireNonNull(value);
    }

    public Node(Node<T> parent, T value) {

        this.parent = requireNonNull(parent);
        this.value = requireNonNull(value);
    }

    public void getUnvisited(Collection<Node<T>> unvisited) {
        
        if(!visited) {
            
            unvisited.add(this);
        }
        
        if(children == null) {
            
            return;
        }
        
        for(Node<T> child : children) {
            
            child.getUnvisited(unvisited);
        }
    }
    
    public void buildPath(List<T> path) {
        
        path.add(value);
        
        if(parent != null) {
            
            parent.buildPath(path);
        }
    }
    
    public Node<T> getRoot() {
        
        if(this.parent == null) {
            
            return this;
        }
        
        return this.parent.getRoot();
    }
    
    Iterable<Node<T>> getChildren() {
        
        return this.children;
    }
    
    public int depth() {

        if (parent != null) {

            return 1 + parent.depth();
        }

        return 0;
    }

    public Node<T> find(T query) {

        if (value.equals(query)) {

            return this;
        }

        if (children != null) {

            for (Node<T> n : children) {

                Node<T> found = n.find(query);

                if (found != null) {

                    return found;
                }
            }
        }

        return null;
    }

    public boolean contains(T query) {

        if (value.equals(query)) {
            
            return true;
        }

        if (children != null) {

            for (Node<T> n : children) {

                if (n.contains(query)) {
                    
                    return true;
                }
            }
        }

        return false;
    }

    public void add(T child) {

        if (children == null) {

            children = new ArrayList<>();
        }

        children.add(new Node(this, child));
    }

    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();
        
        int count = this.depth();
        for (int i = 0; i < count; i++) {

            buffer.append("--");
        }
        buffer.append(value);
        buffer.append('\n');

        if(children != null) {
            
            for(Node<T> n : children) {
                buffer.append(n);
            }
        }
        return buffer.toString();
    }

    public T getValue() {

        return this.value;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
