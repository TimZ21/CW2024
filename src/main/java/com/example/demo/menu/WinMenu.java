package com.example.demo.menu;

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
 * The {@code WinMenu} class displays a "You Win" screen with options to restart the game or quit.
 */
public class WinMenu {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/menu_background.jpg";
    private final Scene currentScene;

    public WinMenu(Scene currentScene) {
        this.currentScene = currentScene;
    }

    public void show() {
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
        vbox.setPadding(new Insets(250, 600, 0, 0));

        StackPane root = new StackPane(backgroundImage, vbox);

        // Add an event filter to block the space key from activating buttons
        currentScene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.SPACE) {
                event.consume();
            }
        });

        currentScene.setRoot(root);
    }

    private void restartGame() {
        try {
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
