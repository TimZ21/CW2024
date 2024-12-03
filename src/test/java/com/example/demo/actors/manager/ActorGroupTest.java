package com.example.demo.actors.manager;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.plane.EnemyPlane;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class ActorGroupTest {

    private ActorGroup actorGroup;
    private List<ActiveActorDestructible> actors;
    private Group root;

    @BeforeEach
    void setUp() {
        // Necessary to initialize JavaFX environment
        new JFXPanel();
        Platform.runLater(() -> {
            actors = new ArrayList<>();
            root = new Group();
            actorGroup = new ActorGroup(actors, root);

            // Adding test actors
            EnemyPlane enemyPlane1 = new EnemyPlane(100, 100);
            EnemyPlane enemyPlane2 = new EnemyPlane(200, 200);
            actors.add(enemyPlane1);
            actors.add(enemyPlane2);
            root.getChildren().addAll(enemyPlane1, enemyPlane2);
        });
    }

    @Test
    void testUpdate() {
        Platform.runLater(() -> {
            actorGroup.update(); // Trigger update method
            actors.forEach(actor -> assertNotNull(actor, "Actor should exist after update."));
        });
    }

    @Test
    void testRemoveDestroyed() {
        Platform.runLater(() -> {
            EnemyPlane enemyPlane = new EnemyPlane(300, 300);
            enemyPlane.destroy(); // Mark this plane as destroyed
            actors.add(enemyPlane);
            root.getChildren().add(enemyPlane);

            actorGroup.removeDestroyed();

            assertFalse(actors.contains(enemyPlane), "Destroyed actor should be removed from the list.");
            assertFalse(root.getChildren().contains(enemyPlane), "Destroyed actor should be removed from the scene.");
        });
    }

    @Test
    void testRemoveOutOfBounds() {
        Platform.runLater(() -> {
            EnemyPlane enemyPlane = new EnemyPlane(5000, 300); // Out of bounds position
            actors.add(enemyPlane);
            root.getChildren().add(enemyPlane);

            actorGroup.removeOutOfBounds(800); // Assume screen width of 800

            assertFalse(actors.contains(enemyPlane), "Out-of-bounds actor should be removed from the list.");
            assertFalse(root.getChildren().contains(enemyPlane), "Out-of-bounds actor should be removed from the scene.");
        });
    }
}
