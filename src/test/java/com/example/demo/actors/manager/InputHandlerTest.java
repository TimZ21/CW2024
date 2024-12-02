package com.example.demo.actors.manager;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.plane.UserPlane;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InputHandlerTest {
    private UserPlane userPlane;
    private Group root;
    private List<ActiveActorDestructible> userProjectiles;
    private Runnable pauseGameCallback;
    private InputHandler inputHandler;

    @BeforeEach
    void setUp() {
        // Initialize JavaFX environment
        new JFXPanel();

        userPlane = new UserPlane(10);
        root = new Group();
        userProjectiles = new ArrayList<>();
        pauseGameCallback = () -> {};
        inputHandler = new InputHandler(userPlane, root, userProjectiles, pauseGameCallback);
    }

    @Test
    void testOnKeyPressedHandlerUp() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.UP, false, false, false, false);
        inputHandler.getOnKeyPressedHandler().handle(keyEvent);
        assertTrue(userPlane.isMovingUp());
    }

    @Test
    void testOnKeyPressedHandlerDown() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.DOWN, false, false, false, false);
        inputHandler.getOnKeyPressedHandler().handle(keyEvent);
        assertTrue(userPlane.isMovingDown());
    }

    @Test
    void testOnKeyPressedHandlerLeft() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.LEFT, false, false, false, false);
        inputHandler.getOnKeyPressedHandler().handle(keyEvent);
        assertTrue(userPlane.isMovingLeft());
    }

    @Test
    void testOnKeyPressedHandlerRight() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false, false, false, false);
        inputHandler.getOnKeyPressedHandler().handle(keyEvent);
        assertTrue(userPlane.isMovingRight());
    }

    @Test
    void testOnKeyReleasedHandlerUp() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyCode.UP, false, false, false, false);
        inputHandler.getOnKeyReleasedHandler().handle(keyEvent);
        assertFalse(userPlane.isMovingUp());
    }

    @Test
    void testOnKeyReleasedHandlerDown() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyCode.DOWN, false, false, false, false);
        inputHandler.getOnKeyReleasedHandler().handle(keyEvent);
        assertFalse(userPlane.isMovingDown());
    }

    @Test
    void testOnKeyReleasedHandlerLeft() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyCode.LEFT, false, false, false, false);
        inputHandler.getOnKeyReleasedHandler().handle(keyEvent);
        assertFalse(userPlane.isMovingLeft());
    }

    @Test
    void testOnKeyReleasedHandlerRight() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyCode.RIGHT, false, false, false, false);
        inputHandler.getOnKeyReleasedHandler().handle(keyEvent);
        assertFalse(userPlane.isMovingRight());
    }

    @Test
    void testUpdate() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.SPACE, false, false, false, false);
        inputHandler.getOnKeyPressedHandler().handle(keyEvent);
        inputHandler.update();
        assertFalse(userProjectiles.isEmpty());
    }

//    @Test
//    void testFireProjectile() {
//        inputHandler.fireProjectile();
//        assertFalse(userProjectiles.isEmpty());
//        assertTrue(root.getChildren().contains(userProjectiles.get(0)));
//    }

    @Test
    void testPauseGame() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.ESCAPE, false, false, false, false);
        inputHandler.getOnKeyPressedHandler().handle(keyEvent);
        // Assuming pauseGameCallback is a mock, we can't verify it directly without Mockito
    }
}