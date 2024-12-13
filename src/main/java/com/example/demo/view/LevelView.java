package com.example.demo.view;

import javafx.scene.Group;

/**
 * The {@code LevelView} class represents the visual components of a game level,
 * including the heart display, win image, and game over image. It manages the
 * initialization and display of these elements.
 *
 * <p>
 * See the source code at <a href="https://github.com/TimZ21/CW2024/blob/master/src/main/java/com/example/demo/view/LevelView.java">LevelView.java</a>
 */
public class LevelView {

	/**
	 * The x-coordinate position where the heart display is initialized on the screen.
	 * This defines the horizontal starting point for the heart display.
	 */
	private static final double HEART_DISPLAY_X_POSITION = 5;

	/**
	 * The y-coordinate position where the heart display is initialized on the screen.
	 * This defines the vertical starting point for the heart display.
	 */
	private static final double HEART_DISPLAY_Y_POSITION = 25;

	/**
	 * The root {@code Group} that contains all visual elements of the level.
	 * This group acts as the container for adding or modifying game-level visual components.
	 */
	private final Group root;

	/**
	 * The {@code HeartDisplay} instance that manages the visual representation of player's lives.
	 * It is responsible for displaying and updating the hearts based on the player's health.
	 */
	private final HeartDisplay heartDisplay;


	/**
	 * Constructs a {@code LevelView} with the specified root group and the number of hearts to display.
	 *
	 * @param root The root {@code Group} for the level view, where visual elements will be added.
	 * @param heartsToDisplay The number of hearts (lives) to display for the player.
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
	}

	/**
	 * Displays the heart display on the screen by adding it to the root group.
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}


	/**
	 * Updates the heart display by removing hearts based on the number of hearts remaining.
	 * This method removes hearts from the display if the player has lost lives.
	 *
	 * @param heartsRemaining The number of hearts (lives) remaining for the player.
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

	/**
	 * Returns the heart display instance.
	 *
	 * @return The {@code HeartDisplay} instance.
	 */
	public HeartDisplay getHeartDisplay() {
		return heartDisplay;
	}
}
