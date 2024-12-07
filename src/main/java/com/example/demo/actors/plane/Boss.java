package com.example.demo.actors.plane;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.manager.AudioManager;
import com.example.demo.actors.manager.FirePatternManager;
import com.example.demo.actors.manager.HealthBarManager;
import com.example.demo.actors.manager.ShieldManager;
import javafx.scene.Group;

import java.util.*;

import static com.example.demo.level.LevelParent.VELOCITY_CHANGE;

/**
 * Represents a boss enemy in the game.
 * The {@code Boss} class extends {@link FighterPlane} and implements special behavior
 * such as shield management, movement patterns, and projectile firing.
 */
public class Boss extends FighterPlane {
	/**
	 * Image file name for the boss plane's appearance.
	 */
	private static final String IMAGE_NAME = "bossplane.png";

	/**
	 * Initial horizontal position of the boss on the game screen.
	 */
	private static final double INITIAL_X_POSITION = 1000.0;

	/**
	 * Initial vertical position of the boss on the game screen.
	 */
	private static final double INITIAL_Y_POSITION = 400;

	/**
	 * Vertical offset for projectile firing position relative to the boss's current Y position.
	 */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;

	/**
	 * Fire rate of the boss, determining how frequently projectiles are fired.
	 */
	private static final double BOSS_FIRE_RATE = 0.04 / VELOCITY_CHANGE;

	/**
	 * Image height for scaling the displayed boss plane.
	 */
	private static final int IMAGE_HEIGHT = 60;

	/**
	 * Vertical velocity for the boss's movement.
	 */
	private static final int VERTICAL_VELOCITY = 3;

	/**
	 * Initial health points of the boss.
	 */
	private static final int HEALTH = 10;

	/**
	 * Number of moves per cycle before potentially changing the movement pattern.
	 */
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;

	/**
	 * Represents no movement; used in the movement pattern.
	 */
	private static final int STATIONARY_MOVE = 0;

	/**
	 * Maximum number of frames the boss moves in the same direction before changing.
	 */
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;

	/**
	 * Upper boundary of the boss's allowed vertical movement.
	 */
	private static final int Y_POSITION_UPPER_BOUND = 0;

	/**
	 * Lower boundary of the boss's allowed vertical movement.
	 */
	private static final int Y_POSITION_LOWER_BOUND = 655;

	/**
	 * The pattern of movements that the Boss will follow.
	 */
	private final List<Integer> movePattern;

	/**
	 * Tracks the number of consecutive moves the Boss has made in the same direction according to the movement pattern.
	 */
	private int consecutiveMovesInSameDirection;

	/**
	 * The current index in the movePattern list.
	 */
	private int indexOfCurrentMove;

	/**
	 * {@link ShieldManager} manages the shield functionality of the Boss.
	 */
	private final ShieldManager shieldManager;

	/**
	 * {@link HealthBarManager} manages the health bar of the Boss.
	 */
	private final HealthBarManager healthBarManager;

	/**
	 * {@link FirePatternManager} manages the firing patterns of projectiles from the Boss.
	 */
	private final FirePatternManager firePatternManager;

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
		this.firePatternManager = new FirePatternManager(PROJECTILE_Y_POSITION_OFFSET);

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
		shieldManager.updateShieldPosition(getAbsoluteX(), getAbsoluteY());
	}

	/**
	 * Fires a projectile from the boss with a probability defined by {@code BOSS_FIRE_RATE}.
	 * This method determines whether to fire based on a random chance and returns a list of projectiles.
	 * If no projectile is fired, it returns an empty list.
	 *
	 * @return A list containing the boss projectiles if fired; otherwise, an empty list.
	 */
	@Override
	public List<ActiveActorDestructible> fireProjectile() {
		if (bossFiresInCurrentFrame()) {
			AudioManager.getInstance().playBossShootEffect();
			return firePatternManager.fireProjectiles(getAbsoluteX(), getAbsoluteY());
		}
		return Collections.emptyList();
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

	/**
	 * Destroys the boss instance, marking it as inactive in the game.
	 * This method overrides the default destroy behavior to include additional cleanup tasks,
	 * such as hiding the health bar associated with the boss when it is destroyed.
	 * This is critical for ensuring that the boss's health bar is no longer displayed once the boss is defeated.
	 */
	@Override
	public void destroy() {
		super.destroy(); // Call the superclass method to handle common destroy functionalities.
		healthBarManager.hideHealthBar(); // Hide the health bar when the boss is destroyed.
	}

	/**
	 * Initializes the movement pattern for the boss.
	 * The pattern alternates between upward, downward, and stationary movements.
	 */
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(STATIONARY_MOVE );
		}
		Collections.shuffle(movePattern);
	}

	/**
	 * Retrieves the next movement direction for the boss based on its current position in the movement pattern.
	 * It cycles through a predefined pattern and shuffles the pattern if the boss has moved in the same direction
	 * for a maximum allowed number of frames. If the end of the pattern list is reached, it resets to the beginning.
	 *
	 * @return The next movement direction, either positive, negative, or stationary.
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
	/**
	 * Getter to get ShieldManager in the Boss class for testing in BossTest
	 *
	 * @return ShieldManager that used in this Class
	 */
	public ShieldManager getShieldManager() {
		return shieldManager;
	}

	/**
	 * Getter to get healthBarManager in the Boss class for testing in BossTest
	 *
	 * @return HealthBarManager that used in this Class
	 */
	public HealthBarManager getHealthBarManager() {
		return healthBarManager;
	}
}
