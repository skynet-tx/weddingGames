package managers;

import javax.swing.*;
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
}
