package com.example.demo.menu;

import com.example.demo.controller.AudioManager;
import com.example.demo.controller.Controller;
import com.example.demo.level.LevelParent;
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

public class PauseMenu {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/menu_background.jpg";
    private final Stage stage;
    private final Scene gameScene;  // The game scene to switch back to
    private final Runnable onResume;  // Action to resume the game
    private final LevelParent levelParent;  // Reference to the LevelParent instance
    private final AudioManager audioManager; // Instance of AudioManager

    public PauseMenu(Stage stage, Scene gameScene, Runnable onResume, LevelParent levelParent) {
        this.stage = stage;
        this.gameScene = gameScene;
        this.onResume = onResume;
        this.levelParent = levelParent;
        this.audioManager = AudioManager.getInstance(); // Initialize the AudioManager
    }

    public void show() {
        ImageView backgroundImage = new ImageView(
                new Image(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_NAME)).toExternalForm())
        );
        backgroundImage.setFitWidth(gameScene.getWidth());
        backgroundImage.setFitHeight(gameScene.getHeight());
        backgroundImage.setPreserveRatio(false);

        Text title = new Text("Game Paused");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 60)); // Changed font to Verdana and made it bold
        title.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 2;");

        Button resumeButton = new Button("Resume");
        Button restartButton = new Button("Restart");
        Button quitButton = new Button("Quit");
        Button muteButton = new Button(audioManager.isMuted() ? "Unmute" : "Mute"); // Mute button

        styleButton(resumeButton);
        styleButton(restartButton);
        styleButton(quitButton);
        styleButton(muteButton);

        resumeButton.setOnAction(e -> resumeGame());
        restartButton.setOnAction(e -> {
            try {
                restartGame();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        quitButton.setOnAction(e -> quitGame());
        muteButton.setOnAction(e -> toggleMute(muteButton)); // Toggle mute when button is pressed

        // Create a VBox for the content with center alignment and increased spacing
        VBox contentVBox = new VBox(30, title, resumeButton, restartButton, muteButton, quitButton); // Added muteButton here
        contentVBox.setAlignment(Pos.CENTER);  // Center alignment for content inside VBox

        // Create a VBox to hold the contentVBox with top-left alignment
        VBox vbox = new VBox(contentVBox);
        vbox.setAlignment(Pos.TOP_LEFT);  // Top-left alignment for the VBox
        vbox.setPadding(new Insets(250, 600, 0, 0));  // Adjust padding to move content to the top-left

        StackPane root = new StackPane(backgroundImage, vbox);

        Scene pauseScene = new Scene(root, gameScene.getWidth(), gameScene.getHeight());
        stage.setScene(pauseScene);
        stage.show();
    }

    private void resumeGame() {
        stage.setScene(gameScene);
        onResume.run();
    }

    private void restartGame() throws Exception {
        System.out.println("Restarting the game...");
        levelParent.cleanUp();
        new Controller(stage).launchGame();
    }

    private void quitGame() {
        Platform.exit();
    }

    private void toggleMute(Button muteButton) {
        if (audioManager.isMuted()) {
            audioManager.unmute();
            muteButton.setText("Mute");
        } else {
            audioManager.mute();
            muteButton.setText("Unmute");
        }
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-font-size: 16px;");
        button.setMinWidth(120);
        button.setMinHeight(40);
    }
}
