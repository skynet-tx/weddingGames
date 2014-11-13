package forms;

import navigation.NavigationController;
import widgets.models.SeaImagePrizeModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

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

        iconDefault.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("resources/default_image.png")));

        picTable.setModel(new SeaImagePrizeModel());
        picTable.setRowHeight(40);
        picTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        picTable.getColumnModel().getColumn(2).setPreferredWidth(300);
        picTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        picTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment( JLabel.CENTER );
        picTable.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            NavigationController.getInstance().switchScreen(NavigationController.SCREEN_PREFERENCES);
        }
    }
}
