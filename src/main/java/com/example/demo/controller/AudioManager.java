package com.example.demo.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class AudioManager {

    private static AudioManager instance; // Singleton instance
    private MediaPlayer backgroundMusicPlayer;
    private boolean isMuted = false;
    private final String SOUNDS_EFFECT= "/com/example/demo/sounds/explosion.mp3";

    // Private constructor to prevent instantiation
    private AudioManager() {}

    /**
     * Gets the singleton instance of the {@code AudioManager}.
     *
     * @return The singleton instance.
     */
    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    public void playBackgroundMusic(String audioFilePath) {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
        }

        URL resource = getClass().getResource(audioFilePath);
        if (resource == null) {
            System.err.println("Error: Audio file not found at " + audioFilePath);
            return;
        }

        Media backgroundMusic = new Media(resource.toString());
        backgroundMusicPlayer = new MediaPlayer(backgroundMusic);
        backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusicPlayer.setVolume(0.5);
        backgroundMusicPlayer.play();
    }

    public void stopBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
        }
    }

    public void setVolume(double volume) {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setVolume(volume);
        }
    }

    public void mute() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setMute(true);
            isMuted = true;
        }
    }

    public void unmute() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setMute(false);
            isMuted = false;
        }
    }

    public boolean isMuted() {
        return isMuted;
    }

    /**
     * Plays a sound effect.
     */
    public void playSoundEffect() {
        URL resource = getClass().getResource(SOUNDS_EFFECT);
        if (resource == null) {
            System.err.println("Error: Sound effect file not found at " + SOUNDS_EFFECT);
            return;
        }

        Media soundEffect = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(soundEffect);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();
    }
}