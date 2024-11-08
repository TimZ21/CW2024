package com.example.demo.level;

import com.example.demo.actors.ActiveActorDestructible;
import java.util.List;

/**
 * CollisionHandler class is responsible for detecting and handling collisions
 * between different lists of game actors (e.g., user projectiles, enemy units, friendly units).
 */
public class CollisionHandler {

    /**
     * Detects collisions between two lists of ActiveActorDestructible objects.
     * If a collision is detected, both actors involved in the collision will take damage.
     *
     * @param list1 The first list of ActiveActorDestructible objects (e.g., user projectiles).
     * @param list2 The second list of ActiveActorDestructible objects (e.g., enemy units).
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
