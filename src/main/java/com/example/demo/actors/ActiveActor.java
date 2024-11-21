package com.example.demo.actors;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * The {@code ActiveActor} class represents an active entity in the game that can be displayed
 * on the screen and updated during the game loop. This is an abstract class that provides
 * common functionality for all actors, such as image handling, positioning, and movement.
 */
public abstract class ActiveActor extends ImageView {

	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	/**
	 * Constructs an {@code ActiveActor} instance with the specified image, size, and position.
	 *
	 * @param imageName   The name of the image file representing the actor.
	 * @param imageHeight The height of the actor's image.
	 * @param initialXPos The initial X-coordinate of the actor.
	 * @param initialYPos The initial Y-coordinate of the actor.
	 */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_LOCATION + imageName)).toExternalForm()));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * Updates the position of the actor.
	 * Subclasses must implement this method to define specific movement behavior.
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
