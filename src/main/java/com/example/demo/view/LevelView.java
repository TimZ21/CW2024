package com.example.demo.view;

import javafx.scene.Group;

/**
 * The {@code LevelView} class represents the visual components of a game level,
 * including the heart display, win image, and game over image. It manages the
 * initialization and display of these elements.
 */
public class LevelView {

	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int LOSS_SCREEN_X_POSITION = -160;
	private static final int LOSS_SCREEN_Y_POSITION = -375;
	private final Group root;
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
}
