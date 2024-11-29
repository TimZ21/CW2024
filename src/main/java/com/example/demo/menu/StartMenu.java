package com.example.demo.menu;

import com.example.demo.controller.AudioManager;
import javafx.application.Platform;
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

public class StartMenu {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/menu_background.jpg";
    private static final String TITLE_TEXT = "Sky Battle";
    private final Stage stage;
    private static final String BACKGROUND_MUSIC = "/com/example/demo/sounds/bg.mp3";
    private Scene scene;
    private StackPane mainLayout;  // Store the main layout

    public StartMenu(Stage stage) {
        this.stage = stage;
        createScene();  // Create the scene and set it on the stage here
    }

    private void createScene() {
        mainLayout = createLayout();  // Create and store the main layout
        scene = new Scene(mainLayout, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
    }

    private StackPane createLayout() {
        Text title = new Text(TITLE_TEXT);
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 100)); // Changed font to Verdana and made it bold
        title.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 2;");

        Button startButton = new Button("Start");
        Button quitButton = new Button("Quit");
        Button muteButton = new Button("Mute");
        Button tutorialButton = new Button("Tutorial");

        styleButton(startButton);
        styleButton(quitButton);
        styleButton(muteButton);
        styleButton(tutorialButton);

        startButton.setOnAction(e -> startGame());
        quitButton.setOnAction(e -> Platform.exit());
        muteButton.setOnAction(e -> toggleMute(muteButton));
        tutorialButton.setOnAction(e -> showTutorial());

        VBox buttonLayout = new VBox(20, startButton, tutorialButton, muteButton, quitButton);
        buttonLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        VBox contentLayout = new VBox(50, title, buttonLayout);
        contentLayout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        ImageView backgroundImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_NAME)).toExternalForm()));
        backgroundImage.setFitWidth(stage.getWidth());
        backgroundImage.setFitHeight(stage.getHeight());
        backgroundImage.setPreserveRatio(false);

        return new StackPane(backgroundImage, contentLayout);
    }

    private void showTutorial() {
        // Use the same background image
        ImageView backgroundImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_NAME)).toExternalForm()));
        backgroundImage.setFitWidth(stage.getWidth());
        backgroundImage.setFitHeight(stage.getHeight());
        backgroundImage.setPreserveRatio(false);

        // Create the tutorial text
        Text tutorialText = new Text(
                """
                        Welcome to Sky Battle!

                        How to Play:
                        - Use the arrow keys to move your plane: Up, Down, Left, Right.
                        - Press 'Space' to shoot at enemy planes.
                        - Press 'Escape' to pause the game.
                        - Avoid enemy bullets and enemy planes.
                        - Destroy enemies to prevent them pass and progress through levels.

                        Objective:
                        - Try to survive as long as possible and beat the boss.
                        - Level 1: Defeating 10 enemies.
                        - Level 2: Defeating 15 enemies.
                        - Level Boss: Defeating the BOSS.

                        Tips:
                        - Watch for the enemy close to the left boundary.
                        - Use your maneuver skills to dodge incoming fire.

                        Good luck, and have fun playing!"""
        );
        tutorialText.setFont(Font.font("Arial", 20));
        tutorialText.setStyle("-fx-fill: white;");
        tutorialText.setWrappingWidth(600);  // Set a wrapping width to control layout

        // Create the close button
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> scene.setRoot(mainLayout));  // Reset to the main layout

        // Create a layout for the tutorial content
        VBox layout = new VBox(20, tutorialText, closeButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 50; -fx-background-color: rgba(0, 0, 0, 0.7);"); // Dark semi-transparent background

        // Use a StackPane to layer the background and content
        StackPane tutorialLayout = new StackPane();
        tutorialLayout.getChildren().addAll(backgroundImage, layout);

        // Update the scene's root to show the tutorial
        scene.setRoot(tutorialLayout);
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-font-size: 16px;");
        button.setMinWidth(120);
        button.setMinHeight(40);
    }

    private void startGame() {
        try {
            new com.example.demo.controller.Controller(stage).launchGame();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void toggleMute(Button muteButton) {
        AudioManager audioManager = AudioManager.getInstance();
        if (audioManager.isMuted()) {
            audioManager.unmute();
            muteButton.setText("Mute");
        } else {
            audioManager.mute();
            muteButton.setText("Unmute");
        }
    }

    public void show() {
        AudioManager.getInstance().playBackgroundMusic(BACKGROUND_MUSIC);
        stage.show();
    }
}
