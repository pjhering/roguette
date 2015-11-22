package roguette;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomGen {

    public static int[][] division(int width, int height) {

        int[][] map = new int[width][height];
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(0, 0, height - 1, width - 1));
        int count = (width / 10) * (height / 9);

        // generate room objects
        div_part_1(rooms, count);

        // connect rooms
        div_part_2(rooms);

        // draw rooms
        div_part_3(rooms, map);

        return map;
    }

    private static void div_part_1(List<Room> rooms, int count) {

        for (int i = 0; i < count; i++) {

            Collections.shuffle(rooms);
            Room next = next(rooms);

            rooms.remove(next);

            double d = Math.random();

            if (d < 0.5) {
                
                if (next.width() > 10) {

                    List<Room> more = next.vSplit();
                    rooms.addAll(more);

                } else if (next.height() > 10) {

                    List<Room> more = next.hSplit();
                    rooms.addAll(more);
                }
            } else {
                
                if (next.height() > 10) {

                    List<Room> more = next.hSplit();
                    rooms.addAll(more);

                } else if (next.width() > 10) {

                    List<Room> more = next.vSplit();
                    rooms.addAll(more);
                }
            }
        }
    }

    private static Room next(List<Room> all) {

        for (Room next : all) {

            if (next.width() > 10 || next.height() > 10) {

                return next;
            }
        }

        return null;
    }

    private static void div_part_2(List<Room> rooms) {
        
        List<Room> copy = new ArrayList<>(rooms);
        
        for(Room a : rooms) {
            for(Room b : copy) {
                
                if(!a.equals(b)) {
                    
                    if(a.isAdjacentEast(b) && a.height() <= b.height()) a.setWest(true);
                    
                    if(a.isAdjacentWest(b) && a.height() <= b.height()) a.setEast(true);
                    
                    if(a.isAdjacentNorth(b) && a.width() <= b.width()) a.setSouth(true);
                    
                    if(a.isAdjacentSouth(b) && a.width() <= b.width()) a.setNorth(true);
                }
            }
        }
    }

    private static void div_part_3(List<Room> rooms, int[][] map) {
        
        rooms.stream().forEach((r) -> {
            r.walls(map);
        });
        
        rooms.stream().forEach((r) -> {
            r.doors(map);
        });
    }
}
