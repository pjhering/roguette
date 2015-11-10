package roguette.mouse;

public class Main {
    
    public static final int COLUMNS = 80;
    public static final int ROWS = 50;
    
    private Grid grid;
    private final Game game;
    private final Console console;
    private final Renderer renderer;
    private final Timer timer;
    
    Main() {
        
        grid = createGrid();
        game = createGame();
        console = createConsole();
        renderer = createRenderer(console);
        timer = createTimer();
    }

    public static void main(String[] args) {
        
        Main main = new Main();
        main.begin();
    }
    
    void restart() {
        
        grid = createGrid();
        timer.restart();
    }
    
    private void begin() {
        
        console.setVisible(true);
        timer.start();
    }
    
    private Grid createGrid(){
        
        return new GridBuilder(COLUMNS, ROWS)
                .createRooms()
                .createHome()
                .createMouse()
                .createCats(10)
                .createCheese(20)
                .createFluff(9)
                .build();
        
    }

    private Game createGame() {
        
        return new Game();
    }
    
    private Console createConsole() {
        
        final Main main = this;
        Console c = new Console(COLUMNS, ROWS, (k) -> {
            game.keyInput(k, grid, main);
            renderer.render(grid);
            console.update();
        });
        
        return c;
    }
    
    private Renderer createRenderer(Console c) {
        
        return new Renderer(c);
    }
    
    private Timer createTimer() {
        
        Timer t = new Timer(5000, 1000, (tick) -> {
            game.timerInput(tick, grid);
            renderer.render(grid);
            console.update();
        });
        
        return t;
    }
}
