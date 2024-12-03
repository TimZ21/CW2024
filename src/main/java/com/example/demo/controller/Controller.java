package com.example.demo.controller;

import javafx.stage.Stage;

public class Controller {

	private final StageController stageController;
	private final LevelController levelController;

	public Controller(Stage stage) {
		this.stageController = new StageController(stage);
		this.levelController = new LevelController(stageController);
	}

	public void launchGame() throws Exception {
		stageController.showStage();
		levelController.goToLevel(LevelController.LEVEL_ONE_CLASS_NAME);
	}
}