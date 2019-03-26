package game.engine;

import java.awt.Rectangle;

import game.enums.TileType;

public class GameObject {

	public int row, col;
	public Level level;

	public GameObject(int row, int col, Level level) {
		this.row = row;
		this.col = col;
		this.level = level;
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
		if (level.intersects(rect, TileType.WALL) == false && level.contains(rect)) {
			row -= steps;
			return true;
		}
		return false;
	}

	public boolean moveDown(int steps) {
		Rectangle rect = new Rectangle(col, row + steps, level.getTileSize(), level.getTileSize());
		if (level.intersects(rect, TileType.WALL) == false && level.contains(rect)) {
			row += steps;
			return true;
		}
		return false;
	}

	public boolean moveRight(int steps) {
		Rectangle rect = new Rectangle(col + steps, row, level.getTileSize(), level.getTileSize());
		if (level.intersects(rect, TileType.WALL) == false && level.contains(rect)) {
			col += steps;
			return true;
		}
		return false;
	}

	public boolean moveLeft(int steps) {
		Rectangle rect = new Rectangle(col - steps, row, level.getTileSize(), level.getTileSize());
		if (level.intersects(rect, TileType.WALL) == false && level.contains(rect)) {
			col -= steps;
			return true;
		}
		return false;
	}
}
