package com.arhiser.wedding.widgets.stuff;

import java.awt.*;

/**
 * Created by SER on 30.11.2014.
 */
public class BorderColorPaintable extends ImagePaintable {

    public static final int BORDER_UP = 1;
    public static final int BORDER_RIGHT = 2;
    public static final int BORDER_BOTTOM = 4;
    public static final int BORDER_LEFT = 8;

    private static final int PADDING = 1;

    private int borders = 15;
    Color borderColor;
    private Stroke stroke = new BasicStroke(3);

    public BorderColorPaintable(Color color, Rectangle bounds) {
        super(null);
        setColor(color);
    }

    public void setBorders(int borders) {
        this.borders = borders;
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        borderColor = new Color(
                (color.getRed() - 100) > 0 ? color.getRed() - 100 : 0,
                (color.getGreen() - 100) > 0 ? color.getGreen() - 100 : 0,
                (color.getBlue() - 100) > 0 ? color.getBlue() - 100 : 0);
    }

    @Override
    public void onPaint(Graphics g) {
        super.onPaint(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(borderColor);
        g2d.setStroke(stroke);
        Rectangle b = getBounds();
        if ((borders & BORDER_UP) > 0) {
            g2d.drawLine(b.x + PADDING, b.y + PADDING, b.x + b.width - PADDING, b.y + PADDING);
        }
        if ((borders & BORDER_RIGHT) > 0) {
            g2d.drawLine(b.x + b.width - PADDING, b.y + PADDING, b.x + b.width - PADDING, b.y + b.height - PADDING);
        }
        if ((borders & BORDER_BOTTOM) > 0) {
            g2d.drawLine(b.x + PADDING, b.y + b.height - PADDING, b.x + b.width - PADDING, b.y + b.height - PADDING);
        }
        if ((borders & BORDER_LEFT) > 0) {
            g2d.drawLine(b.x + PADDING, b.y + PADDING, b.x + PADDING, b.y + b.height - PADDING);
        }
    }
}
