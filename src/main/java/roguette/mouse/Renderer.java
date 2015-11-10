package roguette.mouse;

import java.util.Objects;

public class Renderer {
    
    private final Console console;

    Renderer(Console c) {
        
        this.console = Objects.requireNonNull(c);
    }

    void render(Grid grid) {
    }
}
