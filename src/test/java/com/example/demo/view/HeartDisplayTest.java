package com.example.demo.view;

import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.HBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HeartDisplayTest {
    private HeartDisplay heartDisplay;

    @BeforeEach
    void setUp() {
        // Initialize JavaFX environment
        new JFXPanel();

        heartDisplay = new HeartDisplay(10, 20, 3);
    }

    @Test
    void testInitialization() {
        HBox container = heartDisplay.getContainer();
        assertEquals(10, container.getLayoutX(), "Initial X position should be 10");
        assertEquals(20, container.getLayoutY(), "Initial Y position should be 20");
        assertEquals(3, container.getChildren().size(), "Container should have 3 hearts initially");
    }

    @Test
    void testRemoveHeart() {
        heartDisplay.removeHeart();
        assertEquals(2, heartDisplay.getContainer().getChildren().size(), "Container should have 2 hearts after removing one");

        heartDisplay.removeHeart();
        assertEquals(1, heartDisplay.getContainer().getChildren().size(), "Container should have 1 heart after removing another one");

        heartDisplay.removeHeart();
        assertEquals(0, heartDisplay.getContainer().getChildren().size(), "Container should have 0 hearts after removing all");

        // Removing hearts when there are none should not throw an exception
        heartDisplay.removeHeart();
        assertEquals(0, heartDisplay.getContainer().getChildren().size(), "Container should still have 0 hearts after removing from empty container");
    }
}