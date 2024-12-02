package com.example.demo.view;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LevelViewTest {
    private Group root;
    private LevelView levelView;

    @BeforeEach
    void setUp() {
        // Initialize JavaFX environment
        new JFXPanel();

        root = new Group();
        levelView = new LevelView(root, 3);
    }

    @Test
    void testInitialization() {
        assertNotNull(levelView, "LevelView should be initialized");
        assertNotNull(levelView.getHeartDisplay(), "HeartDisplay should be initialized");
    }

    @Test
    void testShowHeartDisplay() {
        levelView.showHeartDisplay();
        assertTrue(root.getChildren().contains(levelView.getHeartDisplay().getContainer()), "HeartDisplay should be added to the root group");
    }

    @Test
    void testRemoveHearts() {
        levelView.showHeartDisplay();
        levelView.removeHearts(2);
        assertEquals(2, levelView.getHeartDisplay().getContainer().getChildren().size(), "Two hearts should remain after removing one");

        levelView.removeHearts(1);
        assertEquals(1, levelView.getHeartDisplay().getContainer().getChildren().size(), "One heart should remain after removing another one");

        levelView.removeHearts(0);
        assertEquals(0, levelView.getHeartDisplay().getContainer().getChildren().size(), "No hearts should remain after removing all");
    }
}