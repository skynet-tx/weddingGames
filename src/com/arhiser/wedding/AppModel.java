package com.arhiser.wedding;

import games.seekvodka.Room;
import games.seekvodka.RoomsModel;
import sun.misc.IOUtils;

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
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Created by SER on 18.11.2014.
 */
public class AppModel implements Serializable {

    public static final int PRIZE_COUNT = 17;

    private static AppModel instance;

    public String lastFileDir = "" ;

    public SeaBattlePrefs seaBattlePrefs;
    public SeekVodkaPrefs seekVodkaPrefs;

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

        File roomImgDir = new File("room_img");
        if (!roomImgDir.exists()) {
            roomImgDir.mkdir();
            ClassLoader cl= AppModel.class.getClassLoader();
            ArrayList<String> fileNames = new ArrayList<String>();
            String fileName;
            for(String room: Room.ROOMS) {
                for(int i = 0; i < Room.cellsByRoom(room); i++) {
                    fileName = room + "_" + (i + 1) + ".png";
                    fileNames.add("resources/placeholders/" + fileName);
                }
            }
            fileNames.add("resources/placeholders/puzir.png");
            fileNames.add("resources/placeholders/zanachka.png");
            fileNames.add("resources/placeholders/collision.png");
            for(String resourcePath: fileNames) {
                BufferedInputStream is = new BufferedInputStream(cl.getResourceAsStream(resourcePath));
                File name = new File(resourcePath);
                File outputFile = new File("room_img/" + name.getName());
                try {
                    Files.copy(is, outputFile.toPath());
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
        seekVodkaPrefs = new SeekVodkaPrefs();
    }

    public class SeaBattlePrefs implements Serializable {
        public String defaultWinMessage = "Поздравляю, вы выиграли!";
        public String defaultImageFile = "";
        public int prizeCount;
        public PrizeParam[] prizes = new PrizeParam[PRIZE_COUNT];

        SeaBattlePrefs() {
            prizeCount = 10;
            resetPrizes();
        }

        public void resetPrizes() {
            for (int i = 0; i < PRIZE_COUNT; i++){
                prizes[i] = new PrizeParam();
            }
        }
    }

    public class PrizeParam implements Serializable {
        public String prizeName = "Приз";
        public String prizeImageFile = "";
    }

    public class SeekVodkaPrefs implements Serializable {
        public int roomType;
        public int moneyX;
        public int moneyY;
        public int vodkaX;
        public int vodkaY;

        public SeekVodkaPrefs() {
            roomType = RoomsModel.TYPE_SMALL;
            moneyX = -1;
            moneyY = -1;
            vodkaX = -1;
            vodkaY = -1;
        }

        public boolean isCellVodka(int x, int y) {
            return x == vodkaX && y == vodkaY;
        }

        public boolean isCellMoney(int x, int y) {
            return x == moneyX && y == moneyY;
        }

        public boolean isCellCollision(int x, int y) {
            return isCellVodka(x, y) && isCellMoney(x, y);
        }

        public boolean isCellOccuped(int x, int y) {
            return isCellVodka(x, y) || isCellMoney(x, y);
        }
    }
}