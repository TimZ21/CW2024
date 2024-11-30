package com.example.demo.menu;

import com.example.demo.controller.Controller;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The {@code LoseMenu} class displays a "Game Over" screen with options to restart the game or quit.
 */
public class WinMenu {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/menu_background.jpg";
    private final Scene currentScene;

    /**
     * Constructs a {@code LoseMenu} with the given scene.
     *
     * @param currentScene The scene to display the "Game Over" menu.
     */
    public WinMenu(Scene currentScene) {
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

        Text title = new Text("You Win!!!");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
        title.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 2;");

        // Create buttons
        Button restartButton = new Button("Restart Game");
        Button quitButton = new Button("Quit");

        // Style buttons to remove the gray background and center text
        styleButton(restartButton);
        styleButton(quitButton);

        // Set button actions
        restartButton.setOnAction(e -> restartGame());
        quitButton.setOnAction(e -> Platform.exit());

        VBox contentVBox = new VBox(30, title, restartButton, quitButton);
        contentVBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(contentVBox);
        vbox.setAlignment(Pos.TOP_LEFT);
        vbox.setPadding(new Insets(250, 300, 0, 0)); // Adjust padding to move content to the top-left

        StackPane root = new StackPane(backgroundImage, vbox);

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

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-font-size: 16px;");
        button.setMinWidth(120);
        button.setMinHeight(40);
    }
}
