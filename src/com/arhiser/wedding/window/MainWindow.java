package com.arhiser.wedding.window;

import com.arhiser.wedding.managers.ImageManager;
import com.arhiser.wedding.navigation.NavigationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SER on 06.11.2014.
 */
public class MainWindow extends JFrame implements ActionListener {

    public MainWindow() {
        setTitle("Party");
        setSize(1024, 768);
        setMinimumSize(new Dimension(800, 750));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(ImageManager.iconToImage(ImageManager.getImageByResourceName("appIcon.png")));

        NavigationController.init(this);

        NavigationController.getInstance().switchScreen(NavigationController.SCREEN_MAIN_MENU);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
