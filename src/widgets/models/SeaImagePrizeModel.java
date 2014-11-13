package widgets.models;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.net.URL;

/**
 * Created by SER on 13.11.2014.
 */
public class SeaImagePrizeModel extends DefaultTableModel {
    private String[] columns = new String[]{"№", "Текст", "Иконка"};

    private Icon defaultIcon;

    public SeaImagePrizeModel() {
        ClassLoader cl= this.getClass().getClassLoader();
        URL imageURL  = cl.getResource("resources/default_image.png");
        defaultIcon = new ImageIcon(imageURL);
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
                return rowIndex;
            case 1:
                return "Выигрыш";
            case 2:
                return defaultIcon;
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
}
