package roguette.mouse;

import roguette.Item;
import roguette.Cell;
import roguette.Grid;
import java.awt.Point;
import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_UP;
import static java.awt.event.KeyEvent.VK_W;
import static java.lang.System.currentTimeMillis;
import java.util.List;
import static roguette.mouse.Const.*;

public class Game {

    public final int mouseID;
    private int state;
    private long keyTime;
    private int fluffCount;
    private final CatMovement mover;

    Game(int mouseID) {

        this.mouseID = mouseID;
        state = PLAY;
        keyTime = currentTimeMillis();
        mover = new CatMovement();
    }

    private void reset(Main main) {

        main.restart();
        this.state = PLAY;
        this.keyTime = currentTimeMillis();
        this.fluffCount = 0;
    }

    public void keyInput(int key, Grid grid, Main main) {
        
        long now = currentTimeMillis();

        if (now - keyTime > 250) {

            if (state == PLAY) {

                switch (key) {

                    case VK_UP:
                    case VK_W:
                        movePlayer(grid, 0, -1);
                        keyTime = now;
                        break;

                    case VK_DOWN:
                    case VK_S:
                        movePlayer(grid, 0, 1);
                        keyTime = now;
                        break;

                    case VK_LEFT:
                    case VK_A:
                        movePlayer(grid, -1, 0);
                        keyTime = now;
                        break;

                    case VK_RIGHT:
                    case VK_D:
                        movePlayer(grid, 1, 0);
                        keyTime = now;
                        break;
                }
            } else {

                switch (key) {

                    case VK_ENTER:
                        this.reset(main);
                        keyTime = now;
                        break;
                }
            }
        }
    }

    private void movePlayer(Grid grid, int dx, int dy) {

        Point p1 = grid.locateCreature(mouseID);
        Point p2 = new Point(p1.x + dx, p1.y + dy);
        Cell c1 = grid.getCell(p1.x, p1.y);
        Mouse mouse = (Mouse) c1.getOccupant();

        if (grid.isValidCell(p2)) {

            Cell c2 = grid.getCell(p2.x, p2.y);
            int cType = c2.getType();

            if (cType != WALL) {

                Cat cat = (Cat) c2.getOccupant();

                if (cat == null) {

                    grid.moveOccupant(p1, p2);
                    mouse.setHealth(mouse.getHealth() - 0.25);

                    if (mouse.getHealth() <= 0.0) {

                        this.state = LOST;
                        return;
                    }

                    if (cType == HOME) {

                        if (containsItem(mouse.getInventory(), FLUFF)) {

                            List<Item> items = c2.getItems();

                            if (!containsItem(items, FLUFF)) {

                                Item fluff = getItem(mouse.getInventory(), FLUFF);

                                if (fluff != null) {
                                    
                                    items.add(fluff);
                                    mouse.deposit();
                                    
                                    if(mouse.countDeposited() == 9) {
                                        
                                        this.state = WON;
                                    }
                                }
                            }
                        }
                    } else if (cType == FLOOR) {

                        List<Item> items = c2.getItems();

                        if (containsItem(items, FLUFF)) {

                            Item fluff = getItem(items, FLUFF);
                            mouse.getInventory().add(fluff);
                        }

                        if (containsItem(items, CHEESE)) {

                            Item cheese = getItem(items, CHEESE);
                            mouse.getInventory().add(cheese);
                            mouse.setHealth(mouse.getHealth() + 10);
                        }
                    }
                } else {

                    // mouse hits cat
                    mouse.setHealth(mouse.getHealth() - 5.0);

                    if (mouse.getHealth() <= 0.0) {

                        this.state = LOST;
                    }
                }
            } else {

                // mouse hits wall - do nothing
            }
        } else {

            // mouse hits boundary - do nothing
        }
    }

    private Item getItem(List<Item> list, int type) {

        Item item = null;

        for (Item i : list) {

            if (i.getType() == type) {

                item = i;
                break;
            }
        }

        if (item != null) {

            list.remove(item);
        }

        return item;
    }

    private boolean containsItem(List<Item> list, int type) {

        boolean flag = false;

        for (Item i : list) {

            if (i.getType() == type) {

                flag = true;
                break;
            }
        }

        return flag;
    }

    public void timerInput(int tick, Grid grid) {
        
        Point p1 = grid.locateCreature(mouseID);

        for (Point p2 : grid.getLocations(CAT)) {

            double distance = p1.distance(p2);

            if (distance > 10) {

                this.mover.patrolRooms(grid, p2);

            } else if (distance > 1) {

                this.mover.pursueMouse(grid, p2);

            } else {

                this.mover.attackMouse(grid, p2);
            }
        }
    }

    int getState() {
        
        return this.state;
    }
}
