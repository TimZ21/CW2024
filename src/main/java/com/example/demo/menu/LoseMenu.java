package com.example.demo.menu;

import com.example.demo.manager.AudioManager;
import com.example.demo.manager.ScaleUtils;
import com.example.demo.controller.Controller;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The {@code LoseMenu} class displays a "Game Over" screen with options to restart the game or quit.
 * This class handles the setup and display of the game over menu UI elements within a provided game scene.
 *
 * <p>
 * See the source code at <a href=https://github.com/TimZ21/CW2024/blob/master/src/main/java/com/example/demo/menu/LoseMenu.java">LoseMenu.java</a>
 */
public class LoseMenu {

    /**
     * The relative path to the background image for the menu.
     */
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/menu_background.jpg";

    /**
     * The scene that is currently displayed before switching to the game over menu.
     */
    private final Scene currentScene;

    /**
     * Constructs a {@code LoseMenu} with a reference to the current scene.
     *
     * @param currentScene The current scene where the game over menu will be displayed.
     */
    public LoseMenu(Scene currentScene) {
        this.currentScene = currentScene;
    }

    /**
     * Displays the game over menu overlaying the current game scene.
     * This method sets up all UI components necessary for the game over menu.
     */
    public void show() {
        AudioManager.getInstance().playLoseEffect();
        ImageView backgroundImage = new ImageView(
                new Image(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_NAME)).toExternalForm())
        );
        backgroundImage.setFitWidth(currentScene.getWidth());
        backgroundImage.setFitHeight(currentScene.getHeight());
        backgroundImage.setPreserveRatio(false);

        Text title = new Text("Game Over");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
        title.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 2;");

        Button restartButton = new Button("Restart Game");
        Button quitButton = new Button("Quit");

        styleButton(restartButton);
        styleButton(quitButton);

        preventSpaceActivation(restartButton);
        preventSpaceActivation(quitButton);

        restartButton.setOnAction(e -> restartGame());
        quitButton.setOnAction(e -> Platform.exit());

        VBox contentVBox = new VBox(30, title, restartButton, quitButton);
        contentVBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(contentVBox);
        vbox.setAlignment(Pos.CENTER);
        ScaleUtils.updateScale();
        vbox.setPadding(new Insets(ScaleUtils.incrementY, ScaleUtils.incrementX, 0, 0));

        StackPane root = new StackPane(backgroundImage, vbox);

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
     * Applies a standard styling to buttons used in the game over menu.
     *
     * @param button The button to which the style will be applied.
     */
    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-font-size: 16px;");
        button.setMinWidth(120);
        button.setMinHeight(40);
    }

    /**
     * Adds an event filter to a button to prevent it from being activated by the space key.
     *
     * @param button The button to which the event filter will be applied.
     */
    private void preventSpaceActivation(Button button) {
        button.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.SPACE) {
                event.consume();
            }
        });
    }
}
