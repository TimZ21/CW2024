package com.example.demo.actors.projectile;

import com.example.demo.actors.ActiveActorDestructible;

/**
 * The {@code Projectile} class is an abstract representation of projectiles in the game.
 * It extends {@code ActiveActorDestructible} and defines the common behavior and properties
 * for all projectiles, such as their appearance and how they interact with the game world.
 */
public abstract class Projectile extends ActiveActorDestructible {

	/**
	 * Constructs a {@code Projectile} instance with the specified image, size, and initial position.
	 *
	 * @param imageName   The name of the image file representing the projectile.
	 * @param imageHeight The height of the image, used for scaling.
	 * @param initialXPos The initial X-coordinate of the projectile.
	 * @param initialYPos The initial Y-coordinate of the projectile.
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	/**
	 * Handles the logic for when the projectile takes damage.
	 * A projectile is destroyed immediately upon taking damage.
	 */
	@Override
	public void takeDamage() {
		this.destroy();
	}

	/**
	 * Updates the position of the projectile. This method must be implemented
	 * by subclasses to define the specific movement behavior of the projectile.
	 */
	@Override
	public abstract void updatePosition();
}
