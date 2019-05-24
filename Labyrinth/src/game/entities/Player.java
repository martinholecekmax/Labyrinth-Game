package game.entities;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.engine.Level;
import game.gun.IEntityBullets;

public class Player extends Entity implements IEntityBullets {
	private BufferedImage texture;

	public Player(int row, int col, BufferedImage textures, Level level) {
		super(row, col, level);
		this.texture = textures;
	}
	
	public void tick() {

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

	public void render(Graphics g) {
		g.drawImage(texture, col, row, null);
	}
}
