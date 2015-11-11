package roguette.mouse;

import static java.util.Objects.requireNonNull;

public class Renderer {
    
    private final Console console;

    Renderer(Console c) {
        
        this.console = requireNonNull(c);
    }

    void render(Grid grid) {
    }
}
