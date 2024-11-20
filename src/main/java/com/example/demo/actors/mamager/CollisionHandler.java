package com.example.demo.actors.mamager;

import com.example.demo.actors.ActiveActorDestructible;
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
}
