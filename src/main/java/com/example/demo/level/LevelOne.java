package com.example.demo.level;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.plane.EnemyPlane;
import com.example.demo.view.LevelView;

/**
 * The {@code LevelOne} class represents the first level of the game.
 * It defines specific behaviors for spawning enemies, game-over conditions,
 * and the transition to the next level.
 */
public class LevelOne extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.level.LevelBoss";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 10;
	private static final double ENEMY_SPAWN_PROBABILITY = 0.20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * Constructs a {@code LevelOne} instance with the specified screen dimensions.
	 *
	 * @param screenHeight The height of the game screen.
	 * @param screenWidth The width of the game screen.
	 */
	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the game is over. The game ends if the user is destroyed.
	 * If the user reaches the required number of kills, the game transitions to the next level.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (userHasReachedKillTarget()) {
			goToNextLevel(NEXT_LEVEL);
		}
	}

	/**
	 * Initializes friendly units in the level, including the user's plane.
	 * Adds the user's plane to the root group for rendering on the screen.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Spawns enemy units during the game. Enemy planes are added to the scene based
	 * on the defined spawn probability and current number of active enemies.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

	/**
	 * Instantiates the view for Level One, including health display and other UI elements.
	 *
	 * @return A {@code LevelView} instance specific to this level.
	 */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the user has reached the required number of kills to advance to the next level.
	 *
	 * @return {@code true} if the user has achieved the kill target, {@code false} otherwise.
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}
}
