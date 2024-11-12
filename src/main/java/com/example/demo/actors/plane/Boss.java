package com.example.demo.actors.plane;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.projectile.BossProjectile;
import com.example.demo.view.ShieldImage;
import javafx.application.Platform;
import javafx.scene.Group;

import java.util.*;

public class Boss extends FighterPlane {

	private static final String IMAGE_NAME = "bossplane.png";
	private static final double INITIAL_X_POSITION = 1000.0;
	private static final double INITIAL_Y_POSITION = 400;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
private static final double BOSS_FIRE_RATE = .04;
//	private static final double BOSS_SHIELD_PROBABILITY = .002;
private static final double BOSS_SHIELD_PROBABILITY = .002;
	private static final int IMAGE_HEIGHT = 300;
	private static final int VERTICAL_VELOCITY = 8;
//	private static final int HEALTH = 100;
private static final int HEALTH = 10;
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
	private static final int ZERO = 0;
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
	private static final int Y_POSITION_UPPER_BOUND = -100;
	private static final int Y_POSITION_LOWER_BOUND = 475;
	private static final int MAX_FRAMES_WITH_SHIELD = 500;
	private final List<Integer> movePattern;
	private boolean isShielded;
	private int consecutiveMovesInSameDirection;
	private int indexOfCurrentMove;
	private int framesWithShieldActivated;
	private final ShieldImage sld;

	public Boss(Group root) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		isShielded = false;
		initializeMovePattern();

		sld = new ShieldImage(INITIAL_X_POSITION, INITIAL_Y_POSITION);
        Platform.runLater(() -> {
            root.getChildren().add(sld.getContainer());
            sld.hideShield();
            sld.getContainer().toFront();
        });

    }

	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}
	
	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
		updateShieldPosition();
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
	}
	
	@Override
	public void takeDamage() {
		if (!isShielded) {
			super.takeDamage();
		}
	}

	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

//	private void updateShield() {
//		if (isShielded) framesWithShieldActivated++;
//		else if (shieldShouldBeActivated()) activateShield();
//		if (shieldExhausted()) deactivateShield();
//	}

	/**
	 * Updates the shield status for the boss. If the shield is active,
	 * it checks if the shield has been exhausted and deactivates it.
	 * If the shield is not active, it checks if it should be activated
	 * based on a random probability.
	 */
	private void updateShield() {
		if (isShielded) {
			framesWithShieldActivated++;
			System.out.println("Shield is active. Frames with shield: " + framesWithShieldActivated);
			if (shieldExhausted()) {
				deactivateShield();
			}
		} else if (shieldShouldBeActivated()) {
			activateShield();
		}
	}

	/**
	 * Updates the position of the shield image to follow the boss's movement.
	 * The position is calculated with an offset to center the shield on the boss.
	 * This method is run on the JavaFX application thread using {@code Platform.runLater()}.
	 */
	private void updateShieldPosition() {
		Platform.runLater(() -> {
			double bossX = getLayoutX() + getTranslateX();
			double bossY = getLayoutY() + getTranslateY();

			double shieldOffsetX = (IMAGE_HEIGHT - sld.getContainer().getWidth()) / 2;
			double shieldOffsetY = (IMAGE_HEIGHT - sld.getContainer().getHeight()) / 2;

			sld.setPosition(bossX + shieldOffsetX, bossY + shieldOffsetY);
		});
	}

	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}
		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}
		return currentMove;
	}

	private boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	private boolean shieldShouldBeActivated() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

//	private boolean shieldExhausted() {
//		return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
//	}


	private boolean shieldExhausted() {
		boolean exhausted = framesWithShieldActivated >= MAX_FRAMES_WITH_SHIELD;
		System.out.println("Checking if shield is exhausted: " + exhausted);
		return exhausted;
	}
//	private void activateShield() {
//		isShielded = true;
//	}
	/**
	 * Activates the shield, making it visible and setting the shielded status to true.
	 * Logs the activation for debugging purposes.
	 */
	private void activateShield() {
		isShielded = true;
		sld.showShield(); // 显示盾牌
	}
//	private void deactivateShield() {
//		isShielded = false;
//		framesWithShieldActivated = 0;
//	}

	/**
	 * Deactivates the shield, hiding it and resetting the frame counter.
	 * Logs the deactivation for debugging purposes.
	 */
	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
		sld.hideShield(); // 隐藏盾牌
	}

}
