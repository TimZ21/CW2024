package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;

import com.example.demo.menu.StartMenu;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The {@code Main} class serves as the entry point for the Sky Battle game application.
 * It initializes the game window, sets up the {@code Controller}, and launches the first level.
 */
public class Main extends Application {

	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private static final String TITLE = "Sky Battle";
	private Controller myController;

	/**
	 * Starts the JavaFX application by initializing the primary stage and launching the game.
	 *
	 * @param stage The primary stage for the game window.
	 * @throws ClassNotFoundException If the level class cannot be found.
	 * @throws NoSuchMethodException If the constructor for the level class is not found.
	 * @throws SecurityException If access to the constructor is denied.
	 * @throws InstantiationException If the level class cannot be instantiated.
	 * @throws IllegalAccessException If access to the constructor is not allowed.
	 * @throws IllegalArgumentException If the arguments passed to the constructor are invalid.
	 * @throws InvocationTargetException If the constructor invocation fails.
	 */
	@Override
	public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		stage.setTitle(TITLE); // Set the title of the game window
		stage.setResizable(false); // Prevent resizing of the game window
		stage.setHeight(SCREEN_HEIGHT); // Set the height of the game window
		stage.setWidth(SCREEN_WIDTH); // Set the width of the game window
//		myController = new Controller(stage); // Initialize the game controller
//		myController.launchGame(); // Launch the game
		StartMenu startMenu = new StartMenu(stage);
		startMenu.show();
	}

	/**
	 * The main method that launches the JavaFX application.
	 *
	 * @param args Command-line arguments.
	 */
	public static void main(String[] args) {
		launch(); // Launch the JavaFX application
	}
}
