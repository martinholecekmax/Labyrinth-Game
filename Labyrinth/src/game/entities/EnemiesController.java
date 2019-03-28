package game.entities;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

public class EnemiesController implements Iterable<IEntityEnemy> {
	private LinkedList<IEntityEnemy> enemies = new LinkedList<IEntityEnemy>();

	private int enemyKilled = 0;

	public int getEnemyKilled() {
		return enemyKilled;
	}

	public void tick() {
		for (IEntityEnemy enemy : enemies) {
			enemy.tick();
		}
	}

	public void render(Graphics g) {
		for (IEntityEnemy enemy : enemies) {
			enemy.render(g);
		}
	}

	public void addEnemy(IEntityEnemy enemy) {
		enemies.add(enemy);
	}

	public synchronized void removeEnemy(IEntityEnemy enemy) {
		enemies.remove(enemy);
		enemyKilled++;
	}

	public void clear() {
		enemies.clear();
	}

	@Override
	public Iterator<IEntityEnemy> iterator() {
		return enemies.iterator();
	}
}
