package roguette.mouse;

public class Timer {

    public Timer() {
    }

    void restart() {
    }
    
    public static interface Observer {
        
        void timerInput(int tick);
    }
}
