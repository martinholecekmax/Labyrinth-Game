package game.entities;

import java.awt.Graphics;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This class controls all enemies.
 * 
 * @author Martin Holecek
 *
 */
public class EnemiesController implements Iterable<IEntityEnemy> {
	private List<IEntityEnemy> enemies = Collections.synchronizedList(new LinkedList<IEntityEnemy>());

	private int enemyKilled = 0;
	private boolean freeze = false;
	private int freezeInterval = 10000;
	private long freezeTimer = System.currentTimeMillis();

	/**
	 * Freeze all enemies
	 * 
	 * @param freeze - false will freeze movement of all enemies, true will resume
	 *               the movement.
	 */
	public void setFreeze(boolean freeze) {
		this.freeze = freeze;
		freezeTimer = System.currentTimeMillis();
	}

	/**
	 * Change the interval for which are enemies frozen
	 * 
	 * @param freezeInterval
	 */
	public void setFreezeInterval(int freezeInterval) {
		this.freezeInterval = freezeInterval;
	}

	/**
	 * Get number of enemies that have been successfully killed.
	 * 
	 * @return - integer of killed enemies
	 */
	public int getEnemyKilled() {
		return enemyKilled;
	}

	/**
	 * Perform movement of enemies
	 */
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

	/**
	 * Render enemies to the screen.
	 * 
	 * @param g - instance of the Graphics object.
	 */
	public void render(Graphics g) {
		for (IEntityEnemy enemy : enemies) {
			enemy.render(g);
		}
	}

	/**
	 * Add new enemy to the list
	 * 
	 * @param enemy instance of the enemy object
	 */
	public void addEnemy(IEntityEnemy enemy) {
		enemies.add(enemy);
	}

	/**
	 * Remove enemy from the list
	 * 
	 * @param enemy instance of the enemy object
	 */
	public void removeEnemy(IEntityEnemy enemy) {
		enemies.remove(enemy);
	}

	/**
	 * Remove all enemies from the list.
	 */
	public void clear() {
		enemies.clear();
	}

	@Override
	public Iterator<IEntityEnemy> iterator() {
		return enemies.iterator();
	}

	/**
	 * When the enemy is killed, increase the counter.
	 */
	public void increaseKilled() {
		enemyKilled++;
	}
}
