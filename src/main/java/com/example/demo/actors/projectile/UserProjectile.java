package com.example.demo.actors.projectile;

import com.example.demo.level.LevelParent;

import java.lang.annotation.Target;

import static com.example.demo.level.LevelParent.VOLECITY_CHANGE;

/**
 * The {@code UserProjectile} class represents a projectile fired by the player's plane.
 * It defines the specific behavior and appearance of the user's projectiles, such as their
 * velocity and movement.
 */
public class UserProjectile extends Projectile {

	private static final String IMAGE_NAME = "userfire.png";
	private static final int IMAGE_HEIGHT = 6;
	private static final int HORIZONTAL_VELOCITY = 15/VOLECITY_CHANGE;

	/**
	 * Constructs a {@code UserProjectile} instance with the specified initial position.
	 *
	 * @param initialXPos The initial X-coordinate of the projectile.
	 * @param initialYPos The initial Y-coordinate of the projectile.
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the projectile by moving it horizontally to the right.
	 * The movement is determined by the constant horizontal velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the actor's state. For the user projectile, this involves updating its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
