package com.example.demo.actors;

/**
 * The {@code Destructible} interface defines the necessary actions for game entities
 * that can be damaged and ultimately destroyed. Implementing this interface allows
 * objects to respond to damage and handle their destruction within the game.
 *
 * <p>Implementation guidelines:
 * - {@code takeDamage()} should adjust the object's health or state to reflect damage.
 * - {@code destroy()} should handle any cleanup or removal necessary when the object is destroyed.
 *
 * <p>Note: This is a fundamental interface used throughout the game framework to ensure
 * objects conform to basic interactive game mechanics.
 */
public interface Destructible {

	/**
	 * Applies damage effects to this object. Implementations should specify how damage
	 * affects the object, such as reducing health or triggering other effects.
	 */
	void takeDamage();

	/**
	 * Destroys this object, performing any necessary finalization such as removing the object
	 * from the game field, freeing resources, or triggering destruction animations or effects.
	 */
	void destroy();

}
