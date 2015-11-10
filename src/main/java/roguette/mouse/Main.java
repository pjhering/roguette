package roguette.mouse;

public class Main {
    
    public static final int COLUMNS = 80;
    public static final int ROWS = 50;
    
    private Grid grid;
    private Game game;
    private Console console;
    private Renderer renderer;
    private Timer timer;
    
    Main() {
        
        grid = createGrid();
        game = createGame();
        console = createConsole();
        renderer = createRenderer();
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
        return null;//TODO
    }
    
    private Console createConsole() {
        return null;//TODO
    }
    
    private Renderer createRenderer() {
        return null;//TODO
    }
    
    private Timer createTimer() {
        return null;//TODO
    }
}
