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
 *
 * <p>
 * See the source code at <a href="https://github.com/TimZ21/CW2024/blob/master/src/main/java/com/example/demo/actors/plane/EnemyPlane.java">EnemyPlane.java</a>
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
	 * Constructs an {@code EnemyPlane} with specified health and an image.
	 * This constructor variant allows customization for different levels or enemy types.
	 *
	 * @param initialXPos the initial x-coordinate of the enemy plane, specifying where on the screen it appears horizontally.
	 * @param initialYPos the initial y-coordinate of the enemy plane, specifying where on the screen it appears vertically.
	 * @param health the health level with which the enemy plane starts, indicating how much damage it can take before being destroyed.
	 * @param imageName the file name of the image representing this enemy plane, allowing for visual variety.
	 */

	public EnemyPlane(double initialXPos, double initialYPos, int health, String imageName) {
		super(imageName, IMAGE_HEIGHT, initialXPos, initialYPos, health);
	}

	/**
	 * Updates the position of the enemy plane by moving it horizontally to the left.
	 * The movement is determined by a predefined negative horizontal velocity, simulating the plane's attack approach.
	 * This method overrides the {@link FighterPlane#updatePosition()} method
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Fires a projectile with a certain probability defined by {@code FIRE_RATE}.
	 * The projectile is positioned relative to the plane's current position.
	 * This method overrides the {@link FighterPlane#fireProjectile()} method
	 *
	 * @return A list containing a new {@link EnemyProjectile} if fired; otherwise, an empty list.
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
	 * Updates the state of the enemy plane by refreshing its position on the screen.
	 * This method is typically called each frame to handle the plane's movement and any other state changes that need to be checked or refreshed.
	 * This method overrides the {@link FighterPlane#updatePosition()} method
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
