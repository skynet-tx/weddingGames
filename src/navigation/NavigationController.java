package navigation;

import forms.MainMenu;
import forms.ManagedForm;
import forms.SeaBattle;

import javax.swing.*;

/**
 * Created by SER on 10.11.2014.
 */
public class NavigationController {

    public static final int SCREEN_MAIN_MENU = 1;
    public static final int SCREEN_SEABATTLE = 2;

    private JFrame host;

    private SeaBattle seaBattle;
    private MainMenu mainMenu;

    private static NavigationController instace;

    public static void init(JFrame host) {
        instace = new NavigationController(host);
    }

    public static NavigationController getInstance() {
        return instace;
    }

    private NavigationController(JFrame host) {
        this.host = host;
        seaBattle = new SeaBattle();
        seaBattle.init();

        mainMenu = new MainMenu();
        mainMenu.init();
    }

    public void switchScreen(int formId) {
        ManagedForm form = getScreen(formId);
        host.setContentPane(form.getRootContainer());
        host.setTitle(form.getTitle());
        host.getContentPane().revalidate();
        host.getContentPane().repaint();
    }

    public ManagedForm getScreen(int formId) {
        switch (formId) {
            case SCREEN_MAIN_MENU:
                return mainMenu;
            case SCREEN_SEABATTLE:
                return seaBattle;
        }
        return null;
    }

}
