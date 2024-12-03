package com.example.demo.controller;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ControllerTest {

    private Controller controller;
    private Stage testStage;

    @BeforeEach
    void setUp() {
        // Initialize JavaFX environment
        new JFXPanel();
        Platform.runLater(() -> {
            testStage = new Stage();
            controller = new Controller(testStage);
        });
    }

    @Test
    void testLaunchGame() {
        // Use Platform.runLater to execute the test in the JavaFX thread
        Platform.runLater(() -> {
            assertDoesNotThrow(() -> controller.launchGame(), "launchGame should not throw an exception");
        });
    }
}
