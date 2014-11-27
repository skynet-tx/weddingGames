package com.arhiser.wedding.navigation;

import com.arhiser.wedding.forms.*;
import com.arhiser.wedding.forms.menu.MainMenu;
import com.arhiser.wedding.forms.menu.PrefsMenu;
import com.arhiser.wedding.forms.seabattle.SeaBattle;
import com.arhiser.wedding.forms.seabattle.SeabattlePrefs;
import com.arhiser.wedding.forms.seekvodka.SeekVodka;
import com.arhiser.wedding.forms.seekvodka.SeekVodkaPrefs;

import javax.swing.*;

/**
 * Created by SER on 10.11.2014.
 */
public class NavigationController {

    public static final int SCREEN_MAIN_MENU = 1;
    public static final int SCREEN_SEABATTLE = 2;
    public static final int SCREEN_PREFERENCES = 3;
    public static final int SCREEN_PREFERENCES_SEEBATTLE = 4;
    public static final int SCREEN_PREFERENCES_SEEKVODKA = 5;
    public static final int SCREEN_SEEKVODKA = 6;

    private JFrame host;

    private SeaBattle seaBattle;
    private SeekVodka seekVodka;

    private MainMenu mainMenu;
    private PrefsMenu prefsMenu;
    private SeekVodkaPrefs seekVodkaPrefs;
    private SeabattlePrefs seaPrefs;

    private static NavigationController instance;

    public static void init(JFrame host) {
        instance = new NavigationController(host);
    }

    public static NavigationController getInstance() {
        return instance;
    }

    private NavigationController(JFrame host) {
        this.host = host;
        seaBattle = new SeaBattle();
        seaBattle.init();

        mainMenu = new MainMenu();
        mainMenu.init();

        prefsMenu = new PrefsMenu();
        prefsMenu.init();

        seaPrefs = new SeabattlePrefs();
        seaPrefs.init();

        seekVodka = new SeekVodka();
        seekVodka.init();

        seekVodkaPrefs = new SeekVodkaPrefs();
        seekVodkaPrefs.init();
    }

    public void switchScreen(int formId) {
        ManagedForm form = getScreen(formId);
        host.setContentPane(form.getRootContainer());
        form.onShow();
        host.setTitle(form.getTitle());
        host.getContentPane().revalidate();
        host.getContentPane().repaint();
    }

    private ManagedForm getScreen(int formId) {
        switch (formId) {
            case SCREEN_MAIN_MENU:
                return mainMenu;
            case SCREEN_SEABATTLE:
                return seaBattle;
            case SCREEN_PREFERENCES:
                return prefsMenu;
            case SCREEN_PREFERENCES_SEEBATTLE:
                return seaPrefs;
            case SCREEN_PREFERENCES_SEEKVODKA:
                return seekVodkaPrefs;
            case SCREEN_SEEKVODKA:
                return seekVodka;
        }
        return null;
    }

}
