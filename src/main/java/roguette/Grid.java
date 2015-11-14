package roguette;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Grid {

    public final int width, height;
    private final Cell[][] cells;

    public Grid(int columns, int rows) {

        this.width = columns;
        this.height = rows;
        this.cells = new Cell[columns][rows];
    }

    public void setCell(int column, int row, Cell cell) {

        cells[column][row] = Objects.requireNonNull(cell);
    }

    public Cell getCell(int column, int row) {

        return cells[column][row];
    }

    public Point locateCreature(int id) {

        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {

                if (cells[x][y] != null) {

                    Creature occupant = cells[x][y].getOccupant();

                    if (occupant != null) {

                        if (occupant.getId() == id) {

                            return new Point(x, y);
                        }
                    }
                }
            }
        }

        return null;
    }

    public boolean isValidCell(Point p) {

        return p.x >= 0
                && p.y >= 0
                && p.x < cells.length
                && p.y < cells[p.x].length;
    }

    public void moveOccupant(Point p1, Point p2) {

        Creature c1 = cells[p1.x][p1.y].getOccupant();
        Creature c2 = cells[p2.x][p2.y].getOccupant();

        cells[p1.x][p1.y].setOccupant(c2);
        cells[p2.x][p2.y].setOccupant(c1);
    }

    public List<Item> getItems(Point p) {

        Cell c = cells[p.x][p.y];

        if (c != null) {

            return c.getItems();

        } else {

            return null;
        }
    }

    public Iterable<Creature> getCreatures(int type) {

        List<Creature> list = new ArrayList<>();

        for (Cell[] cell1 : cells) {
            for (Cell cell : cell1) {
                if (cell != null) {

                    Creature creature = cell.getOccupant();

                    if (creature != null && creature.getType() == type) {

                        list.add(creature);
                    }
                }
            }
        }

        return list;
    }

    public Iterable<Point> getLocations(int type) {

        List<Point> list = new ArrayList<>();

        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {

                Cell cell = cells[x][y];

                if (cell != null) {

                    Creature creature = cell.getOccupant();

                    if (creature != null && creature.getType() == type) {

                        list.add(new Point(x, y));
                    }
                }
            }
        }

        return list;
    }
}
