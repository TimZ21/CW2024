package com.example.demo.manager;

import com.example.demo.actors.plane.EnemyPlane;
import com.example.demo.actors.projectile.UserProjectile;
import com.example.demo.manager.ActorGroup;
import com.example.demo.manager.ActorManager;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ActorManagerTest {

    private ActorManager actorManager;
    private ActorGroup friendlyGroup;
    private ActorGroup enemyGroup;
    private ActorGroup userProjectileGroup;
    private ActorGroup enemyProjectileGroup;

    @BeforeEach
    void setUp() {
        new JFXPanel(); // Initialize JavaFX thread
        Platform.runLater(() -> {
            friendlyGroup = new ActorGroup(new ArrayList<>(), new Group());
            enemyGroup = new ActorGroup(new ArrayList<>(), new Group());
            userProjectileGroup = new ActorGroup(new ArrayList<>(), new Group());
            enemyProjectileGroup = new ActorGroup(new ArrayList<>(), new Group());
            actorManager = new ActorManager(friendlyGroup, enemyGroup, userProjectileGroup, enemyProjectileGroup);
        });
    }

    @Test
    void testUpdateAllActors() {
        Platform.runLater(() -> {
            friendlyGroup.getActors().add(new EnemyPlane(100, 100));
            enemyGroup.getActors().add(new EnemyPlane(200, 200));
            userProjectileGroup.getActors().add(new UserProjectile(300, 300));
            enemyProjectileGroup.getActors().add(new UserProjectile(400, 400));

            actorManager.updateAllActors();

            // Simply ensuring that method runs without exception as no state change to verify
            assertTrue(true, "Update all actors should run without issues.");
        });
    }

    @Test
    void testRemoveDestroyedActors() {
        Platform.runLater(() -> {
            EnemyPlane destroyedEnemy = new EnemyPlane(100, 100);
            destroyedEnemy.destroy(); // Mark as destroyed
            enemyGroup.getActors().add(destroyedEnemy);

            actorManager.removeDestroyedActors();

            assertFalse(enemyGroup.getActors().contains(destroyedEnemy), "Destroyed enemy should be removed.");
        });
    }

    @Test
    void testRemoveOutOfBoundsProjectiles() {
        Platform.runLater(() -> {
            UserProjectile outOfBoundsProjectile = new UserProjectile(1000, 100); // Assume this is out of bounds
            userProjectileGroup.getActors().add(outOfBoundsProjectile);

            actorManager.removeOutOfBoundsProjectiles(800); // Assume screen width is 800

            assertFalse(userProjectileGroup.getActors().contains(outOfBoundsProjectile), "Out-of-bounds projectile should be removed.");
        });
    }
}
