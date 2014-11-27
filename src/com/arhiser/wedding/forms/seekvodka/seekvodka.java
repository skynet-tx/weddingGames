package com.arhiser.wedding.forms.seekvodka;

import com.arhiser.wedding.forms.ManagedForm;
import com.arhiser.wedding.navigation.NavigationController;
import com.arhiser.wedding.widgets.roomgrid.RoomGridView;
import games.seekvodka.RoomsModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SER on 27.11.2014.
 */
public class SeekVodka extends ManagedForm implements ActionListener {
    private JPanel root;
    private JPanel gameContainer;
    private JButton backToMenu;
    private RoomGridView roomGridView;
    private RoomsModel model;

    @Override
    public String getTitle() {
        return "Заначка и пузырь";
    }

    @Override
    public Container getRootContainer() {
        return root;
    }

    @Override
    public void init() {
        backToMenu.addActionListener(this);

        roomGridView = new RoomGridView();
        model = new RoomsModel();
        roomGridView.setAdapter(model);
        gameContainer.add(roomGridView);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToMenu) {
            NavigationController.getInstance().switchScreen(NavigationController.SCREEN_MAIN_MENU);
        }
    }
}
