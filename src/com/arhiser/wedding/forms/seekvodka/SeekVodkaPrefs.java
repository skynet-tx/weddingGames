package com.arhiser.wedding.forms.seekvodka;

import com.arhiser.wedding.forms.ManagedForm;
import com.arhiser.wedding.navigation.NavigationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SER on 27.11.2014.
 */
public class SeekVodkaPrefs extends ManagedForm implements ActionListener {
    private JPanel root;
    private JButton backToMenu;

    @Override
    public String getTitle() {
        return "Настройки игры \"Заначка и пузырь\"";
    }

    @Override
    public void init() {
        backToMenu.addActionListener(this);
    }

    @Override
    public Container getRootContainer() {
        return root;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToMenu) {
            NavigationController.getInstance().switchScreen(NavigationController.SCREEN_PREFERENCES);
        }
    }
}
