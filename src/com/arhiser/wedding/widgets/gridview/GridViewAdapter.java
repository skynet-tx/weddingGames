package com.arhiser.wedding.widgets.gridview;

import com.arhiser.wedding.widgets.stuff.Paintable;

/**
 * Created by SER on 06.11.2014.
 */
abstract public class GridViewAdapter <T extends GridView> {

    protected T host;

    abstract public int getHorizontalCellCount();
    abstract public int getVerticalCellCount();

    abstract public Paintable getPaintableForCell(int x, int y);

    public void notifyChanged() {
        host.notifyChanged();
    }

    public void setHost(T host) {
        this.host = host;
    }
}
