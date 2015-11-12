package roguette.mouse;

public class GridBuilder implements Types {
    
    private final int columns, rows;
    private final Grid grid;

    public GridBuilder(int columns, int rows) {
        
        this.columns = columns;
        this.rows = rows;
        this.grid = new Grid(columns, rows);
    }
    
    GridBuilder createRooms() {
        
        int[][] map = MapGen.randomRectangularRooms(columns, rows);
        
        for(int x = 0; x < columns; x++) {
            for(int y = 0; y < rows; y++) {
                
                switch(map[x][y]) {
                    
                    case 0:
                        if(x > 0 && x < 4 && y > 0 && y < 4) {
                            
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
        return this;//TODO
    }
    
    GridBuilder createHome() {
        return this;//TODO
    }
    
    GridBuilder createMouse(int column, int row) {
        return this;//TODO
    }
    
    GridBuilder createCats(int count) {
        return this;//TODO
    }
    
    GridBuilder createCheese(int count) {
        return this;//TODO
    }
    
    GridBuilder createFluff(int count) {
        return this;//TODO
    }
    
    Grid build(){
        return grid;
    }
}
