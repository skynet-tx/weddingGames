package com.arhiser.wedding.widgets.stuff;

import com.arhiser.wedding.managers.ImageManager;
import com.sun.javafx.iio.ImageStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by SER on 18.11.2014.
 */
public class ImagePaintable extends Paintable implements Icon {

    private static final int padding = 10;

    private BufferedImage image;
    private BufferedImage imageScaled;


    public ImagePaintable(Icon icon) {
        image = ImageManager.iconToImage(icon);
    }

    @Override
    public void onPaint(Graphics g) {
        Rectangle bounds = getBounds();
        g.drawImage(imageScaled, 0, 0, imageScaled.getWidth(null), imageScaled.getHeight(null), bounds.x, bounds.y, bounds.x + bounds.width, bounds.y + bounds.height, null);
    }

    @Override
    public void setBounds(Rectangle bounds) {
        super.setBounds(bounds);
        imageScaled = ImageManager.scale(image, BufferedImage.TYPE_INT_ARGB, bounds.width, bounds.height, (float)bounds.width/image.getWidth(), (float)bounds.height/image.getHeight());
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        setBounds(new Rectangle(0, 0, c.getWidth(), c.getHeight()));
        onPaint(g);
    }

    @Override
    public int getIconWidth() {
        return image.getWidth(null);
    }

    @Override
    public int getIconHeight() {
        return image.getHeight(null);
    }
}
