package roguette.mapgen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConnectedGrid {

    public static int[][] generate(int width, int height, int columns, int rows) {
        
        int[][] map = new int[width][height];
        int roomWidth = width / columns;
        int roomHeight = height / rows;
        
        List<Point> open = new ArrayList<>();
        Set<Point> closed = new HashSet<>();
        List<Room> rooms = new ArrayList<>();
        
        open.add(new Point(columns / 2, rows / 2));
        
        while(!open.isEmpty()) {
            
            Collections.shuffle(open);
            Point p = open.remove(0);
            closed.add(p);
            
            int x1 = p.x * roomWidth;
            int y1 = p.y * roomHeight;
            int x2 = x1 + roomWidth;
            int y2 = y1 + roomHeight;
            Room room = new Room(y1, x1, y2, x2);
            
            Point north = new Point(p.x, p.y - 1);
            if(valid(north, columns, rows) && !open.contains(north) && !closed.contains(north)) {
                
                open.add(north);
                room.setNorth(true);
            }
            
            Point south = new Point(p.x, p.y + 1);
            if(valid(south, columns, rows) && !open.contains(south) && !closed.contains(south)) {
                
                open.add(south);
                room.setSouth(true);
            }
            
            Point west = new Point(p.x - 1, p.y);
            if(valid(west, columns, rows) && !open.contains(west) && !closed.contains(west)) {
                
                open.add(west);
                room.setWest(true);
            }
            
            Point east = new Point(p.x + 1, p.y);
            if(valid(east, columns, rows) && !open.contains(east) && !closed.contains(east)) {
                
                open.add(east);
                room.setEast(true);
            }
            
            rooms.add(room);
        }
        
        draw(rooms, map);
        
        return map;
    }
    
    private static boolean valid(Point p, int width, int height) {
        
        return p.x >= 0 && p.x < width &&
                p.y >= 0 && p.y < height;
    }

    private static void draw(List<Room> rooms, int[][] map) {
        
        rooms.stream().forEach((r) -> {
            r.walls(map);
        });
        
        rooms.stream().forEach((r) -> {
            r.doors(map);
        });
    }
}
