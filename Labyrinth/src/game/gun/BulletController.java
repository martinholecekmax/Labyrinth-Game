package game.gun;

import java.awt.Graphics;
import java.util.LinkedList;

import game.engine.Level;
import game.engine.Physics;
import game.entities.IEntityEnemy;
import game.enums.TileType;

public class BulletController {
	private LinkedList<IEntityBullets> bullets = new LinkedList<IEntityBullets>();

	IEntityBullets bullet;
	Level level;

	public BulletController(Level level) {
		this.level = level;
	}

	public void tick() {
		for (int index = 0; index < bullets.size(); index++) {
			bullet = bullets.get(index);
			bullet.tick();
			if (bullet.getCol() > level.getColSize() * level.getTileSize() || bullet.getCol() < 0
					|| bullet.getRow() > level.getRowSize() * level.getTileSize() || bullet.getRow() < 0)
				removeBullet(bullet);
			if (level.intersects(bullet.getBounds(), TileType.WALL)) {
				removeBullet(bullet);
			}
		}
	}

	public void render(Graphics g) {
		for (int index = 0; index < bullets.size(); index++) {
			bullets.get(index).render(g);
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
		for (int index = 0; index < bullets.size(); index++) {
			bullet = bullets.get(index);
			if (Physics.Collision(bullet, enemy)) {
				bullets.remove(bullet);
				hit = true;
			}
		}
		return hit;
	}
}
