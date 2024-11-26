package com.example.demo.controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.level.LevelParent;
import com.example.demo.menu.PauseMenu;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * The {@code Controller} class manages the game's flow, including level transitions and
 * stage setup. It handles dynamic loading of game levels using reflection and manages
 * the main game stage.
 */
public class Controller {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.level.LevelOne";
	private final Stage stage;
	private PauseMenu pauseMenu; // To manage the pause menu
	private LevelParent currentLevel; // Reference to the current level

	/**
	 * Constructs a {@code Controller} instance with the specified stage.
	 *
	 * @param stage The primary stage for the game.
	 */
	public Controller(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Launches the game by initializing the first level and displaying the stage.
	 *
	 * @throws Exception If there is an error initializing the game.
	 */
	public void launchGame() throws Exception {
		stage.show();
		goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	/**
	 * Loads and transitions to the specified level using reflection.
	 *
	 * @param className The fully qualified name of the level class to load.
	 * @throws Exception If there is an error loading the level.
	 */
	private void goToLevel(String className) throws Exception {
		// Use reflection to load the level class
		Class<?> myClass = Class.forName(className);
		Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
		currentLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());

		// Initialize the scene for the level
		Scene scene = currentLevel.initializeScene();
		stage.setScene(scene);
		currentLevel.startGame();

		// Create and initialize the PauseMenu
		pauseMenu = new PauseMenu(stage, scene, () -> resumeGame(), currentLevel);
		currentLevel.setPauseMenu(pauseMenu);

		// Add listener for level changes
		currentLevel.nextLevelProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null && !newValue.isEmpty()) {
				try {
					goToLevel(newValue);
				} catch (Exception e) {
					handleException(e);
				}
			}
		});
	}

	/**
	 * Resumes the game after pausing.
	 */
	private void resumeGame() {
		if (currentLevel != null) {
			currentLevel.resumeGame();
		}
	}

	/**
	 * Handles exceptions that occur during game operations and displays an error message.
	 *
	 * @param e The exception to handle.
	 */
	private void handleException(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(e.getClass().toString());
		alert.show();
		e.printStackTrace();
	}
}