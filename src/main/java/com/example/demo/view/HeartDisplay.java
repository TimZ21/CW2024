package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Objects;

/**
 * The {@code HeartDisplay} class represents a visual health display for the player
 * using heart icons. It uses an {@code HBox} to manage the layout of heart images
 * and allows dynamically removing hearts as the player takes damage.
 *
 * <p>
 * See the source code at <a href="https://github.com/TimZ21/CW2024/blob/master/src/main/java/com/example/demo/view/HeartDisplay.java">HeartDisplay.java</a>
 */
public class HeartDisplay {

	/**
	 * The path to the heart image resource used in the heart display.
	 */
	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart_pixel.png";

	/**
	 * The height of each heart image displayed. This constant defines the size of the heart images in the UI.
	 */
	private static final int HEART_HEIGHT = 50;

	/**
	 * The index of the first item in the container. Used for operations that target the first element, such as removal.
	 */
	private static final int INDEX_OF_FIRST_ITEM = 0;

	/**
	 * The container for the heart images. It is an {@code HBox} that arranges the heart images horizontally.
	 */
	private HBox container;

	/**
	 * The x-coordinate position of the heart display container on the screen. Determines where the heart display appears horizontally.
	 */
	private final double containerXPosition;

	/**
	 * The y-coordinate position of the heart display container on the screen. Determines where the heart display appears vertically.
	 */
	private final double containerYPosition;

	/**
	 * The number of hearts to display initially. This value can be set when the heart display is created and adjusted if necessary.
	 */
	private final int numberOfHeartsToDisplay;
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
