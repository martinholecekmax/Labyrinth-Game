package game.entities;

import java.awt.Rectangle;

import game.engine.Level;

/**
 * Entity class which is used for handling collisions
 * 
 * @author Martin Holecek
 *
 */
public class Entity {

	public int row, col;
	public Level level;
	public boolean movable;

	/**
	 * Constructor
	 * 
	 * @param row   - row position
	 * @param col   - column position
	 * @param level - instance of the level class
	 */
	public Entity(int row, int col, Level level) {
		this.row = row;
		this.col = col;
		this.level = level;
		movable = true;
	}

	/**
	 * Get row position
	 * 
	 * @return - integer value of row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Get column position
	 * 
	 * @return - integer value of column
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Get border bounds of the entity
	 * 
	 * @return - instance of the rectangle class
	 */
	public Rectangle getBounds() {
		return new Rectangle(col, row, level.getTileSize(), level.getTileSize());
	}

	/**
	 * Enable or disable entity's movement
	 * 
	 * @param movable - if true entity can move, otherwise false
	 */
	public void setMovable(boolean movable) {
		this.movable = movable;
	}
}
