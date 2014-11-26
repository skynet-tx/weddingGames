package games.seabattle;

import com.arhiser.wedding.AppModel;
import com.arhiser.wedding.managers.ImageManager;

import javax.swing.*;

/**
 * Created by SER on 16.11.2014.
 */
public class Prize {
    String name;
    Icon picture;

    public Prize() {
        picture = ImageManager.getImageByResourceName("default_image.png");
        name = "Приз";
    }

    public Icon getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }

    public static Prize[] getPrizes() {
        Prize[] prizes = new Prize[AppModel.getInstance().seaBattlePrefs.prizeCount];
        for(int i = 0; i < prizes.length; i++) {
            Prize prize = new Prize();
            prize.name = AppModel.getInstance().seaBattlePrefs.prizes[i].prizeName;
            if (AppModel.getInstance().seaBattlePrefs.prizes[i].prizeImageFile.length() > 0) {
                prize.picture = ImageManager.getImageByFileName(AppModel.getInstance().seaBattlePrefs.prizes[i].prizeImageFile);
            } else {
                if (AppModel.getInstance().seaBattlePrefs.defaultImageFile.length() > 0) {
                    prize.picture = ImageManager.getImageByFileName(AppModel.getInstance().seaBattlePrefs.defaultImageFile);
                } else {
                    prize.picture = ImageManager.getImageByResourceName("default_image.png");
                }
            }
            prizes[i] = prize;
        }
        return prizes;
    }
}
