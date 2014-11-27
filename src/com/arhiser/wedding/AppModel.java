package com.arhiser.wedding;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * Created by SER on 18.11.2014.
 */
public class AppModel implements Serializable {

    private static AppModel instance;

    public String lastFileDir = "" ;

    public SeaBattlePrefs seaBattlePrefs;

    public static void initInstance() {
        File file = new File("appModel.json");
        if (file.exists()) {
            try {
                /*
                BufferedReader reader = new BufferedReader(new FileReader(file));
                instance = new Gson().fromJson(reader, AppModel.class);
                reader.close();
                */
                InputStream fileis = new FileInputStream(file);
                InputStream buffer = new BufferedInputStream(fileis);
                ObjectInput input = new ObjectInputStream (buffer);
                instance = (AppModel)input.readObject();
                fileis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            instance = new AppModel();
        }
    }

    public static AppModel getInstance() {
        if (instance != null) {
            return instance;
        }
        throw  new IllegalStateException("com.arhiser.wedding.AppModel is requested but not initialized");
    }

    public static void save() {
        File file = new File("appModel.json");
        if (file.exists()) {
            file.delete();
        }
        try {
            /*
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(new Gson().toJson(instance));
            writer.flush();
            writer.close();
            */
            OutputStream fileos = new FileOutputStream(file);
            OutputStream buffer = new BufferedOutputStream(fileos);
            ObjectOutput output = new ObjectOutputStream(buffer);
            output.writeObject(instance);
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AppModel() {
        seaBattlePrefs = new SeaBattlePrefs();
    }

    public class SeaBattlePrefs implements Serializable {
        public String defaultWinMessage = "Поздравляю, вы выиграли!";
        public String defaultImageFile = "";
        public int prizeCount;
        public PrizeParam[] prizes = new PrizeParam[10];

        SeaBattlePrefs() {
            prizeCount = 10;
            resetPrizes();
        }

        public void resetPrizes() {
            for (int i = 0; i < 10; i++){
                prizes[i] = new PrizeParam();
            }
        }
    }

    public class PrizeParam implements Serializable {
        public String prizeName = "Приз";
        public String prizeImageFile = "";
    }
}