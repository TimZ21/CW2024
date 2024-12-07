package com.example.demo.actors.manager;

/**
 * The {@code GameComponent} interface defines the essential methods
 * for updating game components, removing destroyed components,
 * and handling components that move out of the screen bounds.
 * It provides a standardized way for game components to be managed
 * during the game loop.
 */
public interface GameComponent {

    /**
     * Updates the state of the game component.
     * This method is intended to be called during each game loop iteration.
     */
    void update();

    /**
     * Removes any destroyed game components from the scene and associated lists.
     * This method should handle cleanup tasks to free resources.
     */
    void removeDestroyed();

    /**
     * Removes game components that are out of the screen bounds.
     *
     * @param screenWidth The width of the game screen used to determine the out-of-bounds condition.
     */
    void removeOutOfBounds(double screenWidth);
}
