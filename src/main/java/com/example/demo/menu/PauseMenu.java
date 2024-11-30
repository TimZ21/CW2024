package com.example.demo.menu;

import com.example.demo.controller.Controller;
import com.example.demo.level.LevelParent;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class PauseMenu {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/menu_background.jpg";
    private final Stage stage;
    private final Scene gameScene;  // The game scene to switch back to
    private final Runnable onResume;  // Action to resume the game
    private final LevelParent levelParent;  // Reference to the LevelParent instance

    public PauseMenu(Stage stage, Scene gameScene, Runnable onResume, LevelParent levelParent) {
        this.stage = stage;
        this.gameScene = gameScene;
        this.onResume = onResume;
        this.levelParent = levelParent;
    }

    public void show() {
        // Create the "Pause" background image
        ImageView backgroundImage = new ImageView(
                new Image(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_NAME)).toExternalForm())
        );
        backgroundImage.setFitWidth(gameScene.getWidth());
        backgroundImage.setFitHeight(gameScene.getHeight());
        backgroundImage.setPreserveRatio(false);

        Text title = new Text("Game Paused");
        title.setFont(Font.font("Arial", 50)); // Set font size and style
        title.setStyle("-fx-fill: red; -fx-stroke: black; -fx-stroke-width: 2;"); // Red text with black border

        // Create buttons for Resume, Restart, Quit
        Button resumeButton = new Button("     Resume");
        Button restartButton = new Button("     Restart");
        Button quitButton = new Button("     Quit");

        // Style buttons to remove the gray background and center text
        resumeButton.setStyle("-fx-background-color: transparent; -fx-font-size: 24px; -fx-text-fill: white;");
        restartButton.setStyle("-fx-background-color: transparent; -fx-font-size: 24px; -fx-text-fill: white;");
        quitButton.setStyle("-fx-background-color: transparent; -fx-font-size: 24px; -fx-text-fill: white;");

        resumeButton.setOnAction(e -> resumeGame());
        restartButton.setOnAction(e -> {
            try {
                restartGame();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        quitButton.setOnAction(e -> quitGame());

        // Layout for the buttons
        VBox vbox = new VBox(20, title, resumeButton, restartButton, quitButton);
        vbox.setStyle("-fx-alignment: top-left;"); // Align buttons to the top-left
        vbox.setTranslateX(500); // Shift buttons slightly right
        vbox.setTranslateY(250); // Shift buttons slightly down

        // Combine everything into a StackPane
        StackPane root = new StackPane(backgroundImage, vbox);

        // Create the pause scene
        Scene pauseScene = new Scene(root, gameScene.getWidth(), gameScene.getHeight());

        // Show the pause scene
        stage.setScene(pauseScene);
        stage.show();
    }

    private void resumeGame() {
        // Resume the game (stop showing the pause menu and go back to the game scene)
        stage.setScene(gameScene);
        onResume.run(); // Resumes the game logic (e.g., restarting the timeline or game loop)
    }

    private void restartGame() throws Exception {
        // Restart the game (you can reload the level or reset game variables)
        System.out.println("Restarting the game...");

        // Clean up resources from the previous game session
        levelParent.cleanUp();

        // Launch the new game
        new Controller(stage).launchGame();
    }

    private void quitGame() {
        // Quit the game
        Platform.exit();
    }
}