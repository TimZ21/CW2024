package com.example.demo.actors.projectile;

import static com.example.demo.level.LevelParent.VELOCITY_CHANGE;

/**
 * The {@code EnemyProjectile} class extends {@link Projectile} to represent projectiles fired by enemies in the game.
 * It defines specific attributes and behaviors for these projectiles, such as their appearance
 * and movement pattern, which contrasts with the player's projectiles by moving to the left.
 */
public class EnemyProjectile extends Projectile {

	private static final String IMAGE_NAME = "enemyFire.png";  // Visual representation of the projectile.
	private static final int IMAGE_HEIGHT = 30;  // Scaled height of the projectile image.
	private static final double HORIZONTAL_VELOCITY = -10.0 / VELOCITY_CHANGE;  // Adjusted horizontal speed.

	/**
	 * Constructs an {@code EnemyProjectile} instance at the specified initial position.
	 *
	 * @param initialXPos the initial x-coordinate where the projectile is created.
	 * @param initialYPos the initial y-coordinate where the projectile is created.
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the projectile by moving it horizontally to the left.
	 * This method overrides the {@link Projectile#updatePosition()} method to provide the specific horizontal movement behavior.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the actor's state. For the enemy projectile, this method is primarily concerned with position updates,
	 * as defined in {@link Projectile#updatePosition()}.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
