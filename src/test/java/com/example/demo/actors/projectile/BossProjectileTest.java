package com.example.demo.actors.projectile;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.demo.level.LevelParent.VELOCITY_CHANGE;
import static org.junit.jupiter.api.Assertions.*;

public class BossProjectileTest {
    private BossProjectile bossProjectile;
    private static final int HORIZONTAL_VELOCITY = (int) (-15 /  VELOCITY_CHANGE);
    private static final int VERTICAL_VELOCITY = 0;

    @BeforeEach
    void setUp() {
        new JFXPanel(); // Initialize JavaFX environment

        Platform.runLater(() -> bossProjectile = new BossProjectile(500, 300));

        try {
            Thread.sleep(200); // Wait for initialization
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            fail("Setup interrupted");
        }
    }

    @Test
    void testInitialPosition() {
        Platform.runLater(() -> {
            assertEquals(500.0, bossProjectile.getLayoutX(), "Initial X position should be 500.0");
            assertEquals(300.0, bossProjectile.getLayoutY(), "Initial Y position should be 300.0");
        });
    }


    @Test
    void testUpdatePosition() {
        Platform.runLater(() -> {
            double initialX = bossProjectile.getLayoutX();
            double initialY = bossProjectile.getLayoutY();
            bossProjectile.updatePosition();
                        try {
                Thread.sleep(1000); // Pause for one second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Handle interrupt properly
                fail("Test interrupted");
            }
            assertEquals(initialX + HORIZONTAL_VELOCITY, bossProjectile.getLayoutX() + bossProjectile.getTranslateX(), "Projectile should move horizontally to the left by " + (-HORIZONTAL_VELOCITY) + " units.");
            assertEquals(initialY + VERTICAL_VELOCITY, bossProjectile.getLayoutY() + bossProjectile.getTranslateY(), "Projectile should move vertically to the left by " + (-HORIZONTAL_VELOCITY) + " units.");
        });
    }

    @Test
    void testCustomVelocity() {
        double customXVelocity = -20;
        double customYVelocity = 5;
        Platform.runLater(() -> {
            BossProjectile customBossProjectile = new BossProjectile(500, 300, (int) customXVelocity, (int) customYVelocity);
            double initialX = customBossProjectile.getLayoutX();
            double initialY = customBossProjectile.getLayoutY();
            customBossProjectile.updatePosition();
            assertEquals(initialX + customXVelocity, customBossProjectile.getLayoutX() + customBossProjectile.getTranslateX(), "Projectile should move to the left with custom velocity");
            assertEquals(initialY + customYVelocity, customBossProjectile.getLayoutY() + customBossProjectile.getTranslateY(), "Projectile should move vertically with custom velocity");
        });
    }
}
