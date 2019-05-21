package game.entities;

import java.awt.Graphics;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class EnemiesController implements Iterable<IEntityEnemy> {
	private List<IEntityEnemy> enemies = Collections.synchronizedList(new LinkedList<IEntityEnemy>());

	private int enemyKilled = 0;
	private boolean freeze = false;
	private int freezeInterval = 10000;
	private long freezeTimer = System.currentTimeMillis();
	
	public void setFreeze(boolean freeze) {
		this.freeze = freeze;
		freezeTimer = System.currentTimeMillis();
	}

	public void setFreezeInterval(int freezeInterval) {
		this.freezeInterval = freezeInterval;
	}

	public int getEnemyKilled() {
		return enemyKilled;
	}

	public void tick() {
		if (!freeze) {
			for (IEntityEnemy enemy : enemies) {
				enemy.tick();
			}
		} else {
			if (System.currentTimeMillis() - freezeTimer > freezeInterval) {
				freeze = false;
			}
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

	public void removeEnemy(IEntityEnemy enemy) {
		enemies.remove(enemy);
	}

	public void clear() {
		enemies.clear();
	}

	@Override
	public Iterator<IEntityEnemy> iterator() {
		return enemies.iterator();
	}

	public void increaseKilled() {
		enemyKilled++;
	}
}
