package widgets.stuff;

import java.awt.*;
import java.awt.font.TextMeasurer;

/**
 * Created by SER on 13.11.2014.
 */
public class StringPaintable extends ColorPaintable {
    private String text;

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
        g.setColor(new Color(0xFF7070a0));
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        FontMetrics metrics = g.getFontMetrics();
        int height = metrics.getHeight();
        int width = (int)metrics.getStringBounds(text ,g).getWidth();
        Rectangle bounds = getBounds();
        int x = (bounds.width - width) / 2;
        int y = (bounds.height + height/2) / 2;
        g.drawString(text, bounds.x + x, bounds.y + y);
    }
}
