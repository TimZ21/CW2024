package com.example.demo.level;

import com.example.demo.actors.ActiveActorDestructible;
import javafx.scene.Group;
import java.util.List;

public class ActorManager {

    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;
    private final Group root;

    public ActorManager(List<ActiveActorDestructible> friendlyUnits,
                        List<ActiveActorDestructible> enemyUnits,
                        List<ActiveActorDestructible> userProjectiles,
                        List<ActiveActorDestructible> enemyProjectiles,
                        Group root) {
        this.friendlyUnits = friendlyUnits;
        this.enemyUnits = enemyUnits;
        this.userProjectiles = userProjectiles;
        this.enemyProjectiles = enemyProjectiles;
        this.root = root;
    }

    /**
     * Updates all active actors (friendly units, enemy units, and projectiles).
     */
    public void updateAllActors() {
        friendlyUnits.forEach(ActiveActorDestructible::updateActor);
        enemyUnits.forEach(ActiveActorDestructible::updateActor);
        userProjectiles.forEach(ActiveActorDestructible::updateActor);
        enemyProjectiles.forEach(ActiveActorDestructible::updateActor);
    }

    /**
     * Removes all destroyed actors from the scene and their respective lists.
     */
    public void removeDestroyedActors() {
        removeActorsFromList(friendlyUnits);
        removeActorsFromList(enemyUnits);
        removeActorsFromList(userProjectiles);
        removeActorsFromList(enemyProjectiles);
    }

    /**
     * Removes destroyed actors from a specified list and the scene graph.
     *
     * @param actors The list of actors to check for destruction.
     */
    private void removeActorsFromList(List<ActiveActorDestructible> actors) {
        actors.removeIf(ActiveActorDestructible::isDestroyed);
        root.getChildren().removeIf(node -> node instanceof ActiveActorDestructible && ((ActiveActorDestructible) node).isDestroyed());
    }

    /**
     * Removes projectiles that are completely out of the screen bounds.
     */
    public void removeOutOfBoundsProjectiles() {
        removeOutOfBoundsFromList(userProjectiles);
        removeOutOfBoundsFromList(enemyProjectiles);
    }

    /**
     * Removes projectiles from the list and the scene if they are completely out of the screen bounds.
     *
     * @param projectiles The list of projectiles to check and remove if necessary.
     */
    private void removeOutOfBoundsFromList(List<ActiveActorDestructible> projectiles) {
        double screenWidth = 1300;
        System.out.println(screenWidth);
        projectiles.removeIf(projectile -> {
            double x = projectile.getLayoutX() + projectile.getTranslateX();
            double projectileWidth = projectile.getBoundsInParent().getWidth();

            // Set the minimum X bound as negative projectile width and use scene width for maximum bound
            boolean outOfBounds = (x < -projectileWidth || x > screenWidth);

            // If the projectile is out of bounds, remove it from the scene graph
            if (outOfBounds) {
                root.getChildren().remove(projectile);
                System.out.println("Projectile removed from the screen and memory freed.");
            }

            return outOfBounds; // Remove from the list if out of bounds
        });
    }
}
