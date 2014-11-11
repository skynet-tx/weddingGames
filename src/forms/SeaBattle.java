package forms;

import widgets.gridview.GridView;
import widgets.gridview.TestAdapter;

import javax.swing.*;
import java.awt.*;

/**
 * Created by SER on 09.11.2014.
 */
public class SeaBattle extends ManagedForm {
    public JPanel root;
    public JPanel game;
    public JPanel info;
    public GridView gridView;

    @Override
    public void init() {
        gridView = new GridView();
        game.add(gridView);
        gridView.setBoundColor(new Color(0xff303030));
        gridView.setAdapter(new TestAdapter());
    }

    @Override
    public String getTitle() {
        return "Морской бой";
    }

    @Override
    public Container getRootContainer() {
        return root;
    }

}
