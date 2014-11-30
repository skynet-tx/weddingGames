package com.arhiser.wedding.widgets.gridview;

import com.arhiser.wedding.widgets.stuff.ColorPaintable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by SER on 06.11.2014.
 */
public class GridView <T extends GridViewAdapter> extends JComponent {

    protected T adapter;
    protected Color boundColor = new Color(0xff000000);
    protected ColorPaintable backgroundColor = new ColorPaintable( new Color(0xffb0b0b0), null);
    protected int boundWidth = 2;

    protected float cellWidth;
    protected float cellWidthFull;
    protected float cellHeight;
    protected float cellHeightFull;

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

    public void setBackgroundColor(Color boundColor) {
        backgroundColor.setColor(boundColor);
    }

    @Override
    public void paint(Graphics g) {
        backgroundColor.onPaint(g);
        if (adapter == null) {
            return;
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
            backgroundColor.setBounds(new Rectangle(0, 0, getWidth(), getHeight()));
        }
        invalidate();
    }

    public T getAdapter() {
        return adapter;
    }

    public void setAdapter(T adapter) {
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
