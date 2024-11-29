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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class StartMenu {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/startmenu.jpg";
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
        title.setFont(Font.font("Arial", 70));
        title.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 2;");

        Button startButton = new Button("Start");
        Button quitButton = new Button("Quit");
        Button muteButton = new Button("Mute");
        Button tutorialButton = new Button("Tutorial");

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
                "Welcome to Sky Battle!\n\n" +
                        "How to Play:\n" +
                        "- Use the arrow keys to move your plane: Up, Down, Left, Right.\n" +
                        "- Press 'Space' to shoot at enemy planes.\n" +
                        "- Press 'Escape' to pause the game.\n" +
                        "- Avoid enemy bullets and enemy planes.\n" +
                        "- Destroy enemies to prevent them pass and progress through levels.\n\n" +
                        "Objective:\n" +
                        "- Try to survive as long as possible and beat the boss.\n" +
                        "- Level 1: Defeating 10 enemies.\n " +
                        "- Level 2: Defeating 15 enemies.\n " +
                        "- Level Boss: Defeating the BOSS.\n\n" +
                        "Tips:\n" +
                        "- Watch for the enemy close to the left boundary.\n" +
                        "- Use your maneuver skills to dodge incoming fire.\n\n" +
                        "Good luck, and have fun playing!"
        );
        tutorialText.setFont(Font.font("Arial", 20));
        tutorialText.setStyle("-fx-fill: white;");
        tutorialText.setWrappingWidth(600);  // Set a wrapping width to control layout

        // Create the close button
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> scene.setRoot(mainLayout));  // Reset to the main layout

        // Create a layout for the tutorial content
        VBox layout = new VBox(20, tutorialText, closeButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        // Use a StackPane to layer the background and content
        StackPane tutorialLayout = new StackPane();
        tutorialLayout.getChildren().addAll(backgroundImage, layout);

        // Update the scene's root to show the tutorial
        scene.setRoot(tutorialLayout);
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
