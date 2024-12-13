package com.example.demo.manager;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

/**
 * The AudioManager class manages all audio related functionalities within the application.
 * It handles playing, stopping, and managing volume for background music and sound effects.
 * This class follows the Singleton design pattern to ensure there is only one instance managing audio throughout the application.
 *
 * <p>
 * See the source code at <a href="https://github.com/TimZ21/CW2024/blob/master/src/main/java/com/example/demo/manager/AudioManager.java">AudioManager.java</a>
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
     * MediaPlayer used to play explosion sound effects across the application.
     */
    private MediaPlayer soundEffectPlayer;
    /**
     * MediaPlayer used to play button click sound effects across the application.
     */
    private MediaPlayer clickSoundPlayer;
    /**
     * MediaPlayer used to play user shoot sound effects across the application.
     */
    private MediaPlayer userShootPlayer;
    /**
     * MediaPlayer used to boss user shoot sound effects across the application.
     */
    private MediaPlayer bossShootPlayer;
    /**
     * MediaPlayer used to play win menu sound effects across the application.
     */
    private MediaPlayer winEffectPlayer;
    /**
     * MediaPlayer used to play lose menu sound effects across the application.
     */
    private MediaPlayer loseEffectPlayer;
    /**
     * MediaPlayer used to play shield activate sound effects across the application.
     */
    private MediaPlayer shieldEffectPlayer;

    /**
     * Flag to indicate whether all sounds should be muted or not.
     */
    private boolean isMuted = false;

    /**
     * Path to the default background music file.
     */
    private static final String BACKGROUND_MUSIC = "/com/example/demo/sounds/bg.mp3";

    /**
     * The volume level for background music.
     * This field controls the volume for all background music within the application,
     * ranging from 0.0 (silent) to 1.0 (maximum volume).
     */
    private double musicVolume = 0.5; // Default volume for background music

    /**
     * The volume level for explosion sound effects.
     * Controls the volume of explosion-related audio effects,
     * with a range from 0.0 (silent) to 1.0 (maximum volume).
     */
    private double explosionEffectVolume = 0.5; // Default volume for sound effects

    /**
     * The volume level for click sound effects.
     * This adjusts the volume for UI click sounds within the application,
     * ranging from 0.0 (silent) to 1.0 (maximum volume).
     */
    private double clickEffectVolume = 0.5;

    /**
     * The volume level for user shoot sound effects.
     * Controls the volume of the sound effects generated when the user's character
     * or vehicle fires a weapon, with a volume range from 0.0 (silent) to 1.0 (full volume).
     */
    private double userShootEffectVolume = 0.5;

    /**
     * The volume level for boss shoot sound effects.
     * Controls the volume of sound effects associated with boss characters firing weapons,
     * with a range from 0.0 (silent) to 1.0 (maximum volume).
     */
    private double bossShootEffectVolume = 0.5;

    /**
     * The volume level for win sound effects.
     * This volume control affects the audio played during a win condition in the game,
     * with settings ranging from 0.0 (silent) to 1.0 (maximum volume).
     */
    private double winEffectVolume = 0.5;

    /**
     * The volume level for lose sound effects.
     * Controls the volume of audio played when the player loses or fails a level,
     * ranging from 0.0 (silent) to 1.0 (maximum volume).
     */
    private double loseEffectVolume = 0.5;

    /**
     * The volume level for shield activate sound effects.
     * Controls the volume of audio played when the player loses or fails a level,
     * ranging from 0.0 (silent) to 1.0 (maximum volume).
     */
    private double shieldEffectVolume = 0.5;


    /**
     * Private constructor to prevent instantiation from outside the class.
     */

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
        backgroundMusicPlayer.setVolume(musicVolume);
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
     * Pauses the background music if it is currently playing.
     */
    public void pauseBackgroundMusic() {
        if (backgroundMusicPlayer != null && backgroundMusicPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            backgroundMusicPlayer.pause();
        }
    }

    /**
     * Resumes the background music if it has been paused.
     */
    public void resumeBackgroundMusic() {
        if (backgroundMusicPlayer != null && backgroundMusicPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
            backgroundMusicPlayer.play();
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
     * Sets the volume for explosion sound effects.
     *
     * @param volume Volume level between 0.0 and 1.0.
     */
    public void setExplosionEffectsVolume(double volume) {
        explosionEffectVolume = volume;
            System.out.println("Explosion volume:" + volume);
    }

    /**
     * Sets the volume for button click sound effects.
     *
     * @param volume Volume level between 0.0 and 1.0.
     */
    public void setClickEffectVolume(double volume) {
        clickEffectVolume = volume;
            System.out.println("Click volume:" + volume);
    }

    /**
     * Sets the volume for user shoot sound effects.
     *
     * @param volume Volume level between 0.0 and 1.0.
     */
    public void setUserShootEffectVolume(double volume) {
        userShootEffectVolume = volume;
        System.out.println("User Shoot volume:" + volume);
    }

    /**
     * Sets the volume for boss shoot sound effects.
     *
     * @param volume Volume level between 0.0 and 1.0.
     */
    public void setBossShootEffectVolume(double volume) {
        bossShootEffectVolume = volume;
        System.out.println("Boss Shoot volume:" + volume);
    }

    /**
     * Sets the volume for win game sound effects.
     *
     * @param volume Volume level between 0.0 and 1.0.
     */
    public void setWinEffectVolume(double volume) {
        winEffectVolume = volume;
        System.out.println("Win Effect volume:" + volume);
    }

    /**
     * Sets the volume for lose game sound effects.
     *
     * @param volume Volume level between 0.0 and 1.0.
     */
    public void setLoseEffectVolume(double volume) {
        loseEffectVolume = volume;
        System.out.println("Win Effect volume:" + volume);
    }

    /**
     * Sets the volume for shield sound effects.
     *
     * @param volume Volume level between 0.0 and 1.0.
     */
    public void setShieldEffectVolume(double volume) {
        shieldEffectVolume = volume;
        System.out.println("Shield Effect volume:" + volume);
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
     * Gets the current volume for explosion sound effects.
     *
     * @return The explosionEffectVolume.
     */
    public double getExplosionEffectsVolume() {
        return explosionEffectVolume;
    }

    /**
     * Gets the current volume for button click sound effects.
     *
     * @return The clickEffectVolume.
     */
    public double getClickEffectVolume() {
        return clickEffectVolume;
    }

    /**
     * Gets the current volume for user shoot sound effects.
     *
     * @return The userShootEffectVolume.
     */
    public double getUserShootEffectVolume() {
        return userShootEffectVolume;
    }

    /**
     * Gets the current volume for boss shoot sound effects.
     *
     * @return The bossShootEffectVolume.
     */
    public double getBossShootEffectVolume() {
        return bossShootEffectVolume;
    }

    /**
     * Gets the current volume for win game sound effects.
     *
     * @return The winEffectVolume.
     */
    public double getWinEffectVolume() {
        return winEffectVolume;
    }

    /**
     * Gets the current volume for lose game sound effects.
     *
     * @return The loseEffectVolume.
     */
    public double getLoseEffectVolume() {
        return loseEffectVolume;
    }

    /**
     * Gets the current volume for shield activate sound effects.
     *
     * @return The loseEffectVolume.
     */
    public double getShieldEffectVolume() {
        return loseEffectVolume;
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
     * Stops currently playing explosion sound effects.
     */
    public void stopSoundEffect() {
        if (soundEffectPlayer != null) {
            soundEffectPlayer.stop();
        }
    }

    /**
     * Stops currently playing user shooting sound effects.
     */
    public void stopUserShootEffect() {
        if (userShootPlayer != null) {
            userShootPlayer.stop();
        }
    }

    /**
     * Stops currently playing boss shooting sound effects.
     */
    public void stopBossShootEffect() {
        if (bossShootPlayer != null) {
            bossShootPlayer.stop();
        }
    }

    /**
     * Plays a specific sound effect.
     */
    public void playExplosionEffect() {
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

    public void playButtonClickEffect() {
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

    /**
     * Plays the user fire projectile sound effect.
     */
    public void playUserShootEffect() {
        stopUserShootEffect();
        String USER_SHOOT_EFFECT = "/com/example/demo/sounds/userShoot.mp3";
        URL resource = getClass().getResource(USER_SHOOT_EFFECT);
        if (resource == null) {
            System.err.println("Error: Button click sound file not found at " + USER_SHOOT_EFFECT);
            return;
        }

        Media userShoot = new Media(resource.toString());
        userShootPlayer = new MediaPlayer(userShoot);
        userShootPlayer.setVolume(userShootEffectVolume); // Use stored volume
        if (isMuted) userShootPlayer.setMute(true);
        userShootPlayer.play();
    }

    /**
     * Plays the boss fire projectile sound effect.
     */
    public void playBossShootEffect() {
        stopBossShootEffect();
        String BOSS_SHOOT_EFFECT = "/com/example/demo/sounds/bossShoot.mp3";
        URL resource = getClass().getResource(BOSS_SHOOT_EFFECT);
        if (resource == null) {
            System.err.println("Error: Button click sound file not found at " + BOSS_SHOOT_EFFECT);
            return;
        }

        Media bossShoot = new Media(resource.toString());
        bossShootPlayer = new MediaPlayer(bossShoot);
        bossShootPlayer.setVolume(bossShootEffectVolume); // Use stored volume
        if (isMuted) bossShootPlayer.setMute(true);
        bossShootPlayer.play();
    }

    /**
     * Plays the game win sound effect.
     */
    public void playWinEffect() {
        stopBackgroundMusic();
        String WIN_EFFECT = "/com/example/demo/sounds/win.mp3";
        URL resource = getClass().getResource(WIN_EFFECT);
        if (resource == null) {
            System.err.println("Error: Button click sound file not found at " + WIN_EFFECT);
            return;
        }

        Media winEffect = new Media(resource.toString());
        winEffectPlayer = new MediaPlayer(winEffect);
        winEffectPlayer.setVolume(winEffectVolume); // Use stored volume
        if (isMuted) winEffectPlayer.setMute(true);
        winEffectPlayer.play();
    }

    /**
     * Plays the game lose sound effect.
     */
    public void playLoseEffect() {
        stopBackgroundMusic();
        String LOSE_EFFECT = "/com/example/demo/sounds/lose.mp3";
        URL resource = getClass().getResource(LOSE_EFFECT);
        if (resource == null) {
            System.err.println("Error: Button click sound file not found at " + LOSE_EFFECT);
            return;
        }

        Media loseEffect = new Media(resource.toString());
        loseEffectPlayer = new MediaPlayer(loseEffect);
        loseEffectPlayer.setVolume(loseEffectVolume); // Use stored volume
        if (isMuted) loseEffectPlayer.setMute(true);
        loseEffectPlayer.play();
    }

    /**
     * Plays the game lose sound effect.
     */
    public void playShieldEffect() {
        String SHIELD_EFFECT = "/com/example/demo/sounds/shield.mp3";
        URL resource = getClass().getResource(SHIELD_EFFECT);
        if (resource == null) {
            System.err.println("Error: Button click sound file not found at " + SHIELD_EFFECT);
            return;
        }

        Media shieldEffect = new Media(resource.toString());
        shieldEffectPlayer = new MediaPlayer(shieldEffect);
        shieldEffectPlayer.setVolume(shieldEffectVolume); // Use stored volume
        if (isMuted) shieldEffectPlayer.setMute(true);
        shieldEffectPlayer.play();
    }
}
