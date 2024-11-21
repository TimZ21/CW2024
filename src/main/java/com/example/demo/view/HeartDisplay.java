package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Objects;

/**
 * The {@code HeartDisplay} class represents a visual health display for the player
 * using heart icons. It uses an {@code HBox} to manage the layout of heart images
 * and allows dynamically removing hearts as the player takes damage.
 */
public class HeartDisplay {

	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";
	private static final int HEART_HEIGHT = 50;
	private static final int INDEX_OF_FIRST_ITEM = 0;
	private HBox container;
	private double containerXPosition;
	private double containerYPosition;
	private int numberOfHeartsToDisplay;

	/**
	 * Constructs a {@code HeartDisplay} object and initializes the heart display at the specified position
	 * with the given number of hearts.
	 *
	 * @param xPosition        The X-coordinate of the heart display container.
	 * @param yPosition        The Y-coordinate of the heart display container.
	 * @param heartsToDisplay  The initial number of hearts to display.
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}

	/**
	 * Initializes the container (an {@code HBox}) for holding the heart images
	 * and sets its position on the screen.
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);
	}

	/**
	 * Populates the container with the specified number of heart images.
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(HEART_IMAGE_NAME)).toExternalForm()));
			heart.setFitHeight(HEART_HEIGHT);
			heart.setPreserveRatio(true);
			container.getChildren().add(heart);
		}
	}

	/**
	 * Removes one heart from the display, starting from the first heart in the container.
	 * This method should be called when the player takes damage.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty())
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
	}

	/**
	 * Retrieves the container holding the heart images.
	 *
	 * @return The {@code HBox} containing the heart images.
	 */
	public HBox getContainer() {
		return container;
	}
}
