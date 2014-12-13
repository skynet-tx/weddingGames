package games.seekvodka;

import com.arhiser.wedding.AppModel;
import com.arhiser.wedding.dialogs.DialogFactory;
import com.arhiser.wedding.widgets.gridview.GridView;
import com.arhiser.wedding.widgets.gridview.GridViewAdapter;
import com.arhiser.wedding.widgets.stuff.BorderColorPaintable;
import com.arhiser.wedding.widgets.stuff.ColorPaintable;
import com.arhiser.wedding.widgets.stuff.Paintable;
import com.arhiser.wedding.widgets.stuff.StringPaintable;
import games.seekvodka.paitables.OpenCellPaintable;

import java.awt.*;

/**
 * Created by SER on 30.11.2014.
 */
public class RoomsAdapter extends GridViewAdapter<GridView> {

    private String[] labels = new String[]{"а", "б", "в", "г", "д", "е", "ж", "з", "и", "к"};

    private static final int TILE_PREFS = 1;
    private static final int TILE_CLOSED = 2;
    private static final int TILE_OPENED = 3;

    private GridView host;

    private RoomsModel roomsModel;
    private boolean prefsMode = false;

    private Tile[] tiles;

    private ColorPaintable colorPaintable = new ColorPaintable(new Color(0xfff0ffff), null);
    private StringPaintable stringPaintable = new StringPaintable("", new Color(0xffffffff), null);
    private BorderColorPaintable borderPaintable = new BorderColorPaintable(new Color(0x0000ff), null);
    private OpenCellPaintable openPaintable = new OpenCellPaintable();

    public RoomsAdapter(GridView host, RoomsModel roomsModel, boolean prefsMode) {
        this.host = host;
        this.roomsModel = roomsModel;
        this.prefsMode = prefsMode;
        notifyChanged();
    }

    public RoomsAdapter(GridView host, RoomsModel roomsModel) {
        this(host, roomsModel, false);
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
                paintable = borderPaintable;
                borderPaintable.setText("");
                borderPaintable.setColor(room.color);
                if (prefsMode) {
                    checkObjectsLocation(borderPaintable, x - 1, y - 1);
                } else {
                    Tile tile = getTile(x - 1, y - 1);
                    if (tile.state == TILE_OPENED) {
                        if (tile.hasMoney && tile.hasVodka) {
                            borderPaintable.setText("!");
                        } else if (tile.hasMoney) {
                            borderPaintable.setText("З");
                        } else if (tile.hasVodka) {
                            borderPaintable.setText("П");
                        } else {
                            paintable = openPaintable;
                            openPaintable.setColor(room.color);
                            setPaintableBounds(openPaintable, x - 1, y - 1);
                        }
                    } else {
                        borderPaintable.setColor(room.color);
                    }
                }
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

    public void resetTiles () {
        tiles = new Tile[roomsModel.getWidth() * roomsModel.getHeight()];
        for(int i = 0; i < tiles.length; i++) {
            tiles[i] = new Tile(prefsMode ? TILE_PREFS : TILE_CLOSED);
            int y = i / roomsModel.getWidth();
            int x = i % roomsModel.getWidth();
            tiles[i].hasMoney = (x == AppModel.getInstance().seekVodkaPrefs.moneyX && y == AppModel.getInstance().seekVodkaPrefs.moneyY);
            tiles[i].hasVodka = (x == AppModel.getInstance().seekVodkaPrefs.vodkaX && y == AppModel.getInstance().seekVodkaPrefs.vodkaY);
        }
    }

    class Tile {
        public int state;
        public boolean hasVodka;
        public boolean hasMoney;

        public Tile(int state) {
            this.state = state;
        }
    }

    private Tile getTile(int x, int y) {
        return tiles[y * roomsModel.getWidth() + x];
    }

    public void openCell(int x, int y) {
        getTile(x, y).state = TILE_OPENED;
        notifyChanged();
    }

    public boolean isTileOpened(int x, int y) {
        return getTile(x, y).state == TILE_OPENED;
    }
}
