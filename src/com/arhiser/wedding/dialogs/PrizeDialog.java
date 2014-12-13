package com.arhiser.wedding.dialogs;

import com.arhiser.wedding.managers.ImageManager;
import games.seekvodka.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PrizeDialog extends JDialog {

    public static final int RESULT_ZANACHKA = 1;
    public static final int RESULT_PUZIR = 2;
    public static final int RESULT_COLLISION = 3;

    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel prizeImage;
    private JLabel prizeName;

    public PrizeDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        onCancel();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public void show(String prizeName, Icon prizeImage) {
        this.prizeName.setText(prizeName);
        this.prizeImage.setIcon(prizeImage);
        setSize(new Dimension(prizeImage.getIconWidth() + 150, prizeImage.getIconHeight() + 150));
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
        contentPane.setBackground(new Color(0xFFF0F0F0));
        setVisible(true);
    }

    public void show(int result, String prizeName, Icon prizeImage) {
        contentPane.setBackground(new Color(0xFFFF0000));
        SwingUtilities.invokeLater(new Flash(result));
        this.prizeName.setText(prizeName);
        this.prizeImage.setIcon(prizeImage);
        setSize(new Dimension(prizeImage.getIconWidth() + 150, prizeImage.getIconHeight() + 150));
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
        setVisible(true);
    }

    class Flash implements Runnable {

        int result;
        int flashRemains = 15;

        public Flash(int result) {
            this.result = result;
        }

        @Override
        public void run() {
            if(flashRemains == 0) {
                contentPane.setBackground(new Color(0xFFF0F0F0));
                switch (result) {
                    case RESULT_ZANACHKA:
                        prizeName.setText("Заначка");
                        prizeImage.setIcon(ImageManager.getImageByPathName(Room.IMAGE_DIR, "zanachka"));
                        break;
                    case RESULT_PUZIR:
                        prizeName.setText("Пузырь");
                        prizeImage.setIcon(ImageManager.getImageByPathName(Room.IMAGE_DIR, "puzir"));
                        break;
                    case RESULT_COLLISION:
                        prizeName.setText("Коллизия");
                        prizeImage.setIcon(ImageManager.getImageByPathName(Room.IMAGE_DIR, "collision"));
                        break;
                }
            } else {
                flashRemains--;
                contentPane.setBackground(flashRemains % 2 > 0 ? new Color(0xFFFF0000) : new Color(0xFFF0F0F0));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SwingUtilities.invokeLater(this);
            }
        }
    }
}
