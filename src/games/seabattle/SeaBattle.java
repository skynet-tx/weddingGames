package games.seabattle;

import utils.NumUtils;
import widgets.gridview.GridView;
import widgets.gridview.GridViewAdapter;
import widgets.stuff.ColorPaintable;
import widgets.stuff.Paintable;
import widgets.stuff.StringPaintable;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by SER on 16.11.2014.
 */
public class SeaBattle extends GridViewAdapter implements GridView.CellClickListener {

    private String[] labels = new String[]{"а", "б", "в", "г", "д", "е", "ж", "з", "и", "к"};

    int height = 10;
    int width = 10;

    public Tile[] board = new Tile[width * height];

    private ColorPaintable closedPaintable;
    private ColorPaintable openedPaintable;
    private ColorPaintable hitPaintable;
    private StringPaintable stringPaintable;

    @Override
    public int getHorizontalCellCount() {
        return width + 1;
    }

    @Override
    public int getVerticalCellCount() {
        return height + 1;
    }

    public SeaBattle() {
        openedPaintable = new ColorPaintable(new Color(0xffffff), null);
        closedPaintable = new ColorPaintable(new Color(0xffc0c0c0), null);
        hitPaintable = new ColorPaintable(new Color(0xffff8080), null);
        stringPaintable = new StringPaintable("", new Color(0xffb0b0b0), null);

        Prize[] prizes = new Prize[10];
        generateField(prizes);
    }

    @Override
    public Paintable getPaintableForCell(int x, int y) {
        ColorPaintable paintable = null;
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
            Tile tile = getTileAt(x - 1, y - 1);
            switch (tile.getState()) {
                case Tile.STATE_CLOSED:
                    paintable = closedPaintable;
                    break;
                case Tile.STATE_OPENED:
                    paintable = openedPaintable;
                    break;
                case Tile.STATE_HIT:
                    paintable = hitPaintable;
                    break;
            }
        }

        paintable.setBounds(host.getCellRectangle(x, y));
        return paintable;
    }

    Tile getTileAt(int x, int y) {
        return board[y * height + x];
    }

    private void generateField(Prize[] prizes) {
        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                board[y * height + x] = new Tile();
            }
        }

        int x;
        int y;
        for(int i = 0; i < 10; i++) {
            prizes[i] = new Prize();
            do {
                x = NumUtils.getRandomInt(width);
                y = NumUtils.getRandomInt(height);
            } while (!isPrizeNear(x,y));
            getTileAt(x,y).setPrize(prizes[i]);
        }
    }

    MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

    public MouseListener getMouseListener() {
        return mouseListener;
    }

    @Override
    public void onCellClicked(int x, int y) {
        if (x == 0 || y == 0) {
            return;
        }
        x -= 1;
        y -= 1;
        Tile tile = getTileAt(x, y);
        if (tile.getState() == Tile.STATE_CLOSED) {
            if (tile.getPrize() == null) {
                tile.setState(Tile.STATE_OPENED);
            } else {
                tile.setState(Tile.STATE_HIT);
                for(int i = x - 1; i < x + 2; i++) {
                    for(int j = y - 1; j < y + 2; j++) {
                        if (i >= 0 && i < width && j >= 0 && j < height && !(i == x && j == y)) {
                            getTileAt(i, j).setState(Tile.STATE_OPENED);
                        }
                    }
                }
            }
        }
        host.repaint();
    }

    public boolean isPrizeNear(int x, int y) {
        for(int i = x - 1; i < x + 2; i++) {
            for(int j = y - 1; j < y + 2; j++) {
                if (i >= 0 && i < width && j >= 0 && j < height) {
                    if (getTileAt(i, j).getPrize() != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
