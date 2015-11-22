package roguette;

import java.util.ArrayList;
import java.util.List;

public class Room {
    
    public final int top, left, bottom, right;
    private final int hash;
    private boolean north, south, west, east;

    public Room(int top, int left, int bottom, int right) {
        
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        
        if(top < 0 || left < 0) throw new RuntimeException("negative room coordinate");
        if(top >= bottom || left >= right) throw new RuntimeException("illegal room definition");
        if(bottom - top < 5 || right - left < 5) throw new RuntimeException("room too small");
        
        int n = 7;
        n = 13 * n + this.top;
        n = 13 * n + this.left;
        n = 13 * n + this.bottom;
        hash = 13 * n + this.right;
    }
    
    public void doors(int[][] map) {
        
        int midX = left + ((right - left) / 2);
        int midY = top + ((bottom - top) / 2);
        
        if(north) map[midX][top] = 0;
        
        if(south) map[midX][bottom] = 0;
        
        if(west) map[left][midY] = 0;
        
        if(east) map[right][midY] = 0;
    }
    
    public void walls(int[][] map) {
        
        for(int y = top; y <= bottom; y++) {
            for(int x = left; x <= right; x++) {
                
                if(x == left || x == right || y == top || y == bottom) {
                    
                    map[x][y] = 1;
                    
                } else {
                    
                    map[x][y] = 0;
                }
            }
        }
    }
    
    public int width() {
        
        return right - left;
    }
    
    public int height() {
        
        return bottom - top;
    }
    
    public boolean isAdjacentNorth(Room that) {
        
        return this.bottom == that.top &&
                this.left < that.right &&
                this.right > that.left;
    }
    
    public boolean isAdjacentSouth(Room that) {
        
        return this.top == that.bottom &&
                this.left < that.right &&
                this.right > that.left;
    }
    
    public boolean isAdjacentWest(Room that) {
        
        return this.right == that.left &&
                this.top < that.bottom &&
                this.bottom > that.top;
    }
    
    public boolean isAdjacentEast(Room that) {
        
        return this.left == that.right &&
                this.top < that.bottom &&
                this.bottom > that.top;
    }
    
    public List<Room> hSplit() {
        
        int middle = top + ((bottom - top) / 2);
        
        List<Room> list = new ArrayList<>();
        list.add(new Room(top, left, middle, right));
        list.add(new Room(middle, left, bottom, right));
        
        return list;
    }
    
    public List<Room> vSplit() {
        
        int middle = left + ((right - left) / 2);
        
        List<Room> list = new ArrayList<>();
        list.add(new Room(top, left, bottom, middle));
        list.add(new Room(top, middle, bottom, right));
        
        return list;
    }

    public void setNorth(boolean north) {
        this.north = north;
    }

    public void setSouth(boolean south) {
        this.south = south;
    }

    public void setWest(boolean west) {
        this.west = west;
    }

    public void setEast(boolean east) {
        this.east = east;
    }

    public boolean hasNorth() {
        return north;
    }

    public boolean hasSouth() {
        return south;
    }

    public boolean hasWest() {
        return west;
    }

    public boolean hasEast() {
        return east;
    }
    
    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        if (this.hash != other.hash) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        
        return "[" + top +
                ", " + left +
                ", " + bottom +
                ", " + right +
                "]";
    }
}
