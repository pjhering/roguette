package roguette.mouse;

import roguette.Tile;
import roguette.Creature;
import roguette.CodePage;
import roguette.Grid;
import roguette.CodePageConsole;
import roguette.Console;
import roguette.DefaultRenderer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Main implements Types {

    public static final int COLUMNS = 80;
    public static final int ROWS = 50;

    private Grid grid;
    private final Game game;
    private final JFrame frame;
    private final CodePageConsole console;
    private final DefaultRenderer renderer;
    private final Timer timer;
    private int tick;

    Main() throws IOException {

        grid = createGrid();
        game = createGame();
        console = createConsole();
        frame = createFrame();
        renderer = createRenderer(console, grid);
        timer = createTimer();
    }

    public static void main(String[] args) throws IOException {

        Main main = new Main();
        main.begin();
    }

    void restart() {

        grid = createGrid();
        tick = 0;
        timer.restart();
    }

    private void begin() {

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        renderer.render();
        timer.start();
    }

    private Grid createGrid() {

        return new GridBuilder(COLUMNS, ROWS)
                .createRooms()
                .createHome()
                .createMouse(2, 2)
                .createCats(10)
                .createCheese(20)
                .createFluff(9)
                .build();

    }

    private Game createGame() {

        int mouseID = findMouseID(grid);
        return new Game(mouseID);
    }

    private int findMouseID(Grid grid) {

        Creature c = grid.getCell(2, 2).getOccupant();
        return c.getId();
    }

    private CodePageConsole createConsole() throws IOException {

        CodePage p = new CodePage("/cp437_12x12.png", 16, 16);
        Dimension d = new Dimension(80, 50);
        CodePageConsole c = new CodePageConsole(p, d);

        return c;
    }

    private JFrame createFrame() {

        final Main main = this;
        JFrame f = new JFrame("Roguette Mouse");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(console);
        f.pack();
        f.setResizable(false);
        f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                game.keyInput(e.getKeyCode(), grid, main);
                renderer.render();
            }
        });

        return f;
    }

    private DefaultRenderer createRenderer(Console c, Grid g) {

        Tile[] tiles = createTiles();
        DefaultRenderer r = new DefaultRenderer(c, g, game.mouseID, tiles);
        return r;
    }

    private Tile[] createTiles() {

        Tile[] t = new Tile[7];
        t[WALL] = new Tile('#', new Color(0x606060), new Color(0x404040));
        t[FLOOR] = new Tile(' ', Color.BLACK, Color.BLACK);
        t[HOME] = new Tile('+', Color.GRAY, Color.BLACK);
        t[CHEESE] = new Tile('c', new Color(0xFFFF40), new Color(0x202000));
        t[FLUFF] = new Tile('f', new Color(0x8080FF), new Color(0x000020));
        t[CAT] = new Tile('&', new Color(0xFF8822), Color.BLACK);
        t[MOUSE] = new Tile('@', Color.RED, Color.BLACK);

        return t;
    }

    private Timer createTimer() {

        Timer t = new Timer(5000, (e) -> {
            game.timerInput(tick++, grid);
            renderer.render();
        });
        t.setDelay(750);
        t.setRepeats(true);

        return t;
    }
}
