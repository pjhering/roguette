package roguette;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static java.util.Objects.requireNonNull;
import java.util.Set;

public class DepthFirst<T> implements Search<T> {

    private final SearchSpace search;
    private Node<T> root;
    private final Comparator<Node<T>> deepest = (Node<T> o1, Node<T> o2) -> o1.depth() - o2.depth();

    public DepthFirst(SearchSpace search) {

        this.search = requireNonNull(search);
    }

    @Override
    public List<T> getPath(T origin) {

        List<T> path = new ArrayList<>();
        if(origin == null) {
            
            return path;
        }
        
        root = new Node(origin);

        if (search.isGoal(root.getValue())) {

            root.buildPath(path);

        } else {

            visit(root, path);
        }

        return path;
    }

    private void visit(Node n, List<T> path) {

        n.setVisited(true);
        
        if(search.isGoal(n.getValue())) {
            
            n.buildPath(path);
            return;
        }

        Set<Point> edges = search.getEdges(n.getValue());

        edges.stream().forEach((p) -> {

            if (!root.contains((T) p)) {
                
                n.add(p);
            }
        });

        Node<T> next = next();
        
        if(next == null) {
            
            return;
        }

        if (!search.isGoal(next.getValue())) {

            visit(next, path);

        } else {

            next.buildPath(path);
        }
    }

    private Node<T> next() {
        
        List<Node<T>> unvisited = new ArrayList<>();
        root.getUnvisited(unvisited);
        
        if(unvisited.isEmpty()) return null;
        
        Collections.sort(unvisited, deepest);
        
        return unvisited.get(0);
    }
}
