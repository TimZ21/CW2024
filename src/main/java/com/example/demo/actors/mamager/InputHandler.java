package com.example.demo.actors.mamager;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.plane.UserPlane;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class InputHandler {

    private final UserPlane userPlane;
    private final Group root;
    private final List<ActiveActorDestructible> userProjectiles;
    private final Runnable pauseGameCallback; // A callback for pausing the game
    private final KeyStateTracker keyStateTracker;
    private boolean projectileFired; // Flag to manage projectile firing

    public InputHandler(UserPlane userPlane, Group root, List<ActiveActorDestructible> userProjectiles, Runnable pauseGameCallback) {
        this.userPlane = userPlane;
        this.root = root;
        this.userProjectiles = userProjectiles;
        this.pauseGameCallback = pauseGameCallback;
        this.keyStateTracker = new KeyStateTracker();
        this.projectileFired = false;
    }

    public EventHandler<KeyEvent> getOnKeyPressedHandler() {
        return e -> {
            KeyCode kc = e.getCode();
            handleMovement(kc);
            if (kc == KeyCode.ESCAPE) {
                if (pauseGameCallback != null) {
                    pauseGameCallback.run();
                }
            }
            keyStateTracker.keyPressed(kc);
        };
    }

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

    public void update() {
        handleContinuousMovement();
        if (keyStateTracker.isKeyPressed(KeyCode.SPACE) && !projectileFired) {
            fireProjectile();
            projectileFired = true; // Set the flag when projectile is fired
        }
    }

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

    private void handleStopMovement(KeyCode kc) {
        if (kc == KeyCode.UP || kc == KeyCode.DOWN) {
            userPlane.stopVerticalMovement();
        }
        if (kc == KeyCode.LEFT || kc == KeyCode.RIGHT) {
            userPlane.stopHorizontalMovement();
        }
    }

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

    private void fireProjectile() {
        List<ActiveActorDestructible> projectiles = userPlane.fireProjectile();
        if (projectiles != null) {
            projectiles.forEach(projectile -> {
                root.getChildren().add(projectile);
                userProjectiles.add(projectile);
            });
        }
    }
}
