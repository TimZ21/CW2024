package com.example.demo.level;

import com.example.demo.actors.ActiveActorDestructible;
import javafx.scene.Group;

import java.util.List;

/**
 * The {@code ActorGroup} class implements the {@code GameComponent} interface,
 * managing a group of {@code ActiveActorDestructible} objects. It provides
 * methods for updating actors, removing destroyed actors, and handling actors
 * that move out of the screen bounds.
 */
public class ActorGroup implements GameComponent {

    private final List<ActiveActorDestructible> actors;
    private final Group root;

    /**
     * Constructs an {@code ActorGroup} with the specified list of actors and the root group.
     *
     * @param actors The list of {@code ActiveActorDestructible} objects to manage.
     * @param root   The root {@code Group} for adding and removing actors from the scene graph.
     */
    public ActorGroup(List<ActiveActorDestructible> actors, Group root) {
        this.actors = actors;
        this.root = root;
    }

    /**
     * Updates all actors in the group by invoking their {@code updateActor} method.
     * This method is called during each game loop iteration.
     */
    @Override
    public void update() {
        actors.forEach(ActiveActorDestructible::updateActor);
    }

    /**
     * Removes destroyed actors from the list and the scene graph.
     * This method helps free resources by removing actors marked as destroyed.
     */
    @Override
    public void removeDestroyed() {
        // Remove destroyed actors from the list and the scene graph
        actors.removeIf(ActiveActorDestructible::isDestroyed);
        root.getChildren().removeIf(node -> node instanceof ActiveActorDestructible && ((ActiveActorDestructible) node).isDestroyed());
    }

    /**
     * Removes actors that are completely out of the screen bounds.
     *
     * @param screenWidth The width of the game screen used to determine the out-of-bounds condition.
     */
    @Override
    public void removeOutOfBounds(double screenWidth) {
        actors.removeIf(actor -> {
            double x = actor.getLayoutX() + actor.getTranslateX();
            double width = actor.getBoundsInParent().getWidth();
            boolean outOfBounds = (x < -width || x > screenWidth);

            // If the actor is out of bounds, remove it from the scene graph
            if (outOfBounds) {
                root.getChildren().remove(actor);
                System.out.println("Actor removed from the screen.");
            }

            return outOfBounds;
        });
    }
}
