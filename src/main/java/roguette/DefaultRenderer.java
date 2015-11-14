package roguette;

import java.awt.Point;
import static java.lang.Math.*;
import java.util.List;
import roguette.mouse.Types;
import static java.util.Objects.requireNonNull;

/**
 * The Render uses the Console to draw the World. A player Creature is required
 * to determine where to start rendering.
 *
 * @author tinman
 */
public class DefaultRenderer implements Renderer, Types {

    private final Console console;
    private final Grid grid;
    private final int playerID;
    private final Tile[] tiles;

    public DefaultRenderer(Console console, Grid grid, int playerID, Tile[] tiles) {

        this.console = requireNonNull(console);
        this.grid = requireNonNull(grid);
        this.playerID = playerID;
        this.tiles = requireNonNull(tiles);
    }

    @Override
    public void render() {

        // find the minimum column in both the console and the world
        int consoleMinX = 0;
        int worldMinX = 0;
        // find the minimum row in both the console and the world
        int consoleMinY = 0;
        int worldMinY = 0;

        // find the player's location
        Point loc = grid.locateCreature(playerID);

        // find the width of the world in columns
        int worldWidth = grid.width;
        // find the width of the console in columns
        int consoleWidth = console.getColumns();

        // if world width is less than console width
        if (worldWidth < consoleWidth) {
            // then first console column is console width minus world width divided by two
            consoleMinX = (consoleWidth - worldWidth) / 2;
        } // else first console column is zero
        else {
            // the minimum world column can be no lower than zero and no higher than
            // the world width minus the console width.  Therefore the minimun world
            // column is the min-max of zero, the player column minus half the
            // console width, and the world width minus the console width.
            worldMinX = min_max(0, loc.x - (consoleWidth / 2), worldWidth - consoleWidth);
        }

        // find the height of the world in rows
        int worldHeight = grid.height;
        // find the height of the console in rows
        int consoleHeight = console.getRows();

        // if world height is less than console height
        if (worldHeight < consoleHeight) {
            // then first console row is console height minus world height divided by two
            consoleMinY = (consoleHeight - worldHeight) / 2;
        } // else first console row is zero
        else {
            // the minimum world row can be no lower than zero and no higher than
            // the world height minus the console height.  Therefore the minimun world
            // row is the min-max of zero, the player row minus half the
            // console height, and the world height minus the console height.
            worldMinY = min_max(0, loc.y - (consoleHeight / 2), worldHeight - consoleHeight);
        }
        //
        // find the max world column and row to render
        int consoleMaxX = consoleWidth;
        int worldMaxX = worldWidth;
        int consoleMaxY = consoleHeight;
        int worldMaxY = worldHeight;

        // if the world width is less than the console width
        if (worldWidth < consoleWidth) {
            // then the max world column is the world width
            // and the max console half of the console width plus the world width
            consoleMaxX = (consoleWidth + worldWidth) / 2;
        } // else the max console column is the console width
        else {
            // the maximum world column can be no more than the world width and no
            // less than the console width.  therefore the maximum world column is
            // the min-max of console width, the player column plus half the console
            // width, and the world width.
            worldMaxX = min_max(consoleWidth, loc.x + (consoleWidth / 2), worldWidth);
        }

        // if world height is less than the console height
        if (worldHeight < consoleHeight) {
            // then the max world row is the world height
            // and the max console row is half of the console height plus the world height
            consoleMaxY = (consoleHeight + worldHeight) / 2;
        } // else the max console row is the console height
        else {
            // the maximum world row can be no more than the world height and no
            // less than the console height.  therefore the maximum world row is
            // the min-max of console height, the player row plus half the console
            // height, and the world height.
            worldMaxY = min_max(consoleHeight, loc.y + (consoleHeight / 2), worldHeight);
        }

        // clear the console
        console.clear();

        // calculate column and row offsets
        int columnOffset = consoleMinX - worldMinX;
        int rowOffset = consoleMinY - worldMinY;

        // iterate all tiles in the given column and row ranges
        for (int col = worldMinX; col < worldMaxX; col++) {
            for (int row = worldMinY; row < worldMaxY; row++) {
                // draw creature if present
                Cell cell = grid.getCell(col, row);
                Creature c = cell.getOccupant();
                if (c != null) {
                    Tile t = tiles[c.getType()];
                    console.write(t.getGlyph(), row + rowOffset, col + columnOffset, t.getForeground(), t.getBackground());
                } else {
                    // else draw item if present
                    List<Item> items = cell.getItems();
                    Item i = items.isEmpty() ? null : items.get(0);
                    if (i != null) {
                        Tile t = tiles[i.getType()];
                        console.write(t.getGlyph(), row + rowOffset, col + columnOffset, t.getForeground(), t.getBackground());
                    } else {
                        // else draw tile
                        Tile t = tiles[cell.getType()];
                        console.write(t.getGlyph(), row + rowOffset, col + columnOffset, t.getForeground(), t.getBackground());
                    }
                }
            }
        }
        // update the console
        console.update();
    }

    public static int min_max(int low, int value, int high) {

        return max(low, min(value, high));
    }
}
