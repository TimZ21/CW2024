package com.example.demo.controller;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private Stage testStage;
    private Main mainApplication;

    @BeforeEach
    void setUp() {
        // Initialize JavaFX environment
        new JFXPanel();
        Platform.runLater(() -> {
            testStage = new Stage();
            mainApplication = new Main();
        });
    }

    @Test
    void testStart() {
        // Use Platform.runLater to execute the test in the JavaFX thread
        Platform.runLater(() -> {
            assertDoesNotThrow(() -> mainApplication.start(testStage), "The start method should not throw an exception");
        });
    }

    @Test
    void testStageTitle() {
        Platform.runLater(() -> {
            try {
                mainApplication.start(testStage); // We execute the start method to set stage properties
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            assertEquals("Sky Battle", testStage.getTitle(), "Stage title should be set by the Main class.");
        });
    }


    @Test
    void testStageResizable() {
        Platform.runLater(() -> {
            try {
                mainApplication.start(testStage); // We execute the start method to set stage properties
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            assertFalse(testStage.isResizable(), "Stage should not be resizable.");
        });
    }
    @Test
    void testStageWidth() {
        Platform.runLater(() -> {
            try {
                mainApplication.start(testStage); // We execute the start method to set stage properties
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            assertEquals(750, testStage.getHeight(), "Stage height should match the specified value.");
        });
    }
    @Test
    void testStageHeight() {
        Platform.runLater(() -> {
            try {
                mainApplication.start(testStage); // We execute the start method to set stage properties
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            assertEquals(750, testStage.getHeight(), "Stage height should match the specified value.");
        });
    }
}
