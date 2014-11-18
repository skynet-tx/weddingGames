package com.arhiser.wedding.widgets.stuff;

import java.awt.*;

/**
 * Created by SER on 06.11.2014.
 */
public abstract class Paintable {
    protected Rectangle rect;

    public abstract void onPaint(Graphics g);

    public void setBounds(Rectangle bounds){
        rect = bounds;
    }

    public Rectangle getBounds() {
        return rect;
    }
}
