package com.example.demo.level;

import java.util.*;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.mamager.*;
import com.example.demo.view.LevelView;
import com.example.demo.actors.plane.UserPlane;
import javafx.animation.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.util.Duration;

public abstract class LevelParent {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;

	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;
	private final ActorManager actorManager;
	private final CollisionHandler collisionHandler;
	private final InputHandler inputHandler;
	private final EnemySpawner enemySpawner;



	private int currentNumberOfEnemies;
	private final LevelView levelView;

	private final StringProperty nextLevelProperty = new SimpleStringProperty();

	/**
	 * Constructs a {@code LevelParent} with the specified parameters.
	 *
	 * @param backgroundImageName The name of the background image for the level.
	 * @param screenHeight        The height of the game screen.
	 * @param screenWidth         The width of the game screen.
	 * @param playerInitialHealth The initial health of the player's plane.
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

		//
		this.actorManager = new ActorManager(friendlyGroup, enemyGroup, userProjectileGroup, enemyProjectileGroup);


		// New added constructor for the class I created for single responsibility principle
//		this.actorManager = new ActorManager(friendlyUnits, enemyUnits, userProjectiles, enemyProjectiles);
		this.collisionHandler = CollisionHandler.getInstance();

		// Initialized input handler with user plane, because only user plane need to be control by input
		this.inputHandler = new InputHandler(user, root, userProjectiles);
		this.enemySpawner = new EnemySpawner(enemyUnits, root, enemyProjectiles);


		friendlyUnits.add(user);
	}
	/**
	 * Initializes friendly units in the level.
	 * Must be implemented by subclasses to define specific behaviors.
	 */
	protected abstract void initializeFriendlyUnits();

	/**
	 * Checks if the game is over. Must be implemented by subclasses to define
	 * specific game-over conditions.
	 */
	protected abstract void checkIfGameOver();

	/**
	 * Spawns enemy units during the game.
	 * Must be implemented by subclasses to define enemy spawning logic.
	 */
	protected abstract void spawnEnemyUnits();

	/**
	 * Instantiates the level view for the specific level.
	 *
	 * @return A {@code LevelView} instance for the level.
	 */
	protected abstract LevelView instantiateLevelView();

	/**
	 * Initializes and returns the scene for the level.
	 *
	 * @return The {@code Scene} instance for the level.
	 */
	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}

	/**
	 * Starts the game timeline, enabling game updates and rendering.
	 */
	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

//	public void goToNextLevel(String levelName) {
//		setChanged();
//		notifyObservers(levelName);
//	}

	/**
	 * Sets the name of the next level to transition to.
	 *
	 * @param levelName The name of the next level.
	 */
	public void goToNextLevel(String levelName) {
		nextLevelProperty.set(levelName);
	}

	/**
	 * Updates the scene by handling actor updates, collisions, and game state transitions.
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
		actorManager.removeDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
		actorManager.removeOutOfBoundsProjectiles(1300);
	}

	/**
	 * Initializes the game timeline and its update logic.
	 */
	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	/**
	 * Initializes the background image for the level.
	 */
	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);

		// The input handle function is split into
//		background.setOnKeyPressed(new EventHandler<KeyEvent>() {
//			public void handle(KeyEvent e) {
//				KeyCode kc = e.getCode();
//				if (kc == KeyCode.UP) user.moveUp();
//				if (kc == KeyCode.DOWN) user.moveDown();
//				if (kc == KeyCode.SPACE) fireProjectile();
//			}
//		});
//		background.setOnKeyReleased(new EventHandler<KeyEvent>() {
//			public void handle(KeyEvent e) {
//				KeyCode kc = e.getCode();
//				if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
//			}
//		});
		background.setOnKeyPressed(inputHandler.getOnKeyPressedHandler());
		background.setOnKeyReleased(inputHandler.getOnKeyReleasedHandler());

		root.getChildren().add(background);
	}

//	private void fireProjectile() {
//		ActiveActorDestructible projectile = user.fireProjectile();
//		root.getChildren().add(projectile);
//		userProjectiles.add(projectile);
//	}

	// This method has been moved to EnemySpawner for single responsibility principle
//	private void generateEnemyFire() {
//		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
//	}


	// This method has been moved to EnemySpawner for single responsibility principle
//	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
//		if (projectile != null) {
//			root.getChildren().add(projectile);
//			enemyProjectiles.add(projectile);
//		}
//	}


	//	private void updateActors() {
//		friendlyUnits.forEach(plane -> plane.updateActor());
//		enemyUnits.forEach(enemy -> enemy.updateActor());
//		userProjectiles.forEach(projectile -> projectile.updateActor());
//		enemyProjectiles.forEach(projectile -> projectile.updateActor());
//	}
//
//	private void removeAllDestroyedActors() {
//		removeDestroyedActors(friendlyUnits);
//		removeDestroyedActors(enemyUnits);
//		removeDestroyedActors(userProjectiles);
//		removeDestroyedActors(enemyProjectiles);
//	}
//
//	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
//		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
//				.collect(Collectors.toList());
//		root.getChildren().removeAll(destroyedActors);
//		actors.removeAll(destroyedActors);
//	}

//	private void handlePlaneCollisions() {
//		handleCollisions(friendlyUnits, enemyUnits);
//	}
//
//	private void handleUserProjectileCollisions() {
//		handleCollisions(userProjectiles, enemyUnits);
//	}
//
//	private void handleEnemyProjectileCollisions() {
//		handleCollisions(enemyProjectiles, friendlyUnits);
//	}

//	private void handleCollisions(List<ActiveActorDestructible> actors1,
//			List<ActiveActorDestructible> actors2) {
//		for (ActiveActorDestructible actor : actors2) {
//			for (ActiveActorDestructible otherActor : actors1) {
//				if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
//					actor.takeDamage();
//					otherActor.takeDamage();
//				}
//			}
//		}
//	}

	/**
	 * Handles collisions between friendly and enemy planes.
	 */
	private void handlePlaneCollisions() {
		collisionHandler.detectCollisions(friendlyUnits, enemyUnits);
	}

	/**
	 * Handles collisions between user projectiles and enemy planes.
	 */
	private void handleUserProjectileCollisions() {
		collisionHandler.detectCollisions(userProjectiles, enemyUnits);
	}

	/**
	 * Handles collisions between enemy projectiles and friendly planes.
	 */
	private void handleEnemyProjectileCollisions() {
		collisionHandler.detectCollisions(enemyProjectiles, friendlyUnits);
	}




	/**
	 * Handles scenarios where enemy units penetrate the user's defenses.
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
	 * Updates the level view to reflect the player's current health.
	 */
	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	/**
	 * Updates the player's kill count and transitions the game state accordingly.
	 */
	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	/**
	 * Determines if an enemy has penetrated the user's defenses.
	 *
	 * @param enemy The enemy to check.
	 * @return {@code true} if the enemy has penetrated defenses, {@code false} otherwise.
	 */
	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	/**
	 * Ends the game with a win state.
	 */
	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
	}

	/**
	 * Ends the game with a loss state.
	 */
	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
	}

	/**
	 * Retrieves the user-controlled plane in the level.
	 *
	 * @return The {@code UserPlane} controlled by the player.
	 */
	protected UserPlane getUser() {
		return user;
	}

	/**
	 * Retrieves the root {@code Group} for the scene graph of the level.
	 * The root contains all visual elements displayed in the level.
	 *
	 * @return The root {@code Group} for the level.
	 */
	protected Group getRoot() {
		return root;
	}

	/**
	 * Retrieves the current number of enemy units in the level.
	 *
	 * @return The number of enemy units currently present.
	 */
	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	/**
	 * Adds a new enemy unit to the level.
	 * This method delegates the addition of enemies to the {@code EnemySpawner} class
	 * as part of adhering to the single responsibility principle.
	 *
	 * @param enemy The {@code ActiveActorDestructible} representing the enemy unit to be added.
	 */
	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemySpawner.addEnemyUnit(enemy);
	}

	/**
	 * Retrieves the maximum Y-coordinate position where enemies can spawn.
	 *
	 * @return The maximum Y-position for spawning enemies.
	 */
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	/**
	 * Retrieves the width of the game screen.
	 *
	 * @return The width of the screen in pixels.
	 */
	protected double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Checks whether the user's plane has been destroyed.
	 *
	 * @return {@code true} if the user's plane is destroyed, {@code false} otherwise.
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	/**
	 * Updates the count of enemies currently in the level.
	 * This method recalculates and stores the enemy count.
	 */
	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

	/**
	 * Provides access to the property for transitioning to the next level.
	 *
	 * @return The {@code StringProperty} representing the name of the next level.
	 */
	public StringProperty nextLevelProperty() {
		return nextLevelProperty;
	}


}
