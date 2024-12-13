package com.example.demo.actors;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * The {@code ActiveActor} class serves as an abstract base for all dynamic entities in the game that are capable
 * of being rendered on the screen and updated during the game loop. It provides foundational functionalities such
 * as image management, positioning, and basic movement capabilities, forming the core for all moving game elements
 * like planes and projectiles.
 *
 * @see ImageView for how images are managed and displayed.
 * <p>
 * See the source code at <a href="https://github.com/TimZ21/CW2024/blob/master/src/main/java/com/example/demo/actors/ActiveActor.java">ActiveAcotr.java</a>
 */

public abstract class ActiveActor extends ImageView {

	/**
	 * The base directory path for all actor images. This path is relative to the resource directory
	 * and is used to locate image files for actors.
	 */
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
