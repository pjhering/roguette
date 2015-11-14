package roguette;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.ShortLookupTable;
import static java.util.Arrays.fill;
import static java.util.Objects.requireNonNull;
import javax.swing.JComponent;
import roguette.CodePage.Source;

public class CodePageConsole extends JComponent implements Console {

    private final CodePage page;
    private final Dimension size;
    private final Dimension pixels;
    private final Image front;
    private final Image back;
    private final BufferedImage cell;
    private BufferedImage tile;

    public CodePageConsole(CodePage page, Dimension size) {

        this.page = requireNonNull(page);
        this.size = size;
        pixels = new Dimension(size.width * page.columnWidth, size.height * page.rowHeight);
        this.setPreferredSize(pixels);
        this.setMinimumSize(pixels);
        this.setMaximumSize(pixels);
        this.setSize(pixels);

        // all images have the same type as the code page (TYPE_INT_RGB)
        front = new BufferedImage(pixels.width, pixels.height, page.image.getType());
        back = new BufferedImage(pixels.width, pixels.height, page.image.getType());
        cell = new BufferedImage(page.columnWidth, page.rowHeight, page.image.getType());
        tile = new BufferedImage(page.columnWidth, page.rowHeight, page.image.getType());
    }

    @Override
    public int getColumns() {
        return size.width;
    }

    @Override
    public int getRows() {
        return size.height;
    }

    @Override
    public void update() {

        front.getGraphics().drawImage(back, 0, 0, this);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(front, 0, 0, this);
    }

    @Override
    public void clear() {

        this.clear(this.getBackground());
    }

    @Override
    public void clear(Color bgColor) {

        this.clear(0, 0, size.height, size.width, bgColor);
    }

    @Override
    public void clear(int top, int left, int rows, int columns) {

        this.clear(top, left, rows, columns, this.getBackground());
    }

    /**
     * clears a section of the console using the given background color. This is
     * the only clear methods that actually draws to the screen. All other clear
     * methods ultimately call this method.
     *
     * @param top
     * @param left
     * @param rows
     * @param columns
     * @param bgColor
     */
    @Override
    public void clear(int top, int left, int rows, int columns, Color bgColor) {

        int x = left * page.columnWidth;
        int y = top * page.rowHeight;
        int w = columns * page.columnWidth;
        int h = rows * page.rowHeight;

        Graphics g = back.getGraphics();
        g.setColor(bgColor);
        g.fillRect(x, y, w, h);
    }

    @Override
    public void write(char c, int row, int column) {

        this.write(c, row, column, this.getForeground(), this.getBackground());
    }

    @Override
    public void write(char c, int row, int column, Color fgColor) {

        this.write(c, row, column, fgColor, this.getBackground());
    }

    /**
     * writes a character to the given location using the given colors. This is
     * the only write method that actually does any writing. All other write and
     * center methods ultimately call this one.
     */
    @Override
    public void write(char c, int row, int column, Color fgColor, Color bgColor) {

        // copy section from code page image to cell
        Graphics g1 = cell.getGraphics();
        Source src = page.getSource(c);
        g1.drawImage(page.image,
                0, // dx1
                0, // dy1
                page.columnWidth, // dx2
                page.rowHeight, // dy2
                src.getX(), // sx1
                src.getY(), // sy1
                src.getX() + page.columnWidth, // sx2
                src.getY() + page.rowHeight, // sy2
                this);
        g1.dispose();

        // filter from cell to tile
        LookupOp op = getLookupOp(fgColor, bgColor);
        tile = op.filter(cell, null);

        // draw tile to back buffer
        Graphics g2 = back.getGraphics();
        g2.drawImage(tile,
                column * page.columnWidth,
                row * page.rowHeight,
                this);
        g2.dispose();
    }

    @Override
    public void write(String s, int row, int column) {

        this.write(s, row, column, getForeground(), getBackground());
    }

    @Override
    public void write(String s, int row, int column, Color fgColor) {

        this.write(s, row, column, fgColor, getBackground());
    }

    @Override
    public void write(String s, int row, int column, Color fgColor, Color bgColor) {

        for (int i = 0; i < s.length(); i++) {

            this.write(s.charAt(i), row, column + i, fgColor, bgColor);
        }
    }

    @Override
    public void center(String s, int row) {

        this.center(s, row, getForeground(), getBackground());
    }

    @Override
    public void center(String s, int row, Color fgColor) {

        this.center(s, row, fgColor, getBackground());
    }

    @Override
    public void center(String s, int row, Color fgColor, Color bgColor) {

        int column = (size.width - s.length()) / 2;
        this.write(s, row, column, fgColor, bgColor);
    }

    private LookupOp getLookupOp(Color fgColor, Color bgColor) {

        short[] r = new short[256];
        short[] g = new short[256];
        short[] b = new short[256];

        short sr = (short) fgColor.getRed();
        short sg = (short) fgColor.getGreen();
        short sb = (short) fgColor.getBlue();

        fill(r, 0, 256, sr);
        fill(g, 0, 256, sg);
        fill(b, 0, 256, sb);

        r[0] = (short) bgColor.getRed();
        g[0] = (short) bgColor.getGreen();
        b[0] = (short) bgColor.getBlue();

        LookupTable table = new ShortLookupTable(0, new short[][]{r, g, b});

        return new LookupOp(table, null);
    }
}
