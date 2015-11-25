package roguette.fov;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Line implements Iterable<Point> {
    
    private final List<Point> points;

    public Line(Point a, Point b) {
        
        points = new ArrayList<>();
        // delta of exact value and rounded value of the dependant variable
        int x1 = a.x;
        int y1 = a.y;
        int x2 = b.x;
        int y2 = b.y;
        int d = 0;
 
        int dy = Math.abs(y2 - y1);
        int dx = Math.abs(x2 - x1);
 
        int dy2 = (dy << 1); // slope scaling factors to avoid floating
        int dx2 = (dx << 1); // point
 
        int ix = x1 < x2 ? 1 : -1; // increment direction
        int iy = y1 < y2 ? 1 : -1;
 
        if (dy <= dx) {
            for (;;) {
                points.add(new Point(x1, y1));
                if (x1 == x2)
                    break;
                x1 += ix;
                d += dy2;
                if (d > dx) {
                    y1 += iy;
                    d -= dx2;
                }
            }
        } else {
            for (;;) {
                points.add(new Point(x1, y1));
                if (y1 == y2)
                    break;
                y1 += iy;
                d += dx2;
                if (d > dy) {
                    x1 += ix;
                    d -= dy2;
                }
            }
        }
    }

    @Override
    public Iterator<Point> iterator() {
        
        return points.iterator();
    }
}
