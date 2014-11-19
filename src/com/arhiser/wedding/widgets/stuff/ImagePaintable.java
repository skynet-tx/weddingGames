package com.arhiser.wedding.widgets.stuff;

import com.arhiser.wedding.managers.ImageManager;

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
        g.drawImage(imageScaled, 0, 0, new Color(0xff704040), null);
    }

    @Override
    public void setBounds(Rectangle bounds) {
        super.setBounds(bounds);
        float aspect = (float)image.getWidth()/image.getHeight();
        int width;
        int height;
        if (bounds.width >= bounds.height) {
            height = bounds.height;
            width = (int)(bounds.height * aspect);
        } else {
            width = bounds.width;
            height = (int)(bounds.width / aspect);
        }
        imageScaled = ImageManager.scale(image, BufferedImage.TYPE_INT_ARGB, width, height, (float)width/image.getWidth(), (float)height/image.getHeight());
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
