package com.arhiser.wedding.forms.menu;

import com.arhiser.wedding.forms.ManagedForm;
import com.arhiser.wedding.managers.ImageManager;
import com.arhiser.wedding.navigation.NavigationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        logo.setIcon(ImageManager.getImageByResourceName("logo_default.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == seaBattle) {
            NavigationController.getInstance().switchScreen(NavigationController.SCREEN_SEABATTLE);
        }
        if (e.getSource() == findMoney) {
            NavigationController.getInstance().switchScreen(NavigationController.SCREEN_SEEKVODKA);
        }
        if (e.getSource() == prefs) {
            NavigationController.getInstance().switchScreen(NavigationController.SCREEN_PREFERENCES);
        }
    }

}
