package com.arhiser.wedding.widgets.gridview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by SER on 06.11.2014.
 */
public class GridView extends JComponent {

    private GridViewAdapter adapter;
    private Color boundColor = new Color(0xff000000);
    private Color backgroundColor = new Color(0xffb0b0b0);
    private int boundWidth = 2;

    private float cellWidth;
    private float cellWidthFull;
    private float cellHeight;
    private float cellHeightFull;

    private CellClickListener listener;

    public interface CellClickListener {
        void onCellClicked(int x, int y);
    }

    public void setListener(CellClickListener listener) {
        this.listener = listener;
    }

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

    public GridView() {
        addMouseListener(mouseListener);
    }

    public Rectangle getCellRectangle(int i, int j) {
        if (adapter == null) {
            return null;
        }
        int x = (int)Math.ceil(boundWidth + i * cellWidthFull);
        int y = (int)Math.ceil(boundWidth + j * cellHeightFull);
        return new Rectangle(x, y, (int)Math.ceil(cellWidth), (int)Math.ceil(cellHeight));

    }

    private MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            Point point = e.getPoint();
            if (listener != null) {
                listener.onCellClicked((int)(point.x / cellWidthFull), (int)(point.y / cellHeightFull));
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

}