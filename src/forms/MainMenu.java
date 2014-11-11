package forms;

import navigation.NavigationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SER on 10.11.2014.
 */
public class MainMenu extends ManagedForm{
    private JPanel root;
    private JButton seaBattle;
    private JButton findMoney;
    private JButton prefs;

    @Override
    public String getTitle() {
        return "Выбор игры";
    }

    @Override
    public Container getRootContainer() {
        return root;
    }

    @Override
    public void init() {
        seaBattle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NavigationController.getInstance().switchScreen(NavigationController.SCREEN_SEABATTLE);
            }
        });
    }
}
