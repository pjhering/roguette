package roguette.mouse;

public class Game {
    
    public static final int PLAY = 0;
    public static final int WON = 1;
    public static final int LOST = 2;
    
    private int state;
    
    Game() {
        
        state = PLAY;
    }
    
    private void reset(Main main) {
        
        main.restart();
        this.state = PLAY;
    }
    
    public void keyInput(int key, Grid grid, Main main) {
        
    }
    
    public void timerInput(int tick, Grid grid) {
        
    }
}
