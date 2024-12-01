package com.example.demo.actors.projectile;

/**
 * The {@code UserProjectile} class extends {@link Projectile} to represent projectiles fired by the player's plane.
 * This class encapsulates the specific characteristics of the player's projectiles, including their movement behavior
 * and visual representation. It travels horizontally across the screen at a constant velocity.
 */
public class UserProjectile extends Projectile {

	private static final String IMAGE_NAME = "userfire.png";  // Image file for the projectile's appearance.
	private static final int IMAGE_HEIGHT = 6;  // The display height of the projectile's image.
	private static final int HORIZONTAL_VELOCITY = 6;  // Horizontal velocity of the projectile.

	/**
	 * Constructs a {@code UserProjectile} instance with the specified initial position.
	 *
	 * @param initialXPos The initial X-coordinate of the projectile where it gets created.
	 * @param initialYPos The initial Y-coordinate of the projectile where it gets created.
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the projectile by moving it horizontally to the right.
	 * This method overrides the {@link Projectile#updatePosition()} method to provide the specific horizontal movement behavior.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the actor's state. For the user projectile, this method is primarily concerned with position updates,
	 * as defined in {@link Projectile#updatePosition()}.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
