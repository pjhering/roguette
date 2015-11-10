package roguette.mouse;

import java.util.Objects;

public class Timer {
    
    private final long initialDelay;
    private final long interval;
    private final Observer observer;

    public Timer(long init, long inter, Observer obs) {
        
        this.initialDelay = init;
        this.interval = inter;
        this.observer = Objects.requireNonNull(obs);
    }

    void restart() {
    }

    void start() {
    }
    
    public static interface Observer {
        
        void timerInput(int tick);
    }
}
