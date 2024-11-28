package com.example.demo.actors.plane;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.projectile.BossProjectile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.example.demo.level.LevelParent.VOLECITY_CHANGE;

public class FirePatternManager {

    private static final int FIRE_PATTERN_HORIZONTAL = 0;
    private static final int FIRE_PATTERN_VERTICAL = 1;
    private static final int FIRE_PATTERN_DIAGONAL = 2;

    private final double projectileYPositionOffset;

    public FirePatternManager(double projectileYPositionOffset) {
        this.projectileYPositionOffset = projectileYPositionOffset;
    }

    /**
     * Fires projectiles based on a randomly selected fire pattern.
     *
     * @param x The X position of the boss.
     * @param y The Y position of the boss.
     * @return A list of {@code BossProjectile} objects if the boss decides to fire; otherwise, an empty list.
     */
    public List<ActiveActorDestructible> fireProjectiles(double x, double y) {
        int firePattern = selectFirePattern();
        switch (firePattern) {
            case FIRE_PATTERN_HORIZONTAL:
                return fireHorizontalPattern(x, y);
            case FIRE_PATTERN_VERTICAL:
                return fireVerticalPattern(x, y);
            case FIRE_PATTERN_DIAGONAL:
                return fireDiagonalPattern(x, y);
            default:
                return Collections.emptyList();
        }
    }

    /**
     * Selects a random fire pattern.
     *
     * @return The selected fire pattern.
     */
    private int selectFirePattern() {
        return new Random().nextInt(3); // 3 patterns: horizontal, vertical, diagonal
    }

    /**
     * Fires three projectiles horizontally.
     *
     * @param x The X position of the boss.
     * @param y The Y position of the boss.
     * @return A list of {@code BossProjectile} objects.
     */
    private List<ActiveActorDestructible> fireHorizontalPattern(double x, double y) {
        List<ActiveActorDestructible> projectiles = new ArrayList<>();
        projectiles.add(new BossProjectile(x, y - projectileYPositionOffset, -5, 0));
        projectiles.add(new BossProjectile(x, y, -5, 0));
        projectiles.add(new BossProjectile(x, y + projectileYPositionOffset, -5, 0));
        return projectiles;
    }

    /**
     * Fires three projectiles vertically.
     *
     * @param x The X position of the boss.
     * @param y The Y position of the boss.
     * @return A list of {@code BossProjectile} objects.
     */
    private List<ActiveActorDestructible> fireVerticalPattern(double x, double y) {
        List<ActiveActorDestructible> projectiles = new ArrayList<>();
        projectiles.add(new BossProjectile(x, y - projectileYPositionOffset, -5, 0));
        projectiles.add(new BossProjectile(x, y, -5, 0));
        projectiles.add(new BossProjectile(x, y + projectileYPositionOffset, -5, 0));
        return projectiles;
    }

    /**
     * Fires three projectiles diagonally.
     *
     * @param x The X position of the boss.
     * @param y The Y position of the boss.
     * @return A list of {@code BossProjectile} objects.
     */
    private List<ActiveActorDestructible> fireDiagonalPattern(double x, double y) {
        List<ActiveActorDestructible> projectiles = new ArrayList<>();
        projectiles.add(new BossProjectile(x, y - projectileYPositionOffset, -5, -5)); // Upper left
        projectiles.add(new BossProjectile(x, y, -5, 0)); // Horizontal left
        projectiles.add(new BossProjectile(x, y + projectileYPositionOffset, -5, 5)); // Lower left
        return projectiles;
    }
}