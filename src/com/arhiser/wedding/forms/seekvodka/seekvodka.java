package com.arhiser.wedding.forms.seekvodka;

import com.arhiser.wedding.AppModel;
import com.arhiser.wedding.dialogs.DialogFactory;
import com.arhiser.wedding.dialogs.PrizeDialog;
import com.arhiser.wedding.forms.ManagedForm;
import com.arhiser.wedding.navigation.NavigationController;
import com.arhiser.wedding.widgets.gridview.GridView;
import games.seekvodka.LegendModel;
import games.seekvodka.Room;
import games.seekvodka.RoomsAdapter;
import games.seekvodka.RoomsModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SER on 27.11.2014.
 */
public class SeekVodka extends ManagedForm implements ActionListener, GridView.CellClickListener {
    private JPanel root;
    private JPanel gameContainer;
    private JButton backToMenu;
    private JTable tableLegend;
    private GridView<RoomsAdapter> roomGridView;
    private RoomsAdapter roomsAdapter;
    private RoomsModel roomsModel;

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

        roomGridView = new GridView<RoomsAdapter>();
        roomsModel = new RoomsModel(AppModel.getInstance().seekVodkaPrefs.roomType);
        roomsAdapter = new RoomsAdapter(roomGridView, roomsModel);
        roomGridView.setBackgroundColor(new Color(0xff808080));
        roomGridView.setBoundWidth(3);

        setUpLegend();

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableLegend.setFont(new Font("Arial", Font.BOLD, 30));
        tableLegend.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);

        roomGridView.setAdapter(roomsAdapter);
        roomGridView.setListener(this);
        gameContainer.add(roomGridView);
    }

    private void setUpLegend(){
        LegendModel legendModel = new LegendModel(roomsModel);
        tableLegend.setModel(legendModel);

        tableLegend.setRowHeight(40);
        tableLegend.getColumnModel().getColumn(1).setPreferredWidth(300);
        tableLegend.getColumnModel().getColumn(2).setPreferredWidth(40);
        tableLegend.getColumnModel().getColumn(0).setPreferredWidth(40);
        tableLegend.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToMenu) {
            NavigationController.getInstance().switchScreen(NavigationController.SCREEN_MAIN_MENU);
        }
    }

    @Override
    public void onCellClicked(int x, int y) {
        if (x == 0 || y == 0) {
            return;
        }
        if (roomsAdapter.isTileOpened(x - 1, y - 1)) {
            return;
        }
        Room room = roomsModel.getRoomAt(x - 1, y - 1);
        roomsAdapter.openCell(x - 1, y - 1);
        if (AppModel.getInstance().seekVodkaPrefs.isCellOccuped(x - 1, y - 1)) {
            int result = -1;
            if (AppModel.getInstance().seekVodkaPrefs.isCellCollision(x - 1, y - 1)) {
                result = PrizeDialog.RESULT_COLLISION;
            } else if (AppModel.getInstance().seekVodkaPrefs.isCellMoney(x - 1, y - 1)) {
                result = PrizeDialog.RESULT_ZANACHKA;
            } else if (AppModel.getInstance().seekVodkaPrefs.isCellVodka(x - 1, y - 1)) {
                result = PrizeDialog.RESULT_PUZIR;
            }
            DialogFactory.getInstance().showPrizeDialog(result, room.name, room.getRoomImage(x - 1, y - 1));
        } else {
            DialogFactory.getInstance().showPrizeDialog(room.name, room.getRoomImage(x - 1, y - 1));
        }

    }

    @Override
    public void onShow() {
        roomsModel.setType(AppModel.getInstance().seekVodkaPrefs.roomType);
        setUpLegend();

        roomsAdapter.resetTiles();
        roomGridView.revalidate();
        roomGridView.repaint();
    }
}
