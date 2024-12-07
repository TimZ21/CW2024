package com.example.demo.actors.plane;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.projectile.Projectile;
import com.example.demo.actors.projectile.UserProjectile;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.level.LevelParent.VELOCITY_CHANGE;

/**
 * The {@code UserPlane} class extends {@link FighterPlane} to represent the player-controlled aircraft in the game.
 * It provides mechanisms for user-directed movement and projectile firing, responding directly to player input
 * to navigate through the game environment and engage with enemy targets.
 */
public class UserPlane extends FighterPlane {
	/**
	 * Image file for the user plane's appearance.
	 */
	private static final String IMAGE_NAME = "userplane.png";
	/**
	 * Upper boundary for vertical movement.
	 */
	private static final double Y_UPPER_BOUND = 0;
	/**
	 * Lower boundary for vertical movement.
	 */
	private static final double Y_LOWER_BOUND = 665;
	/**
	 * Left boundary for horizontal movement.
	 */
	private static final double X_LEFT_BOUND = 0;
	/**
	 * Right boundary for horizontal movement.
	 */
	private static final double X_RIGHT_BOUND = 1100;
	/**
	 * Initial X position of the user plane.
	 */
	private static final double INITIAL_X_POSITION = 5.0;
	/**
	 * Initial Y position of the user plane.
	 */
	private static final double INITIAL_Y_POSITION = 300.0;
	/**
	 * Height of the user plane's image.
	 */
	private static final int IMAGE_HEIGHT = 50;
	/**
	 * Movement velocity, adjusted for the game's velocity change factor.
	 */
	private static final double MOVEMENT_VELOCITY = (double) 9 / VELOCITY_CHANGE; // Used for both vertical and horizontal movement
	/**
	 * X position for projectile firing.
	 */
	private static final int PROJECTILE_X_POSITION = 110;
	/**
	 * Y position offset for projectile firing.
	 */
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	/**
	 * Multiplier for vertical velocity to control movement speed and direction.
	 */
	private int verticalVelocityMultiplier;
	/**
	 * Multiplier for horizontal velocity to control movement speed and direction.
	 */
	private int horizontalVelocityMultiplier;
	/**
	 * Number of enemy planes destroyed by this user plane.
	 */
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
	 * This method overrides the {@link FighterPlane#updatePosition()} method
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
	 * This method overrides the {@link FighterPlane#updatePosition()} method
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Fires a projectile from the user plane. This method is invoked in response to player input, such as pressing the fire button.
	 * It creates a {@link UserProjectile} positioned to initiate from the front of the plane, simulating weapon fire.
	 *This method overrides the {@link FighterPlane#fireProjectile()} method
	 *
	 * @return A list containing the newly created {@link UserProjectile}, or an empty list if no projectile was fired.
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


	/**
	 * Checks if the user plane is moving up.
	 *
	 * @return True if the user plane is moving up, false otherwise.
	 */
	public boolean isMovingUp() {
		return verticalVelocityMultiplier == -1;
	}

	/**
	 * Checks if the user plane is moving down.
	 *
	 * @return True if the user plane is moving down, false otherwise.
	 */
	public boolean isMovingDown() {
		return verticalVelocityMultiplier == 1;
	}

	/**
	 * Checks if the user plane is moving left.
	 *
	 * @return True if the user plane is moving left, false otherwise.
	 */
	public boolean isMovingLeft() {
		return horizontalVelocityMultiplier == -1;
	}

	/**
	 * Checks if the user plane is moving right.
	 *
	 * @return True if the user plane is moving right, false otherwise.
	 */
	public boolean isMovingRight() {
		return horizontalVelocityMultiplier == 1;
	}

}
