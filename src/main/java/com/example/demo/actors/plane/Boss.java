package com.example.demo.actors.plane;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.projectile.BossProjectile;
import com.example.demo.actors.plane.ShieldManager;
import javafx.scene.Group;

import java.util.*;

/**
 * Represents a boss enemy in the game.
 * The {@code Boss} class extends {@code FighterPlane} and implements special behavior
 * such as shield management, movement patterns, and projectile firing.
 */
public class Boss extends FighterPlane {

	private static final String IMAGE_NAME = "bossplane.png";
	private static final double INITIAL_X_POSITION = 1000.0;
	private static final double INITIAL_Y_POSITION = 400;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
	private static final double BOSS_FIRE_RATE = 0.04;
	private static final int IMAGE_HEIGHT = 60;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int HEALTH = 10;
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
	private static final int ZERO = 0;
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
	private static final int Y_POSITION_UPPER_BOUND = 0;
	private static final int Y_POSITION_LOWER_BOUND = 655;

	private final List<Integer> movePattern;
	private int consecutiveMovesInSameDirection;
	private int indexOfCurrentMove;
	private final ShieldManager shieldManager;
	private final HealthBarManager healthBarManager;

	/**
	 * Constructs a {@code Boss} instance with its initial position and shield manager.
	 *
	 * @param root The {@code Group} representing the scene graph root where the boss and its shield will be added.
	 */
	public Boss(Group root) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		this.movePattern = new ArrayList<>();
		this.consecutiveMovesInSameDirection = 0;
		this.indexOfCurrentMove = 0;
		this.shieldManager = new ShieldManager(root, INITIAL_X_POSITION, INITIAL_Y_POSITION);

		// Initialize the health bar manager
		this.healthBarManager = new HealthBarManager(400, 10); // Top-center position
		root.getChildren().add(healthBarManager.getContainer());
		healthBarManager.showHealthBar(); // Ensure the health bar is visible initially

		initializeMovePattern();
	}

	/**
	 * Updates the position of the boss according to its movement pattern.
	 * Ensures that the boss stays within its vertical boundaries.
	 */
	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}

	/**
	 * Updates the boss's behavior, including its position and shield status.
	 */
	@Override
	public void updateActor() {
		updatePosition();
		shieldManager.updateShield();
		shieldManager.updateShieldPosition(getLayoutX() + getTranslateX(), getLayoutY() + getTranslateY());
	}

	/**
	 * Fires a projectile from the boss with a probability defined by {@code BOSS_FIRE_RATE}.
	 *
	 * @return A {@code BossProjectile} if the boss decides to fire; otherwise, {@code null}.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
	}

	/**
	 * Takes damage, reducing the boss's health if its shield is not active.
	 */
	@Override
	public void takeDamage() {
		if (!shieldManager.isShielded()) {
			super.takeDamage();
			double healthPercentage = (double) getHealth() / HEALTH;
			healthBarManager.updateHealthBar(healthPercentage);
		}
	}

	@Override
	public void destroy() {
		super.destroy();
		healthBarManager.hideHealthBar(); // Hide health bar when the boss is destroyed
	}
	/**
	 * Initializes the movement pattern for the boss.
	 * The pattern alternates between upward, downward, and stationary movements.
	 */
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	/**
	 * Retrieves the next movement direction for the boss based on its movement pattern.
	 * Shuffles the movement pattern after a set number of consecutive moves in the same direction.
	 *
	 * @return The next movement direction.
	 */
	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}
		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}
		return currentMove;
	}

	/**
	 * Determines whether the boss will fire a projectile in the current frame.
	 *
	 * @return {@code true} if the boss fires a projectile; {@code false} otherwise.
	 */
	private boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	/**
	 * Calculates the initial Y-coordinate for the boss's projectile.
	 *
	 * @return The Y-coordinate for the projectile's starting position.
	 */
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}
}
