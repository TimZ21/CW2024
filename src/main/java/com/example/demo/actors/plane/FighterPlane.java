package com.example.demo.actors.plane;

import com.example.demo.actors.ActiveActorDestructible;

/**
 * The {@code FighterPlane} class is an abstract representation of a combat aircraft
 * in the game. It extends the {@code ActiveActorDestructible} class and introduces
 * common functionality such as health management and firing projectiles.
 */
public abstract class FighterPlane extends ActiveActorDestructible {

	private int health;

	/**
	 * Constructs a {@code FighterPlane} with the specified image properties,
	 * initial position, and health.
	 *
	 * @param imageName     The name of the image file used for this plane.
	 * @param imageHeight   The height of the image.
	 * @param initialXPos   The initial x-coordinate position of the plane.
	 * @param initialYPos   The initial y-coordinate position of the plane.
	 * @param health        The initial health of the plane.
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Fires a projectile from the plane. This method must be implemented
	 * by concrete subclasses to define specific projectile behavior.
	 *
	 * @return An {@code ActiveActorDestructible} representing the fired projectile.
	 */
	public abstract ActiveActorDestructible fireProjectile();

	/**
	 * Decreases the health of the plane when it takes damage. If the health
	 * reaches zero, the plane is destroyed.
	 */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	/**
	 * Calculates the x-coordinate for the initial position of a projectile,
	 * based on the plane's current position and an offset.
	 *
	 * @param xPositionOffset The offset to be applied to the plane's x-coordinate.
	 * @return The x-coordinate for the projectile.
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Calculates the y-coordinate for the initial position of a projectile,
	 * based on the plane's current position and an offset.
	 *
	 * @param yPositionOffset The offset to be applied to the plane's y-coordinate.
	 * @return The y-coordinate for the projectile.
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks if the plane's health has reached zero.
	 *
	 * @return {@code true} if health is zero, {@code false} otherwise.
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Retrieves the current health of the plane.
	 *
	 * @return The current health of the plane.
	 */
	public int getHealth() {
		return health;
	}
}
