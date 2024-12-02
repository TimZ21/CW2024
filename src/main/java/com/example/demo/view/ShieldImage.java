package com.example.demo.view;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;

/**
 * The {@code ShieldImage} class represents the visual shield effect for the boss character.
 * It handles the initialization, positioning, and visibility of the shield image.
 */
public class ShieldImage {
	/**
	 * Resource path for the shield image.
	 */
	private static final String IMAGE_NAME = "/com/example/demo/images/shield.png";
	/**
	 * Size for the shield image, used for both width and height.
	 */
	private static final int SHIELD_SIZE = 200;
	/**
	 * Container for the shield image, allowing for easy positioning and visibility changes.
	 */
	private HBox container;
	/**
	 * Image view that actually displays the shield image.
	 */
	private ImageView shieldImageView;

	/**
	 * Constructs a {@code ShieldImage} with the specified initial position.
	 *
	 * @param xPosition The initial X position of the shield image.
	 * @param yPosition The initial Y position of the shield image.
	 */
	public ShieldImage(double xPosition, double yPosition) {
		initializeContainer(xPosition, yPosition);
		initializeShieldImage();
	}

	/**
	 * Initializes the container for the shield image. The container is an {@code HBox}
	 * that holds the {@code ImageView} of the shield. It sets the initial position
	 * and visibility of the container.
	 *
	 * @param xPosition The initial X position of the container.
	 * @param yPosition The initial Y position of the container.
	 */
	private void initializeContainer(double xPosition, double yPosition) {
		container = new HBox();
		container.setLayoutX(xPosition);
		container.setLayoutY(yPosition);
		container.setVisible(false); // Hide the shield initially
	}

	/**
	 * Initializes and loads the shield image, setting up the visual properties such as size and opacity.
	 */
	private void initializeShieldImage() {
		shieldImageView = new ImageView();
		URL imageUrl = getClass().getResource(IMAGE_NAME);
		if (imageUrl != null) {
			shieldImageView.setImage(new Image(imageUrl.toExternalForm()));
		} else {
			System.err.println("Error: Shield image not found at " + IMAGE_NAME);
		}
		shieldImageView.setFitHeight(SHIELD_SIZE);
		shieldImageView.setFitWidth(SHIELD_SIZE);
		shieldImageView.setOpacity(1.0); // Ensure the shield is fully opaque
		shieldImageView.setPreserveRatio(true);
		container.getChildren().add(shieldImageView);
	}

	/**
	 * Updates the position of the shield on the JavaFX application thread.
	 *
	 * @param x the new X position for the shield
	 * @param y the new Y position for the shield
	 */
	public void setPosition(double x, double y) {
		Platform.runLater(() -> {
			container.setLayoutX(x);
			container.setLayoutY(y);
		});
	}

	/**
	 * Makes the shield visible and ensures it is rendered in front of other elements.
	 */
	public void showShield() {
		Platform.runLater(() -> {
			container.setVisible(true);
			container.toFront(); // Ensure the shield is rendered in front of other elements
		});
	}

	/**
	 * Hides the shield by setting the container's visibility to false.
	 */
	public void hideShield() {
		Platform.runLater(() -> {
			container.setVisible(false);
		});
	}

	/**
	 * Gets the container that holds the shield image, primarily for positioning and visibility manipulation.
	 *
	 * @return the HBox container of the shield image
	 */
	public HBox getContainer() {
		return container;
	}
}
