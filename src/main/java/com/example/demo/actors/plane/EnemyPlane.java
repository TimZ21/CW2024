package com.example.demo.actors.plane;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.projectile.EnemyProjectile;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.level.LevelParent.VELOCITY_CHANGE;

/**
 * The {@code EnemyPlane} class represents an enemy aircraft in the game.
 * It extends the {@link FighterPlane} class and defines specific behaviors
 * for movement, firing projectiles, and updating the actor's state.
 */
public class EnemyPlane extends FighterPlane {

	/**
	 * The image file name for the enemy plane's appearance in the game.
	 */
	private static final String IMAGE_NAME = "enemyplane.png";

	/**
	 * The height of the enemy plane's image in pixels. Used to scale the plane in the game environment.
	 */
	private static final int IMAGE_HEIGHT = 70;

	/**
	 * The horizontal velocity of the enemy plane
	 * Negative values indicate movement to the left.
	 */
	private static final double HORIZONTAL_VELOCITY = -6.0 / VELOCITY_CHANGE;

	/**
	 * The x-coordinate offset for launching projectiles relative to the enemy plane's current position.
	 * A negative value offsets the projectile to the left of the plane.
	 */
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;

	/**
	 * The y-coordinate offset for launching projectiles relative to the enemy plane's current position.
	 * This helps position the projectile at a specific height relative to the plane.
	 */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;

	/**
	 * The initial health of the enemy plane when it is created.
	 */
	private static final int INITIAL_HEALTH = 1;

	/**
	 * The rate at which the enemy plane fires projectiles
	 */
	private static final double FIRE_RATE = 0.01 / VELOCITY_CHANGE;


	/**
	 * Constructs an {@code EnemyPlane} with default health.
	 * This method is used for {@link com.example.demo.level.LevelOne}
	 *
	 * @param initialXPos the initial x-coordinate of the enemy plane
	 * @param initialYPos the initial y-coordinate of the enemy plane
	 */
	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	/**
	 * Constructs an {@code EnemyPlane} with specified health.
	 * This method is used for {@link com.example.demo.level.LevelTwo}
	 *
	 * @param initialXPos the initial x-coordinate of the enemy plane
	 * @param initialYPos the initial y-coordinate of the enemy plane
	 * @param health the health level with which the enemy plane starts
	 */
	public EnemyPlane(double initialXPos, double initialYPos, int health, String imageName) {
		super(imageName, IMAGE_HEIGHT, initialXPos, initialYPos, health);
	}

	/**
	 * Updates the position of the enemy plane by moving it horizontally based on a predefined velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
     * Fires a projectile with a certain probability defined by FIRE_RATE}.
     * The projectile is positioned relative to the plane's current position.
     *
     * @return A new {@link EnemyProjectile} if fired; otherwise, {@code null}.
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
