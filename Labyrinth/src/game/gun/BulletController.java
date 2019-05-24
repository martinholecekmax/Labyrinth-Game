package game.gun;

import java.awt.Graphics;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import game.engine.Level;
import game.engine.Physics;
import game.entities.IEntityEnemy;

/**
 * This class handles bullets.
 * 
 * @author Martin Holecek
 *
 */
public class BulletController implements Iterable<IEntityBullets> {
	private List<IEntityBullets> bullets = Collections.synchronizedList(new LinkedList<IEntityBullets>());

	Level level;

	/**
	 * Constructor
	 * 
	 * @param level - instance of the level object
	 */
	public BulletController(Level level) {
		this.level = level;
	}

	/**
	 * Process movement of the bullets
	 */
	public void tick() {
		for (IEntityBullets bullet : bullets) {
			bullet.tick();
			if (!level.contains(bullet.getBounds()))
				removeBullet(bullet);
			else if (level.intersectsSolid(bullet.getBounds()))
				removeBullet(bullet);
		}
	}

	/**
	 * Renders bullets on the screen
	 * 
	 * @param g - instance of the Graphics object
	 */
	public void render(Graphics g) {
		for (IEntityBullets bullet : bullets) {
			bullet.render(g);
		}
	}

	/**
	 * Add instance of the bullet to the list
	 * 
	 * @param bullet - instance of the bullet object
	 */
	public void addBullet(IEntityBullets bullet) {
		bullets.add(bullet);
	}

	/**
	 * Remove instance of the bullet to the list
	 * 
	 * @param bullet - instance of the bullet object
	 */
	public void removeBullet(IEntityBullets bullet) {
		bullets.remove(bullet);
	}

	/**
	 * Get reference of the bullet list
	 * 
	 * @return bullets list
	 */
	public List<IEntityBullets> getBulletsList() {
		return bullets;
	}

	/**
	 * Handles collision of the bullets
	 * 
	 * @param enemy - instance of the enemy
	 * @return - true if the bullet hit the enemy, otherwise, return false.
	 */
	public boolean collision(IEntityEnemy enemy) {
		boolean hit = false;
		for (IEntityBullets bullet : bullets) {
			if (Physics.Collision(bullet, enemy)) {
				bullets.remove(bullet);
				hit = true;
			}
		}
		return hit;
	}

	@Override
	public Iterator<IEntityBullets> iterator() {
		return bullets.iterator();
	}
}
