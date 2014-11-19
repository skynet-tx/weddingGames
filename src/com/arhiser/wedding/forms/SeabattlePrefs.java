package com.arhiser.wedding.forms;

import com.arhiser.wedding.AppModel;
import com.arhiser.wedding.dialogs.DialogFactory;
import com.arhiser.wedding.managers.ImageManager;
import com.arhiser.wedding.navigation.NavigationController;
import com.arhiser.wedding.widgets.models.SeaImagePrizeModel;
import com.arhiser.wedding.widgets.stuff.ImagePaintable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
    private JButton resetPrizes;

    private SeaImagePrizeModel tableModel;

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
        loadDefaultPicture.addActionListener(this);
        resetPrizes.addActionListener(this);

        tableModel = new SeaImagePrizeModel(picTable);
        picTable.setModel(tableModel);
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
        if (e.getSource() == loadDefaultPicture) {
            loadDefaultImage();
        }
        if(e.getSource() == resetPrizes) {
            AppModel.getInstance().seaBattlePrefs.resetPrizes();
            AppModel.save();
            modelToGui();
        }
    }

    private void modelToGui() {
        AppModel appModel = AppModel.getInstance();
        defaultWinMessage.setText(appModel.seaBattlePrefs.defaultWinMessage);
        if (appModel.seaBattlePrefs.defaultImageFile.length() == 0) {
            ImagePaintable imagePaintable = new ImagePaintable(ImageManager.getImageByResourceName("default_image.png"));
            iconDefault.setIcon(imagePaintable);
        } else {
            ImagePaintable imagePaintable = new ImagePaintable(ImageManager.getImageByFileName(appModel.seaBattlePrefs.defaultImageFile));
            iconDefault.setIcon(imagePaintable);
        }
        tableModel.fireTableDataChanged();
    }

    private void guiToModel() {
        AppModel appModel = AppModel.getInstance();
        appModel.seaBattlePrefs.defaultWinMessage = defaultWinMessage.getText();
    }

    private void loadDefaultImage() {
        File file = DialogFactory.getInstance().showImageFileDialog();
        if (file != null) {
            ImageManager.storeImage(file);
            AppModel appModel = AppModel.getInstance();
            if (appModel.seaBattlePrefs.defaultImageFile.length() == 0) {
                new File(ImageManager.IMAGES_DIR + appModel.seaBattlePrefs.defaultImageFile).delete();
            }
            appModel.seaBattlePrefs.defaultImageFile = file.getName();
            AppModel.save();
            modelToGui();
        }
    }

}
