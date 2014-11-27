package games.seekvodka;

import com.arhiser.wedding.widgets.stuff.ColorPaintable;

import java.awt.*;

/**
* Created by SER on 27.11.2014.
*/
public class Room {
    public String name;
    public ColorPaintable colorPaintable;
    public int x;
    public int y;
    public int width;
    public int height;

    Room(String name, int x, int y, int width, int height, int color) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        colorPaintable = new ColorPaintable(new Color(color), null);
    }
}
