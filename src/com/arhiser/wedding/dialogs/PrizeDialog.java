package com.arhiser.wedding.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PrizeDialog extends JDialog {
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
        setVisible(true);
    }
}
