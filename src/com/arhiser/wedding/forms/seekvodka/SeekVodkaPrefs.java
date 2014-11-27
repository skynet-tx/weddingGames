package com.arhiser.wedding.forms.seekvodka;

import com.arhiser.wedding.forms.ManagedForm;

import javax.swing.*;
import java.awt.*;

/**
 * Created by SER on 27.11.2014.
 */
public class SeekVodkaPrefs extends ManagedForm {
    private JPanel root;

    @Override
    public String getTitle() {
        return "Настройки игры \"Заначка и пузырь\"";
    }

    @Override
    public Container getRootContainer() {
        return root;
    }
}
