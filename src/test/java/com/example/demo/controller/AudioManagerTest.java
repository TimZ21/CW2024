package com.example.demo.controller;

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

}
