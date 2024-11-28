package com.example.demo.actors;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Objects;

public class ExplosionEffectManager extends ImageView {

    private static final String EXPLOSION_IMAGE_NAME = "explosion.png";
    private static final String IMAGE_LOCATION = "/com/example/demo/images/";
    private static final int EXPLOSION_DURATION = 1000; // Duration in milliseconds

    public ExplosionEffectManager(double x, double y, Group root) {
        super(new Image(Objects.requireNonNull(ExplosionEffectManager.class.getResource(IMAGE_LOCATION + EXPLOSION_IMAGE_NAME)).toExternalForm()));

        // Set explosion size and position
        setFitWidth(100);  // Adjust as needed
        setFitHeight(100);
        setPreserveRatio(true);
        setX(x);
        setY(y);

        // Add explosion to root and bring to front
        root.getChildren().add(this);
        this.toFront();
        System.out.println("ExplosionEffectManager created at x=" + x + ", y=" + y);

        // Schedule the removal of the explosion after the specified duration
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(EXPLOSION_DURATION), e -> {
                    root.getChildren().remove(this);
                    System.out.println("ExplosionEffectManager removed.");
                })
        );
        timeline.setCycleCount(1);
        timeline.play();
    }
}
