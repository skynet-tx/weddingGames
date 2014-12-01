package games.seekvodka.paitables;

import com.arhiser.wedding.widgets.stuff.BorderColorPaintable;

import java.awt.*;

/**
 * Created by arkhipov on 01.12.2014.
 */
public class OpenCellPaintable extends BorderColorPaintable{
    private static final int radius = 5;

    private Stroke stroke = new BasicStroke(3);

    public OpenCellPaintable() {
        super(new Color(0xffffff), null);
    }

    @Override
    public void onPaint(Graphics g) {
        super.onPaint(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(stroke);
        g2.setColor(new Color(0xffff0000));
        Rectangle bounds = getBounds();
        //g2.drawRect(bounds.x + 1, bounds.y + 1, bounds.width - 3, bounds.height - 3);
        g2.drawLine(bounds.x + 2, bounds.y + 2, bounds.x + bounds.width - 4, bounds.y + bounds.height - 4);
        g2.drawLine(bounds.x + bounds.width - 4, bounds.y + 2, bounds.x + 2, bounds.y + bounds.height - 4);
    }
}
