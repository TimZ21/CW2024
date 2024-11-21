package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.level.LevelParent;

/**
 * The {@code Controller} class manages the game's flow, including level transitions and
 * stage setup. It handles dynamic loading of game levels using reflection and manages
 * the main game stage.
 */
public class Controller {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.level.LevelOne";
	private final Stage stage;

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
	 * @throws ClassNotFoundException If the level class cannot be found.
	 * @throws NoSuchMethodException If the constructor for the level class is not found.
	 * @throws SecurityException If access to the constructor is denied.
	 * @throws InstantiationException If the level class cannot be instantiated.
	 * @throws IllegalAccessException If access to the constructor is not allowed.
	 * @throws IllegalArgumentException If the arguments passed to the constructor are invalid.
	 * @throws InvocationTargetException If the constructor invocation fails.
	 */
	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		stage.show();
		goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	/**
	 * Loads and transitions to the specified level using reflection.
	 *
	 * @param className The fully qualified name of the level class to load.
	 * @throws ClassNotFoundException If the level class cannot be found.
	 * @throws NoSuchMethodException If the constructor for the level class is not found.
	 * @throws SecurityException If access to the constructor is denied.
	 * @throws InstantiationException If the level class cannot be instantiated.
	 * @throws IllegalAccessException If access to the constructor is not allowed.
	 * @throws IllegalArgumentException If the arguments passed to the constructor are invalid.
	 * @throws InvocationTargetException If the constructor invocation fails.
	 */
	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// Use reflection to load the level class
		Class<?> myClass = Class.forName(className);
		Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
		LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());

		Scene scene = myLevel.initializeScene(); // Initialize the scene for the level
		stage.setScene(scene); // Set the scene on the stage
		myLevel.startGame(); // Start the level

		// Add listener for level change
		myLevel.nextLevelProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null && !newValue.isEmpty()) {
				try {
					goToLevel(newValue);
				} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
						 | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					handleException(e);
				}
			}
		});
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
