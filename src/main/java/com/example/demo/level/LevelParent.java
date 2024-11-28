package com.example.demo.level;

import java.util.*;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.mamager.*;
import com.example.demo.menu.LoseMenu;
import com.example.demo.menu.PauseMenu;
import com.example.demo.menu.WinMenu;
import com.example.demo.view.LevelView;
import com.example.demo.actors.plane.UserPlane;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class LevelParent {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int TARGET_FPS = 60;
	public static final double VOLECITY_CHANGE = TARGET_FPS/20;
	private static final int MILLISECOND_DELAY = (1000/TARGET_FPS);
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
	protected PauseMenu pauseMenu;

	private int currentNumberOfEnemies;
	private final LevelView levelView;

	private final StringProperty nextLevelProperty = new SimpleStringProperty();

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

	protected abstract void initializeFriendlyUnits();
	protected abstract void checkIfGameOver();
	protected abstract void spawnEnemyUnits();
	protected abstract LevelView instantiateLevelView();

	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}

	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	public void goToNextLevel(String levelName) {
		stopTimeline(); // Stop the timeline before transitioning to the next level
		cleanUp();
		nextLevelProperty.set(levelName);
	}

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
	}

	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(inputHandler.getOnKeyPressedHandler());
		background.setOnKeyReleased(inputHandler.getOnKeyReleasedHandler());
		root.getChildren().add(background);
	}

	private void handlePlaneCollisions() {
		collisionHandler.detectCollisions(friendlyUnits, enemyUnits);
	}

	private void handleUserProjectileCollisions() {
		collisionHandler.detectCollisionsWithSoundEffect(userProjectiles, enemyUnits);
	}

	private void handleEnemyProjectileCollisions() {
		collisionHandler.detectCollisions(enemyProjectiles, friendlyUnits);
	}

	private void handleProjectileCollision() {
		collisionHandler.detectCollisions(userProjectiles, enemyProjectiles);
	}

	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}

	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	protected void winGame() {
		stopTimeline(); // Stop the timeline when the game is won
		cleanUp();
		new WinMenu(scene).show();
	}

	protected void loseGame() {
		stopTimeline(); // Stop the timeline when the game is lost
		cleanUp();
		new LoseMenu(scene).show();
	}

	public void cleanUp() {
		if (timeline != null) {
			timeline.stop();
		}
		friendlyUnits.clear();
		enemyUnits.clear();
		userProjectiles.clear();
		enemyProjectiles.clear();
		root.getChildren().clear();
	}

	protected UserPlane getUser() {
		return user;
	}

	protected Group getRoot() {
		return root;
	}

	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemySpawner.addEnemyUnit(enemy);
	}

	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	protected double getScreenWidth() {
		return screenWidth;
	}

	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

	public StringProperty nextLevelProperty() {
		return nextLevelProperty;
	}

	public void setPauseMenu(PauseMenu pauseMenu) {
		this.pauseMenu = pauseMenu;
	}

	public void pauseGame() {
		if (timeline != null) {
			timeline.pause();
		}
		if (pauseMenu != null) {
			pauseMenu.show();
		}
	}

	public void resumeGame() {
		if (timeline != null) {
			timeline.play();
		}
	}

	private void stopTimeline() {
		if (timeline != null) {
			timeline.stop();
		}
	}
}