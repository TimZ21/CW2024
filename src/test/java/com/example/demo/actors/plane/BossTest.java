package com.example.demo.actors.plane;

import com.example.demo.actors.ActiveActorDestructible;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BossTest {
    private Boss boss;
    private Group root;
    private final int HEALTH = 10;

    @BeforeEach
    void setUp() {
        new JFXPanel(); // Initialize JavaFX environment to handle JavaFX components
        root = new Group();
        Platform.runLater(() -> boss = new Boss(root));
        try {
            Thread.sleep(200); // Ensure that the Boss is initialized in the JavaFX thread
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            fail("Setup interrupted");
        }
    }

    @Test
    void testInitialPosition() {
        Platform.runLater(() -> {
            assertEquals(1000.0, boss.getAbsoluteX(), "Initial X position should be 1000.0");
            assertEquals(400.0, boss.getAbsoluteY(), "Initial Y position should be 400.0");
        });
    }

    @Test
    void testBossInitialization() {
        Platform.runLater(() -> {
            assertNotNull(boss.getHealthBarManager(), "HealthBarManager should be initialized");
            assertNotNull(boss.getShieldManager(), "ShieldManager should be initialized");
        });
    }

    @Test
    void testBossInteractionWithShieldManager() {
        Platform.runLater(() -> {
            assertFalse(boss.getShieldManager().isShielded(), "Shield should initially be off");
            boss.getShieldManager().activateShield();
            assertTrue(boss.getShieldManager().isShielded(), "Shield should be activated by boss");
            boss.getShieldManager().deactivateShield();
            assertFalse(boss.getShieldManager().isShielded(), "Shield should be deactivated by boss");
        });
    }

    @Test
    void testBossHealthUpdates() {
        Platform.runLater(() -> {
            int initialHealth = boss.getHealth();
            boss.takeDamage();
            assertTrue(boss.getHealth() < initialHealth, "Boss health should decrease when taking damage");
            boss.getHealthBarManager().updateHealthBar(boss.getHealth() / (double) HEALTH);
            assertTrue(boss.getHealthBarManager().getContainer().isVisible(), "Health bar should be visible when boss takes damage");
        });
    }


    @Test
    void testProjectileFiring() {
        Platform.runLater(() -> {
            List<ActiveActorDestructible> projectiles = boss.fireProjectile();
            assertTrue(projectiles.size() <= 3, "Boss should fire at most three projectile at a time");
        });
    }
}
