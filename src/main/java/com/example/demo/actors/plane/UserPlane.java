package com.example.demo.actors.plane;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.projectile.UserProjectile;

/**
 * The {@code UserPlane} class represents the player's aircraft in the game.
 * It extends the {@code FighterPlane} class and provides specific functionality
 * for user-controlled movement and projectile firing.
 */
public class UserPlane extends FighterPlane {

	private static final String IMAGE_NAME = "userplane.png";
	private static final double Y_UPPER_BOUND = 0;
	private static final double Y_LOWER_BOUND = 665;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 50;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int PROJECTILE_X_POSITION = 110;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;

	private int velocityMultiplier;
	private int numberOfKills;

	/**
	 * Constructs a {@code UserPlane} with the specified initial health.
	 *
	 * @param initialHealth The initial health of the player's plane.
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		velocityMultiplier = 0;
	}

	/**
	 * Updates the position of the user plane based on the current velocity multiplier.
	 * Prevents the plane from moving out of the defined vertical bounds.
	 */
	@Override
	public void updatePosition() {
		if (isMoving()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * velocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
	}

	/**
	 * Updates the actor by updating its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Fires a projectile from the user plane.
	 *
	 * @return A new {@code UserProjectile} object representing the fired projectile.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}

	/**
	 * Checks if the user plane is currently moving.
	 *
	 * @return {@code true} if the plane is moving, {@code false} otherwise.
	 */
	private boolean isMoving() {
		return velocityMultiplier != 0;
	}

	/**
	 * Moves the user plane upward by setting the velocity multiplier to -1.
	 */
	public void moveUp() {
		velocityMultiplier = -1;
	}

	/**
	 * Moves the user plane downward by setting the velocity multiplier to 1.
	 */
	public void moveDown() {
		velocityMultiplier = 1;
	}

	/**
	 * Stops the user plane's movement by setting the velocity multiplier to 0.
	 */
	public void stop() {
		velocityMultiplier = 0;
	}

	/**
	 * Retrieves the total number of kills made by the user plane.
	 *
	 * @return The number of kills.
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}

	/**
	 * Increments the kill count for the user plane.
	 */
	public void incrementKillCount() {
		numberOfKills++;
	}
}
