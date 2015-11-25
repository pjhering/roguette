package roguette.fov;

import java.awt.Point;
import static java.util.Objects.requireNonNull;
import java.util.Set;
import roguette.Grid;

public class FOV {

    private final Grid grid;
    
    public FOV(Grid grid) {
        
        this.grid = requireNonNull(grid);
    }

    public Set<Point> getViewable() {
        
        return null;
    }
}
