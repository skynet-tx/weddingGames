package com.arhiser.wedding.widgets.models;

import com.arhiser.wedding.AppModel;
import com.arhiser.wedding.dialogs.DialogFactory;
import com.arhiser.wedding.managers.ImageManager;
import com.arhiser.wedding.widgets.stuff.ImagePaintable;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

/**
 * Created by SER on 13.11.2014.
 */
public class SeaImagePrizeModel extends DefaultTableModel implements CellEditorListener, MouseListener {
    private String[] columns = new String[]{"№", "Текст", "Иконка"};

    private JTable host;

    public SeaImagePrizeModel(JTable host) {
        this.host = host;
        host.addMouseListener(this);
        host.getDefaultEditor(String.class).addCellEditorListener(this);
    }

    @Override
    public int getRowCount() {
        return 10;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return AppModel.getInstance().seaBattlePrefs.prizes[rowIndex].prizeName;
            case 2:

            if (AppModel.getInstance().seaBattlePrefs.prizes[rowIndex].prizeImageFile.length() > 0) {
                return new ImagePaintable(ImageManager.getImageByFileName(AppModel.getInstance().seaBattlePrefs.prizes[rowIndex].prizeImageFile));
            } else {
                if (AppModel.getInstance().seaBattlePrefs.defaultImageFile.length() > 0) {
                    return new ImagePaintable(ImageManager.getImageByFileName(AppModel.getInstance().seaBattlePrefs.defaultImageFile));
                } else {
                    return new ImagePaintable(ImageManager.getImageByResourceName("default_image.png"));
                }
            }
        }

        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Icon.class;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column > 0;
    }

    @Override
    public void editingStopped(ChangeEvent e) {
        String text = (String) host.getDefaultEditor(String.class).getCellEditorValue();
        AppModel.getInstance().seaBattlePrefs.prizes[host.getSelectedRow()].prizeName = text;
        AppModel.save();
    }

    @Override
    public void editingCanceled(ChangeEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int selected;
        if (host.getSelectedColumn() == 2) {
            selected = host.getSelectedRow();
            File file = DialogFactory.getInstance().showImageFileDialog();
            if (file != null) {
                ImageManager.storeImage(file);
                AppModel.getInstance().seaBattlePrefs.prizes[selected].prizeImageFile = file.getName();
                AppModel.save();
                fireTableDataChanged();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
