package roguette.mouse;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;

public class Game {

    public static final int PLAY = 0;
    public static final int WON = 1;
    public static final int LOST = 2;

    private final int mouseID;
    private int state;
    private long keyTime;
    private int fluffCount;
    private Movement mover;

    Game(int mouseID) {

        this.mouseID = mouseID;
        state = PLAY;
        keyTime = System.currentTimeMillis();
        mover = new Movement();
    }

    private void reset(Main main) {

        main.restart();
        this.state = PLAY;
        this.keyTime = System.currentTimeMillis();
        this.fluffCount = 0;
    }

    public void keyInput(int key, Grid grid, Main main) {

        long now = System.currentTimeMillis();

        if (now - keyTime > 250) {

            if (state == PLAY) {

                switch (key) {

                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        movePlayer(grid, 0, -1);
                        keyTime = now;
                        break;

                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        movePlayer(grid, 0, 1);
                        keyTime = now;
                        break;

                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        movePlayer(grid, -1, 0);
                        keyTime = now;
                        break;

                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        movePlayer(grid, 1, 0);
                        keyTime = now;
                        break;
                }
            } else {

                switch (key) {

                    case KeyEvent.VK_ENTER:
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
        Creature mouse = c1.getOccupant();

        if (grid.isValidCell(p2)) {

            Cell c2 = grid.getCell(p2.x, p2.y);
            int cType = c2.getType();

            if (cType == Cell.FLOOR || cType == Cell.HOME) {

                Creature cat = c2.getOccupant();

                if (cat == null) {

                    grid.moveOccupant(p1, p2);
                    mouse.setHealth(mouse.getHealth() - 0.25);

                    if (mouse.getHealth() <= 0.0) {

                        this.state = Game.LOST;
                        return;
                    }

                    if (cType == Cell.HOME) {

                        Item fluff = getFluff(mouse.getInventory());

                        if (fluff != null) {

                            List<Item> items = c2.getItems();

                            if (!containsFluff(items)) {

                                items.add(fluff);
                                this.fluffCount += 1;
                                
                                if(fluffCount == 9) {
                                    
                                    this.state = Game.WON;
                                }
                            }
                        }
                    }
                } else {

                    // mouse hits cat
                    mouse.setHealth(mouse.getHealth() - 5.0);
                    
                    if(mouse.getHealth() <= 0.0) {
                        
                        this.state = Game.LOST;
                    }
                }
            } else {

                // mouse hits wall - do nothing
            }
        } else {

            // mouse hits boundary - do nothing
        }
    }

    private Item getFluff(List<Item> list) {

        Item fluff = null;

        for (Item i : list) {

            if (i.getType() == Item.FLUFF) {

                fluff = i;
                break;
            }
        }

        if (fluff != null) {

            list.remove(fluff);
        }

        return fluff;
    }

    private boolean containsFluff(List<Item> list) {

        boolean flag = true;

        for (Item i : list) {

            if (i.getType() == Item.FLUFF) {

                flag = false;
                break;
            }
        }

        return flag;
    }

    public void timerInput(int tick, Grid grid) {
        
        Point p1 = grid.locateCreature(mouseID);
        
        for(Point p2 : grid.getLocations(Creature.CAT)) {
            
            double distance = p1.distance(p2);
            
            if(distance > 10) {
                
                patrol(grid, p2);
                
            } else if(distance > 1) {
                
                pursue(grid, p2, p1);
                
            } else {
                
                attack(grid, p1);
            }
        }
    }
    
    private void patrol(Grid grid, Point cat) {
        
        Point p2 = mover.nextPointFollowRightWall(grid, cat);
        grid.moveOccupant(cat, p2);
    }
    
    private void pursue(Grid grid, Point cat, Point mouse) {
        
        Point p2 = mover.nextPointAStar(grid, cat, mouse);
        grid.moveOccupant(cat, p2);
    }
    
    private void attack(Grid grid, Point mouse) {
        
        Cell cell = grid.getCell(mouse.x, mouse.y);
        Creature m = cell.getOccupant();
        m.setHealth(m.getHealth() - 5);
        
        if(m.getHealth() <= 0) {
            
            this.state = Game.LOST;
        }
    }
}
