package com.example.demo.manager;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.plane.UserPlane;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;

/**
 * The {@code InputHandler} handles keyboard input for controlling a user-controlled plane and managing game pausing.
 * This class binds key presses and releases to actions within the game, such as moving a plane or firing projectiles.
 *
 * <p>
 * See the source code at <a href=https://github.com/TimZ21/CW2024/blob/master/src/main/java/com/example/demo/manager/InputHandler.java">InputHandler.java</a>
 */
public class InputHandler {

    /**
     * The user-controlled plane that the input handler manipulates based on key inputs.
     */
    private final UserPlane userPlane;

    /**
     * The root group of the JavaFX scene graph where game elements like UserPlane are added.
     */
    private final Group root;

    /**
     * A list that holds all projectiles fired by the user plane, allowing for tracking and management.
     */
    private final List<ActiveActorDestructible> userProjectiles;

    /**
     * A callback that can be invoked to pause the game. This allows the input handler to control game state.
     */
    private final Runnable pauseGameCallback;

    /**
     * Tracks the state of keyboard keys to manage game inputs such as movement and actions effectively.
     */
    private final KeyStateTracker keyStateTracker;

    /**
     * Flag to manage projectile firing to ensure that projectiles are not fired continuously without control.
     */
    private boolean projectileFired;


    /**
     * Constructs an {@code InputHandler} with specified components and behavior definitions.
     *
     * @param userPlane The user-controlled plane object.
     * @param root The root group of the JavaFX scene graph where actors are added.
     * @param userProjectiles A list of projectiles that the user has fired.
     * @param pauseGameCallback A callback to execute when the game needs to be paused.
     */
    public InputHandler(UserPlane userPlane, Group root, List<ActiveActorDestructible> userProjectiles, Runnable pauseGameCallback) {
        this.userPlane = userPlane;
        this.root = root;
        this.userProjectiles = userProjectiles;
        this.pauseGameCallback = pauseGameCallback;
        this.keyStateTracker = new KeyStateTracker();
        this.projectileFired = false;
    }

    /**
     * Returns an EventHandler for key pressed events that triggers corresponding game actions.
     *
     * @return An EventHandler to handle key pressed events.
     */
    public EventHandler<KeyEvent> getOnKeyPressedHandler() {
        return e -> {
            KeyCode kc = e.getCode();
            handleMovement(kc);
            if (kc == KeyCode.ESCAPE) {
                if (pauseGameCallback != null) {
                    pauseGameCallback.run();
                    keyStateTracker.resetKeyStates();
                    userPlane.stopVerticalMovement();
                    userPlane.stopHorizontalMovement();
                }
            }
            keyStateTracker.keyPressed(kc);
        };
    }

    /**
     * Returns an EventHandler for key released events that handles stopping or modifying game actions.
     *
     * @return An EventHandler to handle key released events.
     */
    public EventHandler<KeyEvent> getOnKeyReleasedHandler() {
        return e -> {
            KeyCode kc = e.getCode();
            handleStopMovement(kc);
            keyStateTracker.keyReleased(kc);
            if (kc == KeyCode.SPACE) {
                projectileFired = false; // Reset projectile fired flag on key release
            }
        };
    }

    /**
     * Periodically updates the state based on continuous key presses.
     */
    public void update() {
        handleContinuousMovement();
        if (keyStateTracker.isKeyPressed(KeyCode.SPACE) && !projectileFired) {
            fireProjectile();
            projectileFired = true; // Set the flag when projectile is fired
        }
    }

    /**
     * Handles keyboard input for directional movement.
     * This method maps specific key presses to movements of the user-controlled plane.
     *
     * @param kc The KeyCode representing the key that was pressed.
     */
    private void handleMovement(KeyCode kc) {
        switch (kc) {
            case UP:
                userPlane.moveUp();
                break;
            case DOWN:
                userPlane.moveDown();
                break;
            case LEFT:
                userPlane.moveLeft();
                break;
            case RIGHT:
                userPlane.moveRight();
                break;
        }
    }

    /**
     * Handles the cessation of movement when directional keys are released.
     * This method ensures that when vertical or horizontal movement keys are released,
     * the corresponding movement of the plane stops.
     *
     * @param kc The KeyCode representing the key that was released.
     */
    private void handleStopMovement(KeyCode kc) {
        if (kc == KeyCode.UP || kc == KeyCode.DOWN) {
            userPlane.stopVerticalMovement();
        }
        if (kc == KeyCode.LEFT || kc == KeyCode.RIGHT) {
            userPlane.stopHorizontalMovement();
        }
    }

    /**
     * Handles continuous movement based on the current state of pressed keys.
     * This method checks the state of directional keys and applies continuous movement
     * to the plane as long as those keys are held down.
     */
    private void handleContinuousMovement() {
        if (keyStateTracker.isKeyPressed(KeyCode.UP)) {
            userPlane.moveUp();
        } else if (keyStateTracker.isKeyPressed(KeyCode.DOWN)) {
            userPlane.moveDown();
        }
        if (keyStateTracker.isKeyPressed(KeyCode.LEFT)) {
            userPlane.moveLeft();
        } else if (keyStateTracker.isKeyPressed(KeyCode.RIGHT)) {
            userPlane.moveRight();
        }
    }

    /**
     * Initiates the firing of projectiles if the space key is pressed and not yet handled.
     * This method checks if a projectile has not been fired since the last key press,
     * and if not, it fires a projectile and registers it in the game scene.
     */
    private void fireProjectile() {
        List<ActiveActorDestructible> projectiles = userPlane.fireProjectile();
        if (projectiles != null) {
            projectiles.forEach(projectile -> {
                AudioManager.getInstance().playUserShootEffect();
                root.getChildren().add(projectile);
                userProjectiles.add(projectile);
            });
        }
    }
}
