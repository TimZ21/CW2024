package com.example.demo.level;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.plane.Boss;
import com.example.demo.actors.plane.UserPlane;
import com.example.demo.view.LevelViewLevelBoss;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class LevelBossTest {

    private LevelBoss levelBoss;
    private Group root;
    private Scene scene;
    private UserPlane user;
    private Timeline timeline;
    private LevelViewLevelBoss levelView;
    private ImageView background;
    private Boss boss;

    @BeforeEach
    void setUp() throws Exception {
        // Initialize JavaFX environment
        new javafx.embed.swing.JFXPanel();

        // Create a LevelBoss instance
        levelBoss = new LevelBoss(800, 1200);

        // Use reflection to access private fields
        root = getPrivateField(LevelParent.class, "root", levelBoss);
        scene = getPrivateField(LevelParent.class, "scene", levelBoss);
        user = getPrivateField(LevelParent.class, "user", levelBoss);
        timeline = getPrivateField(LevelParent.class, "timeline", levelBoss);
        levelView = getPrivateField(LevelParent.class, "levelView", levelBoss);
        background = getPrivateField(LevelParent.class, "background", levelBoss);
        boss = getPrivateField(LevelBoss.class, "boss", levelBoss);
    }

    @Test
    void testConstructor() {
        assertNotNull(levelBoss);
        assertNotNull(root);
        assertNotNull(scene);
        assertNotNull(user);
        assertNotNull(timeline);
        assertNotNull(levelView);
        assertNotNull(background);
        assertNotNull(boss);
    }

    @Test
    void testInitializeScene() {
        Scene initializedScene = levelBoss.initializeScene();
        assertNotNull(initializedScene);
        assertEquals(scene, initializedScene);
        assertTrue(root.getChildren().contains(background));
    }

    @Test
    void testStartGame() {
        levelBoss.startGame();
        assertSame(Timeline.Status.RUNNING, timeline.getStatus());
    }

    @Test
    void testGoToNextLevel() {
        levelBoss.goToNextLevel("LevelFinalBoss");
        assertEquals("LevelFinalBoss", levelBoss.nextLevelProperty().get());
        assertSame(Timeline.Status.STOPPED, timeline.getStatus());
    }

    @Test
    void testPauseGame() {
        levelBoss.pauseGame();
        assertSame(Timeline.Status.STOPPED, timeline.getStatus());
    }

    @Test
    void testResumeGame() {
        levelBoss.pauseGame();
        levelBoss.resumeGame();
        assertSame(Timeline.Status.RUNNING, timeline.getStatus());
    }

    @Test
    void testCleanUp() {
        levelBoss.cleanUp();
        assertSame(Timeline.Status.STOPPED, timeline.getStatus());
        assertTrue(root.getChildren().isEmpty());
    }

    @Test
    void testCheckIfGameOver() throws Exception {
        // Use reflection to access the private method checkIfGameOver
        Method checkIfGameOverMethod = LevelBoss.class.getDeclaredMethod("checkIfGameOver");
        checkIfGameOverMethod.setAccessible(true);

        // Set user as destroyed
        setPrivateField(ActiveActorDestructible.class, "isDestroyed", user, true);
        checkIfGameOverMethod.invoke(levelBoss);
        assertSame(Timeline.Status.STOPPED, timeline.getStatus());

        // Reset timeline status
        timeline.play();

        // Set boss as destroyed
        setPrivateField(ActiveActorDestructible.class, "isDestroyed", boss, true);
        checkIfGameOverMethod.invoke(levelBoss);
        assertSame(Timeline.Status.STOPPED, timeline.getStatus());
    }

    // Helper method to access private fields using reflection
    private <T> T getPrivateField(Class<?> clazz, String fieldName, Object instance) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T) field.get(instance);
    }

    // Helper method to set private fields using reflection
    private void setPrivateField(Class<?> clazz, String fieldName, Object instance, Object value) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(instance, value);
    }
}