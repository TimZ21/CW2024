package com.example.demo.actors.projectile;

import com.example.demo.level.LevelParent;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyProjectileTest {
    private EnemyProjectile enemyProjectile;
    private static final double HORIZONTAL_VELOCITY = -10.0 / LevelParent.VELOCITY_CHANGE;

    @BeforeEach
    void setUp() {
        new JFXPanel();  // Initialize JavaFX environment

        Platform.runLater(() -> enemyProjectile = new EnemyProjectile(300, 150));

        try {
            Thread.sleep(200);  // Wait for initialization
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            fail("Setup interrupted");
        }
    }

    @Test
    void testInitialPosition() {
        Platform.runLater(() -> {
            assertEquals(300.0, enemyProjectile.getLayoutX(), "Initial X position should be 300.0");
            assertEquals(150.0, enemyProjectile.getLayoutY(), "Initial Y position should be 150.0");
        });
    }

    @Test
    void testUpdatePosition() {
        Platform.runLater(() -> {
            double initialX = enemyProjectile.getLayoutX();
            enemyProjectile.updatePosition();
            assertEquals(initialX + HORIZONTAL_VELOCITY, enemyProjectile.getAbsoluteX(), "Projectile should move horizontally to the right by " + HORIZONTAL_VELOCITY + " units.");
        });
    }

    @Test
    void testBoundaryChecks() {
        Platform.runLater(() -> {
            // Move the projectile until it goes out of the left boundary or exceeds a large negative x-coordinate
            while (enemyProjectile.getLayoutX() > -1000) {
                enemyProjectile.updatePosition();
            }
            assertTrue(enemyProjectile.getLayoutX() <= 0, "Projectile should not move left of the left boundary");
        });
    }
}
