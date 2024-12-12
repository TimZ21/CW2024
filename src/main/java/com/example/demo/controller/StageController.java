package com.example.demo.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The {@code StageController} class manages the primary stage for a JavaFX application.
 * It provides methods to manage the scene displayed on the stage, as well as utilities
 * to retrieve stage dimensions and show or hide the stage. This centralization of stage
 * management facilitates the handling of UI changes across the application.
 * <p>
 * See the source code at <a href=https://github.com/TimZ21/CW2024/blob/master/src/main/java/com/example/demo/controller/StageController.java">StageController.java</a>
 */
public class StageController {

    /**
     * The primary {@link Stage} of the application that this controller manages. This stage
     * acts as the main window for the application, where various scenes are displayed as part
     * of the UI lifecycle. The {@code StageController} uses this field to control and apply
     * changes such as scene transitions, stage visibility, and querying size dimensions.
     */
    private final Stage stage;

    /**
     * Constructs a {@code StageController} with a specified {@link Stage}.
     * This controller will be responsible for managing various UI operations such as scene transitions
     * and resizing on the provided stage.
     *
     * @param stage the primary stage of the application to be managed by this controller.
     */
    public StageController(Stage stage) {
        this.stage = stage;
    }

    /**
     * Displays the primary stage. If the stage is not already visible, this method will make it visible.
     * This is typically called at the start of the application to show the initial UI to the user.
     */
    public void showStage() {
        stage.show();
    }

    /**
     * Retrieves the primary {@link Stage} managed by this controller.
     *
     * @return the primary {@link Stage} being managed.
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Sets the current scene for the primary stage. This method allows changing the displayed content
     * by switching between different scenes.
     *
     * @param scene the new scene to be set on the stage.
     */
    public void setScene(Scene scene) {
        stage.setScene(scene);
    }

    /**
     * Retrieves the height of the primary stage.
     *
     * @return the current height of the stage.
     */
    public double getStageHeight() {
        return stage.getHeight();
    }

    /**
     * Retrieves the width of the primary stage.
     *
     * @return the current width of the stage.
     */
    public double getStageWidth() {
        return stage.getWidth();
    }
}