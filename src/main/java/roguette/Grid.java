package roguette;

import java.awt.Point;
import java.util.List;

public interface Grid {

    Cell getCell(int column, int row);

    Iterable<Creature> getCreatures(int type);

    List<Item> getItems(Point p);

    Iterable<Point> getLocations(int type);

    boolean isValidCell(Point p);

    Point locateCreature(int id);

    void moveOccupant(Point p1, Point p2);

    void setCell(int column, int row, Cell cell);
}
