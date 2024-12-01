package com.example.demo.actors;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * The {@code ActiveActor} class represents an active entity in the game that can be displayed
 * on the screen and updated during the game loop. This abstract class provides common functionality
 * for all actors, including image handling, positioning, and movement.
 *
 * @see ImageView
 */
public abstract class ActiveActor extends ImageView {

	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	/**
	 * Constructs an {@code ActiveActor} instance with the specified image, size, and position.
	 *
	 * @param imageName   The name of the image file representing the actor. Must not be null or empty.
	 * @param imageHeight The height of the actor's image. Must be positive.
	 * @param initialXPos The initial X-coordinate of the actor.
	 * @param initialYPos The initial Y-coordinate of the actor.
	 * @throws NullPointerException if imageName is null.
	 * @throws IllegalArgumentException if imageHeight is non-positive.
	 */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		if (imageName == null || imageName.trim().isEmpty()) {
			throw new IllegalArgumentException("Image name must not be null or empty.");
		}
		if (imageHeight <= 0) {
			throw new IllegalArgumentException("Image height must be positive.");
		}

		loadImage(imageName, imageHeight);
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
	}

	/**
	 * Loads the image for the actor and configures its dimensions.
	 *
	 * @param imageName   The name of the image file.
	 * @param imageHeight The height of the image to be set for the actor.
	 */
	private void loadImage(String imageName, int imageHeight) {
		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_LOCATION + imageName)).toExternalForm()));
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * Updates the position of the actor. This method must be implemented by subclasses to define specific movement behavior.
	 */
	public abstract void updatePosition();

	/**
	 * Moves the actor horizontally by the specified amount.
	 *
	 * @param horizontalMove The amount to move the actor horizontally.
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * Moves the actor vertically by the specified amount.
	 *
	 * @param verticalMove The amount to move the actor vertically.
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}
}
