package com.example.demo.actors;

import com.example.demo.actors.projectile.Projectile;

/**
 * The {@code ActiveActorDestructible} class is an abstract representation of an actor
 * in the game that can take damage and be destroyed. It extends the {@link ActiveActor} class
 * and implements the {@link  Destructible} interface, providing additional functionality
 * for managing the actor's destruction state.
 * <p>
 * See the source code at <a href=https://github.com/TimZ21/CW2024/blob/master/src/main/java/com/example/demo/actors/ActiveActorDestructible.java">ActiveActorDestructible.java</a>
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
	 * This method overrides the {@link ActiveActor#updatePosition()} method
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
	 * This method overrides the {@link Destructible#takeDamage()} method
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Marks the actor as destroyed.
	 * This method overrides the {@link Destructible#takeDamage()} method
	 */
	@Override
	public void destroy() {
		setDestroyed();
	}

	/**
	 * Sets the destruction state of the actor.
	 */
	protected void setDestroyed() {
		this.isDestroyed = true;
	}

	/**
	 * Checks if the actor has been destroyed.
	 *
	 * @return true if the actor is destroyed, false otherwise
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}

	/**
	 * Calculates the absolute x-coordinate of the actor, considering its layout and translation.
	 *
	 * @return the absolute x-coordinate
	 */
	public double getAbsoluteX() {
		return getLayoutX() + getTranslateX();
	}

	/**
	 * Calculates the absolute y-coordinate of the actor, considering its layout and translation.
	 *
	 * @return the absolute y-coordinate
	 */
	public double getAbsoluteY() {
		return getLayoutY() + getTranslateY();
	}

}
