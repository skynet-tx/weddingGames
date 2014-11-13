package widgets.gridview;

import widgets.stuff.ColorPaintable;
import widgets.stuff.Paintable;
import widgets.stuff.StringPaintable;

import java.awt.*;

/**
 * Created by SER on 06.11.2014.
 */
public class TestAdapter extends GridViewAdapter {
    private String[] labels = new String[]{"а", "б", "в", "г", "д", "е", "ж", "з", "и", "к"};

    @Override
    public int getHorizontalCellCount() {
        return 11;
    }

    @Override
    public int getVerticalCellCount() {
        return 11;
    }

    @Override
    public Paintable getPaintableForCell(int x, int y) {

        if (x + y == 0) {
            return new StringPaintable("", new Color(0xffb0b0b0), host.getCellRectangle(x, y));
        }

        if (x == 0) {
            return new StringPaintable(Integer.toString(y), new Color(0xffb0b0b0), host.getCellRectangle(x, y));
        }

        if (y == 0) {
            return new StringPaintable(labels[x - 1], new Color(0xffb0b0b0), host.getCellRectangle(x, y));
        }

        return new ColorPaintable(new Color(0xffd0d0d0), host.getCellRectangle(x, y));
    }
}
