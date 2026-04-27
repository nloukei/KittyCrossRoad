package it123;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class RunMusic {
    private static Clip clip;
    private static Clip gamesound;
    

    public static void MainSound(String path) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop continuously
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public static void GameSound() {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("Sounds/gamesound.wav"));
            gamesound = AudioSystem.getClip();
            gamesound.open(inputStream);
            gamesound.loop(Clip.LOOP_CONTINUOUSLY); // Loop continuously
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    public static void stopGameSound() {
        if (gamesound != null && gamesound.isRunning()) {
        	gamesound.stop();
        	
        	
        }
    }
    	public static void resumeGameSound() {
        if (gamesound != null && !gamesound.isRunning()) {
        	gamesound.start();
        }
    }



    public static void SoundNoLoop(String path) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start(); // Start playing the audio
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void stopMainSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

    
    
}