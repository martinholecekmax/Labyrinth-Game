package game.entities;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import game.engine.Level;
import game.enums.GameObjectType;
import game.tiles.Door;

public class Enemy extends Entity implements IEntityEnemy {
	private static final int STEPS = 1;
	private BufferedImage texture;
	private int direction = 0;
	private boolean moveLeft = false;

	private Random random = new Random();

	public Enemy(int row, int col, BufferedImage textures, Level level) {
		super(row, col, level);
		this.texture = textures;
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

	public boolean moveUp(int steps) {
		Rectangle rect = new Rectangle(col, row - steps, level.getTileSize(), level.getTileSize());
		Door door = (Door) level.getTile(rect, GameObjectType.DOOR);
		if (door != null) {
			if (!door.isOpened()) {
				return false;
			}
		}
		if (level.intersectsSolid(rect) == false && level.contains(rect) && movable) {
			row -= steps;
			return true;
		}
		return false;
	}

	public boolean moveDown(int steps) {
		Rectangle rect = new Rectangle(col, row + steps, level.getTileSize(), level.getTileSize());
		Door door = (Door) level.getTile(rect, GameObjectType.DOOR);
		if (door != null) {
			if (!door.isOpened()) {
				return false;
			}
		}
		if (level.intersectsSolid(rect) == false && level.contains(rect) && movable) {
			row += steps;
			return true;
		}
		return false;
	}

	public boolean moveRight(int steps) {
		Rectangle rect = new Rectangle(col + steps, row, level.getTileSize(), level.getTileSize());
		Door door = (Door) level.getTile(rect, GameObjectType.DOOR);
		if (door != null) {
			if (!door.isOpened()) {
				return false;
			}
		}
		if (level.intersectsSolid(rect) == false && level.contains(rect) && movable) {
			col += steps;
			return true;
		}
		return false;
	}

	public boolean moveLeft(int steps) {
		Rectangle rect = new Rectangle(col - steps, row, level.getTileSize(), level.getTileSize());
		Door door = (Door) level.getTile(rect, GameObjectType.DOOR);
		if (door != null) {
			if (!door.isOpened()) {
				return false;
			}
		}
		if (level.intersectsSolid(rect) == false && level.contains(rect) && movable) {
			col -= steps;
			return true;
		}
		return false;
	}

	public void render(Graphics g) {
		g.drawImage(texture, col, row, null);
	}
}
