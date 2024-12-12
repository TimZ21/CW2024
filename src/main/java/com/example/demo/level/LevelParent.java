package com.example.demo.level;

import java.util.*;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.manager.*;
import com.example.demo.menu.LoseMenu;
import com.example.demo.menu.PauseMenu;
import com.example.demo.menu.WinMenu;
import com.example.demo.view.LevelView;
import com.example.demo.actors.plane.UserPlane;
import javafx.animation.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.util.Duration;

/**
 * The {@code LevelParent} class serves as an abstract base for all game levels in the Sky Battle game.
 * It provides common functionalities such as initializing the level scene, handling game mechanics like
 * collisions, projectiles, and enemy movements, and managing transitions between levels.
 * This class should be extended by specific level classes that implement level-specific behaviors and entities.
 *
 * <p>
 * See the source code at <a href=https://github.com/TimZ21/CW2024/blob/master/src/main/java/com/example/demo/level/LevelParent.java">LevelParent.java</a>
 */
public abstract class LevelParent {

	/**
	 * Adjustment value used to determine the maximum Y position of enemies on the screen.
	 */
	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;

	/**
	 * Target frames per second at which the game attempts to run.
	 */
	private static final int TARGET_FPS = 60;

	/**
	 * Velocity change factor derived from the target FPS to adjust movement speeds dynamically.
	 */
	public static final double VELOCITY_CHANGE = (double) TARGET_FPS / 20;

	/**
	 * Delay in milliseconds per frame, calculated from the target FPS, used in the game loop.
	 */
	private static final int MILLISECOND_DELAY = (1000 / TARGET_FPS);

	/**
	 * The height of the screen or game window where the level is displayed.
	 */
	private final double screenHeight;

	/**
	 * The width of the screen or game window where the level is displayed.
	 */
	private final double screenWidth;

	/**
	 * The calculated Y position on the screen beyond which enemy units cannot move.
	 */
	private final double enemyMaximumYPosition;

	/**
	 * The root group for all graphical objects in the level, including actors and projectiles.
	 */
	private final Group root;

	/**
	 * Timeline used for the game loop, controlling periodic updates such as movement and collisions.
	 */
	private final Timeline timeline;

	/**
	 * Player-controlled plane character.
	 */
	private final UserPlane user;

	/**
	 * Scene containing all the visual elements of the level.
	 */
	private final Scene scene;

	/**
	 * Background image view displayed in the game level.
	 */
	private final ImageView background;

	/**
	 * List of all friendly units in the level, including the player's plane.
	 */
	private final List<ActiveActorDestructible> friendlyUnits;

	/**
	 * List of all enemy units that the player must contend with.
	 */
	private final List<ActiveActorDestructible> enemyUnits;

	/**
	 * List of projectiles fired by the user.
	 */
	private final List<ActiveActorDestructible> userProjectiles;

	/**
	 * List of projectiles fired by enemies.
	 */
	private final List<ActiveActorDestructible> enemyProjectiles;

	/**
	 * Manages all actors within the level, handling interactions and state updates.
	 */
	private final ActorManager actorManager;

	/**
	 * Handles detection and response to collisions between actors.
	 */
	private final CollisionHandler collisionHandler;

	/**
	 * Manages user input to control the player's plane and interact with the game.
	 */
	private final InputHandler inputHandler;

	/**
	 * Responsible for spawning enemy units in the level.
	 */
	private final EnemySpawner enemySpawner;

	/**
	 * Pause menu that can be activated to stop the game temporarily.
	 */
	protected PauseMenu pauseMenu;

	/**
	 * Current number of enemy units present in the level.
	 */
	private int currentNumberOfEnemies;

	/**
	 * Visual representation and utilities for displaying level-specific elements like health bars.
	 */
	private final LevelView levelView;

	/**
	 * Property used to signal when the level should transition to the next specified level.
	 */
	private final StringProperty nextLevelProperty = new SimpleStringProperty();

	/**
	 * Constructs a new instance of {@code LevelParent}, setting up the game level with the specified background
	 * image, screen dimensions, and initial health for the player's plane. This constructor initializes all
	 * necessary components and managers required for the game level to function, such as actors, managers for
	 * collisions, inputs, and enemy spawning, and the visual components of the level.
	 *
	 * @param backgroundImageName The file name of the background image for the level, which must be located in the
	 *                            resource directory that the class can access.
	 * @param screenHeight        The height of the game window or canvas where the level will be displayed. This is used
	 *                            to size the scene and various UI components.
	 * @param screenWidth         The width of the game window or canvas where the level will be displayed. This is used
	 *                            to size the scene and various UI components.
	 * @param playerInitialHealth The initial health with which the player's plane will start the level. This is passed
	 *                            to the user plane's constructor.
	 * <p>
	 * Initializes various collections for managing actors within the game level, such as user plane and enemy units,
	 * as well as projectile lists for both the player and enemies. Sets up the background image view and adds it to the
	 * scene's root group. Instantiates the various managers for actor management, collision detection, input handling,
	 * and enemy spawning. Finally, it adds the player's plane as a friendly unit and initializes the game timeline for
	 * continual game updates.
	 */
	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(backgroundImageName)).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		initializeTimeline();

		// Initialize ActorGroups and pass them to ActorManager
		ActorGroup friendlyGroup = new ActorGroup(friendlyUnits, root);
		ActorGroup enemyGroup = new ActorGroup(enemyUnits, root);
		ActorGroup userProjectileGroup = new ActorGroup(userProjectiles, root);
		ActorGroup enemyProjectileGroup = new ActorGroup(enemyProjectiles, root);

		this.actorManager = new ActorManager(friendlyGroup, enemyGroup, userProjectileGroup, enemyProjectileGroup);
		this.collisionHandler = CollisionHandler.getInstance();
		this.inputHandler = new InputHandler(user, root, userProjectiles, this::pauseGame);
		this.enemySpawner = new EnemySpawner(enemyUnits, root, enemyProjectiles);

		friendlyUnits.add(user);
	}

	/**
	 * Initializes the friendly units specific to the level. This method should be implemented
	 * to set up the user plane. Each subclass should define what constitutes a friendly unit in its specific context.
	 */
	protected abstract void initializeFriendlyUnits();

	/**
	 * Checks if the game over condition has been met. This method should be implemented to define
	 * the specific conditions under which the game should end, such as the player losing all health
	 * or all objectives being completed.
	 */
	protected abstract void checkIfGameOver();

	/**
	 * Spawns enemy units for the level. This method should be implemented to handle the generation
	 * and placement of enemies within the level at appropriate intervals or triggers, ensuring the
	 * level remains challenging and dynamic.
	 */
	protected abstract void spawnEnemyUnits();

	/**
	 * Creates and returns the specific {@link LevelView} for the level. This method should be implemented
	 * to instantiate and return an instance of {@link LevelView} that is appropriate for the specific level,
	 * managing aspects of the UI such as health bars, user plane's hearts.
	 *
	 * @return the instantiated {@link LevelView} specific to the level.
	 */
	protected abstract LevelView instantiateLevelView();

	/**
	 * Initializes the scene for the level. This method sets up the background, initializes
	 * friendly units specific to the level, and displays the heart (health) display. It is typically
	 * called once when the level is being set up.
	 *
	 * @return The fully initialized {@link Scene} with all visual elements prepared, ready to be displayed.
	 */
	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}

	/**
	 * Starts the game loop for the level. This method is called to begin gameplay,
	 * focusing the game's background and initiating the timeline which controls game updates.
	 * It ensures that game logic continues from where it left off or starts afresh as needed.
	 */
	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	/**
	 * Transitions to another level specified by the class name. This method stops the current game's
	 * timeline, cleans up the current level's resources, and sets the property for the next level, triggering
	 * the level transition.
	 *
	 * @param levelName The fully qualified class name of the next level to load, which must be a subclass of {@link LevelParent}.
	 *                  This name is used to dynamically load and instantiate the next level.
	 */
	public void goToNextLevel(String levelName) {
		stopTimeline(); // Stop the timeline before transitioning to the next level
		cleanUp();
		nextLevelProperty.set(levelName);
	}

	/**
	 * Updates the game state for each frame in the game loop. This method is responsible for performing
	 * various game logic operations such as spawning new enemy units, updating the state of all actors,
	 * generating enemy fire, managing collisions, and checking game over conditions.
	 * <p>
	 * Specific tasks performed by this method include:
	 * - Spawning enemy units as needed.
	 * - Updating the state and positions of all actors in the game.
	 * - Generating projectiles fired by enemies.
	 * - Checking for and handling any collisions among planes and projectiles.
	 * - Removing any actors that have been destroyed.
	 * - Updating the level view and kill counts.
	 * - Removing projectiles that are out of bounds.
	 * - Processing user input.
	 * <p>
	 * This method is invoked repeatedly by the game loop to ensure continuous and smooth gameplay.
	 */
	private void updateScene() {
		spawnEnemyUnits();
		actorManager.updateAllActors();
		enemySpawner.generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handlePlaneCollisions();
		handleProjectileCollision();
		actorManager.removeDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
		actorManager.removeOutOfBoundsProjectiles(1300);
		inputHandler.update();
	}

	/**
	 * Initializes the game loop timeline. This method sets up a {@link Timeline} that calls {@code updateScene()}
	 * continuously at intervals defined by {@code MILLISECOND_DELAY}. This continuous cycle is crucial for game
	 * mechanics such as movement, collision detection, and other time-sensitive updates.
	 *
	 * The timeline is set to run indefinitely, ensuring the game loop continues until explicitly stopped, typically
	 * when the game is paused, the level is completed, or the application is closed.
	 */
	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	/**
	 * Initializes and sets up the background for the game level. This method configures the background
	 * image's properties such as its size, and event handlers for key presses and releases.
	 * It ensures that the background can respond to input and fits the entire screen area designated for the level.
	 */
	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(inputHandler.getOnKeyPressedHandler());
		background.setOnKeyReleased(inputHandler.getOnKeyReleasedHandler());
		root.getChildren().add(background);
	}

	/**
	 * Handles the detection of collisions between planes in the game, both user plane and enemy. This method
	 * uses the collision handler to check for any intersections between these two groups of units and processes
	 * any interactions that should occur as a result of these collisions.
	 */
	private void handlePlaneCollisions() {
		collisionHandler.detectCollisions(friendlyUnits, enemyUnits);
	}

	/**
	 * Handles the detection of collisions between projectiles fired by the user and enemy units.
	 * This method checks for interactions and applies effects for these collisions, such as explosion effect and sound effect.
	 */
	private void handleUserProjectileCollisions() {
		collisionHandler.detectCollisionsWithEffect(userProjectiles, enemyUnits, root);
	}

	/**
	 * Handles the detection of collisions between projectiles fired by enemies and friendly units,
	 * which is player's plane. This method is crucial for applying damage or other effects to
	 * friendly units hit by enemy projectiles.
	 */
	private void handleEnemyProjectileCollisions() {
		collisionHandler.detectCollisions(enemyProjectiles, friendlyUnits);
	}

	/**
	 * Detects and processes collisions between projectiles fired by the user and those fired by enemies.
	 */
	private void handleProjectileCollision() {
		collisionHandler.detectCollisions(userProjectiles, enemyProjectiles);
	}

	/**
	 * Checks and handles scenarios where enemy units have penetrated the defense line, typically
	 * indicated by exceeding a specific boundary on the screen. This method deals damage to the player
	 * and destroys the enemy unit that has penetrated the defense.
	 */
	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}

	/**
	 * Updates the level's visual representation based on the current game state, particularly
	 * adjusting the health display according to the player's remaining health.
	 */
	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	/**
	 * Updates the count of enemies killed by the player. This method calculates the difference
	 * between the number of enemies initially present and those currently active, incrementing
	 * the player's kill count accordingly.
	 */
	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	/**
	 * Determines whether an enemy has crossed the defensive boundary set within the level, typically
	 * by moving beyond the right edge of the screen.
	 *
	 * @param enemy The enemy unit to check.
	 * @return true if the enemy has moved beyond the designated defensive boundary, false otherwise.
	 */
	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	/**
	 * Handles the actions to be performed when the game is won. This method stops the game timeline,
	 * cleans up the current level, and displays the win menu to the player.
	 * It is called when the win condition for the level is met.
	 */
	protected void winGame() {
		stopTimeline(); // Stop the timeline when the game is won
		cleanUp();
		new WinMenu(scene).show();
	}

	/**
	 * Handles the actions to be performed when the game is lost. Similar to winGame, this method
	 * stops the game timeline, cleans up the level, and shows the lose menu to the player.
	 * It is called when the lose condition for the level is met.
	 */
	protected void loseGame() {
		stopTimeline(); // Stop the timeline when the game is lost
		cleanUp();
		new LoseMenu(scene).show();
	}

	/**
	 * Cleans up the level by stopping the timeline and clearing all game entities from the scene.
	 * This method ensures that no residual game elements remain that could affect future gameplay
	 * or resource management. It is called typically when transitioning between levels or restarting
	 * the game.
	 */
	public void cleanUp() {
		stopTimeline();
		friendlyUnits.clear();
		enemyUnits.clear();
		userProjectiles.clear();
		enemyProjectiles.clear();
		root.getChildren().clear();
	}

	/**
	 * Retrieves the player's plane object. This method provides access to the player's plane, allowing
	 * other parts of the game to interact with or query the player's status, such as health or position.
	 *
	 * @return the {@link UserPlane} instance representing the player's plane.
	 */
	protected UserPlane getUser() {
		return user;
	}

	/**
	 * Retrieves the root group where all game elements are attached. This allows for modifications to
	 * the scene's graphical elements from outside the level class.
	 *
	 * @return the root {@link Group} used in the scene.
	 */
	protected Group getRoot() {
		return root;
	}

	/**
	 * Returns the current number of enemy units active in the level. This can be used to monitor the
	 * game's difficulty or progression.
	 *
	 * @return the number of active enemy units.
	 */
	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	/**
	 * Adds an enemy unit to the game. This method facilitates the dynamic addition of enemies into the
	 * level through the enemy spawner.
	 *
	 * @param enemy the enemy unit to be added to the game.
	 */
	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemySpawner.addEnemyUnit(enemy);
	}

	/**
	 * Retrieves the maximum Y position that enemies can reach on the screen, used to limit their movement
	 * and prevent them from disappearing off the screen or into inappropriately low areas.
	 *
	 * @return the maximum Y coordinate enemies are allowed to reach.
	 */
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	/**
	 * Returns the width of the game screen, which can be useful for positioning UI elements or calculating
	 * movement bounds for actors.
	 *
	 * @return the width of the screen.
	 */
	protected double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Checks if the user's plane has been destroyed, which can be useful for determining game over conditions
	 * or managing game states.
	 *
	 * @return true if the user's plane is destroyed, false otherwise.
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	/**
	 * Updates the internal count of the current number of enemies. This method is typically called to
	 * ensure that the game's state accurately reflects the number of enemies actively in play.
	 */
	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

	/**
	 * Provides access to the property that tracks the transition to the next level.
	 *
	 * @return the property representing the next level.
	 */
	public StringProperty nextLevelProperty() {
		return nextLevelProperty;
	}

	/**
	 * Sets the pause menu to be used within the level. This allows dynamic updates or replacements of
	 * the pause menu during the game.
	 *
	 * @param pauseMenu the {@link PauseMenu} to be set for use when the game is paused.
	 */
	public void setPauseMenu(PauseMenu pauseMenu) {
		this.pauseMenu = pauseMenu;
	}

	/**
	 * Pauses the gameplay by stopping the game's timeline and displaying the pause menu. This method
	 * is typically called when the player initiates a game pause, such as by pressing a pause ESCAPE.
	 * It ensures that all game actions are halted and the game state is preserved during the pause.
	 */
	public void pauseGame() {
		if (timeline != null) {
			timeline.pause();
		}
		if (pauseMenu != null) {
			pauseMenu.show();
		}
	}

	/**
	 * Resumes the game from a paused state by restarting the timeline. This method is called when the
	 * player chooses to continue playing after a pause. It ensures that the game picks up exactly where
	 * it left off without any changes to the game state.
	 */
	public void resumeGame() {
		if (timeline != null) {
			timeline.play();
		}
	}

	/**
	 * Stops the game's timeline. This method is typically called when the game is ending, either due to
	 * a game over condition or when transitioning to a different level or scene. It ensures that all
	 * game actions are completely halted and that no further updates will occur.
	 */
	private void stopTimeline() {
		if (timeline != null) {
			timeline.stop();
		}
	}
}