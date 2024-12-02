package com.example.demo.view;

import javafx.scene.Group;
import javafx.scene.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LevelViewLevelBossTest {

    private Group root;
    private LevelViewLevelBoss levelViewLevelBoss;

    @BeforeEach
    void setUp() {
        // Initialize JavaFX environment
        new javafx.embed.swing.JFXPanel();

        // Create dependencies
        root = new Group();

        // Create the LevelViewLevelBoss instance
        levelViewLevelBoss = new LevelViewLevelBoss(root, 3);
    }

    @Test
    void testConstructor() {
        // Verify that the constructor initializes the LevelViewLevelBoss correctly
        assertNotNull(levelViewLevelBoss);
        assertEquals(root, levelViewLevelBoss.getRoot());
    }

    @Test
    void testAddImagesToRoot() {
        // Verify that the shield image container is added to the root
        Node shieldContainer = getShieldContainer(levelViewLevelBoss);
        assertTrue(root.getChildren().contains(shieldContainer));
    }

    // Helper method to access the shield container from LevelViewLevelBoss
    private Node getShieldContainer(LevelViewLevelBoss levelViewLevelBoss) {
        try {
            java.lang.reflect.Field shieldImageField = LevelViewLevelBoss.class.getDeclaredField("shieldImage");
            shieldImageField.setAccessible(true);
            ShieldImage shieldImage = (ShieldImage) shieldImageField.get(levelViewLevelBoss);
            return shieldImage.getContainer();
        } catch (Exception e) {
            throw new RuntimeException("Failed to access shieldImage field", e);
        }
    }

    // Helper method to access the root from LevelViewLevelBoss
    private Group getRoot(LevelViewLevelBoss levelViewLevelBoss) {
        try {
            java.lang.reflect.Field rootField = LevelViewLevelBoss.class.getDeclaredField("root");
            rootField.setAccessible(true);
            return (Group) rootField.get(levelViewLevelBoss);
        } catch (Exception e) {
            throw new RuntimeException("Failed to access root field", e);
        }
    }
}