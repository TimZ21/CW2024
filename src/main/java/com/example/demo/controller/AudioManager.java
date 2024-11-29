package com.example.demo.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class AudioManager {

    private static AudioManager instance; // Singleton instance
    private MediaPlayer backgroundMusicPlayer;
    private MediaPlayer soundEffectPlayer;
    private boolean isMuted = false;
    private final String SOUND_EFFECT_PATH = "/com/example/demo/sounds/explosion.mp3";

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
        if (isMuted) backgroundMusicPlayer.setMute(true);
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
        if (soundEffectPlayer != null) {
            soundEffectPlayer.setVolume(volume);
        }
    }

    public void mute() {
        isMuted = true;
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setMute(true);
        }
        if (soundEffectPlayer != null) {
            soundEffectPlayer.setMute(true);
        }
    }

    public void unmute() {
        isMuted = false;
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setMute(false);
        }
        if (soundEffectPlayer != null) {
            soundEffectPlayer.setMute(false);
        }
    }

    public boolean isMuted() {
        return isMuted;
    }

    /**
     * Stops the currently playing sound effect.
     */
    public void stopSoundEffect() {
        if (soundEffectPlayer != null) {
            soundEffectPlayer.stop();
        }
    }

    /**
     * Plays a sound effect.
     */
    public void playSoundEffect() {
        stopSoundEffect(); // Stop the current sound effect if it's playing

        URL resource = getClass().getResource(SOUND_EFFECT_PATH);
        if (resource == null) {
            System.err.println("Error: Sound effect file not found at " + SOUND_EFFECT_PATH);
            return;
        }

        Media soundEffect = new Media(resource.toString());
        soundEffectPlayer = new MediaPlayer(soundEffect);
        soundEffectPlayer.setVolume(0.5);
        if (isMuted) soundEffectPlayer.setMute(true);
        soundEffectPlayer.play();
    }
}
