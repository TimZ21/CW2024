package com.example.demo.level;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.plane.Boss;
import com.example.demo.actors.plane.EnemyPlane;
import com.example.demo.view.LevelView;
import com.example.demo.view.LevelViewLevelBoss;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelFinalBoss extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background_boss.jpg";
    private static final int TOTAL_ENEMIES = 3;  // Constant number of enemy planes, excluding the boss
    private static final double ENEMY_SPAWN_PROBABILITY = 0.20; // Spawn probability for regular enemies
    private static final int PLAYER_INITIAL_HEALTH = 10;
    private Boss boss;  // Only one boss instance should be active
    private boolean bossSpawned = false; // To ensure the boss is spawned only once
    private Random random = new Random();
    private LevelViewLevelBoss levelView;

    public LevelFinalBoss(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        boss = new Boss(getRoot()); // Initialize the boss here and manage its lifecycle
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

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

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (boss != null && boss.isDestroyed()) {
            winGame();
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        levelView = new LevelViewLevelBoss(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }
}
