package com.arhiser.wedding.forms.seekvodka;

import com.arhiser.wedding.forms.ManagedForm;
import com.arhiser.wedding.navigation.NavigationController;
import com.arhiser.wedding.widgets.gridview.GridView;
import com.arhiser.wedding.widgets.roomgrid.RoomGridView;
import games.seekvodka.LegendModel;
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
public class SeekVodka extends ManagedForm implements ActionListener {
    private JPanel root;
    private JPanel gameContainer;
    private JButton backToMenu;
    private JTable tableLegend;
    private GridView<RoomsAdapter> roomGridView;

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
        RoomsModel roomsModel = new RoomsModel(RoomsModel.TYPE_LARGE);
        RoomsAdapter roomsAdapter = new RoomsAdapter(roomGridView, roomsModel);

        LegendModel legendModel = new LegendModel(roomsModel);
        tableLegend.setModel(legendModel);

        tableLegend.setRowHeight(40);
        tableLegend.getColumnModel().getColumn(1).setPreferredWidth(300);
        tableLegend.getColumnModel().getColumn(2).setPreferredWidth(40);
        tableLegend.getColumnModel().getColumn(0).setPreferredWidth(40);
        tableLegend.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableLegend.setFont(new Font("Arial", Font.BOLD, 30));
        tableLegend.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);

        roomGridView.setAdapter(roomsAdapter);
        gameContainer.add(roomGridView);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToMenu) {
            NavigationController.getInstance().switchScreen(NavigationController.SCREEN_MAIN_MENU);
        }
    }
}
