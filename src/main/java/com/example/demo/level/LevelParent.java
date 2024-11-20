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
	private LevelView levelView;

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

		//
		this.actorManager = new ActorManager(friendlyGroup, enemyGroup, userProjectileGroup, enemyProjectileGroup);


		// New added constructor for the class I created for single responsibility principle
//		this.actorManager = new ActorManager(friendlyUnits, enemyUnits, userProjectiles, enemyProjectiles);
		this.collisionHandler = new CollisionHandler();

		// Initialized input handler with user plane, because only user plane need to be control by input
		this.inputHandler = new InputHandler(user, root, userProjectiles);
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

//	public void goToNextLevel(String levelName) {
//		setChanged();
//		notifyObservers(levelName);
//	}

	public void goToNextLevel(String levelName) {
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

	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}

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
	private void handlePlaneCollisions() {
		collisionHandler.detectCollisions(friendlyUnits, enemyUnits);
	}

	private void handleUserProjectileCollisions() {
		collisionHandler.detectCollisions(userProjectiles, enemyUnits);
	}

	private void handleEnemyProjectileCollisions() {
		collisionHandler.detectCollisions(enemyProjectiles, friendlyUnits);
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
		timeline.stop();
		levelView.showWinImage();
	}

	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
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

//	protected void addEnemyUnit(ActiveActorDestructible enemy) {
//		enemyUnits.add(enemy);
//		root.getChildren().add(enemy);
//	}

	// Move the detailed code into EnemySpawner class for single responsibility principle
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

}
