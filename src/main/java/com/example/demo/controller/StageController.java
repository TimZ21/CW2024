package com.example.demo.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageController {

    private final Stage stage;

    public StageController(Stage stage) {
        this.stage = stage;
    }

    public void showStage() {
        stage.show();
    }
    public Stage getStage() {
        return stage;
    }


    public void setScene(Scene scene) {
        stage.setScene(scene);
    }

    public double getStageHeight() {
        return stage.getHeight();
    }

    public double getStageWidth() {
        return stage.getWidth();
    }
}