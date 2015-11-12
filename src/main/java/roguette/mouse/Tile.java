package roguette.mouse;

import java.awt.Color;

public class Tile {

    private char glyph;
    private Color foreground;
    private Color background;

    public Tile(char glyph, Color fgColor, Color bgColor) {

        this.glyph = glyph;
        this.foreground = fgColor;
        this.background = bgColor;
    }

    public char getGlyph() {
        return glyph;
    }

    public void setGlyph(char glyph) {
        this.glyph = glyph;
    }

    public Color getForeground() {
        return foreground;
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    @Override
    public String toString() {

        return "Tile{glyph=" + glyph + ", foreground=" + foreground + ", background=" + background + "}";
    }
}
