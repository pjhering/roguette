package roguette.mouse;

public class GridBuilder {
    
    private final Grid grid;

    public GridBuilder(int columns, int rows) {
        this.grid = new Grid(columns, rows);
    }
    
    GridBuilder createRooms() {
        return this;//TODO
    }
    
    GridBuilder createHome() {
        return this;//TODO
    }
    
    GridBuilder createMouse() {
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
