package com.example.demo.actors.plane;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.projectile.EnemyProjectile;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.level.LevelParent.VELOCITY_CHANGE;

/**
 * The {@code EnemyPlane} class represents an enemy aircraft in the game.
 * It extends the {@code FighterPlane} class and defines specific behaviors
 * for movement, firing projectiles, and updating the actor's state.
 */
public class EnemyPlane extends FighterPlane {

	private static final String IMAGE_NAME = "enemyplane.png";
	private static final int IMAGE_HEIGHT = 70;
	private static final double HORIZONTAL_VELOCITY = (double) -6 / VELOCITY_CHANGE;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	private static final int INITIAL_HEALTH = 1;
	private static final double FIRE_RATE = .01/ VELOCITY_CHANGE;

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
	public List<ActiveActorDestructible> fireProjectile() {
		List<ActiveActorDestructible> projectiles = new ArrayList<>();
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			projectiles.add(new EnemyProjectile(projectileXPosition, projectileYPosition));
		}
		return projectiles;
	}


	/**
	 * Updates the state of the enemy plane, including its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
