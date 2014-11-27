package com.arhiser.wedding.widgets.stuff;

import java.awt.*;

/**
 * Created by SER on 06.11.2014.
 */
public class ColorPaintable extends Paintable {

    Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ColorPaintable(Color color, Rectangle bounds) {
        this.color = color;
        this.rect = bounds;
    }

    @Override
    public void onPaint(Graphics g) {
        if ((color.getBlue() + color.getRed() + color.getGreen()) > 0) {
            g.setColor(color);
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
    }
}
