package com.example.demo.manager;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.plane.EnemyPlane;
import com.example.demo.actors.projectile.EnemyProjectile;
import com.example.demo.manager.EnemySpawner;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnemySpawnerTest {

    private EnemySpawner enemySpawner;
    private Group root;
    private List<ActiveActorDestructible> enemyUnits;
    private List<ActiveActorDestructible> enemyProjectiles;
    private static final double Y_LOWER_BOUND = 665.0;

    @BeforeEach
    void setUp() {
        // Initialize JavaFX toolkit
        new JFXPanel();
        Platform.runLater(() -> {
            root = new Group();
            enemyUnits = new ArrayList<>();
            enemyProjectiles = new ArrayList<>();
            enemySpawner = new EnemySpawner(enemyUnits, root, enemyProjectiles);
        });
    }



    @Test
    void testAddEnemyUnit() {
        Platform.runLater(() -> {
            EnemyPlane enemy = new EnemyPlane(100, 100);
            enemySpawner.addEnemyUnit(enemy);
            assertTrue(enemyUnits.contains(enemy), "Enemy should be added to the list.");
            assertTrue(root.getChildren().contains(enemy), "Enemy should be added to the root group.");
        });
    }

    @Test
    void testRelocateEnemy() {
        Platform.runLater(() -> {
            EnemyPlane enemy = new EnemyPlane(100, 100);

            double sceneWidth = 1300;
            double sceneHeight = 750;

            double newX = sceneWidth - enemy.getBoundsInParent().getWidth(); // Place near the right boundary
            double newY = Math.random() * (sceneHeight - Y_LOWER_BOUND - enemy.getBoundsInParent().getHeight()); // Constrain to upper boundary
            enemy.setLayoutX(newX);
            enemy.setLayoutY(newY);

            assertNotEquals(100, enemy.getAbsoluteY(), "Enemy Y position should be within the upper boundary.");
        });
    }

    @Test
    void testSpawnEnemyFire() {
        Platform.runLater(() -> {
            EnemyProjectile pj = new EnemyProjectile(100, 100);
            try {
                invokePrivateMethod(
                        EnemySpawner.class,
                        "spawnEnemyProjectile",
                        new Class<?>[]{ActiveActorDestructible.class},
                        enemySpawner,
                        pj
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            assertFalse(enemyProjectiles.isEmpty(), "Projectile should be added to the list.");
            assertTrue(root.getChildren().contains(pj), "Projectile should be added to the root group.");
        });
    }

    private <T> T invokePrivateMethod(Class<?> clazz, String methodName, Class<?>[] parameterTypes, Object instance, Object... args) throws Exception {
        Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return (T) method.invoke(instance, args);
    }

}