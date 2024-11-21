package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * The {@code WinImage} class represents a visual element displayed when the player wins the game.
 * It extends {@code ImageView} and provides functionality to display a "You Win" image at a specified location.
 */
public class WinImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png";
	private static final int HEIGHT = 500;
	private static final int WIDTH = 600;

	/**
	 * Constructs a {@code WinImage} object and initializes its properties,
	 * including size, visibility, and position.
	 *
	 * @param xPosition The X-coordinate of the win image.
	 * @param yPosition The Y-coordinate of the win image.
	 */
	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
		this.setVisible(false); // Initially hidden
		this.setFitHeight(HEIGHT); // Set the height of the image
		this.setFitWidth(WIDTH); // Set the width of the image
		this.setLayoutX(xPosition); // Set the X-coordinate of the image
		this.setLayoutY(yPosition); // Set the Y-coordinate of the image
	}

	/**
	 * Makes the "You Win" image visible.
	 * This method should be called when the player wins the game.
	 */
	public void showWinImage() {
		this.setVisible(true);
	}

}
