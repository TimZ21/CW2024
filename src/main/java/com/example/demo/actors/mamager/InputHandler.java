package com.example.demo.actors.mamager;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.plane.UserPlane;
import com.example.demo.actors.projectile.UserProjectile;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;

/**
 * The {@code InputHandler} class manages user input and maps key events
 * to actions for controlling the {@code UserPlane}.
 * It handles movement controls (up, down, left, right) and the firing of projectiles.
 */
public class InputHandler {

    private final UserPlane userPlane;
    private final Group root;
    private final List<ActiveActorDestructible> userProjectiles;

    /**
     * Constructs an {@code InputHandler} to handle user input for the specified user plane.
     *
     * @param userPlane The {@code UserPlane} instance that will be controlled by user input.
     * @param root The {@code Group} to which projectiles will be added.
     * @param userProjectiles The list to store user projectiles.
     */
    public InputHandler(UserPlane userPlane, Group root, List<ActiveActorDestructible> userProjectiles) {
        this.userPlane = userPlane;
        this.root = root;
        this.userProjectiles = userProjectiles;
    }

    /**
     * Provides an {@code EventHandler} for handling key pressed events.
     * Supports the following key actions:
     * <ul>
     *   <li>{@code UP}: Moves the user plane upwards.</li>
     *   <li>{@code DOWN}: Moves the user plane downwards.</li>
     *   <li>{@code LEFT}: Moves the user plane to the left.</li>
     *   <li>{@code RIGHT}: Moves the user plane to the right.</li>
     *   <li>{@code SPACE}: Fires a projectile from the user plane.</li>
     * </ul>
     *
     * @return An {@code EventHandler<KeyEvent>} for key press events.
     */
    public EventHandler<KeyEvent> getOnKeyPressedHandler() {
        return e -> {
            KeyCode kc = e.getCode();
            if (kc == KeyCode.UP) {
                userPlane.moveUp();
            } else if (kc == KeyCode.DOWN) {
                userPlane.moveDown();
            } else if (kc == KeyCode.LEFT) {
                userPlane.moveLeft();
            } else if (kc == KeyCode.RIGHT) {
                userPlane.moveRight();
            } else if (kc == KeyCode.SPACE) {
                fireProjectile();
            }
        };
    }

    /**
     * Provides an {@code EventHandler} for handling key released events.
     * Stops the movement of the user plane when the movement keys are released.
     *
     * @return An {@code EventHandler<KeyEvent>} for key release events.
     */
    public EventHandler<KeyEvent> getOnKeyReleasedHandler() {
        return e -> {
            KeyCode kc = e.getCode();
            if (kc == KeyCode.UP || kc == KeyCode.DOWN) {
                userPlane.stopVerticalMovement();
            }
            if ( kc == KeyCode.LEFT || kc == KeyCode.RIGHT) {
                userPlane.stopHorizontalMovement();
            }
        };
    }

    /**
     * Handles the firing of a projectile from the user plane.
     * Adds the fired projectile to the scene and the list of user projectiles.
     */
    private void fireProjectile() {
        ActiveActorDestructible projectile = userPlane.fireProjectile();
        if (projectile != null) {
            root.getChildren().add(projectile);
            userProjectiles.add(projectile);
        }
    }
}
