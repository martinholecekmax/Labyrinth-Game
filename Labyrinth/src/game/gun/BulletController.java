package game.gun;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

import game.engine.Level;
import game.engine.Physics;
import game.entities.IEntityEnemy;

public class BulletController implements Iterable<IEntityBullets>{
	private LinkedList<IEntityBullets> bullets = new LinkedList<IEntityBullets>();

	Level level;

	public BulletController(Level level) {
		this.level = level;
	}

	public void tick() {
		for (IEntityBullets bullet : bullets) {
			bullet.tick();
			if(!level.contains(bullet.getBounds()))
				removeBullet(bullet);
			else if (level.intersectsSolid(bullet.getBounds()))
				removeBullet(bullet);
		}
	}

	public void render(Graphics g) {
		for (IEntityBullets bullet : bullets) {
			bullet.render(g);
		}
	}

	public void addBullet(IEntityBullets block) {
		bullets.add(block);
	}

	public void removeBullet(IEntityBullets block) {
		bullets.remove(block);
	}

	public LinkedList<IEntityBullets> getBulletsList() {
		return bullets;
	}

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
