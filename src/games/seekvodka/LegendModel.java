package games.seekvodka;

import com.arhiser.wedding.widgets.stuff.ColorIcon;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 * Created by SER on 30.11.2014.
 */
public class LegendModel extends DefaultTableModel {

    RoomsModel model;

    public LegendModel(RoomsModel roomsModel) {
        this.model = roomsModel;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return model == null ? 0 : model.getRooms().length;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "№";
            case 1:
                return "Комната";
            case 2:
                return "Цвет";
        }
        return "";
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
        return false;
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return model.getRooms()[row].name;
            case 2:
                return new ColorIcon(model.getRooms()[row].color);
        }
        return null;
    }

}
