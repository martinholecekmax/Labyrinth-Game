import java.awt.Graphics;
import java.util.LinkedList;

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
//			if (bullet.getCol() > 200)
//				removeBullet(bullet);
//			if (bullet.getCol() < 10)
//				removeBullet(bullet);
//			if (bullet.getRow() > 200)
//				removeBullet(bullet);
//			if (bullet.getRow() < 10)
//				removeBullet(bullet);
			if (bullet.getCol() > 320)
				removeBullet(bullet);
			if (bullet.getCol() < 0)
				removeBullet(bullet);
			if (bullet.getRow() > 320)
				removeBullet(bullet);
			if (bullet.getRow() < 0)
				removeBullet(bullet);
			
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
	
	public LinkedList<IEntityBullets> getBulletsList(){
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
