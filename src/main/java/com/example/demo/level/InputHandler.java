package com.example.demo.level;

import com.example.demo.actors.plane.UserPlane;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * The {@code InputHandler} class is responsible for handling user keyboard input
 * and delegating actions to the user's plane (e.g., moving up, moving down, firing a projectile).
 * It encapsulates the logic for key press and key release events.
 */
public class InputHandler {

    private final UserPlane userPlane;
    private final Runnable fireAction;  // Holds a reference to the fireProjectile action

    /**
     * Constructs an {@code InputHandler} with the specified user plane and fire action.
     *
     * @param userPlane The {@code UserPlane} instance controlled by the user.
     * @param fireAction A {@code Runnable} action that defines the behavior for firing a projectile.
     */
    public InputHandler(UserPlane userPlane, Runnable fireAction) {
        this.userPlane = userPlane;
        this.fireAction = fireAction;
    }

    /**
     * Returns an {@code EventHandler} for handling key pressed events.
     * This handler processes the following keys:
     * <ul>
     *   <li>{@code UP}: Moves the user plane up.</li>
     *   <li>{@code DOWN}: Moves the user plane down.</li>
     *   <li>{@code SPACE}: Triggers the fire action (fires a projectile).</li>
     * </ul>
     *
     * @return An {@code EventHandler<KeyEvent>} for key pressed events.
     */
    public EventHandler<KeyEvent> getOnKeyPressedHandler() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.UP) {
                    userPlane.moveUp();
                } else if (kc == KeyCode.DOWN) {
                    userPlane.moveDown();
                } else if (kc == KeyCode.SPACE) {
                    fireAction.run();  // Execute the fire action defined in LevelParent
                }
            }
        };
    }

    /**
     * Returns an {@code EventHandler} for handling key released events.
     * This handler processes the following keys:
     * <ul>
     *   <li>{@code UP} or {@code DOWN}: Stops the movement of the user plane.</li>
     * </ul>
     *
     * @return An {@code EventHandler<KeyEvent>} for key released events.
     */
    public EventHandler<KeyEvent> getOnKeyReleasedHandler() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.UP || kc == KeyCode.DOWN) {
                    userPlane.stop();
                }
            }
        };
    }
}
