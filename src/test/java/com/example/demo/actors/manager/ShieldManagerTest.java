package com.example.demo.actors.manager;

import com.example.demo.actors.plane.Boss;
import javafx.application.Platform;
import javafx.scene.layout.HBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;

public class ShieldManagerTest {
    private ShieldManager shieldManager;
    private Group root;

    @BeforeEach
    void setUp() {
        // Necessary to initialize the JavaFX toolkit and environment
        new JFXPanel();
        root = new Group();
        shieldManager = new ShieldManager(root, 100.0, 100.0);
    }

    @Test
    void testInitialProperties() {
        Platform.runLater(() -> {
            // Test initial positions and visibility
            HBox container = shieldManager.getShieldImage().getContainer();
            assertEquals(100, container.getLayoutX(), "Initial X position should match constructor argument");
            assertEquals(100, container.getLayoutY(), "Initial Y position should match constructor argument");
            assertFalse(container.isVisible(), "Shield should initially be invisible");
        });
    }


    @Test
    void testShieldActivation() {
        // Activate the shield
        Platform.runLater(() -> {
        shieldManager.activateShield();
        assertTrue(shieldManager.isShielded(), "Shield should be activated.");
        });
    }

    @Test
    void testShieldDeactivation() {
        Platform.runLater(() -> {
          // Deactivate the shield
            shieldManager.deactivateShield();
            assertFalse(shieldManager.isShielded(), "Shield should be deactivated.");
        });
    }
}
