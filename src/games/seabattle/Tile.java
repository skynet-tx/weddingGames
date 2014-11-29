package games.seabattle;

/**
 * Created by SER on 16.11.2014.
 */
public class Tile {
    public static final int STATE_CLOSED = 1;
    public static final int STATE_OPENED = 2;
    public static final int STATE_OPENED_AUTO = 3;
    public static final int STATE_HIT = 4;

    private int state = STATE_CLOSED;
    private Prize prize = null;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }
}
