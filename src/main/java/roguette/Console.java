package roguette;

import java.awt.Color;

/**
 * defines all functions available in any Console object
 */
public interface Console {

    /**
     * returns number of columns visible in console
     *
     * @return
     */
    public int getColumns();

    /**
     * returns number of rows visible in console
     *
     * @return
     */
    public int getRows();

    /**
     * clear entire console using default background color
     */
    public void clear();

    /**
     * clear entire console using given background color
     *
     * @param bgColor
     */
    public void clear(Color bgColor);

    /**
     * clear section of console using default background color
     *
     * @param top
     * @param left
     * @param rows
     * @param columns
     */
    public void clear(int top, int left, int rows, int columns);

    /**
     * clear section of console using given background color
     *
     * @param top
     * @param left
     * @param rows
     * @param columns
     * @param bgColor
     */
    public void clear(int top, int left, int rows, int columns, Color bgColor);

    /**
     * write a character at the given location using default colors
     *
     * @param c
     * @param row
     * @param column
     */
    public void write(char c, int row, int column);

    /**
     * write a character at the given location using the given foreground color
     *
     * @param c
     * @param row
     * @param column
     * @param fgColor
     */
    public void write(char c, int row, int column, Color fgColor);

    /**
     * write a character at the given location using the given fore- and
     * background color
     *
     * @param c
     * @param row
     * @param column
     * @param fgColor
     * @param bgColor
     */
    public void write(char c, int row, int column, Color fgColor, Color bgColor);

    /**
     * write a string beginning at the given location using default colors
     *
     * @param s
     * @param row
     * @param column
     */
    public void write(String s, int row, int column);

    /**
     * write a string beginning at the given location using the given foreground
     * color
     *
     * @param s
     * @param row
     * @param column
     * @param fgColor
     */
    public void write(String s, int row, int column, Color fgColor);

    /**
     * write a string beginning at the given location using the given fore- and
     * background color
     *
     * @param s
     * @param row
     * @param column
     * @param fgColor
     * @param bgColor
     */
    public void write(String s, int row, int column, Color fgColor, Color bgColor);

    /**
     * write a string centered on the given row using default colors
     *
     * @param s
     * @param row
     */
    public void center(String s, int row);

    /**
     * write a string centered on the given row using the given foreground color
     *
     * @param s
     * @param row
     * @param fgColor
     */
    public void center(String s, int row, Color fgColor);

    /**
     * write a string centered on the given row using given colors
     *
     * @param s
     * @param row
     * @param fgColor
     * @param bgColor
     */
    public void center(String s, int row, Color fgColor, Color bgColor);

    /**
     * present current state of console
     */
    public void update();
}
