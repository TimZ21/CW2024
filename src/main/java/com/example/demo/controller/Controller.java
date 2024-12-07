package com.example.demo.controller;

import javafx.stage.Stage;

/**
 * The {@code Controller} class serves as the main controller for the game,
 * control the initial setup and launching of the game. It initializes
 * the necessary controllers for managing the stage and levels, thereby
 * facilitating the game's overall flow and transitions.
 */
public class Controller {

	/**
	 * Manages the stage aspects of the game, including showing and setting up the initial scene.
	 */
	private final StageController stageController;

	/**
	 * Manages the levels within the game, including loading and transitioning between different levels.
	 */
	private final LevelController levelController;

	/**
	 * Constructs a {@code Controller} with a specified {@link Stage}.
	 * This constructor initializes the stage and level controllers, setting up
	 * the fundamental components necessary for the game to function.
	 *
	 * @param stage The primary stage of the application, used to display the game's UI.
	 */
	public Controller(Stage stage) {
		this.stageController = new StageController(stage);
		this.levelController = new LevelController(stageController);
	}

	/**
	 * Launches the game by displaying the primary stage and loading the first level.
	 * This method acts as the entry point for the game's runtime, initiating the sequence
	 * of level transitions and gameplay.
	 *
	 * @throws Exception If an error occurs during the initial level loading.
	 */
	public void launchGame() throws Exception {
		stageController.showStage();
		levelController.goToLevel(LevelController.LEVEL_ONE_CLASS_NAME);
	}
}
