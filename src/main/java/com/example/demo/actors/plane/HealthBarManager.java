package com.example.demo.actors.plane;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;

/**
 * The {@code HealthBarManager} class manages the health bar for a game entity (e.g., the boss).
 * It handles the initialization, positioning, visibility, and updating of the health bar.
 */
public class HealthBarManager {

    private static final int HEALTH_BAR_WIDTH = 500; // Default width of the health bar
    private static final int HEALTH_BAR_HEIGHT = 20; // Default height of the health bar

    private final HBox container;
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
     * Sets the position of the health bar container.
     *
     * @param x The new X position of the health bar.
     * @param y The new Y position of the health bar.
     */
    public void setPosition(double x, double y) {
        Platform.runLater(() -> {
            container.setLayoutX(x);
            container.setLayoutY(y);
        });
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
        Platform.runLater(() -> {
            container.setVisible(false);
        });
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
