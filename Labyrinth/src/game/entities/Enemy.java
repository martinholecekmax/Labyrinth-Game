package game.entities;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import game.engine.GameObject;
import game.engine.Level;
import game.enums.TileType;
import game.sprites.Textures;

public class Enemy extends GameObject implements IEntityEnemy {
	private static final int STEPS = 1;
	private Textures textures;
	private Level level;
	private int direction = 0;
	private boolean moveLeft = false;

	private Random random = new Random();

	public Enemy(int row, int col, Textures textures, Level level) {
		super(row, col);
		this.textures = textures;
		this.level = level;
	}

	public boolean moveUp() {
		if (row <= 0)
			return false;

		Rectangle rect = new Rectangle(row - 1, col, 32, 32);
		if (level.intersects(rect, TileType.WALL) == false) {
			row = row - STEPS;
			return true;
		}
		return false;
	}

	public boolean moveDown() {
		if (row >= 288)
			return false;

		Rectangle rect = new Rectangle(row + 1, col, 32, 32);
		if (level.intersects(rect, TileType.WALL) == false) {
			row = row + STEPS;
			return true;
		}
		return false;
	}

	public boolean moveRight() {
		if (col >= 288)
			return false;

		Rectangle rect = new Rectangle(row, col + 1, 32, 32);
		if (level.intersects(rect, TileType.WALL) == false) {
			col = col + STEPS;
			return true;
		}
		return false;
	}

	public boolean moveLeft() {
		if (col <= 0)
			return false;

		Rectangle rect = new Rectangle(row, col - 1, 32, 32);
		if (level.intersects(rect, TileType.WALL) == false) {
			col = col - STEPS;
			return true;
		}
		return false;
	}

	public void tick() {
		if (direction == 0) {
			moveLeft = moveUp();
		} else if (direction == 1) {
			moveLeft = moveLeft();
		} else if (direction == 2) {
			moveLeft = moveDown();
		} else {
			moveLeft = moveRight();
		}

		if (!moveLeft) {
			direction = random.nextInt(4);
		}
	}

	public void render(Graphics g) {
		g.drawImage(textures.enemy, col, row, null);
	}
}
