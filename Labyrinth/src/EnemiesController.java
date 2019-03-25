import java.awt.Graphics;
import java.util.LinkedList;

public class EnemiesController {
	private LinkedList<IEntityEnemy> enemies = new LinkedList<IEntityEnemy>();

	IEntityEnemy enemy;
	
	public void tick() {
		for (int index = 0; index < enemies.size(); index++) {
			enemy = enemies.get(index);
			enemy.tick();
		}
	}

	public void render(Graphics g) {
		for (int index = 0; index < enemies.size(); index++) {
			enemies.get(index).render(g);
		}
	}

	public void addEnemy(IEntityEnemy block) {
		enemies.add(block);
	}

	public void removeEnemy(IEntityEnemy block) {
		enemies.remove(block);
	}
	
	public LinkedList<IEntityEnemy> getAllEnemies(){
		return enemies;
	}
}