package com.example.demo.menu;

import com.example.demo.controller.Controller;
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

/**
 * The {@code LoseMenu} class displays a "Game Over" screen with options to restart the game or quit.
 */
public class LoseMenu {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/startmenu.jpg";
    private final Scene currentScene;
    /**
     * Constructs a {@code LoseMenu} with the given scene.
     *
     * @param currentScene The scene to display the "Game Over" menu.
     */
    public LoseMenu(Scene currentScene) {
        this.currentScene = currentScene;
    }

    /**
     * Displays the "Game Over" menu within the current scene.
     */
    public void show() {
        // Create the "Game Over" background image
        ImageView backgroundImage = new ImageView(
                new Image(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_NAME)).toExternalForm())
        );
        backgroundImage.setFitWidth(currentScene.getWidth());
        backgroundImage.setFitHeight(currentScene.getHeight());
        backgroundImage.setPreserveRatio(false);

        Text title = new Text("Game Lose");
        title.setFont(Font.font("Arial", 50)); // Set font size and style
        title.setStyle("-fx-fill: red; -fx-stroke: black; -fx-stroke-width: 2;"); // Red text with black border

        // Create buttons
        Button restartButton = new Button("     Restart Game");
        Button quitButton = new Button("            Quit");

        // Style buttons to remove the gray background and center text
        restartButton.setStyle("-fx-background-color: transparent; -fx-font-size: 24px; -fx-text-fill: white;");
        quitButton.setStyle("-fx-background-color: transparent; -fx-font-size: 24px; -fx-text-fill: white;");

        // Set button actions
        restartButton.setOnAction(e -> restartGame());
        quitButton.setOnAction(e -> Platform.exit());

        // Layout for buttons
        VBox buttonLayout = new VBox(10, title ,restartButton, quitButton);
        buttonLayout.setStyle("-fx-alignment: top-left;"); // Align buttons to the top-left
        buttonLayout.setTranslateX(500); // Shift buttons slightly right
        buttonLayout.setTranslateY(250); // Shift buttons slightly down

        // Combine everything into a StackPane
        StackPane root = new StackPane(backgroundImage, buttonLayout);

        // Set the new content on the existing scene
        currentScene.setRoot(root);
    }

    /**
     * Restarts the game by launching the game again.
     */
    private void restartGame() {
        try {
            // Retrieve the stage from the scene and restart the game
            new Controller((Stage) currentScene.getWindow()).launchGame();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
