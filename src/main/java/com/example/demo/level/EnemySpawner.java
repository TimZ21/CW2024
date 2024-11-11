package com.example.demo.level;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.plane.FighterPlane;
import javafx.scene.Group;

import java.util.List;

/**
 * The {@code EnemySpawner} class is responsible for managing the logic
 * of adding enemy units and generating their projectiles.
 */
public class EnemySpawner {

    private final List<ActiveActorDestructible> enemyUnits;
    private final Group root;
    private final List<ActiveActorDestructible> enemyProjectiles;

    /**
     * Constructs an {@code EnemySpawner} with the specified list of enemy units and the root group.
     *
     * @param enemyUnits The list of enemy units to manage.
     * @param root The root {@code Group} for adding enemy units to the scene.
     */
    public EnemySpawner(List<ActiveActorDestructible> enemyUnits, Group root, List<ActiveActorDestructible> enemyProjectiles) {
        this.enemyUnits = enemyUnits;
        this.root = root;
        this.enemyProjectiles = enemyProjectiles;
    }

    /**
     * Adds an enemy unit to the list and the scene graph.
     *
     * @param enemy The enemy unit to add.
     */
    public void addEnemyUnit(ActiveActorDestructible enemy) {
        enemyUnits.add(enemy);
        root.getChildren().add(enemy);
    }

    /**
     * Generates fire for all enemy units, spawning projectiles.
     */
    public void generateEnemyFire() {
        enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
    }


    /**
     * Spawns a projectile fired by an enemy unit.
     *
     * @param projectile The projectile to add to the scene.
     */
    private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
        if (projectile != null) {
            root.getChildren().add(projectile);
            enemyProjectiles.add(projectile);
        }
    }
}
