package games.seabattle;

import com.arhiser.wedding.AppModel;
import com.arhiser.wedding.utils.NumUtils;
import com.arhiser.wedding.widgets.gridview.GridView;
import com.arhiser.wedding.widgets.gridview.GridViewAdapter;
import com.arhiser.wedding.widgets.stuff.ColorPaintable;
import com.arhiser.wedding.widgets.stuff.Paintable;
import com.arhiser.wedding.widgets.stuff.StringPaintable;

import java.awt.*;

/**
 * Created by SER on 16.11.2014.
 */
public class SeaBattle extends GridViewAdapter implements GridView.CellClickListener {

    public interface SeaBattleListener {
        public void onTurn(Prize prize);
        public void onGameEnds();
    }

    private String[] labels = new String[]{"а", "б", "в", "г", "д", "е", "ж", "з", "и", "к"};

    int height = 10;
    int width = 10;

    public Tile[] board = new Tile[width * height];

    private ColorPaintable closedPaintable;
    private ColorPaintable openedPaintable;
    private ColorPaintable hitPaintable;
    private StringPaintable stringPaintable;

    private SeaBattleListener listener;

    public void setListener(SeaBattleListener listener) {
        this.listener = listener;
    }

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

        generateField(Prize.getPrizes());
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

    public void generateField(Prize[] prizes) {

        if (prizes == null) {
            prizes = new Prize[AppModel.getInstance().seaBattlePrefs.prizeCount];
            for(int i = 0; i < prizes.length; i++) {
                prizes[i] = new Prize();
            }
        }

        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                board[y * height + x] = new Tile();
            }
        }

        int x;
        int y;
        for(int i = 0; i < prizes.length; i++) {
            do {
                x = NumUtils.getRandomInt(width);
                y = NumUtils.getRandomInt(height);
            } while (!isPrizeNear(x,y));
            getTileAt(x,y).setPrize(prizes[i]);
        }
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
                if (listener != null) {
                    listener.onTurn(null);
                }
            } else {
                tile.setState(Tile.STATE_HIT);
                for(int i = x - 1; i < x + 2; i++) {
                    for(int j = y - 1; j < y + 2; j++) {
                        if (i >= 0 && i < width && j >= 0 && j < height && !(i == x && j == y)) {
                            getTileAt(i, j).setState(Tile.STATE_OPENED);
                        }
                    }
                }
                if (listener != null) {
                    listener.onTurn(tile.getPrize());
                    if (isGameEnded()) {
                        listener.onGameEnds();
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

    public boolean isGameEnded() {
        for(int i = 0; i < board.length; i++) {
            if (board[i].getPrize() != null && board[i].getState() == Tile.STATE_CLOSED) {
                return false;
            }
        }
        return true;
    }
}
