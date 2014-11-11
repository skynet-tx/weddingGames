package forms;

import navigation.NavigationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SER on 11.11.2014.
 */
public class PrefsMenu  extends ManagedForm implements ActionListener{
    private JPanel root;
    private JButton back;
    private JButton seaBattle;
    private JButton seekHidden;

    @Override
    public String getTitle() {
        return "Настройки игр";
    }

    @Override
    public Container getRootContainer() {
        return root;
    }

    @Override
    public void init() {
        back.addActionListener(this);
        seaBattle.addActionListener(this);
        seekHidden.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            NavigationController.getInstance().switchScreen(NavigationController.SCREEN_MAIN_MENU);
        }
        if (e.getSource() == seaBattle) {

        }
        if (e.getSource() == seekHidden) {

        }
    }
}
