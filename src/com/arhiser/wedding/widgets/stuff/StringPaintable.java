package com.arhiser.wedding.widgets.stuff;

import java.awt.*;

/**
 * Created by SER on 13.11.2014.
 */
public class StringPaintable extends ColorPaintable {
    private String text;
    private Font font;

    public StringPaintable(String text, Color color, Rectangle bounds) {
        super(color, bounds);
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void onPaint(Graphics g) {
        super.onPaint(g);
        if (text.length() == 0) {
            return;
        }
        g.setColor(new Color(0xFF303030));
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics();
        int height = metrics.getHeight();
        int width = (int)metrics.getStringBounds(text ,g).getWidth();
        Rectangle bounds = getBounds();
        int x = (bounds.width - width) / 2;
        int y = (bounds.height + (int)(height/1.5)) / 2;
        g.drawString(text, bounds.x + x, bounds.y + y);
    }

    @Override
    public void setBounds(Rectangle bounds) {
        super.setBounds(bounds);
        font = new Font("Arial", Font.BOLD, (int)(rect.height*0.7));
    }
}
