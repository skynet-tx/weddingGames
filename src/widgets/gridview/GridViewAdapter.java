package widgets.gridview;

import widgets.stuff.Paintable;

/**
 * Created by SER on 06.11.2014.
 */
abstract public class GridViewAdapter {

    protected GridView host;

    abstract public int getHorizontalCellCount();
    abstract public int getVerticalCellCount();

    abstract public Paintable getPaintableForCell(int x, int y);

    public void notifyChanged() {
        host.notifyChanged();
    }

    public void setHost(GridView host) {
        this.host = host;
    }
}
