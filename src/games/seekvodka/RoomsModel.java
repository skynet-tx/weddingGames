package games.seekvodka;

import com.arhiser.wedding.managers.ImageManager;

import javax.swing.*;

/**
 * Created by SER on 27.11.2014.
 */
public class RoomsModel {

    public static final int TYPE_SMALL = 1;
    public static final int TYPE_MEDIUM = 2;
    public static final int TYPE_LARGE = 3;
    /*
    private static final int COLOR_BALCONY = 0xffffc0e4;
    private static final int COLOR_WC = 0xffdec0ff;
    private static final int COLOR_KITCHEN = 0xffc0d5ff;
    private static final int COLOR_HALL = 0xffc0feff;
    private static final int COLOR_ROOM1 = 0xffc0ffd3;
    private static final int COLOR_ROOM2 = 0xfff2ffc0;
    private static final int COLOR_ROOM3 = 0xffffdfc0;
    */
    private static final int COLOR_BALCONY = 0xfffc84c9;
    private static final int COLOR_WC = 0xfffc848d;
    private static final int COLOR_KITCHEN = 0xffac84fc;
    private static final int COLOR_HALL = 0xff84d0fc;
    private static final int COLOR_ROOM1 = 0xff84fc90;
    private static final int COLOR_ROOM2 = 0xfffbfc84;
    private static final int COLOR_ROOM3 = 0xfffcac84;

    private int width;
    private int height;

    Room [] rooms;

    Icon defaultIcon = ImageManager.getImageByResourceName("default_image.png");

    protected void initModel(int type) {
        switch (type) {
            case TYPE_SMALL:
                width = 4;
                height = 4;

                rooms = new Room[5];
                rooms[0] = new Room(Room.ROOM_BALCONY, "Лоджия", 0, 0, 1, 2, COLOR_BALCONY);
                rooms[1] = new Room(Room.ROOM_WC, "Сан. узел", 0, 2, 1, 2, COLOR_WC);
                rooms[2] = new Room(Room.ROOM_KITCHEN, "Кухня", 1, 0, 1, 2, COLOR_KITCHEN);
                rooms[3] = new Room(Room.ROOM_HALL, "Коридор", 1, 2, 1, 2, COLOR_HALL);
                rooms[4] = new Room(Room.ROOM_ROOM1, "Комната", 2, 0, 2, 4, COLOR_ROOM1);

                break;
            case TYPE_MEDIUM:
                width = 7;
                height = 5;

                rooms = new Room[6];
                rooms[0] = new Room(Room.ROOM_BALCONY, "Лоджия", 0, 0, 1, 3, COLOR_BALCONY);
                rooms[1] = new Room(Room.ROOM_WC, "Сан. узел", 0, 3, 2, 2, COLOR_WC);
                rooms[2] = new Room(Room.ROOM_ROOM1,"Зал", 1, 0, 3, 3, COLOR_ROOM1);
                rooms[3] = new Room(Room.ROOM_HALL,"Коридор", 2, 3, 2, 2, COLOR_HALL);
                rooms[4] = new Room(Room.ROOM_ROOM2, "Спальня", 4, 0, 3, 3, COLOR_ROOM2);
                rooms[5] = new Room(Room.ROOM_KITCHEN, "Кухня", 4, 3, 3, 2, COLOR_KITCHEN);

                break;
            case TYPE_LARGE:
                width = 10;
                height = 5;

                rooms = new Room[7];
                rooms[0] = new Room(Room.ROOM_BALCONY, "Лоджия", 0, 0, 1, 3, COLOR_BALCONY);
                rooms[1] = new Room(Room.ROOM_WC, "Сан. узел", 0, 3, 2, 2, COLOR_WC);
                rooms[2] = new Room(Room.ROOM_ROOM1, "Зал", 1, 0, 3, 3, COLOR_ROOM1);
                rooms[3] = new Room(Room.ROOM_HALL,"Коридор", 2, 3, 2, 2, COLOR_HALL);
                rooms[4] = new Room(Room.ROOM_ROOM2, "Спальня", 4, 0, 3, 3, COLOR_ROOM2);
                rooms[5] = new Room(Room.ROOM_KITCHEN, "Кухня", 4, 3, 3, 2, COLOR_KITCHEN);
                rooms[6] = new Room(Room.ROOM_ROOM3, "Гостевая", 7, 0, 3, 5, COLOR_ROOM3);
                break;
        }

    }

    public RoomsModel(int type) {
        initModel(type);
    }

    public Room[] getRooms() {
        return rooms;
    }

    public Icon getIconForCell(int x, int y) {
        return defaultIcon;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Room getRoomAt(int x, int y) {
        for(Room room: rooms) {
            if (room.isInRoom(x, y)) {
                return room;
            }
        }
        return null;
    }

    public void setType(int type) {
        initModel(type);
    }
}
