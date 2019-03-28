package game.entities;

import java.awt.Rectangle;

import game.engine.Level;

public class Entity {

	public int row, col;
	public Level level;
	public boolean movable;
	
	public Entity(int row, int col, Level level) {
		this.row = row;
		this.col = col;
		this.level = level;
		movable = true;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public Rectangle getBounds() {
		return new Rectangle(col, row, level.getTileSize(), level.getTileSize());
	}

	public boolean moveUp(int steps) {
		Rectangle rect = new Rectangle(col, row - steps, level.getTileSize(), level.getTileSize());
		if (level.intersectsSolid(rect) == false && level.contains(rect) && movable) {
			row -= steps;
			return true;
		}
		return false;
	}

	public boolean moveDown(int steps) {
		Rectangle rect = new Rectangle(col, row + steps, level.getTileSize(), level.getTileSize());
		if (level.intersectsSolid(rect) == false && level.contains(rect) && movable) {
			row += steps;
			return true;
		}
		return false;
	}

	public boolean moveRight(int steps) {
		Rectangle rect = new Rectangle(col + steps, row, level.getTileSize(), level.getTileSize());
		if (level.intersectsSolid(rect) == false && level.contains(rect) && movable) {
			col += steps;
			return true;
		}
		return false;
	}

	public boolean moveLeft(int steps) {
		Rectangle rect = new Rectangle(col - steps, row, level.getTileSize(), level.getTileSize());
		if (level.intersectsSolid(rect) == false && level.contains(rect) && movable) {
			col -= steps;
			return true;
		}
		return false;
	}
	
	public void setMovable(boolean movable) {
		this.movable = movable;
	}
}
