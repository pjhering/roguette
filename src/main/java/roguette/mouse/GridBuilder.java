package roguette.mouse;

import roguette.Item;
import roguette.Creature;
import roguette.Cell;
import roguette.Grid;
import java.awt.Point;
import roguette.mapgen.RoomGen;

public class GridBuilder implements Const {

    private final int columns, rows;
    private final Grid grid;

    public GridBuilder(int columns, int rows) {

        this.columns = columns;
        this.rows = rows;
        this.grid = new Grid(columns, rows);
    }

    GridBuilder createRooms() {

        int[][] map = RoomGen.division(columns, rows);

        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {

                switch (map[x][y]) {

                    case 0:
                        if (x > 0 && x < 4 && y > 0 && y < 4) {

                            grid.setCell(x, y, new Cell(HOME));

                        } else {

                            grid.setCell(x, y, new Cell(FLOOR));
                        }
                        break;

                    case 1:
                        grid.setCell(x, y, new Cell(WALL));
                        break;
                }
            }
        }
        return this;
    }

    GridBuilder createHome() {

        for (int x = 1; x < 4; x++) {
            for (int y = 1; y < 4; y++) {

                grid.getCell(x, y).setType(HOME);
            }
        }

        return this;
    }

    GridBuilder createMouse(int column, int row) {

        Creature mouse = new Mouse();
        grid.getCell(2, 2).setOccupant(mouse);

        return this;
    }

    GridBuilder createCats(int count) {

        int i = 0;

        while (i < count) {

            Point p = randomPoint(12, 12, columns - 12, rows - 12);
            Cell cell = grid.getCell(p.x, p.y);

            if (cell.getType() == FLOOR && cell.getOccupant() == null) {

                cell.setOccupant(new Cat());
                i += 1;
            }
        }

        return this;
    }

    private Point randomPoint(int x1, int y1, int width, int height) {

        int x = (int) (x1 + (Math.random() * width));
        int y = (int) (y1 + (Math.random() * height));

        return new Point(x, y);
    }

    GridBuilder createCheese(int count) {

        createItem(count, CHEESE);
        return this;
    }

    GridBuilder createFluff(int count) {

        createItem(count, FLUFF);
        return this;
    }

    private void createItem(int count, int type) {

        int i = 0;

        while (i < count) {

            Point p = randomPoint(12, 12, columns - 12, rows - 12);
            Cell cell = grid.getCell(p.x, p.y);

            if (cell.getType() == FLOOR) {

                cell.getItems().add(new Item(type));
                i += 1;
            }
        }
    }

    Grid build() {

        return grid;
    }
}
