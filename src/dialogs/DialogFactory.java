package dialogs;

import javax.swing.*;

/**
 * Created by SER on 11.11.2014.
 */
public class DialogFactory {
    private static DialogFactory instance;

    public static final int DIALOG_EXIT_GAME = 1;

    private JFrame frame;
    private JOptionPane optionPane;

    public static DialogFactory getInstance() {
        if (instance == null) {
            instance = new DialogFactory();
        }
        return instance;
    }

    private DialogFactory() {
        this.frame = new JFrame();
        this.optionPane = new JOptionPane();
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
        }
        return -1;
    }

}
