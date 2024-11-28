package com.example.demo.actors.projectile;

import static com.example.demo.level.LevelParent.VOLECITY_CHANGE;

/**
 * The {@code BossProjectile} class represents a projectile fired by the boss in the game.
 * It extends the {@code Projectile} class and defines specific behavior for the boss's projectile.
 */
public class BossProjectile extends Projectile {

	private static final String IMAGE_NAME = "fireball.png";
	private static final int IMAGE_HEIGHT = 75;
	private static final double HORIZONTAL_VELOCITY = (double) -15 /VOLECITY_CHANGE;
	private static final int INITIAL_X_POSITION = 950;

	private double xVelocity;
	private double yVelocity;

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
	 */
	public BossProjectile(double initialXPos, double initialYPos, int xVelocity, int yVelocity) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}

	/**
	 * Sets the velocity of the projectile.
	 *
	 * @param xVelocity The horizontal velocity of the projectile.
	 * @param yVelocity The vertical velocity of the projectile.
	 */
	public void setVelocity(int xVelocity, int yVelocity) {
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}

	/**
	 * Updates the position of the projectile based on its velocities.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(xVelocity);
		moveVertically(yVelocity);
	}

	/**
	 * Updates the state of the projectile, including its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}