package com.example.demo.actors.plane;

import com.example.demo.actors.ActiveActorDestructible;

import java.util.List;

/**
 * Represents a combat aircraft within the game, providing mechanisms for health management,
 * projectile firing, and damage handling. This abstract class extends {@code ActiveActorDestructible}
 * to utilize advanced destructive capabilities and requires subclasses to implement specific
 * projectile firing behavior.
 */
public abstract class FighterPlane extends ActiveActorDestructible {

	private int health;

	/**
	 * Constructs a FighterPlane with specified image properties, position, and health.
	 *
	 * @param imageName     the name of the image file for the plane's visual representation.
	 * @param imageHeight   the display height of the plane's image.
	 * @param initialXPos   the initial x-coordinate of the plane on the game screen.
	 * @param initialYPos   the initial y-coordinate of the plane on the game screen.
	 * @param health        the initial health value of the plane.
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Fires a projectile. The specific projectile characteristics and behavior are defined
	 * in subclasses.
	 *
	 * @return a list of {@code ActiveActorDestructible} objects representing the projectiles fired.
	 */
	public abstract List<ActiveActorDestructible> fireProjectile();

	/**
	 * Reduces the plane's health by one. If health drops to zero, the plane is destroyed.
	 */
	@Override
	public void takeDamage() {
		health--;
		if (health <= 0) {
			destroy();
		}
	}

	/**
	 * Calculates the x-coordinate for launching a projectile, considering an offset.
	 *
	 * @param xPositionOffset the horizontal offset from the plane's current x-coordinate.
	 * @return the calculated x-coordinate for the projectile's initial position.
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Calculates the y-coordinate for launching a projectile, considering an offset.
	 *
	 * @param yPositionOffset the vertical offset from the plane's current y-coordinate.
	 * @return the calculated y-coordinate for the projectile's initial position.
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks whether the plane's health has been depleted.
	 *
	 * @return {@code true} if health is zero or less, {@code false} otherwise.
	 */
	private boolean healthAtZero() {
		return health <= 0;
	}

	/**
	 * Gets the current health status of the plane.
	 *
	 * @return the current health.
	 */
	public int getHealth() {
		return health;
	}
}
