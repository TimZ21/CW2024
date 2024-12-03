package com.example.demo.actors.manager;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.plane.Boss;
import com.example.demo.actors.plane.EnemyPlane;
import com.example.demo.actors.plane.UserPlane;
import com.example.demo.actors.projectile.EnemyProjectile;
import com.example.demo.actors.projectile.UserProjectile;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Bounds;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollisionHandlerTest {

    private CollisionHandler collisionHandler;

    @BeforeEach
    void setUp() {
        // Initialize JavaFX environment to handle component creation
        new JFXPanel();
        // Ensure CollisionHandler instance is initialized in the JavaFX thread
        Platform.runLater(() -> collisionHandler = CollisionHandler.getInstance());
    }

    @Test
    void testDetectCollisionsUserProjectileEnemyPlane() {
        Platform.runLater(() -> {
            // Mock setup
            EnemyPlane enemyPlane1 = new EnemyPlane(100,100, 1);
            EnemyPlane enemyPlane2 = new EnemyPlane(100, 200,2);
            EnemyPlane enemyPlane3 = new EnemyPlane(500, 500,2);

            UserProjectile userProjectile1 = new UserProjectile(100, 100);
            UserProjectile userProjectile2 = new UserProjectile(100, 200);
            UserProjectile userProjectile3 = new UserProjectile(20, 20);
            // List setup
            List<ActiveActorDestructible> list1 = List.of(enemyPlane1, enemyPlane2, enemyPlane3);
            List<ActiveActorDestructible> list2 = List.of(userProjectile1, userProjectile2, userProjectile3);

            // Test collision detection
            collisionHandler.detectCollisions(list1, list2);

            // Assertions to verify interactions
            assertEquals( 0,enemyPlane1.getHealth());
            assertTrue(enemyPlane1.isDestroyed());
            assertEquals(1, enemyPlane2.getHealth());
            assertEquals(2, enemyPlane3.getHealth());
            assertTrue(userProjectile1.isDestroyed());
            assertTrue(userProjectile2.isDestroyed());
            assertFalse(userProjectile3.isDestroyed());
        });
    }

    @Test
    void testDetectCollisionsUserPlaneEnemyProjectile() {
        Platform.runLater(() -> {
            UserPlane userPlane = new UserPlane(5);
            EnemyProjectile enemyProjectile = new EnemyProjectile(5, 300);

            // List setup
            List<ActiveActorDestructible> list1 = List.of(userPlane);
            List<ActiveActorDestructible> list2 = List.of(enemyProjectile);

            // Test collision detection
            collisionHandler.detectCollisions(list1, list2);

            // Assertions to verify interactions
            assertEquals( 4,userPlane.getHealth());
            assertTrue(enemyProjectile.isDestroyed());
        });
    }

    @Test
    void testDetectCollisionsUserPlaneEnemyProjectileDestried() {
        Platform.runLater(() -> {
            UserPlane userPlane = new UserPlane(1);
            EnemyProjectile enemyProjectile = new EnemyProjectile(5, 300);

            // List setup
            List<ActiveActorDestructible> list1 = List.of(userPlane);
            List<ActiveActorDestructible> list2 = List.of(enemyProjectile);

            // Test collision detection
            collisionHandler.detectCollisions(list1, list2);

            // Assertions to verify interactions
            assertEquals( 0,userPlane.getHealth());
            assertTrue(userPlane.isDestroyed());
            assertTrue(enemyProjectile.isDestroyed());
        });
    }

    @Test
    void testDetectCollisionsUserPlaneEnemyProjectileNoCollision() {
        Platform.runLater(() -> {
            UserPlane userPlane = new UserPlane(1);
            EnemyProjectile enemyProjectile = new EnemyProjectile(500, 300);

            // List setup
            List<ActiveActorDestructible> list1 = List.of(userPlane);
            List<ActiveActorDestructible> list2 = List.of(enemyProjectile);

            // Test collision detection
            collisionHandler.detectCollisions(list1, list2);

            // Assertions to verify interactions
            assertEquals( 1,userPlane.getHealth());
            assertFalse(userPlane.isDestroyed());
            assertFalse(enemyProjectile.isDestroyed());
        });
    }

    @Test
    void testDetectCollisionsProjectiles() {
        Platform.runLater(() -> {
            UserProjectile userProjectile1 = new UserProjectile(100, 100);
            UserProjectile userProjectile2 = new UserProjectile(200, 300);
            EnemyProjectile enemyProjectile1 = new EnemyProjectile(100, 100);
            EnemyProjectile enemyProjectile2 = new EnemyProjectile(400, 600);

            // List setup
            List<ActiveActorDestructible> list1 = List.of(userProjectile1, userProjectile2);
            List<ActiveActorDestructible> list2 = List.of(enemyProjectile1, enemyProjectile2);

            // Test collision detection
            collisionHandler.detectCollisions(list1, list2);

            // Assertions to verify interactions
            assertTrue(userProjectile1.isDestroyed());
            assertFalse(userProjectile2.isDestroyed());
            assertTrue(enemyProjectile1.isDestroyed());
            assertFalse(enemyProjectile2.isDestroyed());
        });
    }



    @Test
    void testDetectCollisionse() {
        Platform.runLater(() -> {
            // Mock setup
            EnemyPlane enemyPlane1 = new EnemyPlane(100,100, 1);
            EnemyPlane enemyPlane2 = new EnemyPlane(100, 200,2);
            UserProjectile userProjectile1 = new UserProjectile(100, 100);
            UserProjectile userProjectile2 = new UserProjectile(100, 200);
            // List setup
            List<ActiveActorDestructible> list1 = List.of(enemyPlane1, enemyPlane2);
            List<ActiveActorDestructible> list2 = List.of(userProjectile1, userProjectile2);

            // Test collision detection
            collisionHandler.detectCollisions(list1, list2);

            // Assertions to verify interactions
            assertEquals( 0,enemyPlane1.getHealth()); // Assuming takeDamage reduces health by 1
            assertEquals(1, enemyPlane2.getHealth()); // Same as above
            assertTrue(userProjectile1.isDestroyed()); // No collision, no damage
            assertTrue(userProjectile2.isDestroyed()); // No collision, no damage
        });
    }


}
