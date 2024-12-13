package com.example.demo.actors.projectile;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.plane.FighterPlane;

/**
 * The {@code Projectile} class is an abstract representation of projectiles within the game,
 * extending {@link ActiveActorDestructible}. This class provides a foundational framework for defining the
 * appearance, behavior, and interaction of projectiles with other entities in the game, such as how they
 * move, how they impact targets, and how they are visualized.
 *
 * See the source code at <a href="https://github.com/TimZ21/CW2024/blob/master/src/main/java/com/example/demo/actors/projectile/Projectile.java">Projectile.java</a>
 */
public abstract class Projectile extends ActiveActorDestructible {

	/**
	 * Constructs a {@code Projectile} instance with the specified image, size, and initial position.
	 *
	 * @param imageName   The name of the image file representing the projectile.
	 * @param imageHeight The height of the image, used for scaling.
	 * @param initialXPos The initial X-coordinate of the projectile.
	 * @param initialYPos The initial Y-coordinate of the projectile.
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	/**
	 * Handles the logic for when the projectile takes damage.
	 * A projectile is destroyed immediately upon taking damage.
	 * This method overrides the {@link ActiveActorDestructible#destroy()} method
	 */
	@Override
	public void takeDamage() {
		this.destroy();
	}

	/**
	 * Updates the position of the projectile. This method must be implemented
	 * by subclasses to define the specific movement behavior of the projectile.
	 * This method overrides the {@link ActiveActorDestructible#updatePosition()} method
	 */
	@Override
	public abstract void updatePosition();
}
