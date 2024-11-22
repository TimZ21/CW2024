package com.example.demo.level;

import com.example.demo.actors.plane.Boss;
import com.example.demo.view.LevelView;
import com.example.demo.view.LevelViewLevelBoss;

/**
 * The {@code LevelBoss} class represents the second level of the game.
 * It extends {@code LevelParent} and defines the specific setup, enemy spawning,
 * and game-over conditions for Level Two.
 */
public class LevelBoss extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private LevelViewLevelBoss levelView;

	/**
	 * Constructs a {@code LevelBoss} instance with the specified screen dimensions.
	 *
	 * @param screenHeight The height of the game screen.
	 * @param screenWidth The width of the game screen.
	 */
	public LevelBoss(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss(getRoot());
	}

	/**
	 * Initializes friendly units in the level, including the player's plane.
	 * Adds the user's plane to the root group for display.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Checks if the game is over. If the user is destroyed, it triggers a game loss.
	 * If the boss is destroyed, it triggers a game win.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (boss.isDestroyed()) {
			winGame();
		}
	}

	/**
	 * Spawns enemy units in the level. Adds the boss to the enemy units if no enemies are present.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	/**
	 * Instantiates the level view specific to Level Two. Creates an instance of
	 * {@code LevelViewLevelBoss} and returns it.
	 *
	 * @return An instance of {@code LevelViewLevelBoss} for this level.
	 */
	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelBoss(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}
}
