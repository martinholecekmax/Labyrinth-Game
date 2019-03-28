package game.engine;
import game.entities.IEntityEnemy;
import game.gun.IEntityBullets;

public class Physics {
	public static boolean Collision(IEntityBullets entityA, IEntityEnemy entityB) {
		if (entityA.getBounds().intersects(entityB.getBounds())) {
			return true;
		}
		return false;
	}
}
