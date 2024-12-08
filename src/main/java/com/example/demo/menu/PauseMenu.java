package com.example.demo.menu;

import com.example.demo.actors.manager.ScaleUtils;
import com.example.demo.actors.manager.AudioManager;
import com.example.demo.controller.Controller;
import com.example.demo.level.LevelParent;
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
        AudioManager.getInstance().pauseBackgroundMusic();
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
        Button volumeButton = new Button("Volume");
        Button quitButton = new Button("Quit");
        Button muteButton = new Button(audioManager.isMuted() ? "Unmute" : "Mute"); // Mute button

        styleButton(resumeButton);
        styleButton(restartButton);
        styleButton(quitButton);
        styleButton(volumeButton);
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
        volumeButton.setOnAction(e -> showVolumeSettings());

        // Create a VBox for the content with center alignment and increased spacing
        VBox contentVBox = new VBox(30, title, resumeButton, restartButton, muteButton, volumeButton, quitButton); // Added muteButton here
        contentVBox.setAlignment(Pos.CENTER);  // Center alignment for content inside VBox

        // Create a VBox to hold the contentVBox with top-left alignment
        VBox vbox = new VBox(contentVBox);
        vbox.setAlignment(Pos.TOP_LEFT);  // Top-left alignment for the VBox
        ScaleUtils.updateScale();
        System.out.println(ScaleUtils.scale+" " + ScaleUtils.incrementX+" " + ScaleUtils.incrementY);
        vbox.setPadding(new Insets(ScaleUtils.scaleYRelocate(200), ScaleUtils.scaleXRelocate(650), 0, 0));  // Adjust padding to move content to the top-left

        StackPane root = new StackPane(backgroundImage, vbox);

        Scene pauseScene = new Scene(root, gameScene.getWidth(), gameScene.getHeight());
        stage.setScene(pauseScene);
        stage.show();
    }

    private void resumeGame() {
        AudioManager.getInstance().playButtonClickEffect();
        stage.setScene(gameScene);
        AudioManager.getInstance().resumeBackgroundMusic();
        onResume.run();
    }

    private void restartGame() throws Exception {
        AudioManager.getInstance().playButtonClickEffect();
        System.out.println("Restarting the game...");
        AudioManager.getInstance().playBackgroundMusic();
        levelParent.cleanUp();
        new Controller(stage).launchGame();
    }

    private void quitGame() {
        AudioManager.getInstance().playButtonClickEffect();
        Platform.exit();
    }

    private void toggleMute(Button muteButton) {
        AudioManager.getInstance().playButtonClickEffect();
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
}
