package com.example.demo.manager;

import com.example.demo.manager.AudioManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AudioManagerTest {

    @Test
    void testSingletonInstance() {
        AudioManager firstInstance = AudioManager.getInstance();
        AudioManager secondInstance = AudioManager.getInstance();
        assertSame(firstInstance, secondInstance, "Both instances should be the same");
    }

    @Test
    void testMuteAndUnmute() {
        AudioManager audioManager = AudioManager.getInstance();

        // Mute and check if the system is muted
        audioManager.mute();
        assertTrue(audioManager.isMuted(), "Audio should be muted");

        // Unmute and check if the system is unmuted
        audioManager.unmute();
        assertFalse(audioManager.isMuted(), "Audio should be unmuted");
    }

    @Test
    void testSetBackgroundMusicVolume() {
        AudioManager audioManager = AudioManager.getInstance();
        double volume = 65;
        audioManager.setBackgroundMusicVolume(volume);
        assertEquals(audioManager.getMusicVolume(), volume);
    }

    @Test
    void testSetExplosionEffectsVolume() {
        AudioManager audioManager = AudioManager.getInstance();
        double volume = 65;
        audioManager.setExplosionEffectsVolume(volume);
        assertEquals(audioManager.getExplosionEffectsVolume(), volume);
    }

    @Test
    void testSetClickEffectVolume() {
        AudioManager audioManager = AudioManager.getInstance();
        double volume = 65;
        audioManager.setClickEffectVolume(volume);
        assertEquals(audioManager.getClickEffectVolume(), volume);
    }

    @Test
    void testSetUserShootEffectVolume() {
        AudioManager audioManager = AudioManager.getInstance();
        double volume = 65;
        audioManager.setUserShootEffectVolume(volume);
        assertEquals(audioManager.getUserShootEffectVolume(), volume);
    }

    @Test
    void testSetBossShootEffectVolume() {
        AudioManager audioManager = AudioManager.getInstance();
        double volume = 65;
        audioManager.setBossShootEffectVolume(volume);
        assertEquals(audioManager.getBossShootEffectVolume(), volume);
    }

    @Test
    void testSetWinEffectVolume() {
        AudioManager audioManager = AudioManager.getInstance();
        double volume = 65;
        audioManager.setWinEffectVolume(volume);
        assertEquals(audioManager.getWinEffectVolume(), volume);
    }

    @Test
    void testSetLoseEffectVolume() {
        AudioManager audioManager = AudioManager.getInstance();
        double volume = 65;
        audioManager.setLoseEffectVolume(volume);
        assertEquals(audioManager.getLoseEffectVolume(), volume);
    }

}
