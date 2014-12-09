package com.arhiser.wedding.forms.seabattle;

import com.arhiser.wedding.AppModel;
import com.arhiser.wedding.dialogs.DialogFactory;
import com.arhiser.wedding.forms.ManagedForm;
import com.arhiser.wedding.managers.ImageManager;
import com.arhiser.wedding.navigation.NavigationController;
import com.arhiser.wedding.utils.StringUtils;
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
    private JButton loadDefaultPicture;
    private JTable picTable;
    private JLabel iconDefault;
    private JButton resetPrizes;
    private JTextField prizeCountField;

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
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);
        picTable.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);

        prizeCountField.setInputVerifier(new PrizeCountInputVerifer());
        prizeCountField.setText(Integer.toString(AppModel.getInstance().seaBattlePrefs.prizeCount));
        prizeCountField.addActionListener(this);

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
        if (e.getSource() == resetPrizes) {
            AppModel.getInstance().seaBattlePrefs.resetPrizes();
            AppModel.save();
            modelToGui();
        }
        if(e.getSource() == prizeCountField) {
            checkPrizeCount();
            KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
        }
    }

    private void modelToGui() {
        AppModel appModel = AppModel.getInstance();
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

    private void checkPrizeCount() {
        String number = prizeCountField.getText();
        int count;
        if (StringUtils.isNumber(number) && (count = Integer.parseInt(number)) <= 17 && count >= 5) {
            AppModel.getInstance().seaBattlePrefs.prizeCount = count;
            AppModel.save();
            tableModel.refresh();
        } else {
            prizeCountField.setText(Integer.toString(AppModel.getInstance().seaBattlePrefs.prizeCount));
        }
    }

    class PrizeCountInputVerifer extends InputVerifier {
        @Override
        public boolean verify(JComponent input) {
            checkPrizeCount();
            return true;
        }
    }

}
