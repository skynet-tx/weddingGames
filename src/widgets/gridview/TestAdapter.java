package widgets.gridview;

import widgets.stuff.ColorPaintable;
import widgets.stuff.Paintable;

import java.awt.*;

/**
 * Created by SER on 06.11.2014.
 */
public class TestAdapter extends GridViewAdapter {
    @Override
    public int getHorizontalCellCount() {
        return 10;
    }

    @Override
    public int getVerticalCellCount() {
        return 10;
    }

    @Override
    public Paintable getPaintableForCell(int x, int y) {
        return new ColorPaintable(new Color(0xff906060), host.getCellRectangle(x, y));
    }
}
