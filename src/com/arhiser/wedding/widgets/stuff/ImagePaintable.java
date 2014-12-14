package com.arhiser.wedding.widgets.stuff;

import com.arhiser.wedding.managers.ImageManager;
import org.imgscalr.Scalr;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by SER on 18.11.2014.
 */
public class ImagePaintable extends ColorPaintable implements Icon {

    private static final int padding = 10;

    private BufferedImage image;
    private BufferedImage imageScaled;


    public ImagePaintable(Icon icon) {
        super(new Color(0xff000000), null);
        setIcon(icon);
    }

    public void setIcon(Icon icon) {
        if (icon != null) {
            image = ImageManager.iconToImage(icon);
            if (getBounds() != null) {
                setBounds(getBounds());
            }
        } else {
            image = null;
        }
    }

    @Override
    public void onPaint(Graphics g) {
        super.onPaint(g);
        if (image != null) {
            int x = (int) (getBounds().x + (getBounds().getWidth() - imageScaled.getWidth()) / 2);
            int y = (int) (getBounds().y + (getBounds().getHeight() - imageScaled.getHeight()) / 2);
            g.drawImage(imageScaled, x, y, null);
        }
    }

    @Override
    public void setBounds(Rectangle bounds) {
        super.setBounds(bounds);
        if(image == null) {
            return;
        }
        float boundAspect = (float)getBounds().width / getBounds().height;
        float imageAspect = (float)image.getWidth() / image.getHeight();
        imageScaled = image;
        if(boundAspect > imageAspect) {
            imageScaled = Scalr.resize(image, Scalr.Method.BALANCED, Scalr.Mode.FIT_TO_HEIGHT, getBounds().width, getBounds().height);
        } else {
            imageScaled = Scalr.resize(image, Scalr.Method.BALANCED, Scalr.Mode.FIT_TO_WIDTH, getBounds().width, getBounds().height);
        }
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
