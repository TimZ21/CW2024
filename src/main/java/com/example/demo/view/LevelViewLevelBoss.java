package com.example.demo.view;

import javafx.scene.Group;

/**
 * The {@code LevelViewLevelBoss} class is a specialized version of {@link LevelView}
 * for the second level of the game. It includes additional visual elements specific
 * to LevelBoss, such as the shield image.
 */
public class LevelViewLevelBoss extends LevelView {

	/**
	 * The x-coordinate position for placing the shield image on the screen.
	 */
	private static final int SHIELD_X_POSITION = 1150;

	/**
	 * The y-coordinate position for placing the shield image on the screen.
	 */
	private static final int SHIELD_Y_POSITION = 500;

	/**
	 * The root {@code Group} that contains all visual elements for the LevelBoss view.
	 */
	private final Group root;

	/**
	 * The {@code ShieldImage} instance responsible for managing the display of the shield image in the LevelBoss.
	 */
	private final ShieldImage shieldImage;

	/**
	 * Constructs a {@code LevelViewLevelBoss} with the specified root and number of hearts to display.
	 *
	 * @param root The root {@code Group} for the level view.
	 * @param heartsToDisplay The number of hearts (lives) to display for the player.
	 */
	public LevelViewLevelBoss(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
		addImagesToRoot();
	}

	/**
	 * Adds the shield image container to the root {@code Group}. This method ensures that
	 * the shield image is part of the scene graph and will be displayed correctly in LevelBoss.
	 */
	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage.getContainer());
	}

	/**
	 * Returns the root {@code Group} for the level view.
	 *
	 * @return The root {@code Group} for the level view.
	 */
	public Group getRoot() {
		return root;
	}
}
