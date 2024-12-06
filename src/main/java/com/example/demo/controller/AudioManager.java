package com.example.demo.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

/**
 * The AudioManager class manages all audio related functionalities within the application.
 * It handles playing, stopping, and managing volume for background music and sound effects.
 * This class follows the Singleton design pattern to ensure there is only one instance managing audio throughout the application.
 */
public class AudioManager {


    /**
     * The single instance of AudioManager, implementing the Singleton pattern to ensure it is the only one created.
     */
    private static AudioManager instance;

    /**
     * MediaPlayer used to play background music across the application.
     */
    private MediaPlayer backgroundMusicPlayer;

    /**
     * MediaPlayer used to play sound effects across the application.
     */
    private MediaPlayer soundEffectPlayer;

    private MediaPlayer clickSoundPlayer;
    /**
     * Flag to indicate whether all sounds should be muted or not.
     */
    private boolean isMuted = false;

    /**
     * Path to the default background music file.
     */
    private static final String BACKGROUND_MUSIC = "/com/example/demo/sounds/bg.mp3";

    /**
     * Private constructor to prevent instantiation from outside the class.
     */

    private double musicVolume = 0.5; // Default volume for background music
    private double explosionEffectVolume = 0.5; // Default volume for sound effects
    private double clickEffectVolume = 0.5;
    private AudioManager() {}

    /**
     * Provides a global access point to the AudioManager instance.
     * @return the single instance of AudioManager
     */
    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    /**
     * Plays the background music continuously.
     * If the music is already playing, it will stop and restart.
     */
    public void playBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
        }

        URL resource = getClass().getResource(BACKGROUND_MUSIC);
        if (resource == null) {
            System.err.println("Error: Audio file not found at " + BACKGROUND_MUSIC);
            return;
        }

        Media backgroundMusic = new Media(resource.toString());
        backgroundMusicPlayer = new MediaPlayer(backgroundMusic);
        backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusicPlayer.setVolume(0.5);
        if (isMuted) backgroundMusicPlayer.setMute(true);
        backgroundMusicPlayer.play();
    }

    /**
     * Stops the background music if it is currently playing.
     */
    public void stopBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
        }
    }

    /**
     * Sets the volume for all audio output.
     * @param volume the volume level between 0.0 (silent) and 1.0 (maximum)
     */
    public void setVolume(double volume) {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setVolume(volume);
        }
        if (soundEffectPlayer != null) {
            soundEffectPlayer.setVolume(volume);
        }
    }

    /**
     * Sets the volume for background music.
     *
     * @param volume Volume level between 0.0 and 1.0.
     */
    public void setBackgroundMusicVolume(double volume) {
        musicVolume = volume;
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setVolume(volume);
            System.out.println("BGM volume:" + volume);
        }
    }

    /**
     * Sets the volume for sound effects.
     *
     * @param volume Volume level between 0.0 and 1.0.
     */
    public void setExplosionEffectsVolume(double volume) {
        explosionEffectVolume = volume;
            System.out.println("Explosion volume:" + volume);
    }

    public void setClickEffectVolume(double volume) {
        clickEffectVolume = volume;
            System.out.println("Click volume:" + volume);
    }

    /**
     * Gets the current volume level for background music.
     *
     * @return The volume level.
     */
    public double getMusicVolume() {
        return musicVolume;
    }

    /**
     * Gets the current volume level for sound effects.
     *
     * @return The volume level.
     */
    public double getExplosionEffectsVolume() {
        return explosionEffectVolume;
    }

    public double getClickEffectVolume() {
        return clickEffectVolume;
    }


    /**
     * Mutes all audio output.
     */
    public void mute() {
        isMuted = true;
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setMute(true);
        }
        if (soundEffectPlayer != null) {
            soundEffectPlayer.setMute(true);
        }
    }

    /**
     * Unmutes all audio output.
     */
    public void unmute() {
        isMuted = false;
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setMute(false);
        }
        if (soundEffectPlayer != null) {
            soundEffectPlayer.setMute(false);
        }
    }

    /**
     * Checks if the audio is currently muted.
     * @return true if muted, false otherwise
     */
    public boolean isMuted() {
        return isMuted;
    }

    /**
     * Stops any currently playing sound effects.
     */
    public void stopSoundEffect() {
        if (soundEffectPlayer != null) {
            soundEffectPlayer.stop();
        }
    }

    /**
     * Plays a specific sound effect.
     */
    public void playSoundEffect() {
        stopSoundEffect(); // Stop the current sound effect if it's playing

        String SOUND_EFFECT_PATH = "/com/example/demo/sounds/explosion.mp3";
        URL resource = getClass().getResource(SOUND_EFFECT_PATH);
        if (resource == null) {
            System.err.println("Error: Sound effect file not found at " + SOUND_EFFECT_PATH);
            return;
        }

        Media soundEffect = new Media(resource.toString());
        soundEffectPlayer = new MediaPlayer(soundEffect);
        soundEffectPlayer.setVolume(explosionEffectVolume); // Use stored volume
        if (isMuted) soundEffectPlayer.setMute(true);
        soundEffectPlayer.play();
    }

    /**
     * Plays the button click sound effect.
     */

    public void playButtonClickSound() {
        String BUTTON_CLICK_EFFECT = "/com/example/demo/sounds/click.mp3";
        URL resource = getClass().getResource(BUTTON_CLICK_EFFECT);
        if (resource == null) {
            System.err.println("Error: Button click sound file not found at " + BUTTON_CLICK_EFFECT);
            return;
        }

        Media clickSound = new Media(resource.toString());
        clickSoundPlayer = new MediaPlayer(clickSound);
        clickSoundPlayer.setVolume(clickEffectVolume); // Use stored volume
        if (isMuted) clickSoundPlayer.setMute(true);
        clickSoundPlayer.play();
    }
}
