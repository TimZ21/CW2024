package com.example.demo.actors.plane;

import com.example.demo.view.ShieldImage;
import javafx.application.Platform;
import javafx.scene.Group;

import static com.example.demo.level.LevelParent.VOLECITY_CHANGE;

/**
 * The `ShieldManager` class manages the shield's activation, deactivation,
 * position updates, and status checks for an entity with a shield.
 */
public class ShieldManager {

    private final ShieldImage shieldImage;
    private boolean isShielded;
    private int framesWithShieldActivated;
    private static final int MAX_FRAMES_WITH_SHIELD = 50*VOLECITY_CHANGE;
    private static final double SHIELD_PROBABILITY = 0.02/VOLECITY_CHANGE;

    /**
     * Constructs a `ShieldManager` with the specified root and initial position.
     *
     * @param root The root group to add the shield image.
     * @param xPosition The initial X position of the shield.
     * @param yPosition The initial Y position of the shield.
     */
    public ShieldManager(Group root, double xPosition, double yPosition) {
        this.shieldImage = new ShieldImage(xPosition, yPosition);
        this.isShielded = false;
        this.framesWithShieldActivated = 0;

        Platform.runLater(() -> {
            root.getChildren().add(shieldImage.getContainer());
            shieldImage.hideShield();
        });
    }

    /**
     * Updates the shield status based on the activation probability and duration.
     */
    public void updateShield() {
        if (isShielded) {
            framesWithShieldActivated++;
            if (shieldExhausted()) {
                deactivateShield();
            }
        } else if (shieldShouldBeActivated()) {
            activateShield();
        }
    }

    /**
     * Updates the position of the shield image.
     *
     * @param x The X position of the entity.
     * @param y The Y position of the entity.
     */
    public void updateShieldPosition(double x, double y) {
        Platform.runLater(() -> shieldImage.setPosition(x, y));
    }

    /**
     * Checks if the shield is currently active.
     *
     * @return `true` if the shield is active, `false` otherwise.
     */
    public boolean isShielded() {
        return isShielded;
    }

    /**
     * Activates the shield and makes it visible.
     */
    public void activateShield() {
        isShielded = true;
        shieldImage.showShield();
        System.out.println("Shield activated.");
    }

    /**
     * Deactivates the shield and hides it.
     */
    public void deactivateShield() {
        isShielded = false;
        framesWithShieldActivated = 0;
        shieldImage.hideShield();
        System.out.println("Shield deactivated.");
    }

    /**
     * Checks if the shield should be activated based on a random probability.
     *
     * @return `true` if the shield should be activated, `false` otherwise.
     */
    private boolean shieldShouldBeActivated() {
        return Math.random() < SHIELD_PROBABILITY;
    }

    /**
     * Checks if the shield has been active for the maximum duration.
     *
     * @return `true` if the shield is exhausted, `false` otherwise.
     */
    private boolean shieldExhausted() {
        return framesWithShieldActivated >= MAX_FRAMES_WITH_SHIELD;
    }
}
