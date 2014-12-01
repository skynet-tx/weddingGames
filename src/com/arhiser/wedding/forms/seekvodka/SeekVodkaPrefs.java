package com.arhiser.wedding.forms.seekvodka;

import com.arhiser.wedding.AppModel;
import com.arhiser.wedding.forms.ManagedForm;
import com.arhiser.wedding.navigation.NavigationController;
import com.arhiser.wedding.widgets.gridview.GridView;
import games.seekvodka.RoomsAdapter;
import games.seekvodka.RoomsModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SER on 27.11.2014.
 */
public class SeekVodkaPrefs extends ManagedForm implements ActionListener, GridView.CellClickListener {

    public static final int PLACEMENT_NONE = 0;
    public static final int PLACEMENT_VODKA = 1;
    public static final int PLACEMENT_MONEY = 2;

    private JPanel root;
    private JButton backToMenu;
    private JRadioButton rbSmall;
    private JRadioButton rbLarge;
    private JRadioButton rbMedium;
    private JPanel flatHolder;
    private JButton addVodka;
    private JButton addMoney;

    RoomsAdapter roomsAdapter;

    private int placementMode = PLACEMENT_NONE;

    @Override
    public String getTitle() {
        return "Настройки игры \"Заначка и пузырь\"";
    }

    @Override
    public void init() {
        backToMenu.addActionListener(this);
        rbSmall.addActionListener(this);
        rbMedium.addActionListener(this);
        rbLarge.addActionListener(this);
        addVodka.addActionListener(this);
        addMoney.addActionListener(this);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rbSmall);
        buttonGroup.add(rbMedium);
        buttonGroup.add(rbLarge);

        GridView<RoomsAdapter> gridView = new GridView<RoomsAdapter>();
        RoomsModel roomsModel = new RoomsModel(AppModel.getInstance().seekVodkaPrefs.roomType);
        roomsAdapter = new RoomsAdapter(gridView, roomsModel);
        gridView.setAdapter(roomsAdapter);
        flatHolder.add(gridView, BorderLayout.CENTER);
        gridView.setListener(this);

        switch (AppModel.getInstance().seekVodkaPrefs.roomType) {
            case RoomsModel.TYPE_SMALL:
                rbSmall.setSelected(true);
                break;
            case RoomsModel.TYPE_MEDIUM:
                rbMedium.setSelected(true);
                break;
            case RoomsModel.TYPE_LARGE:
                rbLarge.setSelected(true);
                break;
        }

    }

    @Override
    public Container getRootContainer() {
        return root;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToMenu) {
            NavigationController.getInstance().switchScreen(NavigationController.SCREEN_PREFERENCES);
            AppModel.save();
        }
        if (e.getSource() == rbSmall) {
            clearMoneyPosition();
            roomsAdapter.setRoomType(RoomsModel.TYPE_SMALL);
            AppModel.getInstance().seekVodkaPrefs.roomType = RoomsModel.TYPE_SMALL;
        }
        if (e.getSource() == rbMedium) {
            clearMoneyPosition();
            roomsAdapter.setRoomType(RoomsModel.TYPE_MEDIUM);
            AppModel.getInstance().seekVodkaPrefs.roomType = RoomsModel.TYPE_MEDIUM;
        }
        if (e.getSource() == rbLarge) {
            clearMoneyPosition();
            roomsAdapter.setRoomType(RoomsModel.TYPE_LARGE);
            AppModel.getInstance().seekVodkaPrefs.roomType = RoomsModel.TYPE_LARGE;
        }
        if (e.getSource() == addVodka) {
            addVodka.setSelected(setPlacement(PLACEMENT_VODKA));
        }
        if (e.getSource() == addMoney) {
            addMoney.setSelected(setPlacement(PLACEMENT_MONEY));
        }
    }

    private boolean setPlacement(int mode) {
        if (placementMode > 0 && mode > 0) {
            return false;
        }
        if (placementMode > 0 && mode == 0) {
            root.setCursor(Cursor.getDefaultCursor());
            addVodka.setSelected(false);
            addMoney.setSelected(false);
            placementMode = PLACEMENT_NONE;
            return true;
        }
        root.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        placementMode = mode;
        return true;
    }

    public void clearMoneyPosition() {
        AppModel.getInstance().seekVodkaPrefs.vodkaX = -1;
        AppModel.getInstance().seekVodkaPrefs.vodkaY = -1;
        AppModel.getInstance().seekVodkaPrefs.moneyX = -1;
        AppModel.getInstance().seekVodkaPrefs.moneyY = -1;
        roomsAdapter.notifyChanged();
    }

    @Override
    public void onCellClicked(int x, int y) {
        if (placementMode == PLACEMENT_MONEY) {
            AppModel.getInstance().seekVodkaPrefs.moneyX = x - 1;
            AppModel.getInstance().seekVodkaPrefs.moneyY = y - 1;
            roomsAdapter.notifyChanged();
        }
        if (placementMode == PLACEMENT_VODKA) {
            AppModel.getInstance().seekVodkaPrefs.vodkaX = x - 1;
            AppModel.getInstance().seekVodkaPrefs.vodkaY = y - 1;
            roomsAdapter.notifyChanged();
        }
        setPlacement(PLACEMENT_NONE);
    }
}
