package com.example.demo.level;

import com.example.demo.actors.plane.UserPlane;
import com.example.demo.view.LevelView;
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

public class LevelOneTest {

    private LevelOne levelOne;
    private Group root;
    private Scene scene;
    private UserPlane user;
    private Timeline timeline;
    private LevelView levelView;
    private ImageView background;

    @BeforeEach
    void setUp() throws Exception {
        // Initialize JavaFX environment
        new javafx.embed.swing.JFXPanel();

        // Create a LevelOne instance
        levelOne = new LevelOne(800, 1200);

        // Use reflection to access private fields
        root = getPrivateField("root", levelOne);
        scene = getPrivateField("scene", levelOne);
        user = getPrivateField("user", levelOne);
        timeline = getPrivateField("timeline", levelOne);
        levelView = getPrivateField("levelView", levelOne);
        background = getPrivateField("background", levelOne);
    }

    @Test
    void testConstructor() {
        assertNotNull(levelOne);
        assertNotNull(root);
        assertNotNull(scene);
        assertNotNull(user);
        assertNotNull(timeline);
        assertNotNull(levelView);
        assertNotNull(background);
    }

    @Test
    void testInitializeScene() {
        Scene initializedScene = levelOne.initializeScene();
        assertNotNull(initializedScene);
        assertEquals(scene, initializedScene);
        assertTrue(root.getChildren().contains(background));
    }

    @Test
    void testStartGame() {
        levelOne.startGame();
        assertSame(timeline.getStatus(), Timeline.Status.RUNNING);
    }

    @Test
    void testGoToNextLevel() {
        levelOne.goToNextLevel("Level2");
        assertEquals("Level2", levelOne.nextLevelProperty().get());
        assertSame(timeline.getStatus(), Timeline.Status.STOPPED);
    }

    @Test
    void testPauseGame() {
        levelOne.pauseGame();
        assertSame(Timeline.Status.STOPPED, timeline.getStatus(), "Wrong pause test"); // Corrected assertion
    }

    @Test
    void testResumeGame() {
        levelOne.resumeGame();
        assertSame(Timeline.Status.RUNNING, timeline.getStatus());
    }

    @Test
    void testCleanUp() {
        levelOne.cleanUp();
        assertSame(timeline.getStatus(), Timeline.Status.STOPPED);
        assertTrue(root.getChildren().isEmpty());
    }

    @Test
    void testUserHasReachedKillTarget() throws Exception {
        // Use reflection to access the private method userHasReachedKillTarget
        Method userHasReachedKillTargetMethod = LevelOne.class.getDeclaredMethod("userHasReachedKillTarget");
        userHasReachedKillTargetMethod.setAccessible(true);

        // Use reflection to set the numberOfKills field
        setPrivateField("numberOfKills", user, 9);
        assertFalse((boolean) userHasReachedKillTargetMethod.invoke(levelOne));

        // Use reflection to set the numberOfKills field
        setPrivateField("numberOfKills", user, 10);
        assertTrue((boolean) userHasReachedKillTargetMethod.invoke(levelOne));
    }

    // Helper method to access private fields using reflection
    private <T> T getPrivateField(String fieldName, Object instance) throws Exception {
        Field field = LevelParent.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T) field.get(instance);
    }

    // Helper method to set private fields using reflection
    private void setPrivateField(String fieldName, Object instance, Object value) throws Exception {
        Field field = UserPlane.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(instance, value);
    }
}