package roguette;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapGen {

    private MapGen() {/* NOT USED - STATIC METHODS ONLY */

    }

    public static int[][] randomRectangularRooms(int columns, int rows) {

        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(0, 0, columns - 1, rows - 1));
        int strikes = 0;
        int maxStrikes = (columns / 10) * (rows / 10);
        Random rand = new Random();

        while (strikes < maxStrikes) {

            Room a = rooms.get(rand.nextInt(rooms.size()));

            List<Room> split = rand.nextBoolean() ? Room.getLeftRightSplit(a) : Room.getTopBottomSplit(a);

            if (!split.isEmpty()) {

                rooms.remove(a);
                rooms.addAll(split);

            } else {

                strikes += 1;
            }
        }

        for (int i = 0; i < rooms.size(); i++) {

            Room a = rooms.get(i);

            for (int j = i + 1; j < rooms.size(); j++) {

                Room b = rooms.get(j);

                if (a != b) {
                    a.connect(b);
                }
            }
        }

        int[][] array = new int[columns][rows];

        rooms.stream().forEach((r) -> {

            r.draw(array);
        });

        rooms.stream().forEach((r) -> {

            r.doors().stream().forEach((d) -> {

                array[d[0]][d[1]] = 0;
            });
        });

        for (int x = 1; x < array.length - 1; x++) {
            for (int y = 1; y < array[x].length - 1; y++) {

                int walls = 0;
                if (array[x - 1][y] == 1) {
                    walls += 1;
                }
                if (array[x + 1][y] == 1) {
                    walls += 1;
                }
                if (array[x][y - 1] == 1) {
                    walls += 1;
                }
                if (array[x][y + 1] == 1) {
                    walls += 1;
                }

                if (walls > 2) {

                    array[x][y] = 1;
                }
            }
        }

        return array;
    }
}
