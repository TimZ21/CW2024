package com.example.demo.actors;

import com.example.demo.Destructible;

/**
 * The {@code ActiveActorDestructible} class is an abstract representation of an actor
 * in the game that can take damage and be destroyed. It extends the {@code ActiveActor} class
 * and implements the {@code Destructible} interface, providing additional functionality
 * for managing the actor's destruction state.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	private boolean isDestroyed;

	/**
	 * Constructs an {@code ActiveActorDestructible} instance with the specified image,
	 * size, and position.
	 *
	 * @param imageName   The name of the image file representing the actor.
	 * @param imageHeight The height of the actor's image.
	 * @param initialXPos The initial X-coordinate of the actor.
	 * @param initialYPos The initial Y-coordinate of the actor.
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	/**
	 * Updates the position of the actor.
	 * Subclasses must implement this method to define specific movement behavior.
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * Updates the actor's state.
	 * Subclasses must implement this method to define how the actor behaves during the game loop.
	 */
	public abstract void updateActor();

	/**
	 * Defines the logic for taking damage.
	 * Subclasses must implement this method to specify how the actor reacts to damage.
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Marks the actor as destroyed.
	 */
	@Override
	public void destroy() {
		setDestroyed(true);
	}

	/**
	 * Sets the destruction state of the actor.
	 *
	 * @param isDestroyed {@code true} if the actor is destroyed, {@code false} otherwise.
	 */
	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	/**
	 * Returns whether the actor is destroyed.
	 *
	 * @return {@code true} if the actor is destroyed, {@code false} otherwise.
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
}
