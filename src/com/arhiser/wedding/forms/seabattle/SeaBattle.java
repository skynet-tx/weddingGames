package com.arhiser.wedding.forms.seabattle;

import com.arhiser.wedding.dialogs.DialogFactory;
import com.arhiser.wedding.forms.ManagedForm;
import com.arhiser.wedding.managers.SoundManager;
import com.arhiser.wedding.navigation.NavigationController;
import com.arhiser.wedding.widgets.gridview.GridView;
import com.arhiser.wedding.widgets.gridview.GridViewAdapter;
import games.seabattle.Prize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SER on 09.11.2014.
 */
public class SeaBattle extends ManagedForm implements ActionListener, games.seabattle.SeaBattle.SeaBattleListener {
    public JPanel root;
    public JPanel game;
    public JPanel info;
    private JButton back;
    private JButton restartGame;
    private JLabel turns;
    private JLabel prizes;
    public GridView<GridViewAdapter> gridView;

    games.seabattle.SeaBattle seaBattle;

    int turnCount = 0;
    int prizeCount = 0;
    boolean gameInProgress = true;

    @Override
    public void init() {
        gridView = new GridView<GridViewAdapter>();
        game.add(gridView);
        gridView.setBoundColor(new Color(0xffa8a8f0));
        seaBattle = new games.seabattle.SeaBattle();
        seaBattle.setListener(this);
        gridView.setListener(seaBattle);
        gridView.setAdapter(seaBattle);
        back.addActionListener(this);
        restartGame.addActionListener(this);

    }

    @Override
    public String getTitle() {
        return "Морской бой";
    }

    @Override
    public Container getRootContainer() {
        return root;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            if (!gameInProgress || JOptionPane.YES_OPTION == DialogFactory.getInstance().showDialog(DialogFactory.DIALOG_EXIT_GAME)) {
                NavigationController.getInstance().switchScreen(NavigationController.SCREEN_MAIN_MENU);
            }
        }
        if (e.getSource() == restartGame) {
            if (!gameInProgress || JOptionPane.YES_OPTION == DialogFactory.getInstance().showDialog(DialogFactory.DIALOG_RESTART_GAME)) {
                gameRestart();
            }
        }
    }

    @Override
    public void onTurn(Prize prize) {
        turnCount++;
        updateUI();
        if (prize != null) {
            prizeCount++;
            SoundManager.playResource("explosion.wav");
            DialogFactory.getInstance().showPrizeDialog(prize.getName(), prize.getPicture());
        } else {
            SoundManager.playResource("shot.wav");
        }
    }

    @Override
    public void onGameEnds() {
        gameInProgress = false;
    }

    public void gameRestart() {
        seaBattle.generateField(Prize.getPrizes());
        turnCount = 0;
        prizeCount = 0;
        gameInProgress = true;
        updateUI();
        gridView.repaint();
    }

    private void updateUI() {
        turns.setText("Ходов: " + turnCount);
        prizes.setText("Сбито кораблей: " + prizeCount);
    }

    @Override
    public void onShow() {
        gameRestart();
    }
}
