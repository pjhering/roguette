package roguette.fov;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Circle implements Iterable<Point> {

    private final List<Point> points;

    public Circle(Point center, int radius) {

        points = new ArrayList<>();

        int d = (5 - radius * 4) / 4;
        int x = 0;
        int y = radius;

        do {
            points.add(new Point(center.x + x, center.y + y));
            points.add(new Point(center.x + x, center.y - y));
            points.add(new Point(center.x - x, center.y + y));
            points.add(new Point(center.x - x, center.y - y));
            points.add(new Point(center.x + y, center.y + x));
            points.add(new Point(center.x + y, center.y - x));
            points.add(new Point(center.x - y, center.y + x));
            points.add(new Point(center.x - y, center.y - x));
            
            if (d < 0) {
                
                d += 2 * x + 1;
                
            } else {
                
                d += 2 * (x - y) + 1;
                y--;
            }
            
            x++;
            
        } while (x <= y);
    }

    @Override
    public Iterator<Point> iterator() {

        return points.iterator();
    }
}
