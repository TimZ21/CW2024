package com.example.demo.controller;

import com.example.demo.level.LevelParent;
import com.example.demo.menu.PauseMenu;
import javafx.scene.Scene;

import java.lang.reflect.Constructor;

/**
 * The {@code LevelController} class is responsible for managing the game levels within the application.
 * It facilitates the dynamic loading and transitioning between different game levels using reflection,
 * ensuring smooth gameplay transitions. Additionally, it manages interactions with the pause menu,
 * handling game pausing and resumption seamlessly.
 *
 * <p>
 * See the source code at <a href="https://github.com/TimZ21/CW2024/blob/master/src/main/java/com/example/demo/controller/LevelController.java">LevelController.java</a>
 */
public class LevelController {

    /**
     * The fully qualified name of the class for the first level in the game.
     * This is used as a reference point for starting the game and dynamic level loading.
     */
    public static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.level.LevelOne";

    /**
     * The controller for managing the main stage and scenes of the application.
     * It is used for setting the scene during level transitions.
     */
    private final StageController stageController;

    /**
     * Manages the pause menu functionality, providing options to pause and resume the game.
     */
    private PauseMenu pauseMenu;

    /**
     * The current active level in the game, an instance of {@link LevelParent}, which
     * allows for dynamic level management and state handling.
     */
    private LevelParent currentLevel;

    /**
     * Constructs a {@code LevelController} with a reference to the {@link StageController}.
     * This controller is used to manage stage transitions and scene setups throughout the game.
     *
     * @param stageController The stage controller responsible for managing scene transitions and window properties.
     */

    public LevelController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * Loads and transitions to a game level specified by its class name. This method utilizes reflection
     * to instantiate level classes dynamically based on their names, facilitating flexible level transitions.
     *
     * @param className The fully qualified class name of the level to load.
     * @throws Exception If the level cannot be instantiated, typically due to reflection errors such as
     *                   a missing class or a lack of an appropriate constructor.
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
     * Handles exceptions during level transitions by logging the error and displaying an alert to the user.
     * This method ensures that all operational exceptions are managed gracefully, maintaining the game's stability.
     *
     * @param e The exception encountered during level handling.
     */
    private void handleException(Exception e) {
        ExceptionHandler.handle(e);
    }
}