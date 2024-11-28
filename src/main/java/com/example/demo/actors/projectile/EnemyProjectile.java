package com.example.demo.actors.projectile;

import static com.example.demo.level.LevelParent.VELOCITY_CHANGE;

/**
 * The {@code EnemyProjectile} class represents a projectile fired by an enemy in the game.
 * It extends the {@code Projectile} class and defines specific behavior for the enemy's projectile.
 */
public class EnemyProjectile extends Projectile {

	private static final String IMAGE_NAME = "enemyFire.png";
	private static final int IMAGE_HEIGHT = 30;
	private static final double HORIZONTAL_VELOCITY = (double) -10 / VELOCITY_CHANGE;

	/**
	 * Constructs an {@code EnemyProjectile} with the specified initial X and Y positions.
	 *
	 * @param initialXPos The initial X-coordinate of the projectile.
	 * @param initialYPos The initial Y-coordinate of the projectile.
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the projectile by moving it horizontally to the left.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the projectile, including its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
