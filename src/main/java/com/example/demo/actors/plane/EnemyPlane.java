package com.example.demo.actors.plane;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.projectile.EnemyProjectile;

/**
 * The {@code EnemyPlane} class represents an enemy aircraft in the game.
 * It extends the {@code FighterPlane} class and defines specific behaviors
 * for movement, firing projectiles, and updating the actor's state.
 */
public class EnemyPlane extends FighterPlane {

	private static final String IMAGE_NAME = "enemyplane.png";
	private static final int IMAGE_HEIGHT = 80;
	private static final int HORIZONTAL_VELOCITY = -6;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	private static final int INITIAL_HEALTH = 1;
	private static final double FIRE_RATE = .01;

	/**
	 * Constructs an {@code EnemyPlane} with the specified initial position.
	 *
	 * @param initialXPos The initial x-coordinate position of the enemy plane.
	 * @param initialYPos The initial y-coordinate position of the enemy plane.
	 */
	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	public EnemyPlane(double initialXPos, double initialYPos, int health) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, health);
	}


	/**
	 * Updates the position of the enemy plane by moving it horizontally
	 * based on a predefined velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Fires a projectile with a certain probability defined by {@code FIRE_RATE}.
	 * The projectile is positioned relative to the plane's current position.
	 *
	 * @return A new {@code EnemyProjectile} if fired; otherwise, {@code null}.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPosition);
		}
		return null;
	}

	/**
	 * Updates the state of the enemy plane, including its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
