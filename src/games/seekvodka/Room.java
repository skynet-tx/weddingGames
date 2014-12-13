package games.seekvodka;

import java.awt.*;

/**
* Created by SER on 27.11.2014.
*/
public class Room {

    public static final String ROOM_BALCONY = "balcony";
    public static final String ROOM_WC = "wc";
    public static final String ROOM_KITCHEN = "kitchen";
    public static final String ROOM_HALL = "hall";
    public static final String ROOM_ROOM1 = "room1";
    public static final String ROOM_ROOM2 = "room2";
    public static final String ROOM_ROOM3 = "room3";

    public static final String[] ROOMS = new String[]{
        ROOM_BALCONY, ROOM_WC, ROOM_HALL, ROOM_KITCHEN, ROOM_ROOM1, ROOM_ROOM2, ROOM_ROOM3
    };

    public static int cellsByRoom(String room) {
        if (room.equals(ROOM_BALCONY)) return 3;
        if (room.equals(ROOM_WC)) return 4;
        if (room.equals(ROOM_KITCHEN)) return 6;
        if (room.equals(ROOM_HALL)) return 4;
        if (room.equals(ROOM_ROOM1)) return 9;
        if (room.equals(ROOM_ROOM2)) return 9;
        if (room.equals(ROOM_ROOM3)) return 15;
        return 0;
    }

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
