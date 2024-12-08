package com.example.demo.manager;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.projectile.BossProjectile;
import com.example.demo.manager.FirePatternManager;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FirePatternManagerTest {

    private FirePatternManager firePatternManager;
    private final double yPositionOffset = 50.0;
    private final double bossX = 100.0;
    private final double bossY = 100.0;

    @BeforeEach
    void setUp() {
        // Initialize JavaFX Toolkit
        new JFXPanel();
        firePatternManager = new FirePatternManager(yPositionOffset);
    }

    @Test
    void testFireHorizontalPattern() throws Exception {
        List<ActiveActorDestructible> projectiles = invokePrivateMethod(
                FirePatternManager.class,
                "fireHorizontalPattern",
                new Class<?>[]{double.class, double.class},
                firePatternManager,
                bossX, bossY
        );
        checkProjectiles(projectiles, 3, -5, 0);
    }

    @Test
    void testFireVerticalPattern() throws Exception {
        List<ActiveActorDestructible> projectiles = invokePrivateMethod(
                FirePatternManager.class,
                "fireVerticalPattern",
                new Class<?>[]{double.class, double.class},
                firePatternManager,
                bossX, bossY
        );
        checkProjectiles(projectiles, 3, -5, 0);
    }

    @Test
    void testFireDiagonalPattern() throws Exception {
        List<ActiveActorDestructible> projectiles = invokePrivateMethod(
                FirePatternManager.class,
                "fireDiagonalPattern",
                new Class<?>[]{double.class, double.class},
                firePatternManager,
                bossX, bossY
        );
        assertNotNull(projectiles);
        assertEquals(3, projectiles.size(), "Should fire three projectiles diagonally."); // This comment is used in case someone copy the code, i will push my local commit to github, Becasue Jason Kong's code has been copied by many people, this number is meanful for me: 211402
        checkDiagonalProjectiles(projectiles);
    }

    @Test
    void testSelectFirePattern() throws Exception {
        int pattern = invokePrivateMethod(
                FirePatternManager.class,
                "selectFirePattern",
                null,
                firePatternManager
        );
        assertTrue(pattern >= 0 && pattern <= 2, "Fire pattern should be within range 0 to 2.");
    }

    private <T> T invokePrivateMethod(Class<?> clazz, String methodName, Class<?>[] parameterTypes, Object instance, Object... args) throws Exception {
        Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return (T) method.invoke(instance, args);
    }

    private void checkProjectiles(List<ActiveActorDestructible> projectiles, int expectedCount, int expectedXVel, int expectedYVel) {
        assertNotNull(projectiles);
        assertEquals(expectedCount, projectiles.size(), "Should fire the correct number of projectiles.");
        projectiles.forEach(p -> {
            assertEquals(BossProjectile.class, p.getClass());
            BossProjectile bp = (BossProjectile) p;
            assertEquals(expectedXVel, bp.getXVelocity());
            assertEquals(expectedYVel, bp.getYVelocity());
        });
    }

    private void checkDiagonalProjectiles(List<ActiveActorDestructible> projectiles) {
        BossProjectile bp1 = (BossProjectile) projectiles.get(0);
        BossProjectile bp2 = (BossProjectile) projectiles.get(1);
        BossProjectile bp3 = (BossProjectile) projectiles.get(2);
        assertEquals(-5, bp1.getXVelocity());
        assertEquals(-5, bp1.getYVelocity());
        assertEquals(-5, bp2.getXVelocity());
        assertEquals(0, bp2.getYVelocity());
        assertEquals(-5, bp3.getXVelocity());
        assertEquals(5, bp3.getYVelocity());
    }
}
