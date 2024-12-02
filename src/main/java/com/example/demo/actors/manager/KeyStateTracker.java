package com.example.demo.actors.manager;

import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

public class KeyStateTracker {
    private final Map<KeyCode, Boolean> keyStateMap;

    public KeyStateTracker() {
        keyStateMap = new HashMap<>();
    }

    public void keyPressed(KeyCode keyCode) {
        keyStateMap.put(keyCode, true);
    }

    public void keyReleased(KeyCode keyCode) {
        keyStateMap.put(keyCode, false);
    }

    public boolean isKeyPressed(KeyCode keyCode) {
        return keyStateMap.getOrDefault(keyCode, false);
    }
}