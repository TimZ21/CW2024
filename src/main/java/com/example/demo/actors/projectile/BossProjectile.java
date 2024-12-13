package com.example.demo.actors.projectile;

import static com.example.demo.level.LevelParent.VELOCITY_CHANGE;

/**
 * The {@code BossProjectile} class represents a projectile fired by a boss character in the game.
 * It extends the {@link Projectile} class, providing custom behaviors tailored for the boss's attacks,
 * such as special movement patterns, differentiating it from standard projectiles.
 * <p>
 * See the source code at <a href="https://github.com/TimZ21/CW2024/blob/master/src/main/java/com/example/demo/actors/projectile/BossProjectile.java">BossProjectile.java</a>
 */

public class BossProjectile extends Projectile {
	/**
	 * Image file for the boss's projectile appearance.
	 */
	private static final String IMAGE_NAME = "fireball.png";
	/**
	 * The display height of the boss's projectile image.
	 */
	private static final int IMAGE_HEIGHT = 75;
	/**
	 * Default horizontal velocity of the boss's projectile.
	 */
	private static final double HORIZONTAL_VELOCITY = (double) -15 / VELOCITY_CHANGE;
	/**
	 * The horizontal velocity of the projectile, allowing for dynamic movement modifications.
	 */
	private final double xVelocity;
	/**
	 * The vertical velocity of the projectile, allowing for dynamic movement modifications.
	 */
	private final double yVelocity;

	/**
	 * Constructs a {@code BossProjectile} with the specified initial position.
	 *
	 * @param initialXPos The initial X-coordinate of the projectile.
	 * @param initialYPos The initial Y-coordinate of the projectile.
	 */
	public BossProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
		this.xVelocity = HORIZONTAL_VELOCITY;
		this.yVelocity = 0;
	}


	/**
	 * Constructs a {@code BossProjectile} with the specified initial position and velocities.
	 *
	 * @param initialXPos The initial X-coordinate of the projectile.
	 * @param initialYPos The initial Y-coordinate of the projectile.
	 * @param xVelocity The horizontal velocity of the projectile.
	 * @param yVelocity The vertical velocity of the projectile.
	 *
	 */
	public BossProjectile(double initialXPos, double initialYPos, int xVelocity, int yVelocity) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}

	/**
	 * Updates the position of the projectile by applying horizontal and vertical velocities.
	 * This method overrides the {@link Projectile#updatePosition()} to specify the movement behavior,
	 * moving the projectile based on the velocities provided at construction or defaults.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(xVelocity);
		moveVertically(yVelocity);
	}

	/**
	 * Updates the actor's state. For the enemy projectile, this method is primarily concerned with position updates,
	 * as defined in {@link Projectile#updatePosition()}.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Returns the horizontal velocity of the projectile.
	 * This velocity determines how fast and in which direction (left or right) the projectile moves horizontally.
	 *
	 * @return The horizontal velocity of the projectile.
	 */
	public double getXVelocity() {
		return xVelocity;
	}

	/**
	 * Returns the vertical velocity of the projectile.
	 * This velocity determines how fast and in which direction (up or down) the projectile moves vertically.
	 *
	 * @return The vertical velocity of the projectile.
	 */
	public double getYVelocity() {
		return yVelocity;
	}
}