package game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.engine.Level;
import game.gun.IEntityBullets;

/**
 * Player class
 * 
 * @author Martin Holecek
 *
 */
public class Player extends Entity implements IEntityBullets {
	private BufferedImage texture;

	/**
	 * Constructor
	 * 
	 * @param row      - row position
	 * @param col      - column position
	 * @param textures - texture that will be rendered
	 * @param level    - instance of the level class
	 */
	public Player(int row, int col, BufferedImage textures, Level level) {
		super(row, col, level);
		this.texture = textures;
	}

	/**
	 * Not implemented
	 */
	public void tick() {
	}

	/**
	 * Move player number of steps up.
	 * 
	 * @param steps - integer value of the number of steps
	 * @return - true if the enemy moved, otherwise false
	 */
	public boolean moveUp(int steps) {
		Rectangle rect = new Rectangle(col, row - steps, level.getTileSize(), level.getTileSize());
		if (level.intersectsSolid(rect) == false && level.contains(rect) && movable) {
			row -= steps;
			return true;
		}
		return false;
	}

	/**
	 * Move player number of steps down.
	 * 
	 * @param steps - integer value of the number of steps
	 * @return - true if the enemy moved, otherwise false
	 */
	public boolean moveDown(int steps) {
		Rectangle rect = new Rectangle(col, row + steps, level.getTileSize(), level.getTileSize());
		if (level.intersectsSolid(rect) == false && level.contains(rect) && movable) {
			row += steps;
			return true;
		}
		return false;
	}

	/**
	 * Move player number of steps right.
	 * 
	 * @param steps - integer value of the number of steps
	 * @return - true if the enemy moved, otherwise false
	 */
	public boolean moveRight(int steps) {
		Rectangle rect = new Rectangle(col + steps, row, level.getTileSize(), level.getTileSize());
		if (level.intersectsSolid(rect) == false && level.contains(rect) && movable) {
			col += steps;
			return true;
		}
		return false;
	}

	/**
	 * Move player number of steps left.
	 * 
	 * @param steps - integer value of the number of steps
	 * @return - true if the enemy moved, otherwise false
	 */
	public boolean moveLeft(int steps) {
		Rectangle rect = new Rectangle(col - steps, row, level.getTileSize(), level.getTileSize());
		if (level.intersectsSolid(rect) == false && level.contains(rect) && movable) {
			col -= steps;
			return true;
		}
		return false;
	}

	/**
	 * Render the player to the screen.
	 */
	public void render(Graphics g) {
		g.drawImage(texture, col, row, null);
	}
}
