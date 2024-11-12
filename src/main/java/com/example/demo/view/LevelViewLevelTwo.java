package com.example.demo.view;

import javafx.application.Platform;
import javafx.scene.Group;

/**
 * The {@code LevelViewLevelTwo} class is a specialized version of {@code LevelView}
 * for the second level of the game. It includes additional visual elements specific
 * to LevelTwo, such as the shield image.
 */
public class LevelViewLevelTwo extends LevelView {

	private static final int SHIELD_X_POSITION = 1150;
	private static final int SHIELD_Y_POSITION = 500;
	private final Group root;
	private final ShieldImage shieldImage;

	/**
	 * Constructs a {@code LevelViewLevelTwo} with the specified root and number of hearts to display.
	 *
	 * @param root The root {@code Group} for the level view.
	 * @param heartsToDisplay The number of hearts (lives) to display for the player.
	 */
	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
		addImagesToRoot();
	}

	/**
	 * Adds the shield image container to the root {@code Group}. This method ensures that
	 * the shield image is part of the scene graph and will be displayed correctly in LevelTwo.
	 */
	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage.getContainer());
	}

	/**
	 * Shows the shield image by making it visible. This method is used to display the
	 * shield when it is activated during gameplay.
	 */
	public void showShield() {
		shieldImage.showShield();
	}

	/**
	 * Hides the shield image by making it invisible. This method is used to hide the
	 * shield when it is deactivated during gameplay.
	 */
	public void hideShield() {
		shieldImage.hideShield();
	}
}
