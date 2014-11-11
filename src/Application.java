import window.MainWindow;

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
                mainWindow = new MainWindow();
                mainWindow.setVisible(true);
            }
        });
    }
}
