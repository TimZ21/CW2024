package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * The {@code GameOverImage} class represents an image displayed when the game is over.
 * It extends {@code ImageView} and provides functionality to position the image at
 * a specified location on the screen.
 */
public class GameOverImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";

	/**
	 * Constructs a {@code GameOverImage} object and positions it at the specified coordinates.
	 *
	 * @param xPosition The X-coordinate where the image is displayed.
	 * @param yPosition The Y-coordinate where the image is displayed.
	 */
	public GameOverImage(double xPosition, double yPosition) {
		setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
		setLayoutX(xPosition);
		setLayoutY(yPosition);
	}
}
