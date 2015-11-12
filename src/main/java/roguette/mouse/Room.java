package roguette.mouse;

import static java.lang.Math.*;
import java.util.ArrayList;
import java.util.List;

public class Room {

    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;
    private final List<int[]> doors;

    public Room(int x1, int y1, int x2, int y2) {

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.doors = new ArrayList<>();
    }

    public int width() {

        return x2 - x1;
    }

    public int height() {

        return y2 - y1;
    }

    public List<int[]> doors() {

        return doors;
    }

    public static List<Room> getTopBottomSplit(Room room) {

        List<Room> list = new ArrayList<>();
        int length = room.y2 - room.y1;

        if (length > 8) {

            int newY = room.y1 + (length / 2);

            Room a = new Room(room.x1, newY, room.x2, room.y2);
            Room b = new Room(room.x1, room.y1, room.x2, newY);

            list.add(a);
            list.add(b);
        }

        return list;
    }

    public static List<Room> getLeftRightSplit(Room room) {

        List<Room> list = new ArrayList<>();
        int length = room.x2 - room.x1;

        if (length > 8) {

            int newX = room.x1 + (length / 2);

            Room a = new Room(room.x1, room.y1, newX, room.y2);
            Room b = new Room(newX, room.y1, room.x2, room.y2);

            list.add(a);
            list.add(b);
        }

        return list;
    }

    public boolean isNeighbor(Room that) {

        return this.x1 == that.x2 || // this is right of that
                this.x2 == that.x1 || // this is left of that
                this.y1 == that.y2 || // this is below that
                this.y2 == that.y1;   // this is above that
    }

    public void draw(int[][] map) {

        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {

                map[x][y] = (x == x1 || x == x2 || y == y1 || y == y2) ? 1 : 0;
            }
        }
    }

    public void connect(Room that) {

        if (this.isNeighbor(that)) {

            int[] door = new int[2];

            if (this.x1 == that.x2) { // this room is right of that room

                door[0] = this.x1;
                int minY = max(this.y1, that.y1);
                int maxY = min(this.y2, that.y2);
                door[1] = minY + ((maxY - minY) / 2);

            } else if (this.x2 == that.x1) { // this room is left of that room

                door[0] = this.x2;
                int minY = max(this.y1, that.y1);
                int maxY = min(this.y2, that.y2);
                door[1] = minY + ((maxY - minY) / 2);

            } else if (this.y1 == that.y2) { // this room is below that room

                int minX = max(this.x1, that.x1);
                int maxX = min(this.x2, that.x2);
                door[0] = minX + ((maxX - minX) / 2);
                door[1] = this.y1;

            } else if (this.y2 == that.y1) { // this room is above that room

                int minX = max(this.x1, that.x1);
                int maxX = min(this.x2, that.x2);
                door[0] = minX + ((maxX - minX) / 2);
                door[1] = this.y2;

            } else {

                throw new RuntimeException("WTF!");
            }

            this.doors.add(door);
        }
    }
}
