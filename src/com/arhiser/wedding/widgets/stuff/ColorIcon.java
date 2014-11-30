package com.arhiser.wedding.widgets.stuff;

import javax.swing.*;
import java.awt.*;

/**
 * Created by SER on 30.11.2014.
 */
public class ColorIcon implements Icon {
    private Color color;

    public ColorIcon(Color color) {
        this.color = color;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(color);
        g.fillRect(0, 0, 40, 40);
    }

    @Override
    public int getIconWidth() {
        return 40;
    }

    @Override
    public int getIconHeight() {
        return 40;
    }
}
