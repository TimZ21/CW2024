package com.example.demo.manager;

import com.example.demo.manager.KeyStateTracker;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KeyStateTrackerTest {
    private KeyStateTracker keyStateTracker;

    @BeforeEach
    void setUp() {
        keyStateTracker = new KeyStateTracker();
    }

    @Test
    void testKeyInitialState() {
        // Test that keys are not pressed initially
        assertFalse(keyStateTracker.isKeyPressed(KeyCode.A), "Key A should not be pressed initially.");
    }

    @Test
    void testKeyPressing() {
        // Simulate pressing a key
        keyStateTracker.keyPressed(KeyCode.A);
        assertTrue(keyStateTracker.isKeyPressed(KeyCode.A), "Key A should be marked as pressed.");

        // Check another key to ensure it's not affected
        assertFalse(keyStateTracker.isKeyPressed(KeyCode.B), "Key B should not be affected by pressing Key A.");
    }

    @Test
    void testKeyReleasing() {
        // Simulate pressing and then releasing a key
        keyStateTracker.keyPressed(KeyCode.A);
        keyStateTracker.keyReleased(KeyCode.A);
        assertFalse(keyStateTracker.isKeyPressed(KeyCode.A), "Key A should be marked as not pressed after being released.");
    }

    @Test
    void testMultipleKeys() {
        // Test multiple keys to ensure individual tracking
        keyStateTracker.keyPressed(KeyCode.A);
        keyStateTracker.keyPressed(KeyCode.B);
        assertTrue(keyStateTracker.isKeyPressed(KeyCode.A), "Key A should be pressed.");
        assertTrue(keyStateTracker.isKeyPressed(KeyCode.B), "Key B should be pressed.");

        keyStateTracker.keyReleased(KeyCode.A);
        assertFalse(keyStateTracker.isKeyPressed(KeyCode.A), "Key A should be released.");
        assertTrue(keyStateTracker.isKeyPressed(KeyCode.B), "Key B should still be pressed.");
    }

    @Test
    void testNullKeyHandling() {
        // Test handling of null keys
        assertDoesNotThrow(() -> keyStateTracker.keyPressed(null), "Passing null to keyPressed should not throw an exception.");
        assertDoesNotThrow(() -> keyStateTracker.keyReleased(null), "Passing null to keyReleased should not throw an exception.");
        assertFalse(keyStateTracker.isKeyPressed(null), "Checking a null key should return false and not throw an exception.");
    }
}
