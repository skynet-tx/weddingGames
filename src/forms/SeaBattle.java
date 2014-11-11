package forms;

import dialogs.DialogFactory;
import navigation.NavigationController;
import widgets.gridview.GridView;
import widgets.gridview.TestAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SER on 09.11.2014.
 */
public class SeaBattle extends ManagedForm implements ActionListener {
    public JPanel root;
    public JPanel game;
    public JPanel info;
    private JButton back;
    private JButton restartGame;
    public GridView gridView;

    @Override
    public void init() {
        gridView = new GridView();
        game.add(gridView);
        gridView.setBoundColor(new Color(0xffa8a8f0));
        gridView.setAdapter(new TestAdapter());
        back.addActionListener(this);
        restartGame.addActionListener(this);

    }

    @Override
    public String getTitle() {
        return "Морской бой";
    }

    @Override
    public Container getRootContainer() {
        return root;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            if (JOptionPane.YES_OPTION == DialogFactory.getInstance().showDialog(DialogFactory.DIALOG_EXIT_GAME)) {
                NavigationController.getInstance().switchScreen(NavigationController.SCREEN_MAIN_MENU);
            }
        }
        if (e.getSource() == restartGame) {

        }
    }
}
