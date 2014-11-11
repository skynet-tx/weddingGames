package widgets.gridview;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by SER on 06.11.2014.
 */
public class GridView extends Component {

    private GridViewAdapter adapter;
    private Color boundColor = new Color(0xff000000);
    private Color backgroundColor = new Color(0xffb0b0b0);
    private int boundWidth = 2;

    private float cellWidth;
    private float cellWidthFull;
    private float cellHeight;
    private float cellHeightFull;


    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        notifyChanged();
    }

    public void setBoundWidth(int boundWidth) {
        this.boundWidth = boundWidth;
        notifyChanged();
    }

    public void setBoundColor(Color boundColor) {
        this.boundColor = boundColor;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (adapter == null) {
            return;
        }

        g.setColor(boundColor);

        for(int y = 0; y <= adapter.getVerticalCellCount(); y++) {
            g.fillRect(0, (int)Math.ceil(y * cellHeightFull), getWidth(), boundWidth);
        }
        for(int x = 0; x <= adapter.getHorizontalCellCount(); x++) {
            g.fillRect((int)Math.ceil(x * cellWidthFull), 0, boundWidth, getHeight());
        }

        for(int y = 0; y < adapter.getVerticalCellCount(); y++) {
            for(int x = 0; x < adapter.getHorizontalCellCount(); x++) {
                adapter.getPaintableForCell(x, y).onPaint(g);
            }
        }

    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        super.processMouseEvent(e);
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent e) {
        super.processMouseMotionEvent(e);
    }

    public void notifyChanged() {
        if (adapter != null) {
            int xCount = adapter.getHorizontalCellCount();
            int yCount = adapter.getVerticalCellCount();
            cellWidthFull = (float)(getWidth() - boundWidth) / xCount;
            cellHeightFull = (float)(getHeight() - boundWidth) / yCount;
            cellWidth = cellWidthFull - boundWidth;
            cellHeight = cellHeightFull - boundWidth;
        }
        invalidate();
    }

    public GridViewAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(GridViewAdapter adapter) {
        this.adapter = adapter;
        adapter.setHost(this);
        notifyChanged();
    }

    public Rectangle getCellRectangle(int i, int j) {
        if (adapter == null) {
            return null;
        }
        int x = (int)Math.ceil(boundWidth + i * cellWidthFull);
        int y = (int)Math.ceil(boundWidth + j * cellHeightFull);
        return new Rectangle(x, y, (int)Math.ceil(cellWidth), (int)Math.ceil(cellHeight));
    }

}
