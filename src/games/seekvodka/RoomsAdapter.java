package games.seekvodka;

import com.arhiser.wedding.AppModel;
import com.arhiser.wedding.widgets.gridview.GridView;
import com.arhiser.wedding.widgets.gridview.GridViewAdapter;
import com.arhiser.wedding.widgets.stuff.BorderColorPaintable;
import com.arhiser.wedding.widgets.stuff.ColorPaintable;
import com.arhiser.wedding.widgets.stuff.Paintable;
import com.arhiser.wedding.widgets.stuff.StringPaintable;

import java.awt.*;

/**
 * Created by SER on 30.11.2014.
 */
public class RoomsAdapter extends GridViewAdapter<GridView> {

    private String[] labels = new String[]{"а", "б", "в", "г", "д", "е", "ж", "з", "и", "к"};

    private GridView host;

    private RoomsModel roomsModel;

    private ColorPaintable colorPaintable = new ColorPaintable(new Color(0xfff0ffff), null);
    private StringPaintable stringPaintable = new StringPaintable("", new Color(0xffffffff), null);
    private BorderColorPaintable borderPaintable = new BorderColorPaintable(new Color(0x0000ff), null);

    public RoomsAdapter(GridView host, RoomsModel roomsModel) {
        this.host = host;
        this.roomsModel = roomsModel;
    }

    @Override
    public int getHorizontalCellCount() {
        return 1 + roomsModel.getWidth();
    }

    @Override
    public int getVerticalCellCount() {
        return 1 + roomsModel.getHeight();
    }

    @Override
    public Paintable getPaintableForCell(int x, int y) {
        Paintable paintable;
        if (x == 0 && y == 0) {
            paintable = stringPaintable;
            stringPaintable.setText("");
        } else if (x == 0) {
            paintable = stringPaintable;
            stringPaintable.setText(Integer.toString(y));
        } else if (y == 0) {
            paintable = stringPaintable;
            stringPaintable.setText(labels[x - 1]);
        } else {
            paintable = borderPaintable;
            Room room = roomsModel.getRoomAt(x - 1, y - 1);
            if (room != null) {
                borderPaintable.setColor(room.color);
                checkObjectsLocation(borderPaintable, x - 1, y - 1);
                setPaintableBounds(borderPaintable, x - 1, y - 1);
            } else {
                paintable = colorPaintable;
            }
        }
        paintable.setBounds(host.getCellRectangle(x, y));
        return paintable;
    }

    private void checkObjectsLocation(BorderColorPaintable paintable, int x, int y) {
        if (    x == AppModel.getInstance().seekVodkaPrefs.moneyX
                && y == AppModel.getInstance().seekVodkaPrefs.moneyY
                && x == AppModel.getInstance().seekVodkaPrefs.vodkaX
                && y == AppModel.getInstance().seekVodkaPrefs.vodkaY) {
            paintable.setText("!");
        } else if (    x == AppModel.getInstance().seekVodkaPrefs.moneyX
                && y == AppModel.getInstance().seekVodkaPrefs.moneyY) {
            paintable.setText("З");
        } else if (    x == AppModel.getInstance().seekVodkaPrefs.vodkaX
                && y == AppModel.getInstance().seekVodkaPrefs.vodkaY) {
            paintable.setText("П");
        } else {
            paintable.setText("");
        }
    }

    private void setPaintableBounds(BorderColorPaintable paintable, int x, int y) {
        int bounds = 0;
        Room room = roomsModel.getRoomAt(x, y);
        if (y > 0 && roomsModel.getRoomAt(x, y - 1) != room) {
            bounds += BorderColorPaintable.BORDER_UP;
        }
        if (y < (roomsModel.getHeight() - 1) && roomsModel.getRoomAt(x, y + 1) != room) {
            bounds += BorderColorPaintable.BORDER_BOTTOM;
        }
        if (x > 0 && roomsModel.getRoomAt(x - 1, y) != room) {
            bounds += BorderColorPaintable.BORDER_LEFT;
        }
        if (x < (roomsModel.getWidth() - 1) && roomsModel.getRoomAt(x + 1, y) != room) {
            bounds += BorderColorPaintable.BORDER_RIGHT;
        }
        paintable.setBorders(bounds);
    }

    public void setRoomType(int type) {
        roomsModel.setType(type);
        notifyChanged();
    }
}
