package com.example.demo.menu;

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

    public StartMenu(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        // Create title
        Text title = new Text(TITLE_TEXT);
        title.setFont(Font.font("Arial", 70)); // Customize font and size
        title.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 2;");

        // Create buttons
        Button startButton = new Button("Start");
        Button quitButton = new Button("Quit");

        // Set button actions
        startButton.setOnAction(e -> startGame());
        quitButton.setOnAction(e -> Platform.exit());

        // Creat e layout for buttons
        VBox buttonLayout = new VBox(20, startButton, quitButton);
        buttonLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        // Create layout for the title and buttons
        VBox contentLayout = new VBox(50, title, buttonLayout);
        contentLayout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        // Load and set the background image
        ImageView backgroundImage = new ImageView(
                new Image(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE_NAME)).toExternalForm())
        );
        backgroundImage.setFitWidth(stage.getWidth());
        backgroundImage.setFitHeight(stage.getHeight());
        backgroundImage.setPreserveRatio(false);

        // Combine the background and content into a StackPane
        StackPane root = new StackPane(backgroundImage, contentLayout);

        // Create and set the scene
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        stage.show();
    }

    private void startGame() {
        // Trigger the game start logic, e.g., show the first game level
        try {
            new com.example.demo.controller.Controller(stage).launchGame();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
