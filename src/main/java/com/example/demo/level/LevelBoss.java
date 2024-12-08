package com.example.demo.level;

import com.example.demo.actors.plane.Boss;
import com.example.demo.view.LevelView;
import com.example.demo.view.LevelViewLevelBoss;

/**
 * The {@code LevelBoss} class represents third level in the game which is a boss level,
 * challenging the player with a powerful enemy, the Boss.
 * It extends {@link LevelParent} and defines the specific setup, enemy spawning,
 * and game-over conditions for a critical encounter in the game.
 */
public class LevelBoss extends LevelParent {

	/**
	 * The relative path to the background image specifically for the boss level.
	 */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background_boss.jpg";

	/**
	 * The initial health with which the player starts in the boss level.
	 */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * The boss of this level, a significant enemy the player must defeat to progress.
	 */
	private final Boss boss;

	/**
	 * The level view for this boss level, handling all UI components that are specific to this level's layout,
	 * such as health bars, special indicators, or boss-related animations and effects.
	 */
	private LevelViewLevelBoss levelView;

	/**
	 * The fully qualified class name of the next level to transition to after completing this boss level.
	 */
	private static final String NEXT_LEVEL = "com.example.demo.level.LevelFinalBoss";


	/**
	 * Constructs a {@code LevelBoss} instance with the specified screen dimensions and initializes the environment.
	 * Sets up the boss battle by positioning the boss and configuring the initial settings for the player and the boss.
	 *
	 * @param screenHeight The height of the game screen, used to scale the game view appropriately.
	 * @param screenWidth The width of the game screen, used to place game elements correctly.
	 */
	public LevelBoss(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss(getRoot());
	}

	/**
	 * Initializes player-controlled units for this boss level by adding the user's plane to the scene.
	 * This method override the {@link LevelParent#initializeFriendlyUnits()}
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Checks the game over conditions for Level Two. This method determines if the game should end
	 * either by the player's defeat or by progressing to the next level after boss is defeated.
	 * This method override the {@link LevelParent#checkIfGameOver()} method.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (boss.isDestroyed()) {
			goToNextLevel(NEXT_LEVEL);
		}
	}

	/**
	 * Spawns enemy units in the level. Adds the boss to the enemy units if no enemies are present.
	 * This method override the {@link LevelParent#checkIfGameOver()} method.
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
