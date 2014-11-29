package games.seabattle.paintables;

import com.arhiser.wedding.widgets.stuff.ColorPaintable;

import java.awt.*;

/**
 * Created by arkhipov on 29.11.2014.
 */
public class OpenedPaintable extends ColorPaintable {
    private static final int radius = 5;

    private Color circleColor;

    public OpenedPaintable(Color circleColor, Rectangle bounds) {
        super(new Color(0xfff2f4f8), bounds);
        this.circleColor = circleColor;
    }

    @Override
    public void onPaint(Graphics g) {
        super.onPaint(g);

        Rectangle bounds = getBounds();
        int x = bounds.x + bounds.width/2 - radius;
        int y = bounds.y + bounds.height/2 - radius;
        g.setColor(circleColor);
        g.fillOval(x, y, 2 * radius, 2 * radius);
    }
}
