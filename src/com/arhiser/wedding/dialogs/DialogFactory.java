package com.arhiser.wedding.dialogs;

import com.arhiser.wedding.AppModel;
import com.arhiser.wedding.Application;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Created by SER on 11.11.2014.
 */
public class DialogFactory {
    private static DialogFactory instance;

    public static final int DIALOG_EXIT_GAME = 1;
    public static final int DIALOG_RESTART_GAME = 2;

    private JFrame frame;
    private JOptionPane optionPane;
    private PrizeDialog prizeDialog;

    public static DialogFactory getInstance() {
        if (instance == null) {
            instance = new DialogFactory();
        }
        return instance;
    }

    private DialogFactory() {
        this.frame = new JFrame();
        this.optionPane = new JOptionPane();
        prizeDialog = new PrizeDialog();
    }

    public int showDialog(int dialog) {
        switch (dialog) {
            case DIALOG_EXIT_GAME:
                return JOptionPane.showOptionDialog(frame,
                        "Игровой процесс будет потерян",
                        "Вернуться назад?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,     //do not use a custom Icon
                        new String[]{"Да", "Нет"},  //the titles of buttons
                        "Да"); //default button title

            case DIALOG_RESTART_GAME:
                return JOptionPane.showOptionDialog(frame,
                        "Игровой процесс будет потерян",
                        "Начать игру заново?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,     //do not use a custom Icon
                        new String[]{"Да", "Нет"},  //the titles of buttons
                        "Да"); //default button title
        }
        return -1;
    }

    public void showPrizeDialog(String name, Icon image) {
        prizeDialog.show(name, image);
    }

    public void showPrizeDialog(int result, String name, Icon image) {
        prizeDialog.show(result, name, image);
    }

    public File showImageFileDialog() {
        JFileChooser chooser = new JFileChooser();
        if (AppModel.getInstance().lastFileDir.length() > 0) {
            chooser.setCurrentDirectory(new File(AppModel.getInstance().lastFileDir));
        }
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Изображение в формате jpg, png", "jpg", "png");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(Application.mainWindow);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File selected = chooser.getSelectedFile();
            AppModel.getInstance().lastFileDir = selected.getAbsolutePath();
            return selected;
        }
        return null;
    }
}
