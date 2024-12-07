package com.example.demo.menu;

import com.example.demo.actors.manager.AudioManager;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
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
        Button volumeButton = new Button("Volume");
        Button tutorialButton = new Button("Tutorial");

        styleButton(startButton);
        styleButton(quitButton);
        styleButton(muteButton);
        styleButton(volumeButton);
        styleButton(tutorialButton);

        startButton.setOnAction(e -> startGame());
        quitButton.setOnAction(e -> Platform.exit());
        muteButton.setOnAction(e -> toggleMute(muteButton));
        volumeButton.setOnAction(e -> showVolumeSettings());
        tutorialButton.setOnAction(e -> showTutorial());

        VBox buttonLayout = new VBox(20, startButton, tutorialButton, muteButton, volumeButton, quitButton);
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
        AudioManager.getInstance().playButtonClickEffect();
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
        closeButton.setOnAction(e ->
                scene.setRoot(mainLayout));  // Reset to the main layout

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
            AudioManager.getInstance().playButtonClickEffect();
            new com.example.demo.controller.Controller(stage).launchGame();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void toggleMute(Button muteButton) {
        AudioManager audioManager = AudioManager.getInstance();
        if (audioManager.isMuted()) {
            AudioManager.getInstance().playButtonClickEffect();

            audioManager.unmute();
            muteButton.setText("Mute");
        } else {
            AudioManager.getInstance().playButtonClickEffect();
            audioManager.mute();
            muteButton.setText("Unmute");
        }
    }

    private void showVolumeSettings() {
        AudioManager.getInstance().playButtonClickEffect();
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));

        // Volume controls for background music
        Slider musicVolumeSlider = new Slider(0, 1, AudioManager.getInstance().getMusicVolume());
        musicVolumeSlider.setShowTickLabels(true);
        musicVolumeSlider.setShowTickMarks(true);
        musicVolumeSlider.setMajorTickUnit(0.1);
        musicVolumeSlider.setBlockIncrement(0.05);

        Label musicVolumeLabel = new Label("Background Music Volume: " + (int) (musicVolumeSlider.getValue() * 100));
        musicVolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            AudioManager.getInstance().setBackgroundMusicVolume(newVal.doubleValue());
            musicVolumeLabel.setText("Background Music Volume: " + (int) (newVal.doubleValue() * 100));
        });

        // Volume controls for collision explosion sound effects
        Slider explosionEffectVolumeSlider = new Slider(0, 1, AudioManager.getInstance().getExplosionEffectsVolume());
        explosionEffectVolumeSlider.setShowTickLabels(true);
        explosionEffectVolumeSlider.setShowTickMarks(true);
        explosionEffectVolumeSlider.setMajorTickUnit(0.1);
        explosionEffectVolumeSlider.setBlockIncrement(0.05);

        Label explosionEffectsVolumeLabel = new Label("Explosion Effect Volume: " + (int) (explosionEffectVolumeSlider.getValue() * 100));
        explosionEffectVolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            AudioManager.getInstance().setExplosionEffectsVolume(newVal.doubleValue());
            explosionEffectsVolumeLabel.setText("Explosion Effect Volume: " + (int) (newVal.doubleValue() * 100));
        });

        // Volume controls for button click sound effects
        Slider clickEffectsVolumeSlider = new Slider(0, 1, AudioManager.getInstance().getClickEffectVolume());
        clickEffectsVolumeSlider.setShowTickLabels(true);
        clickEffectsVolumeSlider.setShowTickMarks(true);
        clickEffectsVolumeSlider.setMajorTickUnit(0.1);
        clickEffectsVolumeSlider.setBlockIncrement(0.05);

        Label clickEffectsVolumeLabel = new Label("Click Effect Volume: " + (int) (clickEffectsVolumeSlider.getValue() * 100));
        clickEffectsVolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            AudioManager.getInstance().setClickEffectVolume(newVal.doubleValue());
            clickEffectsVolumeLabel.setText("Click Effect Volume: " + (int) (newVal.doubleValue() * 100));
        });

        // Volume controls for user fire projectile sound effects
        Slider userShottEffectsVolumeSlider = new Slider(0, 1, AudioManager.getInstance().getUserShootEffectVolume());
        userShottEffectsVolumeSlider.setShowTickLabels(true);
        userShottEffectsVolumeSlider.setShowTickMarks(true);
        userShottEffectsVolumeSlider.setMajorTickUnit(0.1);
        userShottEffectsVolumeSlider.setBlockIncrement(0.05);

        Label userShootEffectsVolumeLabel = new Label("User Shoot Effect Volume: " + (int) (userShottEffectsVolumeSlider.getValue() * 100));
        userShottEffectsVolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            AudioManager.getInstance().setUserShootEffectVolume(newVal.doubleValue());
            userShootEffectsVolumeLabel.setText("User Shoot Effect Volume: " + (int) (newVal.doubleValue() * 100));
        });

        // Volume controls for boss fire projectile sound effects
        Slider bossShottEffectsVolumeSlider = new Slider(0, 1, AudioManager.getInstance().getBossShootEffectVolume());
        bossShottEffectsVolumeSlider.setShowTickLabels(true);
        bossShottEffectsVolumeSlider.setShowTickMarks(true);
        bossShottEffectsVolumeSlider.setMajorTickUnit(0.1);
        bossShottEffectsVolumeSlider.setBlockIncrement(0.05);

        Label bossShootEffectsVolumeLabel = new Label("Boss Shoot Effect Volume: " + (int) (bossShottEffectsVolumeSlider.getValue() * 100));
        bossShottEffectsVolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            AudioManager.getInstance().setBossShootEffectVolume(newVal.doubleValue());
            bossShootEffectsVolumeLabel.setText("Boss Shoot Effect Volume: " + (int) (newVal.doubleValue() * 100));
        });

        // Volume controls for win sound effects
        Slider winEffectsVolumeSlider = new Slider(0, 1, AudioManager.getInstance().getWinEffectVolume());
        winEffectsVolumeSlider.setShowTickLabels(true);
        winEffectsVolumeSlider.setShowTickMarks(true);
        winEffectsVolumeSlider.setMajorTickUnit(0.1);
        winEffectsVolumeSlider.setBlockIncrement(0.05);

        Label winEffectsVolumeLabel = new Label("Win Effect Volume: " + (int) (winEffectsVolumeSlider.getValue() * 100));
        winEffectsVolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            AudioManager.getInstance().setWinEffectVolume(newVal.doubleValue());
            winEffectsVolumeLabel.setText("Win Effect Volume: " + (int) (newVal.doubleValue() * 100));
        });

        // Volume controls for lose sound effects
        Slider loseEffectsVolumeSlider = new Slider(0, 1, AudioManager.getInstance().getLoseEffectVolume());
        loseEffectsVolumeSlider.setShowTickLabels(true);
        loseEffectsVolumeSlider.setShowTickMarks(true);
        loseEffectsVolumeSlider.setMajorTickUnit(0.1);
        loseEffectsVolumeSlider.setBlockIncrement(0.05);

        Label loseEffectsVolumeLabel = new Label("Lose Effect Volume: " + (int) (loseEffectsVolumeSlider.getValue() * 100));
        loseEffectsVolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            AudioManager.getInstance().setLoseEffectVolume(newVal.doubleValue());
            loseEffectsVolumeLabel.setText("Lose Effect Volume: " + (int) (newVal.doubleValue() * 100));
        });

        layout.getChildren().addAll(new Label("Adjust Volume"),
                musicVolumeLabel, musicVolumeSlider,
                explosionEffectsVolumeLabel, explosionEffectVolumeSlider,
                clickEffectsVolumeLabel, clickEffectsVolumeSlider,
                userShootEffectsVolumeLabel, userShottEffectsVolumeSlider,
                bossShootEffectsVolumeLabel, bossShottEffectsVolumeSlider,
                winEffectsVolumeLabel, winEffectsVolumeSlider,
                loseEffectsVolumeLabel, loseEffectsVolumeSlider);

        // Wrapping the layout in a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(layout);
        scrollPane.setFitToWidth(true); // Ensures the width of the scroll pane matches the width of its content
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scrollbar as needed
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Never show a horizontal scrollbar

        // Setting up the Scene and Stage
        Stage settingsStage = new Stage();
        settingsStage.setTitle("Volume Settings");
        Scene scene = new Scene(scrollPane, 400, 600); // Adjusted size for better visibility
        settingsStage.setScene(scene);
        settingsStage.show();
    }


    public void show() {
        AudioManager.getInstance().playBackgroundMusic();
        stage.show();
    }
}
