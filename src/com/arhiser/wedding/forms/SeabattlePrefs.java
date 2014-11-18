package com.arhiser.wedding.forms;

import com.arhiser.wedding.AppModel;
import com.arhiser.wedding.managers.ImageManager;
import com.arhiser.wedding.navigation.NavigationController;
import com.arhiser.wedding.widgets.models.SeaImagePrizeModel;
import com.arhiser.wedding.widgets.stuff.ImagePaintable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SER on 13.11.2014.
 */
public class SeabattlePrefs extends ManagedForm implements ActionListener {
    private JPanel root;
    private JButton back;
    private JTextField defaultWinMessage;
    private JButton loadDefaultPicture;
    private JCheckBox checkDefault;
    private JTable picTable;
    private JLabel iconDefault;

    @Override
    public String getTitle() {
        return "Настройки игры \"Морской бой\"";
    }

    @Override
    public Container getRootContainer() {
        return root;
    }

    @Override
    public void init() {
        back.addActionListener(this);

        ImagePaintable imagePaintable = new ImagePaintable(ImageManager.getImageByResourceName("default_image.png"));
        iconDefault.setIcon(imagePaintable);

        picTable.setModel(new SeaImagePrizeModel());
        picTable.setRowHeight(40);
        picTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        picTable.getColumnModel().getColumn(2).setPreferredWidth(300);
        picTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        picTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment( JLabel.CENTER );
        picTable.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        modelToGui();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            NavigationController.getInstance().switchScreen(NavigationController.SCREEN_PREFERENCES);
            guiToModel();
            AppModel.save();
        }
    }

    private void modelToGui() {
        AppModel appModel = AppModel.getInstance();
        defaultWinMessage.setText(appModel.seaBattlePrefs.defaultWinMessage);

    }

    private void guiToModel() {
        AppModel appModel = AppModel.getInstance();
        appModel.seaBattlePrefs.defaultWinMessage = defaultWinMessage.getText();
    }
}
