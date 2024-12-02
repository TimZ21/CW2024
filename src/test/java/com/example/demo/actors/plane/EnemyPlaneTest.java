package com.example.demo.actors.plane;

import com.example.demo.actors.ActiveActorDestructible;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyPlaneTest {
    private EnemyPlane enemyPlane;


    @BeforeEach
    void setUp() {
        new JFXPanel(); // Initialize JavaFX environment
        // Setting up an enemy plane at a known position
        Platform.runLater(() -> enemyPlane = new EnemyPlane(100, 100));
        try {
            Thread.sleep(200); // Wait for initialization on JavaFX thread
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            fail("Setup interrupted");
        }
    }

    @Test
    void testInitialPosition() {
        Platform.runLater(() -> {
            assertEquals(100, enemyPlane.getLayoutX(), "Initial X position should be 5.0");
            assertEquals(100, enemyPlane.getLayoutY(), "Initial Y position should be 300.0");
        });
    }

    @Test
    void testMovement() {
        Platform.runLater(() -> {
            double initialX = enemyPlane.getLayoutX();
            enemyPlane.updatePosition();
            assertTrue(enemyPlane.getAbsoluteX() < initialX, "Enemy plane should move left");
        });
    }

    @Test
    void testProjectileFiring() {
        Platform.runLater(() -> {
            List<ActiveActorDestructible> projectiles = enemyPlane.fireProjectile();
            assertNotNull(projectiles, "fireProjectile should never return null");
            assertTrue(projectiles.size() <= 1, "Should fire at most one projectile at a time");
        });
    }

    // This is actually same with the testMovement, might be redundant
    @Test
    void testUpdateActor() {
        Platform.runLater(() -> {
            double initialX = enemyPlane.getLayoutX();
            enemyPlane.updateActor();
            assertTrue(enemyPlane.getAbsoluteX() < initialX, "updateActor should update the plane's position");
        });
    }
}
