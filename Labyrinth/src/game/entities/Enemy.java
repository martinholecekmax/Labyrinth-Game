package game.entities;
import java.awt.Graphics;
import java.util.Random;

import game.engine.GameObject;
import game.engine.Level;
import game.sprites.Textures;

public class Enemy extends GameObject implements IEntityEnemy {
	private static final int STEPS = 1;
	private Textures textures;
	private int direction = 0;
	private boolean moveLeft = false;

	private Random random = new Random();

	public Enemy(int row, int col, Textures textures, Level level) {
		super(row, col, level);
		this.textures = textures;
	}

	public void tick() {
		if (direction == 0) {
			moveLeft = moveUp(STEPS);
		} else if (direction == 1) {
			moveLeft = moveLeft(STEPS);
		} else if (direction == 2) {
			moveLeft = moveDown(STEPS);
		} else {
			moveLeft = moveRight(STEPS);
		}

		if (!moveLeft) {
			direction = random.nextInt(4);
		}
	}

	public void render(Graphics g) {
		g.drawImage(textures.enemy, col, row, null);
	}
}
