package com.example.demo.manager;

import com.example.demo.manager.HealthBarManager;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.HBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HealthBarManagerTest {
    private HealthBarManager healthBarManager;

    @BeforeEach
    void setUp() {
        // Initialize JavaFX environment
        new JFXPanel();

        healthBarManager = new HealthBarManager(100, 200);
    }

    @Test
    void testInitialization() {
        Platform.runLater(() -> {
            HBox container = healthBarManager.getContainer();
            assertEquals(100, container.getLayoutX(), "Initial X position should be 100");
            assertEquals(200, container.getLayoutY(), "Initial Y position should be 200");
            assertFalse(container.isVisible(), "Health bar should be initially hidden");
        });
    }

    // Other methods already been tested in BossTest
}