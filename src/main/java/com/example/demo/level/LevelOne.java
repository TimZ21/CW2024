package com.example.demo.level;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.plane.EnemyPlane;
import com.example.demo.view.LevelView;

/**
 * The {@code LevelOne} class represents the first level of the game.
 * It defines specific behaviors for spawning enemies, game-over conditions,
 * and the transition to the next level. This class extends {@link LevelParent}
 * and customizes the level-specific settings and mechanics, including enemy
 * spawning probabilities, initial player health, and background settings.
 *
 * <p>
 * See the source code at <a href="https://github.com/TimZ21/CW2024/blob/master/src/main/java/com/example/demo/level/LevelOne.java">LevelOne.java</a>
 */
public class LevelOne extends LevelParent {
	/**
	 * The relative path to the background image for Level One.
	 * This image sets the visual theme and atmosphere for the first level of the game.
	 */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";

	/**
	 * The fully qualified class name of the next level to transition to after completing Level One.
	 * This is used to dynamically load Level Two when the player meets the level advancement criteria.
	 */
	private static final String NEXT_LEVEL = "com.example.demo.level.LevelTwo";

	/**
	 * The total number of enemies that can be simultaneously present on the screen in Level One.
	 * This constant is used to control the game difficulty by limiting the number of active enemies.
	 */
	private static final int TOTAL_ENEMIES = 5;

	/**
	 * The number of kills the player needs to achieve to advance from Level One to Level Two.
	 * This kill count is a gameplay mechanic to increase the challenge and engagement of the level.
	 */
	private static final int KILLS_TO_ADVANCE = 10;

	/**
	 * The probability of an enemy spawning in any given frame, expressed as a fraction of 1.
	 * This probability factor controls the rate at which enemies appear, influencing the level's pacing and difficulty.
	 */
	private static final double ENEMY_SPAWN_PROBABILITY = 0.20;

	/**
	 * The initial health with which the player starts Level One.
	 * This value is crucial for balancing the game's difficulty, particularly in terms of how forgiving the level is towards player mistakes.
	 */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * Constructs a {@code LevelOne} instance with the specified screen dimensions.
	 * Initializes the level with a specific background image and sets the initial health
	 * for the player.
	 *
	 * @param screenHeight The height of the game screen, used to scale the game view.
	 * @param screenWidth The width of the game screen, used to position game elements.
	 */
	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the game is over by evaluating whether the player's plane has been destroyed
	 * or the required number of enemy kills has been reached to advance to the next level.
	 * Triggers either the win or lose game scenario accordingly.
	 * This method override the {@link LevelParent#checkIfGameOver()}
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
	 * Initializes friendly units in the level, specifically adding the user's plane
	 * to the scene. This setup is crucial for ensuring that player-controlled elements
	 * are active and interactable within the game environment.
	 * This method override the {@link LevelParent#initializeFriendlyUnits()}
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Dynamically spawns enemy units based on the defined probability and the difference
	 * between the total enemies allowed and those currently active. Ensures the level
	 * maintains a consistent challenge by replenishing enemy units.
	 * This method override the {@link LevelParent#spawnEnemyUnits()}
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
	 * This method override the {@link LevelParent#instantiateLevelView()}
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
