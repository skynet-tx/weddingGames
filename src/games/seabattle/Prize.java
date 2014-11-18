package games.seabattle;

import com.arhiser.wedding.managers.ImageManager;

import javax.swing.*;

/**
 * Created by SER on 16.11.2014.
 */
public class Prize {
    String name;
    Icon picture;

    public Prize() {
        picture = ImageManager.getImageByResourceName("default_image.png");
        name = "Приз";
    }

    public Icon getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }
}
