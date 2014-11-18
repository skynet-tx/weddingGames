package com.arhiser.wedding.managers;

import sun.awt.image.BufferedImageDevice;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SER on 16.11.2014.
 */
public class ImageManager {

    static Map<String, Icon> cache = new HashMap<String, Icon>();

    public static Icon getImageByResourceName(String fileName) {
        if (!cache.containsKey(fileName)) {
            ClassLoader cl= ImageManager.class.getClassLoader();
            URL imageURL   = cl.getResource("resources/" + fileName);
            if (imageURL == null) {
                return null;
            }
            cache.put(fileName, new ImageIcon(imageURL));
        }
        return cache.get(fileName);
    }

    public static BufferedImage iconToImage(Icon icon) {
        /*
        if (icon instanceof ImageIcon) {
            return (BufferedImage)((ImageIcon)icon).getImage();
        }
        else {
        */
            int w = icon.getIconWidth();
            int h = icon.getIconHeight();
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gd.getDefaultConfiguration();
            BufferedImage image = gc.createCompatibleImage(w, h);
            Graphics2D g = image.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            return image;
        //}
    }

    public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
        BufferedImage dbi = null;
        if(sbi != null) {
            dbi = new BufferedImage(dWidth, dHeight, imageType);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
            g.drawRenderedImage(sbi, at);
        }
        return dbi;
    }
}
