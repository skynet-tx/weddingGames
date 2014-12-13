package com.arhiser.wedding.managers;

import org.imgscalr.Scalr;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SER on 16.11.2014.
 */
public class ImageManager {

    public static final String IMAGES_DIR = "images/";

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

    public static Icon getImageByFileName(String fileName) {
        if (!cache.containsKey(fileName)) {
            File file = new File(IMAGES_DIR);
            if (!file.exists()) {
                throw new RuntimeException("images folder not found");
            }
            File imageFile = new File(file.getAbsolutePath() + "/" + fileName);
            if (!imageFile.exists()) {
                throw new RuntimeException("image not found: " + imageFile);
            }
            try {
                ImageIcon imageIcon = new ImageIcon(imageFile.toURI().toURL());
                if (imageIcon.getIconWidth() > 1024 || imageIcon.getIconHeight() > 768) {
                    imageIcon = new ImageIcon(Scalr.resize(iconToImage(imageIcon), Scalr.Method.QUALITY, Scalr.Mode.AUTOMATIC, 1024, 768, Scalr.OP_ANTIALIAS));
                }
                cache.put(fileName, imageIcon);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return cache.get(fileName);
    }

    public static Icon getImageByPathName(String path, String name) {
        String extension = null;
        int i = name.lastIndexOf('.');
        if (i > 0) {
            extension = name.substring(i+1);
        }
        if (extension == null) {
            if (new File(path + name + ".png").exists()) {
                name += ".png";
            } else {
                name += ".jpg";
            }
        }
        if (!cache.containsKey(name)) {
            try {
                File imageFile = new File(path + name);
                ImageIcon imageIcon = new ImageIcon(imageFile.toURI().toURL());
                if (imageIcon.getIconWidth() > 1024 || imageIcon.getIconHeight() > 768) {
                    imageIcon = new ImageIcon(Scalr.resize(iconToImage(imageIcon), Scalr.Method.QUALITY, Scalr.Mode.AUTOMATIC, 1024, 768, Scalr.OP_ANTIALIAS));
                }
                cache.put(name, imageIcon);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return cache.get(name);
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

    public static void storeImage(File image) {
        File dest = new File(IMAGES_DIR + image.getName());
        File dir = new File(IMAGES_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            Files.copy(image.toPath(), dest.toPath());

        } catch (FileAlreadyExistsException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
