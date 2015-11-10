package roguette.mouse;

public class Grid {
    
    private final Cell[][] cells;

    Grid(int columns, int rows) {
        
        this.cells = new Cell[columns][rows];
    }
}
