package com.example.demo.actors.projectile;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserProjectileTest {
    private UserProjectile userProjectile;
    private static final int HORIZONTAL_VELOCITY = 6;


    @BeforeEach
    void setUp() {
        new JFXPanel();

        Platform.runLater(() -> userProjectile = new UserProjectile(100, 200));

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInitialPosition() {
        Platform.runLater(() -> {
            assertEquals(100.0, userProjectile.getLayoutX(), "Initial X position should be 100.0");
            assertEquals(200.0, userProjectile.getLayoutY(), "Initial Y position should be 200.0");
        });
    }

    @Test
    void testUpdatePosition() {
        Platform.runLater(() -> {
            double initialX = userProjectile.getLayoutX();
            userProjectile.updatePosition();
            assertEquals(initialX + HORIZONTAL_VELOCITY, userProjectile.getAbsoluteX(), "Projectile should move horizontally to the right by " + HORIZONTAL_VELOCITY + " units.");
        });
    }


    @Test
    void testBoundaryChecks() {
        Platform.runLater(() -> {
            // Move the projectile until it reaches the right boundary
            while (userProjectile.getLayoutX() < 1100) {
                userProjectile.updatePosition();
            }
            assertTrue(userProjectile.getLayoutX() <= 1100, "Projectile should not move right of the right boundary");
        });
    }
}