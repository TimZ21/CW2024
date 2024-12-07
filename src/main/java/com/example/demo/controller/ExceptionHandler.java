package com.example.demo.controller;

import javafx.scene.control.Alert;

/**
 * The {@code ExceptionHandler} class provides a centralized mechanism for handling exceptions
 * throughout the application. It displays an alert with the error information and logs the exception
 * to the standard error stream.
 * <p>
 * This class is designed to simplify error handling in the application by providing a uniform approach
 * to reporting and logging exceptions. It can be especially useful in a user interface context where
 * notifying the user about errors in a consistent manner is critical.
 */
public class ExceptionHandler {

    /**
     * Handles exceptions by displaying an alert with the type of exception and printing the stack trace.
     * This method is static, allowing it to be called from anywhere in the application without needing
     * an instance of {@code ExceptionHandler}.
     * <p>
     * The alert provides a simple error message that can be expanded in future versions to include more
     * detailed information based on the exception type or the context in which the exception occurred.
     *
     * @param e The exception to handle. This exception is displayed in an alert and logged.
     */
    public static void handle(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(e.getClass().toString());
        alert.show();
        e.printStackTrace();
    }
}
