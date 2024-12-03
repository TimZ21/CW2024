package com.example.demo.controller;

import com.example.demo.level.LevelParent;
import com.example.demo.menu.PauseMenu;
import javafx.scene.Scene;

import java.lang.reflect.Constructor;

/**
 * The {@code LevelController} class is responsible for managing the game levels.
 * It handles the loading and transitioning between levels using reflection,
 * and manages the pause menu and game resumption.
 */
public class LevelController {

    /** The fully qualified name of the first level class. */
    public static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.level.LevelOne";

    private final StageController stageController;
    private PauseMenu pauseMenu;
    private LevelParent currentLevel;

    /**
     * Constructs a {@code LevelController} instance with the specified stage controller.
     *
     * @param stageController The stage controller to manage stage and scene transitions.
     */
    public LevelController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * Loads and transitions to the specified level using reflection.
     *
     * @param className The fully qualified name of the level class to load.
     * @throws Exception If there is an error loading the level.
     */
    public void goToLevel(String className) throws Exception {
        Class<?> myClass = Class.forName(className);
        Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
        currentLevel = (LevelParent) constructor.newInstance(stageController.getStageHeight(), stageController.getStageWidth());

        Scene scene = currentLevel.initializeScene();
        stageController.setScene(scene);
        currentLevel.startGame();

        pauseMenu = new PauseMenu(stageController.getStage(), scene, this::resumeGame, currentLevel);
        currentLevel.setPauseMenu(pauseMenu);

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
    public void resumeGame() {
        if (currentLevel != null) {
            currentLevel.resumeGame();
        }
    }

    /**
     * Handles exceptions that occur during level transitions and displays an error message.
     *
     * @param e The exception to handle.
     */
    private void handleException(Exception e) {
        ExceptionHandler.handle(e);
    }
}