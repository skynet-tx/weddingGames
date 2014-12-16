package com.arhiser.wedding;

import com.arhiser.wedding.window.MainWindow;

import javax.swing.*;

/**
 * Created by SER on 06.11.2014.
 */
public class Application {
    public static MainWindow mainWindow;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mainWindow = new MainWindow();
                AppModel.initInstance();
                mainWindow.initAppController();
                mainWindow.setVisible(true);
            }
        });
    }
}
