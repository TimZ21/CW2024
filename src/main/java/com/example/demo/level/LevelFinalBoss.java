package com.example.demo.level;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.plane.Boss;
import com.example.demo.actors.plane.EnemyPlane;
import com.example.demo.view.LevelView;
import com.example.demo.view.LevelViewLevelBoss;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The {@code LevelFinalBoss} class represents the final boss level in the game,
 * extending {@link LevelParent}. This level includes unique challenges, notably
 * the presence of a powerful boss and a limited number of additional enemy planes.
 * It manages the spawning of these enemies, the conditions for winning or losing the level,
 * and the initialization of level-specific views and settings.
 *
 * <p>
 * See the source code at <a href=https://github.com/TimZ21/CW2024/blob/master/src/main/java/com/example/demo/level/LevelFinalBoss.java">LevelFinalBoss.java</a>
 */
public class LevelFinalBoss extends LevelParent {

    /**
     * The path to the background image used for the final boss level. This image sets the visual
     * theme and atmosphere appropriate for a climactic battle.
     */
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background_boss.jpg";

    /**
     * The path to the background image used for the final boss level. This image sets the visual
     * theme and atmosphere appropriate for a climactic battle.
     */
    private static final int TOTAL_ENEMIES = 3;

    /**
     * The probability of spawning a regular enemy in any given update frame, ensuring dynamic gameplay.
     */
    private static final double ENEMY_SPAWN_PROBABILITY = 0.20;

    /**
     * The initial health with which the player starts the final boss level, setting the difficulty level.
     */
    private static final int PLAYER_INITIAL_HEALTH = 7;

    /**
     * Represents the boss character in this level, managing all interactions and behaviors specific to the boss.
     */
    private Boss boss;

    /**
     * A flag to track whether the boss has been initially spawned to prevent multiple instances.
     */
    private boolean bossSpawned = false;

    /**
     * The level-specific view handling all UI components for the final boss level.
     */
    private LevelViewLevelBoss levelView;

    /**
     * Constructs a {@code LevelFinalBoss} instance with specified screen dimensions.
     * Initializes the game environment specific to the final boss level, setting up the player
     * and boss with initial configurations.
     *
     * @param screenHeight The height of the game screen
     * @param screenWidth The width of the game screen
     */
    public LevelFinalBoss(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        boss = new Boss(getRoot()); // Initialize the boss here and manage its lifecycle
    }

    /**
     * Initializes player-controlled units for the final boss level by adding the user's plane to the game scene.
     * This method override the {@link LevelParent#initializeFriendlyUnits()} method.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Manages the spawning of the boss and regular enemy planes. The boss is only spawned once.
     * Regular enemies are spawned based on a defined probability until
     * the total number of enemies on screen reaches the pre-set limit.
     * This method override the {@link LevelParent#spawnEnemyUnits()} method.
     */
    @Override
    protected void spawnEnemyUnits() {
        // Spawn the boss if it has not been added yet and is not destroyed
        if (!bossSpawned || (bossSpawned && boss.isDestroyed())) {
            boss = new Boss(getRoot()); // Reinstantiate the boss if needed
            addEnemyUnit(boss);
            bossSpawned = true;
        }

        // Regular enemy management
        int currentNumberOfEnemies = getCurrentNumberOfEnemies() - (bossSpawned && !boss.isDestroyed() ? 1 : 0);
        while (currentNumberOfEnemies < TOTAL_ENEMIES) {
            if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                addEnemyUnit(new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition));
                currentNumberOfEnemies++;
            }
        }
    }

    /**
     * Checks if the game should end based on the player's defeat or the boss's destruction.
     * Triggers the appropriate game over sequence by either losing or winning the game.
     * This method override the {@link LevelParent#checkIfGameOver()} method.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (boss != null && boss.isDestroyed()) {
            winGame();
        }
    }

    /**
     * Creates and returns a customized level view for the final boss level, including UI elements
     * like health bars and other indicators specific to this critical stage of the game.
     * This method override the {@link LevelParent#instantiateLevelView()} method.
     *
     * @return An instance of {@code LevelViewLevelBoss} tailored to the final boss level.
     */
    @Override
    protected LevelView instantiateLevelView() {
        levelView = new LevelViewLevelBoss(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }
}
