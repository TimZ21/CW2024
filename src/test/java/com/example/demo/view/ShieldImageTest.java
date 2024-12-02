package com.example.demo.view;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.HBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShieldImageTest {
    ShieldImage shieldImage;
    private static final double INITIAL_X = 100;
    private static final double INITIAL_Y = 100;

    @BeforeEach
    void setUp() {
        // Necessary to initialize JavaFX toolkit
        new JFXPanel();
        shieldImage = new ShieldImage(INITIAL_X, INITIAL_Y);
    }

    @Test
    void testInitialProperties() {
        Platform.runLater(() -> {
        // Test initial positions and visibility
        HBox container = shieldImage.getContainer();
        assertEquals(INITIAL_X, container.getLayoutX(), "Initial X position should match constructor argument");
        assertEquals(INITIAL_Y, container.getLayoutY(), "Initial Y position should match constructor argument");
        assertFalse(container.isVisible(), "Shield should initially be invisible");
        });
    }

    @Test
    void testShowShield() {
        Platform.runLater(() -> {
            shieldImage.hideShield(); // First make it invisible
            shieldImage.showShield();
            assertTrue(shieldImage.getContainer().isVisible(), "Shield should be visible after calling showShield");
        });
    }

    @Test
    void testHideShield() {
        Platform.runLater(() -> {
        shieldImage.showShield(); // First make it visible
        shieldImage.hideShield();
        assertFalse(shieldImage.getContainer().isVisible(), "Shield should be invisible after calling hideShield");
        });
    }
}
