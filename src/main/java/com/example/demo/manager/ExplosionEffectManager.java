package com.example.demo.manager;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Objects;

/**
 * The {@code ExplosionEffectManager} class manages the visual representation of explosions
 * in the game. It extends {@link ImageView} to display an explosion image at a specified location
 * on the screen for a brief duration before automatically removing it.
 *
 * <p>
 * See the source code at <a href="https://github.com/TimZ21/CW2024/blob/master/src/main/java/com/example/demo/manager/ExplosionEffectManager.java">ExplosionEffectManager.java</a>
 */
public class ExplosionEffectManager extends ImageView {

    /**
     * The file name of the explosion image used for the visual effect.
     */
    private static final String EXPLOSION_IMAGE_NAME = "explosion.png";

    /**
     * The path to the directory containing the image resources.
     */
    private static final String IMAGE_LOCATION = "/com/example/demo/images/";

    /**
     * The duration in milliseconds for which the explosion is displayed before being removed.
     */
    private static final int EXPLOSION_DURATION = 500;

    /**
     * Constructs an {@code ExplosionEffectManager} instance at the specified coordinates
     * within the provided {@link Group}. The explosion image is displayed for a short duration
     * and then removed from the scene.
     *
     * @param x    The x-coordinate where the explosion should appear on the screen.
     * @param y    The y-coordinate where the explosion should appear on the screen.
     * @param root The {@link Group} to which the explosion will be added.
     */
    public ExplosionEffectManager(double x, double y, Group root) {
        super(new Image(Objects.requireNonNull(ExplosionEffectManager.class.getResource(IMAGE_LOCATION + EXPLOSION_IMAGE_NAME)).toExternalForm()));

        // Set explosion size and position
        setFitWidth(150);  // Adjust as needed
        setFitHeight(150);
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
