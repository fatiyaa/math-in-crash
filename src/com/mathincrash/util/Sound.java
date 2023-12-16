package com.mathincrash.util;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import com.mathincrash.ui.GamePanel;

public class Sound {
    
    public final int maxType = 7;
    public static final int bgmGame = 0;
    // public static final int bgmRace = 1;
    public static final int bgmMIC = 2;
    public static final int sfxClick = 3;
    public static final int sfxPLay = 4;
    public static final int sfxCrash = 5;
    public static final int sfxGameOver = 6;

    public Clip clip;
    private URL[] soundURL;
    FloatControl fc;
    private int currentVol, newVolume;
    float dB = 3.0f;
    
    private GamePanel gp;
    

    public Sound(GamePanel gp) {
        soundURL = new URL[maxType];
            soundURL[bgmGame] = getClass().getResource("/sound/Game.wav");
            soundURL[bgmMIC] = getClass().getResource("/sound/MIC.wav");
            soundURL[sfxClick] = getClass().getResource("/sound/Button Click.wav");
            // soundURL[sfxPLay] = getClass().getResource("/sound/Game.mp3");
            soundURL[sfxCrash] = getClass().getResource("/sound/Crash.wav");
            soundURL[sfxGameOver] = getClass().getResource("/sound/Game Over.wav");
            currentVol = gp.volumeButton.volume;
            newVolume = gp.volumeButton.volume;
            this.gp=gp;
    }

    public void play(int type) {
        this.setFile(type);
        if(!gp.soundButton.active) {
        	clip.start();
        }
    }

    public void playLoop(int type) {
        if (clip != null) clip.stop();
        this.setFile(type);
        if(!gp.soundButton.active) {
        	clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        clip.stop();
    }

    private void setFile(int type) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[type]);
            this.clip = AudioSystem.getClip();
            this.clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            fc.setValue(dB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void update() {
    	newVolume = gp.volumeButton.volume;
    	if(newVolume != currentVol) {
	    	changedB();
	    	fc.setValue(dB);
	    	currentVol = newVolume;
    	}
    	
    }
    
    private void changedB() {
    	if(newVolume == 0) {
    		dB = -80;
    		return;
    	}
    	dB = newVolume*43/100;
    	dB -= 40;
    }
    
}
