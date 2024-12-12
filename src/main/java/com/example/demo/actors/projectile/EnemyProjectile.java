package com.example.demo.actors.projectile;

import static com.example.demo.level.LevelParent.VELOCITY_CHANGE;

/**
 * The {@code EnemyProjectile} class extends {@link Projectile} to represent projectiles fired by enemies in the game.
 * It defines specific attributes and behaviors for these projectiles, such as their appearance
 * and movement pattern, which contrasts with the player's projectiles by moving to the left.
 * <p>
 * See the source code at <a href=https://github.com/TimZ21/CW2024/blob/master/src/main/java/com/example/demo/actors/projectile/BossProjectile.java">EnemyProjectile.java</a>
 */
public class EnemyProjectile extends Projectile {
	/**
	 * Image file for the projectile's appearance.
	 */
	private static final String IMAGE_NAME = "enemyFire.png";
	/**
	 * The display height of the projectile's image.
	 */
	private static final int IMAGE_HEIGHT = 30;
	/**
	 * Horizontal velocity of the projectile.
	 */
	private static final double HORIZONTAL_VELOCITY = -10.0 / VELOCITY_CHANGE;

	 /**
	 * Constructs an {@code EnemyProjectile} instance at the specified initial position, ready to be launched.
	 * This position determines where on the screen the projectile will initially appear, directly affecting gameplay
	 * by impacting how the player perceives and reacts to the threat.
	 *
	 * @param initialXPos the initial x-coordinate where the projectile is created, typically off the enemy entity.
	 * @param initialYPos the initial y-coordinate where the projectile is created, aligning with the firing entity's position.
	 */

	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the projectile by moving it horizontally to the left.
	 * This leftward movement is typical for enemy attacks, aiming to collide with the player's plane from the right.
	 * This method overrides the {@link Projectile#updatePosition()} method to provide specific behavior tailored to enemy projectiles.
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
