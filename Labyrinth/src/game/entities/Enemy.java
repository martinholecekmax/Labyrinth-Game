package game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import game.engine.Level;
import game.enums.GameObjectType;
import game.tiles.Door;

/**
 * Enemy object class.
 * 
 * @author Martin Holecek
 *
 */
public class Enemy extends Entity implements IEntityEnemy {
	private static final int STEPS = 1;
	private BufferedImage texture;
	private int direction = 0;
	private boolean moveLeft = false;

	private Random random = new Random();

	/**
	 * Constructor
	 * 
	 * @param row      - x position of the enemy
	 * @param col      - y position of the enemy
	 * @param textures - texture that will be rendered
	 * @param level    - game level
	 */
	public Enemy(int row, int col, BufferedImage textures, Level level) {
		super(row, col, level);
		this.texture = textures;
	}

	/**
	 * Process enemy's movement
	 */
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

	/**
	 * Move enemy number of steps up.
	 * 
	 * @param steps - integer value of the number of steps
	 * @return - true if the enemy moved, otherwise false
	 */
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

	/**
	 * Move enemy number of steps down.
	 * 
	 * @param steps - integer value of the number of steps
	 * @return - true if the enemy moved, otherwise false
	 */
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

	/**
	 * Move enemy number of steps right.
	 * 
	 * @param steps - integer value of the number of steps
	 * @return - true if the enemy moved, otherwise false
	 */
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

	/**
	 * Move enemy number of steps left.
	 * 
	 * @param steps - integer value of the number of steps
	 * @return - true if the enemy moved, otherwise false
	 */
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

	/**
	 * Render enemy to the screen
	 */
	public void render(Graphics g) {
		g.drawImage(texture, col, row, null);
	}
}
