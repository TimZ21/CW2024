package com.example.demo.controller;

import javafx.scene.control.Alert;

public class ExceptionHandler {

    public static void handle(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(e.getClass().toString());
        alert.show();
        e.printStackTrace();
    }
}