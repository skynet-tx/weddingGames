package window;

import forms.SeaBattle;
import navigation.NavigationController;
import widgets.gridview.GridView;
import widgets.gridview.TestAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Created by SER on 06.11.2014.
 */
public class MainWindow extends JFrame implements ActionListener {

    public MainWindow() {
        setTitle("Party");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        NavigationController.init(this);

        NavigationController.getInstance().switchScreen(NavigationController.SCREEN_MAIN_MENU);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
