package com.arhiser.wedding;

import com.google.gson.Gson;

import java.io.*;

/**
 * Created by SER on 18.11.2014.
 */
public class AppModel {

    private static AppModel instance;

    public String lastFileDir = "" ;

    public SeaBattlePrefs seaBattlePrefs;

    public static void initInstance() {
        File file = new File("appModel.json");
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                instance = new Gson().fromJson(reader, AppModel.class);
                reader.close();
            } catch (IOException e) {
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
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(new Gson().toJson(instance));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AppModel() {
        seaBattlePrefs = new SeaBattlePrefs();
    }

    public class SeaBattlePrefs {
        public String defaultWinMessage = "Поздравляю, вы выиграли!";
        public String defaultImageFile = "";

        public PrizeParam[] prizes = new PrizeParam[10];

        SeaBattlePrefs() {
            resetPrizes();
        }

        public void resetPrizes() {
            for (int i = 0; i < 10; i++){
                prizes[i] = new PrizeParam();
            }
        }
    }

    public class PrizeParam {
        public String prizeName = "Приз";
        public String prizeImageFile = "";
    }
}