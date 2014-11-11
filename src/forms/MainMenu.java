package forms;

import navigation.NavigationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Created by SER on 10.11.2014.
 */
public class MainMenu extends ManagedForm implements ActionListener {
    private JPanel root;
    private JButton seaBattle;
    private JButton findMoney;
    private JButton prefs;
    private JLabel logo;

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
        seaBattle.addActionListener(this);
        prefs.addActionListener(this);
        findMoney.addActionListener(this);
        seaBattle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NavigationController.getInstance().switchScreen(NavigationController.SCREEN_SEABATTLE);
            }
        });

        ClassLoader cl= this.getClass().getClassLoader();
        URL imageURL   = cl.getResource("resources/logo_default.png");
        ImageIcon icon = new ImageIcon(imageURL);
        logo.setIcon(icon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == seaBattle) {
            NavigationController.getInstance().switchScreen(NavigationController.SCREEN_SEABATTLE);
        }
        if (e.getSource() == findMoney) {

        }
        if (e.getSource() == prefs) {
            NavigationController.getInstance().switchScreen(NavigationController.SCREEN_PREFERENCES);
        }
    }

}