package com.example.demo.actors.projectile;

import static com.example.demo.level.LevelParent.VELOCITY_CHANGE;

/**
 * The {@code UserProjectile} class extends {@link Projectile} to represent projectiles fired by the player's plane.
 * This class encapsulates the specific characteristics of the player's projectiles, including their movement behavior
 * and visual representation. It travels horizontally across the screen at a constant velocity.
 *
 * See the source code at <a href=https://github.com/TimZ21/CW2024/blob/master/src/main/java/com/example/demo/actors/projectile/UserProjectile.java">UserProjectile.java</a>
 */
public class UserProjectile extends Projectile {
	/**
	 * Image file for the projectile's appearance.
	 */
	private static final String IMAGE_NAME = "userfire.png";
	/**
	 * The display height of the projectile's image.
	 */
	private static final int IMAGE_HEIGHT = 6;
	/**
	 * Horizontal velocity of the projectile.
	 */
	private static final double HORIZONTAL_VELOCITY = 20/VELOCITY_CHANGE;

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
