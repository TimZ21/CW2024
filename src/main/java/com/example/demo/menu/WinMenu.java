package com.example.demo.menu;

import com.example.demo.actors.manager.AudioManager;
import com.example.demo.actors.manager.ScaleUtils;
import com.example.demo.controller.Controller;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The {@code WinMenu} class displays a victory screen with options to either restart the game or exit.
 * This class handles the setup and display of the win menu UI elements within a provided game scene.
 */
public class WinMenu {

    /**
     * The relative path to the background image for the menu.
     */
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/menu_background.jpg";

    /**
     * The scene that is currently displayed before switching to the win menu.
     */
    private final Scene currentScene;

    /**
     * Constructs a {@code WinMenu} with a reference to the current scene.
     *
     * @param currentScene The current scene where the win menu will be displayed.
     */
    public WinMenu(Scene currentScene) {
        this.currentScene = currentScene;
    }

    /**
     * Displays the win menu overlaying the current game scene.
     * This method sets up all UI components necessary for the win menu.
     */
    public void show() {
        AudioManager.getInstance().playWinEffect();
        ImageView backgroundImage = new ImageView(
                new Image(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_NAME)).toExternalForm())
        );
        backgroundImage.setFitWidth(currentScene.getWidth());
        backgroundImage.setFitHeight(currentScene.getHeight());
        backgroundImage.setPreserveRatio(false);

        Text title = new Text("You Win!!!");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
        title.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 2;");

        Button restartButton = new Button("Restart Game");
        Button quitButton = new Button("Quit");
        styleButton(restartButton);
        styleButton(quitButton);

        restartButton.setOnAction(e -> restartGame());
        quitButton.setOnAction(e -> Platform.exit());

        VBox contentVBox = new VBox(30, title, restartButton, quitButton);
        contentVBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(contentVBox);
        vbox.setAlignment(Pos.TOP_LEFT);
        ScaleUtils.updateScale();
        vbox.setPadding(new Insets(ScaleUtils.scaleYRelocate(250), ScaleUtils.scaleXRelocate(600), 0, 0));

        StackPane root = new StackPane(backgroundImage, vbox);

        // Add an event filter to block the space key from activating buttons
        currentScene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.SPACE) {
                event.consume();
            }
        });

        currentScene.setRoot(root);
    }

    /**
     * Restarts the game by re-initializing the game's main controller and resuming the background music.
     */
    private void restartGame() {
        AudioManager.getInstance().playButtonClickEffect();
        try {
            new Controller((Stage) currentScene.getWindow()).launchGame();
            AudioManager.getInstance().playBackgroundMusic();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Applies a standard styling to buttons used in the win menu.
     *
     * @param button The button to which the style will be applied.
     */
    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-font-size: 16px;");
        button.setMinWidth(120);
        button.setMinHeight(40);
    }
}
