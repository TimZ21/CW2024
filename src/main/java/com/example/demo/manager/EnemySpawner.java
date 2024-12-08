package com.example.demo.manager;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.plane.FighterPlane;
import javafx.scene.Group;

import java.util.List;

/**
 * The {@code EnemySpawner} is responsible for managing the spawning and positioning of enemy units in the game.
 * This class is responsible for adding enemy units to the game scene, positioning them within valid bounds, and managing their projectile firing.
 */
public class EnemySpawner {

    /**
     * List of all active enemy units currently in the game.
     */
    private final List<ActiveActorDestructible> enemyUnits;

    /**
     * The main group root of the game scene where all game elements are added.
     */
    private final Group root;

    /**
     * List of all projectiles fired by enemy units.
     */
    private final List<ActiveActorDestructible> enemyProjectiles;

    /**
     * Lower boundary on the Y-axis to prevent enemy spawn below this line. Helps maintain enemies within visible game area.
     */
    private static final double Y_LOWER_BOUND = 665.0;

    /**
     * Constructs an {@code EnemySpawner} with the specified list of enemy units and the root group.
     *
     * @param enemyUnits The list of enemy units to manage.
     * @param root The root {@code Group} for adding enemy units to the scene.
     * @param enemyProjectiles The list of enemy projectiles.
     */
    public EnemySpawner(List<ActiveActorDestructible> enemyUnits, Group root, List<ActiveActorDestructible> enemyProjectiles) {
        this.enemyUnits = enemyUnits;
        this.root = root;
        this.enemyProjectiles = enemyProjectiles;
    }

    /**
     * Attempts to add an enemy unit to the game scene ensuring no overlapping with existing units and within the right game boundaries.
     *
     * @param enemy The enemy unit to add.
     */
    public void addEnemyUnit(ActiveActorDestructible enemy) {
        boolean positionValid;
        int maxAttempts = 10; // Limit attempts to find a valid position
        int attempts = 0;

        do {
            positionValid = true;
            for (ActiveActorDestructible existingEnemy : enemyUnits) {
                if (enemy.getBoundsInParent().intersects(existingEnemy.getBoundsInParent())) {
                    positionValid = false;
                    relocateEnemy(enemy); // Relocate enemy to a new position
                     ;
                }
            }
            attempts++;
        } while (!positionValid && attempts < maxAttempts);

        if (positionValid) {
            enemyUnits.add(enemy);
            root.getChildren().add(enemy);
        } else {
            System.err.println("Failed to place enemy after " + maxAttempts + " attempts.");
        }
    }

    /**
     * Relocates the enemy to a random position at the right boundary of the game area,
     * ensuring it is above the lower boundary.
     *
     * @param enemy The enemy to relocate.
     */
    private void relocateEnemy(ActiveActorDestructible enemy) {
        double sceneWidth = root.getScene().getWidth();
        double sceneHeight = root.getScene().getHeight();

        double newX = sceneWidth - enemy.getBoundsInParent().getWidth(); // Place near the right boundary
        double newY = Math.random() * (sceneHeight - Y_LOWER_BOUND - enemy.getBoundsInParent().getHeight()); // Constrain to upper boundary
        enemy.setLayoutX(newX);
        enemy.setLayoutY(newY);
    }

    /**
     * Generates fire for all enemy units, spawning projectiles.
     */
    public void generateEnemyFire() {
        enemyUnits.forEach(enemy -> {
            List<ActiveActorDestructible> projectiles = ((FighterPlane) enemy).fireProjectile();
            if (projectiles != null && !projectiles.isEmpty()) {
                projectiles.forEach(this::spawnEnemyProjectile); // Handle each projectile
            }
        });
    }


    /**
     * Spawns a projectile fired by an enemy unit.
     *
     * @param projectile The projectile to add to the scene.
     */
    private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
        if (projectile != null) {
            root.getChildren().add(projectile); // Add to scene graph
            enemyProjectiles.add(projectile);  // Track in list
        }
    }

}
