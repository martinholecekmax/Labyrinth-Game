package game.engine;
import java.util.LinkedList;

import game.entities.IEntityEnemy;
import game.gun.IEntityBullets;

public class Physics {
	public static boolean Collision(IEntityBullets entityA, LinkedList<IEntityEnemy> entityB) {
		for (int i = 0; i < entityB.size(); i++) {
			if (entityA.getBounds().intersects(entityB.get(i).getBounds())) {
				return true;
			}
		}
		return false;
	}

	public static boolean Collision(IEntityEnemy entityB, LinkedList<IEntityBullets> entityA) {
		for (int i = 0; i < entityA.size(); i++) {
			if (entityB.getBounds().intersects(entityA.get(i).getBounds())) {
				return true;
			}
		}
		return false;
	}

	public static boolean Collision(IEntityBullets entityA, IEntityEnemy entityB) {
		if (entityA.getBounds().intersects(entityB.getBounds())) {
			return true;
		}
		return false;
	}
}
