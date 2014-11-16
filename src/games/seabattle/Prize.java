package games.seabattle;

import managers.ImageManager;

import javax.swing.*;

/**
 * Created by SER on 16.11.2014.
 */
public class Prize {
    String name;
    Icon picture;

    public Prize() {
        picture = ImageManager.getImageByResourceName("default_manager");
        name = "Приз";
    }
}
