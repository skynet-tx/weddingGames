package com.arhiser.wedding.widgets.roomgrid;

import com.arhiser.wedding.widgets.gridview.GridView;
import com.arhiser.wedding.widgets.stuff.ColorPaintable;
import games.seekvodka.Room;
import games.seekvodka.RoomsAdapter;
import games.seekvodka.RoomsModel;

import java.awt.*;

/**
 * Created by SER on 27.11.2014.
 */
public class RoomGridView extends GridView<RoomsAdapter> {

    public RoomGridView() {
        super();
        backgroundColor = new ColorPaintable(new Color(0x0), null);
    }

    @Override
    public void notifyChanged() {
        if (adapter != null) {
            int xCount = adapter.getHorizontalCellCount();
            int yCount = adapter.getVerticalCellCount();
            cellWidthFull = (float)(getWidth() - boundWidth) / xCount;
            cellHeightFull = (float)(getHeight() - boundWidth) / yCount;
            cellWidthFull = Math.min(cellWidthFull, cellHeightFull);
            cellHeightFull = Math.min(cellWidthFull, cellHeightFull);

            cellWidth = cellWidthFull - boundWidth;
            cellHeight = cellHeightFull - boundWidth;
            backgroundColor.setBounds(new Rectangle(0, 0, getWidth(), getHeight()));
        }
        invalidate();
    }

    public float getCellWidth() {
        return cellWidthFull;
    }

    public float getCellHeight() {
        return cellHeightFull;
    }

    @Override
    public void paint(Graphics g) {
        /*
        adapter.recalculateRoomScreenBounds();
        Room[] rooms = adapter.getRooms();
        for(int i = 0; i < rooms.length; i++) {
            rooms[i].colorPaintable.onPaint(g);
        }
        super.paint(g);
        */
    }
}
