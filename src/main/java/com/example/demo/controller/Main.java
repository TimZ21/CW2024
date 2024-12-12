package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;

import com.example.demo.menu.StartMenu;
import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * The {@code Main} class serves as the entry point for the Sky Battle game application.
 * It initializes the game window, sets up the {@code Controller}, and launches the first level.
 * <p>
 * See the source code at <a href=https://github.com/TimZ21/CW2024/blob/master/src/main/java/com/example/demo/controller/Main.java">Main.java</a>
 */
public class Main extends Application {
	/**
	 * The width of the game window in pixels. This defines the fixed width that the application window will use.
	 */
	private static final int SCREEN_WIDTH = 1300;

	/**
	 * The height of the game window in pixels. This defines the fixed height that the application window will use.
	 */
	private static final int SCREEN_HEIGHT = 750;

	/**
	 * The title of the game window, which appears in the window's title bar.
	 */
	private static final String TITLE = "Sky Battle";

	/**
	 * Starts the JavaFX application by setting up the primary stage and displaying the start menu.
	 * This method configures the stage properties such as title, size, and initializes
	 * the start menu as the initial scene of the application.
	 *
	 * @param stage The primary stage for the game window, provided by JavaFX during application launch.
	 * @throws ClassNotFoundException        If a class needed during initialization is not found.
	 * @throws NoSuchMethodException         If a required method reflection call cannot be resolved.
	 * @throws SecurityException             If access to a class or method is denied.
	 * @throws InstantiationException        If an instance of a class cannot be created.
	 * @throws IllegalAccessException        If access to the class constructor is illegal.
	 * @throws IllegalArgumentException      If incorrect parameters are passed to a method.
	 * @throws InvocationTargetException     If the underlying constructor or method throws an exception.
	 */

	@Override
	public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		stage.setTitle(TITLE); // Set the title of the game window
		stage.setResizable(false); // Prevent resizing of the game window
		stage.setHeight(SCREEN_HEIGHT); // Set the height of the game window
		stage.setWidth(SCREEN_WIDTH); // Set the width of the game window
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
