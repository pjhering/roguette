package roguette;

public class Location {
    
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public final int x, y, z;
    
    public Location(int x, int y, int z) {
        
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean is(int HOME) {
        return false;
    }

    public double distance(Location location) {
        return 0;
    }

    public int direction(Location get) {
        return 0;
    }
}
