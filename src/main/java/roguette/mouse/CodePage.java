package roguette.mouse;

import java.awt.Color;
import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import static java.awt.Color.decode;
import java.awt.Image;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.io.IOException;
import static java.util.Objects.requireNonNull;
import static javax.imageio.ImageIO.read;

public class CodePage {

    public final BufferedImage image;
    public final int offsetX;
    public final int offsetY;
    public final int vGap;
    public final int hGap;
    public final int columns;
    public final int rows;
    public final Color foreground;
    public final Color background;
    public final int columnWidth;
    public final int rowHeight;
    private final int[] xs;
    private final int[] ys;
    private final int[] cs;
    private final int[] rs;

    public CodePage(String path, int columns, int rows) throws IOException {

        this(path, columns, rows, WHITE, BLACK);
    }

    public CodePage(String path, int columns, int rows, Color fgColor, Color bgColor) throws IOException {

        this(read(CodePage.class.getResource(path)), columns, rows, fgColor, bgColor);
    }

    public CodePage(String path, int columns, int rows, String fgColor, String bgColor) throws IOException {

        this(read(CodePage.class.getResource(path)), columns, rows, decode(fgColor), decode(bgColor));
    }

    public CodePage(String path, int columns, int rows, String fgColor, String bgColor, int offX, int offY, int vgap, int hgap) throws IOException {

        this(read(CodePage.class.getResource(path)), columns, rows, decode(fgColor), decode(bgColor), offX, offY, vgap, hgap);
    }

    public CodePage(BufferedImage image, int columns, int rows, Color fgColor, Color bgColor) {

        this(image, columns, rows, fgColor, bgColor, 0, 0, 0, 0);
    }

    public CodePage(BufferedImage image, int columns, int rows, Color fgColor, Color bgColor, int offX, int offY, int vgap, int hgap) {

        // create a new image with TYPE_INT_ARGB and copy the image passed in
        // this is necessary to ensure that the image is NOT a grayscale image
        this.image = new BufferedImage(image.getWidth(), image.getHeight(), TYPE_INT_RGB);
        this.image.getGraphics().drawImage(image, 0, 0, null);

        this.columns = columns;
        this.rows = rows;
        this.offsetX = offX;
        this.offsetY = offY;
        this.vGap = vgap;
        this.hGap = hgap;
        this.foreground = requireNonNull(fgColor);
        this.background = requireNonNull(bgColor);
        this.columnWidth = (image.getWidth() - (2 * offsetX) - (columns * hgap)) / columns;
        this.rowHeight = (image.getHeight() - (2 * offsetY) - (rows * vgap)) / rows;

        int total = columns * rows;
        xs = new int[total];
        ys = new int[total];
        cs = new int[total];
        rs = new int[total];

        for (int i = 0; i < total; i++) {

            // columns
            cs[i] = i % columns;

            // rows
            rs[i] = i / columns;

            // x
            xs[i] = offsetX + (cs[i] * columnWidth) + (cs[i] * hGap);

            //y
            ys[i] = offsetY + (rs[i] * rowHeight) + (rs[i] * vGap);
        }
    }

    @Override
    public String toString() {

        return "CodePage{width=" + image.getWidth()
                + ", height=" + image.getHeight()
                + ", columns=" + columns
                + ", rows=" + rows
                + ", offsetX=" + offsetX
                + ", offsetY=" + offsetY
                + ", hGap=" + hGap
                + ", vGap=" + vGap
                + ", columnWidth=" + columnWidth
                + ", rowHeight=" + rowHeight
                + "}";
    }

    public Source getSource(char c) {

        return new Source((int) c);
    }

    public Source getSource(int i) {

        return new Source(i);
    }

    public class Source {

        private final int index;

        public Source(int i) {

            this.index = i;
        }

        public Image getImage() {
            return image;
        }

        public int getColumn() {
            return cs[index];
        }

        public int getRow() {
            return rs[index];
        }

        public int getX() {
            return xs[index];
        }

        public int getY() {
            return ys[index];
        }

        public int getWidth() {
            return columnWidth;
        }

        public int getHeight() {
            return rowHeight;
        }

        @Override
        public String toString() {

            return "Source{index=" + index
                    + ", column=" + getColumn()
                    + ", row=" + getRow()
                    + ", x=" + getX()
                    + ", y=" + getY()
                    + ", width=" + getWidth()
                    + ", height=" + getHeight()
                    + "}";
        }
    }
}
