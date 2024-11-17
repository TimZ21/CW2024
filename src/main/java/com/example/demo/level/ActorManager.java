package com.example.demo.level;

import com.example.demo.actors.ActiveActorDestructible;

/**
 * The {@code ActorManager} class is responsible for managing different groups of game actors,
 * including friendly units, enemy units, user projectiles, and enemy projectiles.
 * It utilizes the {@code ActorGroup} class to perform operations such as updating actors,
 * removing destroyed actors, and handling projectiles that move out of screen bounds.
 */
public class ActorManager {

    private final ActorGroup friendlyGroup;
    private final ActorGroup enemyGroup;
    private final ActorGroup userProjectileGroup;
    private final ActorGroup enemyProjectileGroup;

    /**
     * Constructs an {@code ActorManager} with the specified actor groups.
     *
     * @param friendlyGroup        The {@code ActorGroup} for friendly units.
     * @param enemyGroup           The {@code ActorGroup} for enemy units.
     * @param userProjectileGroup  The {@code ActorGroup} for user projectiles.
     * @param enemyProjectileGroup The {@code ActorGroup} for enemy projectiles.
     */
    public ActorManager(ActorGroup friendlyGroup, ActorGroup enemyGroup,
                        ActorGroup userProjectileGroup, ActorGroup enemyProjectileGroup) {
        this.friendlyGroup = friendlyGroup;
        this.enemyGroup = enemyGroup;
        this.userProjectileGroup = userProjectileGroup;
        this.enemyProjectileGroup = enemyProjectileGroup;
    }

    /**
     * Updates all actors in the game, including friendly units, enemy units,
     * user projectiles, and enemy projectiles.
     * This method should be called during each game loop iteration.
     */
    public void updateAllActors() {
        friendlyGroup.update();
        enemyGroup.update();
        userProjectileGroup.update();
        enemyProjectileGroup.update();
    }

    /**
     * Removes all destroyed actors from each actor group, freeing up resources.
     * This method helps prevent memory leaks by ensuring destroyed actors are removed from the scene.
     */
    public void removeDestroyedActors() {
        friendlyGroup.removeDestroyed();
        enemyGroup.removeDestroyed();
        userProjectileGroup.removeDestroyed();
        enemyProjectileGroup.removeDestroyed();
    }

    /**
     * Removes projectiles that are completely out of the screen bounds for both user
     * and enemy projectile groups.
     *
     * @param screenWidth The width of the game screen used to determine out-of-bounds condition.
     */
    public void removeOutOfBoundsProjectiles(double screenWidth) {
        userProjectileGroup.removeOutOfBounds(screenWidth);
        enemyProjectileGroup.removeOutOfBounds(screenWidth);
    }
}
