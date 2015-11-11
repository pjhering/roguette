package roguette.mouse;

import java.awt.Point;

public class Grid {
    
    private final Cell[][] cells;

    Grid(int columns, int rows) {
        
        this.cells = new Cell[columns][rows];
    }
    
    public Cell getCell(int column, int row) {
        
        return cells[column][row];
    }

    Point locateCreature(int id) {
        
        for(int x = 0; x < cells.length; x++) {
            for(int y = 0; y < cells[x].length; y++) {
                
                if(cells[x][y] != null) {
                    
                    Creature occupant = cells[x][y].getOccupant();
                    
                    if(occupant != null) {
                        
                        if(occupant.getId() == id) {
                            
                            return new Point(x, y);
                        }
                    }
                }
            }
        }
        
        return null;
    }

    boolean isValidCell(Point p) {
        
        return p.x >= 0 && p.y >= 0 &&
                p.x < cells.length && p.y < cells[p.x].length;
    }

    void moveOccupant(Point p1, Point p2) {
        
        Creature c1 = cells[p1.x][p1.y].getOccupant();
        Creature c2 = cells[p2.x][p2.y].getOccupant();
        
        cells[p1.x][p1.y].setOccupant(c2);
        cells[p2.x][p2.y].setOccupant(c1);
    }
}
