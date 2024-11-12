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

	private static final String IMAGE_NAME = "/com/example/demo/images/shield.png";
	private static final int SHIELD_SIZE = 200;
	private HBox container;
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
	 * Initializes the shield image. Loads the image from the specified resource path
	 * and sets its size, opacity, and aspect ratio preservation.
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
	 * Sets the position of the shield container. Uses {@code Platform.runLater()}
	 * to ensure that the UI update occurs on the JavaFX application thread.
	 *
	 * @param x The new X position of the shield container.
	 * @param y The new Y position of the shield container.
	 */
	public void setPosition(double x, double y) {
		Platform.runLater(() -> {
			container.setLayoutX(x);
			container.setLayoutY(y);
		});
	}

	/**
	 * Shows the shield image by making the container visible and bringing it to the front.
	 * Uses {@code Platform.runLater()} to update the UI on the JavaFX application thread.
	 */
	public void showShield() {
		Platform.runLater(() -> {
			container.setVisible(true);
			container.toFront(); // Ensure the shield is rendered in front of other elements
		});
	}

	/**
	 * Hides the shield image by setting the container's visibility to false.
	 * Uses {@code Platform.runLater()} to update the UI on the JavaFX application thread.
	 */
	public void hideShield() {
		Platform.runLater(() -> {
			container.setVisible(false);
		});
	}

	/**
	 * Returns the container holding the shield image.
	 *
	 * @return The {@code HBox} container for the shield image.
	 */
	public HBox getContainer() {
		return container;
	}
}
