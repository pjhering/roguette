package roguette.mouse;

import static java.util.Objects.requireNonNull;

public class Console {
    
    private final int columns;
    private final int rows;
    private final Observer observer;
    
    public Console(int columns, int rows, Observer observer) {
        
        this.columns = columns;
        this.rows = rows;
        this.observer = requireNonNull(observer);
    }

    void setVisible(boolean b) {
    }

    void update() {
    }

    public static interface Observer {
        
        public void keyInput(int key);
    }
}
