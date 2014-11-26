package com.arhiser.wedding.managers;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;


/**
 * Created by arkhipov on 26.11.2014.
 */
public class SoundManager {

    private static HashMap<String, Clip> cache = new HashMap<String, Clip>();

    public static void playFile(String file) {
        try {
            if (!cache.containsKey(file)) {
                String gongFile = "sounds/ + file";
                InputStream in = new FileInputStream(gongFile);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(in));
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                cache.put(file, clip);
            }
            Clip clip = cache.get(file);
            clip.setFramePosition(0);
            clip.loop(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playResource(String file) {
        try {
            if (!cache.containsKey(file)) {
                ClassLoader cl= ImageManager.class.getClassLoader();
                InputStream inputStream = cl.getResourceAsStream("resources/sound/" + file);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(inputStream));
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                cache.put(file, clip);
            }
            Clip clip = cache.get(file);
            clip.setFramePosition(0);
            clip.loop(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
