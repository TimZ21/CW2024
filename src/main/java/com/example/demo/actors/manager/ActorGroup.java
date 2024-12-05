package com.example.demo.actors.manager;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.GameComponent;
import javafx.scene.Group;

import java.util.List;

/**
 * The {@code ActorGroup} class implements the {@code GameComponent} interface,
 * managing a group of {@code ActiveActorDestructible} objects. It provides
 * methods for updating actors, removing destroyed actors, and handling actors
 * that move out of the screen bounds.
 */
public class ActorGroup implements GameComponent {

    /**
     * A list of actors that this group manages. Each actor is an instance of {@code ActiveActorDestructible},
     * which contains logic for its own update and render methods, and tracks its destruction state.
     */
    private final List<ActiveActorDestructible> actors;

    /**
     * The root group where all actors are added for rendering in the scene. This group acts as the
     * primary node that contains all child actor nodes, allowing for collective manipulation of visibility or removal.
     */
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

    /**
     * Returns the list of actors managed by this group.
     *
     * @return The list of {@code ActiveActorDestructible} actors.
     */
    public List<ActiveActorDestructible> getActors() {
        return actors;
    }
}
