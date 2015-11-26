package roguette.fov;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public abstract class FOV {

    private final int radius;
    private final Set<Point> memory;
    
    public FOV(int radius) {
        
        this.radius = radius;
        this.memory = new HashSet<>();
    }
    
    public abstract boolean isOpaque(Point p);
    
    public Set<Point> getMemory() {
        
        return memory;
    }
    
    public boolean isInLineOfSight(Point here, Point there) {
        
        Line line = new Line(here, there);
        
        for(Point p : line) {
            
            if(isOpaque(p)) {
                
                return false;
            }
        }
        
        return true;
    }
    
    public Set<Point> getFieldOfVision(Point center) {
        
        Circle circle = new Circle(center, radius);
        Set<Point> fov = new HashSet<>();
        
        for(Point perimeter : circle) {
            
            Line line = new Line(center, perimeter);
            
            LINE:
            for(Point p : line) {
                
                if(isOpaque(p)) {
                    
                    if(isOpaque(p)) {
                        
                        memory.add(p);
                        break LINE;
                    }
                    
                    fov.add(p);
                }
            }
        }
        
        return fov;
    }
}
