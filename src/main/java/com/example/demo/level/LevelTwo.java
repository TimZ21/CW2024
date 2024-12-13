package com.example.demo.level;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.plane.EnemyPlane;
import com.example.demo.view.LevelView;

/**
 * The {@code LevelTwo} class represents the second level of the game, featuring more challenging enemies
 * with increased health. This class extends {@link LevelParent} and defines level-specific behaviors and
 * game mechanics, such as enemy spawn rates, health settings, and the background environment.
 *
 *  * <p>
 *  * See the source code at <a href="https://github.com/TimZ21/CW2024/blob/master/src/main/java/com/example/demo/level/LevelTwo.java">LevelTwo.java</a>
 */
public class LevelTwo extends LevelParent {

    /**
     * The relative path to the background image for Level Two.
     */
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";

    /**
     * The fully qualified class name of the next level to transition to after completing Level Two.
     */
    private static final String NEXT_LEVEL = "com.example.demo.level.LevelBoss";

    /**
     * The image file name for enemy planes in Level Two.
     */
    private static final String IMAGE_NAME = "enemyplane2.png";

    /**
     * The total number of enemies that can be simultaneously present on the screen in Level Two.
     */
    private static final int TOTAL_ENEMIES = 7;

    /**
     * The number of kills the player needs to achieve to advance from Level Two to the next level.
     */
    private static final int KILLS_TO_ADVANCE = 15;

    /**
     * The probability of an enemy spawning in any given frame, expressed as a fraction of 1.
     * This probability factor controls the rate at which enemies appear, influencing the level's pacing and difficulty.
     */
    private static final double ENEMY_SPAWN_PROBABILITY = 0.25;

    /**
     * The initial health with which the player starts Level Two.
     */
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /**
     * Constructs a {@code LevelTwo} instance with specified screen dimensions, setting up the initial
     * game environment including background, player health, and enemy characteristics.
     *
     * @param screenHeight The height of the game screen.
     * @param screenWidth The width of the game screen.
     */
    public LevelTwo(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
    }

    /**
     * Checks the game over conditions for Level Two. This method determines if the game should end
     * either by the player's defeat or by progressing to the next level after achieving the required
     * number of kills.
     * This method override the {@link LevelParent#checkIfGameOver()} method.
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
     * Initializes player-controlled units for Level Two. This typically includes setting up the player's
     * plane on the screen.
     * This method override the {@link LevelParent#initializeFriendlyUnits()} method.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Dynamically spawns enemy units during the game based on a defined probability, which is higher
     * than in Level One. This method ensures a progressive challenge by introducing enemies with more
     * health and adjusting their spawn rate and total number.
     * This method override the {@link LevelParent#spawnEnemyUnits()} method.
     */
    @Override
    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
            if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                EnemyPlane newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition, 2, IMAGE_NAME); // Health is 2
                addEnemyUnit(newEnemy);
            }
        }
    }

    /**
     * Creates and returns the level view for Level Two, setting up necessary UI components like health heart.
     * This method override the {@link LevelParent#instantiateLevelView()} method.
     *
     * @return A {@link LevelView} instance customized for Level Two.
     */
    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Determines if the player has reached the required kill count to advance from Level Two to
     * the next level, facilitating game progression based on player performance.
     *
     * @return true if the kill target is met, false otherwise.
     */
    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }
}
