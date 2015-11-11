package roguette.mouse;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.List;

public class Game {
    
    public static final int PLAY = 0;
    public static final int WON = 1;
    public static final int LOST = 2;
    
    private final int mouseID;
    private int state;
    private long keyTime;
    
    Game(int mouseID) {
        
        this.mouseID = mouseID;
        state = PLAY;
        keyTime = System.currentTimeMillis();
    }
    
    private void reset(Main main) {
        
        main.restart();
        this.state = PLAY;
    }
    
    public void keyInput(int key, Grid grid, Main main) {
        
        long now = System.currentTimeMillis();
        
        if(now - keyTime > 250) {
            
            if(state == PLAY) {
                
                switch(key) {
                    
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
                
                switch(key) {
                    
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
        
        if(grid.isValidCell(p2)) {
            
            Cell c2 = grid.getCell(p2.x, p2.y);
            int cType = c2.getType();
            
            if(cType == Cell.FLOOR || cType == Cell.HOME) {
                
                Creature cat = c2.getOccupant();
                
                if(cat == null) {
                    
                    grid.moveOccupant(p1, p2);
                    
                    if(cType == Cell.HOME) {
                        
                        // mouse enters HOME
                        
                        List<Item> items = grid.getItems(p2);
                        
                        if(items.isEmpty()) {
                            
                            List<Item> inv = mouse.getInventory();
                        }
                    }
                    
                } else {
                    
                    // mouse hits cat
                }
            } else {
                
                // mouse hits wall
            }
        } else {
            
            // mouse hits boundary
        }
    }
    
    public void timerInput(int tick, Grid grid) {
        
    }
}
