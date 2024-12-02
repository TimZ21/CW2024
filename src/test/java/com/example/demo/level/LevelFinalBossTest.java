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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LevelFinalBossTest {

    private LevelFinalBoss levelFinalBoss;
    private Group root;
    private Scene scene;
    private UserPlane user;
    private Timeline timeline;
    private LevelViewLevelBoss levelView;
    private ImageView background;
    private Boss boss;
    private List<ActiveActorDestructible> enemyUnits;

    @BeforeEach
    void setUp() throws Exception {
        // Initialize JavaFX environment
        new javafx.embed.swing.JFXPanel();

        // Create a LevelFinalBoss instance
        levelFinalBoss = new LevelFinalBoss(800, 1200);

        // Use reflection to access private fields
        root = getPrivateField(LevelParent.class, "root", levelFinalBoss);
        scene = getPrivateField(LevelParent.class, "scene", levelFinalBoss);
        user = getPrivateField(LevelParent.class, "user", levelFinalBoss);
        timeline = getPrivateField(LevelParent.class, "timeline", levelFinalBoss);
        levelView = getPrivateField(LevelParent.class, "levelView", levelFinalBoss);
        background = getPrivateField(LevelParent.class, "background", levelFinalBoss);
        boss = getPrivateField(LevelFinalBoss.class, "boss", levelFinalBoss);
        enemyUnits = getPrivateField(LevelParent.class, "enemyUnits", levelFinalBoss);
    }

    @Test
    void testConstructor() {
        assertNotNull(levelFinalBoss);
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
        Scene initializedScene = levelFinalBoss.initializeScene();
        assertNotNull(initializedScene);
        assertEquals(scene, initializedScene);
        assertTrue(root.getChildren().contains(background));
    }

    @Test
    void testStartGame() {
        levelFinalBoss.startGame();
        assertSame(Timeline.Status.RUNNING, timeline.getStatus());
    }

    @Test
    void testGoToNextLevel() {
        levelFinalBoss.goToNextLevel("LevelFinalBoss");
        assertEquals("LevelFinalBoss", levelFinalBoss.nextLevelProperty().get());
        assertSame(Timeline.Status.STOPPED, timeline.getStatus());
    }

    @Test
    void testPauseGame() {
        levelFinalBoss.pauseGame();
        assertSame(Timeline.Status.STOPPED, timeline.getStatus());
    }

    @Test
    void testResumeGame() {
        levelFinalBoss.pauseGame();
        levelFinalBoss.resumeGame();
        assertSame(Timeline.Status.RUNNING, timeline.getStatus());
    }

    @Test
    void testCleanUp() {
        levelFinalBoss.cleanUp();
        assertSame(Timeline.Status.STOPPED, timeline.getStatus());
        assertTrue(root.getChildren().isEmpty());
    }

    @Test
    void testCheckIfGameOver() throws Exception {
        // Use reflection to access the private method checkIfGameOver
        Method checkIfGameOverMethod = LevelFinalBoss.class.getDeclaredMethod("checkIfGameOver");
        checkIfGameOverMethod.setAccessible(true);

        // Set user as destroyed
        setPrivateField(ActiveActorDestructible.class, "isDestroyed", user, true);
        checkIfGameOverMethod.invoke(levelFinalBoss);
        assertSame(Timeline.Status.STOPPED, timeline.getStatus());

        // Reset timeline status
        timeline.play();

        // Set boss as destroyed
        setPrivateField(ActiveActorDestructible.class, "isDestroyed", boss, true);
        checkIfGameOverMethod.invoke(levelFinalBoss);
        assertSame(Timeline.Status.STOPPED, timeline.getStatus());
    }

    @Test
    void testSpawnEnemyUnits() throws Exception {
        // Use reflection to access the private method spawnEnemyUnits
        Method spawnEnemyUnitsMethod = LevelFinalBoss.class.getDeclaredMethod("spawnEnemyUnits");
        spawnEnemyUnitsMethod.setAccessible(true);

        // Invoke the spawnEnemyUnits method
        spawnEnemyUnitsMethod.invoke(levelFinalBoss);

        // Check if the correct number of enemy planes are spawned
        int expectedMaxEnemyCount = 4; // Including the boss
        int expectedMinEnemyCount = 3;
        assertTrue(expectedMaxEnemyCount >= enemyUnits.size(), "The size of enemy is not correct");
        assertTrue(expectedMinEnemyCount <= enemyUnits.size(), "The size of enemy is not correct");
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