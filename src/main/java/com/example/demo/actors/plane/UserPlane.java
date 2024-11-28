package com.example.demo.actors.plane;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.projectile.UserProjectile;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code UserPlane} class represents the player's aircraft in the game.
 * It extends the {@code FighterPlane} class and provides specific functionality
 * for user-controlled movement and projectile firing.
 */
public class UserPlane extends FighterPlane {

	private static final String IMAGE_NAME = "userplane.png";
	private static final double Y_UPPER_BOUND = 0;
	private static final double Y_LOWER_BOUND = 665;
	private static final double X_LEFT_BOUND = 0;
	private static final double X_RIGHT_BOUND = 1100;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 50;
	private static final int MOVEMENT_VELOCITY = 8; // Used for both vertical and horizontal movement
	private static final int PROJECTILE_X_POSITION = 110;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;

	private int verticalVelocityMultiplier;
	private int horizontalVelocityMultiplier;
	private int numberOfKills;

	/**
	 * Constructs a {@code UserPlane} with the specified initial health.
	 *
	 * @param initialHealth The initial health of the player's plane.
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		verticalVelocityMultiplier = 0;
		horizontalVelocityMultiplier = 0;
	}

	/**
	 * Updates the position of the user plane based on the current velocity multipliers.
	 * Prevents the plane from moving out of the defined bounds.
	 */
	@Override
	public void updatePosition() {
		boolean isVerticalMoving = verticalVelocityMultiplier != 0;
		boolean isHorizontalMoving = horizontalVelocityMultiplier != 0;

		// Update vertical movement
		if (isVerticalMoving) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(MOVEMENT_VELOCITY * verticalVelocityMultiplier);
			double newPositionY = getLayoutY() + getTranslateY();
			if (newPositionY < Y_UPPER_BOUND || newPositionY > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}

		// Update horizontal movement
		if (isHorizontalMoving) {
			double initialTranslateX = getTranslateX();
			this.moveHorizontally(MOVEMENT_VELOCITY * horizontalVelocityMultiplier);
			double newPositionX = getLayoutX() + getTranslateX();
			if (newPositionX < X_LEFT_BOUND || newPositionX > X_RIGHT_BOUND) {
				this.setTranslateX(initialTranslateX);
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
	public List<ActiveActorDestructible> fireProjectile() {
		List<ActiveActorDestructible> projectiles = new ArrayList<>();
		projectiles.add(new UserProjectile(getProjectileXPosition(PROJECTILE_X_POSITION), getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET)));
		return projectiles;
	}


	/**
	 * Moves the user plane upward by setting the vertical velocity multiplier to -1.
	 */
	public void moveUp() {
		verticalVelocityMultiplier = -1;
	}

	/**
	 * Moves the user plane downward by setting the vertical velocity multiplier to 1.
	 */
	public void moveDown() {
		verticalVelocityMultiplier = 1;
	}

	/**
	 * Moves the user plane to the left by setting the horizontal velocity multiplier to -1.
	 */
	public void moveLeft() {
		horizontalVelocityMultiplier = -1;
	}

	/**
	 * Moves the user plane to the right by setting the horizontal velocity multiplier to 1.
	 */
	public void moveRight() {
		horizontalVelocityMultiplier = 1;
	}

	/**
	 * Stops the user plane's vertical movement by setting the vertical velocity multiplier to 0.
	 */
	public void stopVerticalMovement() {
		verticalVelocityMultiplier = 0;
	}

	/**
	 * Stops the user plane's horizontal movement by setting the horizontal velocity multiplier to 0.
	 */
	public void stopHorizontalMovement() {
		horizontalVelocityMultiplier = 0;
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
