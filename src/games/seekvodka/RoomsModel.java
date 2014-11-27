package games.seekvodka;

import com.arhiser.wedding.widgets.gridview.GridViewAdapter;
import com.arhiser.wedding.widgets.roomgrid.RoomGridView;
import com.arhiser.wedding.widgets.stuff.ColorPaintable;
import com.arhiser.wedding.widgets.stuff.Paintable;

import java.awt.*;

/**
 * Created by SER on 27.11.2014.
 */
public class RoomsModel extends GridViewAdapter<RoomGridView>{
    int width = 7;
    int height = 5;

    ColorPaintable colorPaintable = new ColorPaintable(new Color(0x0), null);

    Room [] rooms;

    @Override
    public int getHorizontalCellCount() {
        return width;
    }

    @Override
    public int getVerticalCellCount() {
        return height;
    }

    @Override
    public Paintable getPaintableForCell(int x, int y) {
        colorPaintable.setBounds(host.getCellRectangle(x, y));
        return colorPaintable;
    }

    public RoomsModel() {
        rooms = new Room[6];
        rooms[0] = new Room("балкон", 0, 0, 1, 5, 0xfff0fff0);
        rooms[1] = new Room("гостинная", 1, 0, 4, 3, 0xffa0ffa0);
        rooms[2] = new Room("спальня", 1, 3, 3, 2, 0xffffa0a0);
        rooms[3] = new Room("прихожая", 4, 3, 1, 2, 0xffffffa0);
        rooms[4] = new Room("кухня", 5, 0, 2, 3, 0xffffa0ff);
        rooms[5] = new Room("санузел", 5, 3, 2, 2, 0xffa0a0ff);
    }

    private Rectangle getRoomCoords(Room room) {
        Rectangle rectangle = new Rectangle();
        rectangle.x = (int)(room.x * host.getCellWidth());
        rectangle.y = (int)(room.y * host.getCellHeight());
        rectangle.width = (int)(room.width * host.getCellWidth());
        rectangle.height = (int)(room.height * host.getCellHeight());
        return rectangle;
    }

    public void recalculateRoomScreenBounds() {
        for(int i = 0; i < rooms.length; i++) {
            rooms[i].colorPaintable.setBounds(getRoomCoords(rooms[i]));
        }
    }

    public Room[] getRooms() {
        return rooms;
    }

}
