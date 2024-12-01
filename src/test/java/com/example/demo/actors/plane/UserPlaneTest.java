package com.example.demo.actors.plane;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.projectile.UserProjectile;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserPlaneTest {
    private UserPlane userPlane;

    @BeforeEach
    void setUp() {
        new JFXPanel();

        Platform.runLater(() -> userPlane = new UserPlane(10));

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    void testInitialPosition() {
        Platform.runLater(() -> {
            assertEquals(5.0, userPlane.getLayoutX(), "Initial X position should be 5.0");
            assertEquals(300.0, userPlane.getLayoutY(), "Initial Y position should be 300.0");
        });
    }

    @Test
    void testMoveUp() {
        userPlane.moveUp();
        userPlane.updatePosition();
        assertTrue(userPlane.getTranslateY() < 0, "Plane should move up");
    }

    @Test
    void testMoveDown() {
        userPlane.moveDown();
        userPlane.updatePosition();
        assertTrue(userPlane.getTranslateY() > 0, "Plane should move down");
    }

    @Test
    void testMoveLeft() {
        userPlane.moveLeft();
        userPlane.updatePosition();
        assertTrue(userPlane.getTranslateX() < 0, "Plane should move left");
    }

    @Test
    void testMoveRight() {
        userPlane.moveRight();
        userPlane.updatePosition();
        assertTrue(userPlane.getTranslateX() > 0, "Plane should move right");
    }

    @Test
    void testStopVerticalMovement() {
        userPlane.moveUp();
        userPlane.stopVerticalMovement();
        userPlane.updatePosition();
        assertEquals(0, userPlane.getTranslateY(), "Vertical movement should stop");
    }

    @Test
    void testStopHorizontalMovement() {
        userPlane.moveRight();
        userPlane.stopHorizontalMovement();
        userPlane.updatePosition();
        assertEquals(0, userPlane.getTranslateX(), "Horizontal movement should stop");
    }

    @Test
    void testFireProjectile() {
        List<ActiveActorDestructible> projectiles = userPlane.fireProjectile();
        assertEquals(1, projectiles.size(), "Should fire one projectile");
        assertInstanceOf(UserProjectile.class, projectiles.get(0), "Fired projectile should be an instance of UserProjectile");
    }

    @Test
    void testIncrementKillCount() {
        userPlane.incrementKillCount();
        assertEquals(1, userPlane.getNumberOfKills(), "Kill count should be incremented");
    }

    @Test
    void testBoundaryChecks() {
        // Move to the upper boundary
        userPlane.moveUp();
        for (int i = 0; i < 100; i++) {
            userPlane.updatePosition();
        }
        assertTrue(userPlane.getLayoutY() + userPlane.getTranslateY() >= 0, "Plane should not move above the upper boundary");

        // Move to the lower boundary
        userPlane.moveDown();
        for (int i = 0; i < 100; i++) {
            userPlane.updatePosition();
        }
        assertTrue(userPlane.getLayoutY() + userPlane.getTranslateY() <= 665, "Plane should not move below the lower boundary");

        // Move to the left boundary
        userPlane.moveLeft();
        for (int i = 0; i < 100; i++) {
            userPlane.updatePosition();
        }
        assertTrue(userPlane.getLayoutX() + userPlane.getTranslateX() >= 0, "Plane should not move left of the left boundary");

        // Move to the right boundary
        userPlane.moveRight();
        for (int i = 0; i < 100; i++) {
            userPlane.updatePosition();
        }
        assertTrue(userPlane.getLayoutX() + userPlane.getTranslateX() <= 1100, "Plane should not move right of the right boundary");
    }
}
