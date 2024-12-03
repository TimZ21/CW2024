package com.example.demo.controller;

import com.example.demo.level.LevelParent;
import com.example.demo.menu.PauseMenu;
import javafx.scene.Scene;

import java.lang.reflect.Constructor;

public class LevelController {
    public static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.level.LevelOne";
    private final StageController stageController;
    private PauseMenu pauseMenu;
    private LevelParent currentLevel;

    public LevelController(StageController stageController) {
        this.stageController = stageController;
    }

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

    public void resumeGame() {
        if (currentLevel != null) {
            currentLevel.resumeGame();
        }
    }

    private void handleException(Exception e) {
        ExceptionHandler.handle(e);
    }
}