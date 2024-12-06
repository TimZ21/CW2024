package com.example.demo.actors.manager;

import javafx.scene.input.KeyCode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code KeyStateTracker} tracks the state of keyboard keys.
 * Allows registering key presses and releases to track which keys are currently pressed.
 * Optimize the game experience when multiple keys are pressed together.
 */
public class KeyStateTracker {
    /**
     * Stores the state of keys with true indicating pressed and false indicating released.
     */
    private final Map<KeyCode, Boolean> keyStateMap;

    /**
     * Constructs a new KeyStateTracker initializing the key state map.
     */
    public KeyStateTracker() {
        keyStateMap = Collections.synchronizedMap(new HashMap<>()); // Makes the map thread-safe
    }

    /**
     * Registers that a key has been pressed.
     *
     * @param keyCode The key code of the key that was pressed.
     */
    public void keyPressed(KeyCode keyCode) {
        if (keyCode != null) {
            keyStateMap.put(keyCode, true);
        }
    }

    /**
     * Registers that a key has been released.
     *
     * @param keyCode The key code of the key that was released.
     */
    public void keyReleased(KeyCode keyCode) {
        if (keyCode != null) {
            keyStateMap.put(keyCode, false);
        }
    }

    /**
     * Checks if a specific key is pressed.
     *
     * @param keyCode The key code of the key to check.
     * @return true if the key is currently pressed, false otherwise.
     */
    public boolean isKeyPressed(KeyCode keyCode) {
        return keyCode != null && keyStateMap.getOrDefault(keyCode, false);
    }
}
