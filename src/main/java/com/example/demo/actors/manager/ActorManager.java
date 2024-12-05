package com.example.demo.actors.manager;

/**
 * The {@code ActorManager} class is responsible for managing different groups of game actors,
 * including friendly units, enemy units, user projectiles, and enemy projectiles. This class
 * simplifies interactions with complex subsystems of actor groups, effectively implementing
 * a Facade design pattern by providing a high-level interface that hides the underlying complexity
 * of individual actor group operations.
 *
 * <p>This class also hints at a Composite pattern usage through its management of {@link ActorGroup}
 * objects that treat individual and composite objects uniformly. However, this implementation focuses
 * primarily on the aggregation of these groups rather than a full composite structure.</p>
 *
 * <p>Depending on the application's context, if the {@link ActorGroup} instances are managed as singletons
 * or instantiated in a controlled manner that restricts multiple creations, the Singleton pattern could
 * also be inferred. However, such implementation details are not specified here and would depend on the
 * instantiation mechanism provided elsewhere in the application.</p>
 *
 * <p>The {@link ActorManager} thus acts as a central point for managing the life cycle and interaction
 * of all game actors across different groups, streamlining updates, destruction checks, and boundary management.</p>
 */
public class ActorManager {

    /**
     * Manages the group of friendly units in the game. Responsible for updating,
     * rendering, and lifecycle management of all friendly actors.
     */
    private final ActorGroup friendlyGroup;

    /**
     * Manages the group of enemy units. This group handles updating and managing
     * the lifecycle and behaviors of all enemy actors in the game.
     */
    private final ActorGroup enemyGroup;

    /**
     * Manages projectiles launched by the player. This includes updating their state,
     * rendering them on the screen, and removing them when they go out of bounds or hit a target.
     */
    private final ActorGroup userProjectileGroup;

    /**
     * Manages projectiles launched by enemies. Similar to userProjectileGroup,
     * this manages the state, visibility, and removal of enemy-fired projectiles.
     */
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
