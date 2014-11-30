package games.seekvodka;

import java.awt.*;

/**
* Created by SER on 27.11.2014.
*/
public class Room {
    public String name;
    public Color color;
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
        this.color = new Color(color);
    }

    public boolean isInRoom(int px, int py) {
        return px >= x && px <= (x + (width - 1)) && py >= y && py <= (y + (height - 1));
    }
}
