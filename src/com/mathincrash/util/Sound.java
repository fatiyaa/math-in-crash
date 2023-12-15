package com.mathincrash.util;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    
    public final int maxType = 7;
    public static final int bgmGame = 0;
    // public static final int bgmRace = 1;
    public static final int bgmMIC = 2;
    public static final int sfxClick = 3;
    public static final int sfxPLay = 4;
    public static final int sfxCrash = 5;
    public static final int sfxGameOver = 6;

    private Clip clip;
    private URL[] soundURL;

    public Sound() {
        soundURL = new URL[maxType];
            soundURL[bgmGame] = getClass().getResource("/sound/Game.wav");
            soundURL[bgmMIC] = getClass().getResource("/sound/MIC.wav");
            soundURL[sfxClick] = getClass().getResource("/sound/Button Click.wav");
            // soundURL[sfxPLay] = getClass().getResource("/sound/Game.mp3");
            soundURL[sfxCrash] = getClass().getResource("/sound/Crash.wav");
            soundURL[sfxGameOver] = getClass().getResource("/sound/Game Over.wav");
    }

    public void play(int type) {
        this.setFile(type);
        clip.start();
    }

    public void playLoop(int type) {
        if (clip != null) clip.stop();
        this.setFile(type);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    private void setFile(int type) {
        try {
            URL url = soundURL[type];
            System.out.println("Sound file URL: " + url);

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[type]);
            this.clip = AudioSystem.getClip();
            this.clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
