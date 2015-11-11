package roguette.mouse;

import static java.util.Objects.requireNonNull;

public class Timer {
    
    private final long initialDelay;
    private final long interval;
    private final Observer observer;

    public Timer(long init, long inter, Observer obs) {
        
        this.initialDelay = init;
        this.interval = inter;
        this.observer = requireNonNull(obs);
    }

    void restart() {
    }

    void start() {
    }
    
    public static interface Observer {
        
        void timerInput(int tick);
    }
}
