package game.engine;

import game.entities.IEntityEnemy;
import game.gun.IEntityBullets;

/**
 * This class handles Physics of the game.
 * 
 * @author Martin Holecek
 *
 */
public class Physics {

	/**
	 * Check if there is the collision between bullets and enemies.
	 * 
	 * @param entityA - bullets entity
	 * @param entityB - enemy entity
	 * @return - true if there is a collision, otherwise, false.
	 */
	public static boolean Collision(IEntityBullets entityA, IEntityEnemy entityB) {
		if (entityA.getBounds().intersects(entityB.getBounds())) {
			return true;
		}
		return false;
	}
}
