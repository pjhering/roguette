package roguette;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.Timer;
import roguette.ui.CodePage;
import static roguette.ui.CodePage.getDefault;
import roguette.ui.CodePageConsole;
import roguette.ui.DefaultRenderer;
import roguette.ui.Tile;

public abstract class BasicGame implements KeyListener, ActionListener {

    private final DefaultRenderer renderer;
    private final CodePageConsole console;
    private final JFrame frame;
    private final Timer timer;
    private Timer paintTimer;

    public BasicGame(String title, int viewWidth, int viewHeight, int timerInterval) throws IOException {

        this.renderer = createDefaultRenderer();
        this.console = createCodePageConsole(new Dimension(viewWidth, viewHeight));
        this.frame = createJFrame(title);
        this.timer = createTimer(timerInterval);
    }

    public abstract int getPlayerID();

    public abstract BasicGrid createBasicGrid();

    public abstract Tile[] createTiles();

    public void show() {

        if (frame.isVisible()) {

            return;
        }

        if (paintTimer == null) {
            paintTimer = new Timer(97, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    renderer.render();
                }
            });
        }
        
        frame.setVisible(true);
        timer.start();
        paintTimer.start();
    }

    private DefaultRenderer createDefaultRenderer() {

        BasicGrid bg = createBasicGrid();
        int pid = getPlayerID();
        Tile[] t = createTiles();
        DefaultRenderer dr = new DefaultRenderer(console, bg, pid, t);

        return dr;
    }

    private CodePageConsole createCodePageConsole(Dimension size) throws IOException {

        CodePage cp = getDefault();
        CodePageConsole cpc = new CodePageConsole(cp, size);

        return cpc;
    }

    private JFrame createJFrame(String title) {

        JFrame jf = new JFrame(title);
        jf.setContentPane(console);
        jf.pack();
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.addKeyListener(this);

        return jf;
    }

    private Timer createTimer(int interval) {

        Timer t = new Timer(interval, this);

        return t;
    }
}
