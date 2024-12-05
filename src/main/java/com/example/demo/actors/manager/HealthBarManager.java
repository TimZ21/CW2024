package com.example.demo.actors.manager;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;

/**
 * The {@code HealthBarManager} class manages the health bar for {@link com.example.demo.actors.plane.Boss}.
 * It handles the initialization, positioning, visibility, and updating of the health bar.
 */
public class HealthBarManager {

    /**
     * Default width of the health bar.
     */
    private static final int HEALTH_BAR_WIDTH = 500;

    /**
     * Default height of the health bar.
     */
    private static final int HEALTH_BAR_HEIGHT = 20;

    /**
     * Container for the health bar, allowing for positioning and styling.
     */
    private final HBox container;

    /**
     * The health bar UI component that visually represents health as a progress bar.
     */
    private final ProgressBar healthBar;

    /**
     * Constructs a {@code HealthBarManager} with the specified initial position.
     *
     * @param xPosition The initial X position of the health bar.
     * @param yPosition The initial Y position of the health bar.
     */
    public HealthBarManager(double xPosition, double yPosition) {
        container = new HBox();
        container.setLayoutX(xPosition);
        container.setLayoutY(yPosition);
        container.setVisible(false); // Initially hide the health bar

        healthBar = new ProgressBar(1.0); // Initialize with full health
        healthBar.setPrefWidth(HEALTH_BAR_WIDTH);
        healthBar.setPrefHeight(HEALTH_BAR_HEIGHT);
        healthBar.setStyle("-fx-accent: red;"); // Set the health bar color

        container.getChildren().add(healthBar);
    }

    /**
     * Updates the health bar's progress based on the current health percentage.
     *
     * @param healthPercentage The current health percentage (value between 0.0 and 1.0).
     */
    public void updateHealthBar(double healthPercentage) {
        Platform.runLater(() -> {
            healthBar.setProgress(Math.max(0.0, Math.min(1.0, healthPercentage)));
            if (healthPercentage <= 0.0) {
                hideHealthBar();
            } else {
                showHealthBar();
            }
        });
    }

    /**
     * Shows the health bar by making the container visible and bringing it to the front.
     */
    public void showHealthBar() {
        Platform.runLater(() -> {
            container.setVisible(true);
            container.toFront(); // Ensure the health bar is in front of other elements
        });
    }

    /**
     * Hides the health bar by setting the container's visibility to false.
     */
    public void hideHealthBar() {
        Platform.runLater(() -> container.setVisible(false));
    }

    /**
     * Returns the container holding the health bar.
     *
     * @return The {@code HBox} container for the health bar.
     */
    public HBox getContainer() {
        return container;
    }
}
