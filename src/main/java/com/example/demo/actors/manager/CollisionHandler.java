package com.example.demo.actors.manager;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.ExplosionEffectManager;
import javafx.scene.Group;

import java.util.List;

/**
 * The {@code CollisionHandler} class is a singleton responsible for detecting
 * and handling collisions between different lists of game actors.
 * <p>
 * This class provides a thread-safe implementation of the Singleton pattern.
 * It ensures that only one instance of {@code CollisionHandler} exists throughout the application.
 * </p>
 */
public class CollisionHandler {

    /**
     * The single instance of {@code CollisionHandler}.
     */
    private static CollisionHandler instance;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * <p>
     * This ensures that the Singleton pattern is enforced.
     * </p>
     */
    private CollisionHandler() {
        // Initialization logic (if any)
    }

    /**
     * Provides access to the single instance of {@code CollisionHandler}.
     * <p>
     * This method uses a thread-safe double-checked locking mechanism to ensure
     * that the instance is created only once, even in multi-threaded environments.
     * </p>
     *
     * @return The single instance of {@code CollisionHandler}.
     */
    public static CollisionHandler getInstance() {
        if (instance == null) {
            synchronized (CollisionHandler.class) {
                if (instance == null) {
                    instance = new CollisionHandler();
                }
            }
        }
        return instance;
    }

    /**
     * Detects collisions between two lists of {@code ActiveActorDestructible} objects.
     * <p>
     * If a collision is detected, both actors involved in the collision will take damage.
     * </p>
     *
     * @param list1 The first list of {@code ActiveActorDestructible} objects (e.g., user projectiles).
     * @param list2 The second list of {@code ActiveActorDestructible} objects (e.g., enemy units).
     */
    public void detectCollisions(List<ActiveActorDestructible> list1, List<ActiveActorDestructible> list2) {
        for (ActiveActorDestructible actor1 : list1) {
            for (ActiveActorDestructible actor2 : list2) {
                // Check if the bounding boxes of the two actors intersect
                if (actor1.getBoundsInParent().intersects(actor2.getBoundsInParent())) {
                    // Apply damage to both actors upon collision
                    actor1.takeDamage();
                    actor2.takeDamage();
                }
            }
        }
    }

    /**
     * Detects collisions between two lists of {@code ActiveActorDestructible} objects and manages effects upon collision.
     * <p>This method also handles sound effects and visual effects when a destructive collision occurs,
     * only for collision between {@link com.example.demo.actors.projectile.UserProjectile} and {@link com.example.demo.actors.plane.EnemyPlane}.</p>
     *
     * @param list1 The first list of {@code ActiveActorDestructible} objects.
     * @param list2 The second list of {@code ActiveActorDestructible} objects.
     * @param root The root group where visual effects should be added.
     */
    public void detectCollisionsWithEffect(List<ActiveActorDestructible> list1, List<ActiveActorDestructible> list2, Group root) {
        for (ActiveActorDestructible actor1 : list1) {
            for (ActiveActorDestructible actor2 : list2) {
                if (actor1.getBoundsInParent().intersects(actor2.getBoundsInParent())) {
                    actor1.takeDamage();
                    actor2.takeDamage();

                    if (actor2.isDestroyed()) {
                        // Play sound effect
                        AudioManager.getInstance().playSoundEffect();

                        // Render explosion effect at actor2's position
                        double x = actor2.getAbsoluteX();
                        double y = actor2.getAbsoluteY();
                        new ExplosionEffectManager(x, y, root);
                    }
                }
            }
        }
    }
}
