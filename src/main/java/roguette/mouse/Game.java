package roguette.mouse;

import java.awt.event.KeyEvent;

public class Game {
    
    public static final int PLAY = 0;
    public static final int WON = 1;
    public static final int LOST = 2;
    
    private int state;
    private long keyTime;
    
    Game() {
        
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
                        break;
                        
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        movePlayer(grid, 0, 1);
                        break;
                        
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        movePlayer(grid, -1, 0);
                        break;
                        
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        movePlayer(grid, 1, 0);
                        break;
                }
            } else {
                
                switch(key) {
                    
                    case KeyEvent.VK_ENTER:
                        this.reset(main);
                        break;
                }
            }
        }
    }
    
    private void movePlayer(Grid grid, int dx, int dy) {
        
    }
    
    public void timerInput(int tick, Grid grid) {
        
    }
}
