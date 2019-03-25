package game.entities;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.engine.GameObject;
import game.engine.Level;
import game.enums.TileType;
import game.gun.IEntityBullets;
import game.sprites.Textures;

public class Player extends GameObject implements IEntityBullets {
	private Textures textures;
	private Level level;

	public Player(int row, int col, Textures textures, Level level) {
		super(row, col);
		this.textures = textures;
		this.level = level;
	}
	
	public boolean moveUp() {
		if (row <= 0)
			return false;

		Rectangle rect = new Rectangle(row - 1, col, 32, 32);
		if (level.intersects(rect, TileType.WALL) == false) {
			row = row - 32;
			return true;
		}
		return false;
	}

	public boolean moveDown() {
		if (row >= 288)
			return false;
		Rectangle rect = new Rectangle(row + 1, col, 32, 32);
		if (level.intersects(rect, TileType.WALL) == false) {
			row = row + 32;
			return true;
		}
		return false;
	}

	public boolean moveRight() {
		if (col >= 288)
			return false;

		Rectangle rect = new Rectangle(row, col + 1, 32, 32);
		if (level.intersects(rect, TileType.WALL) == false) {
			col = col + 32;
			return true;
		}
		return false;
	}

	public boolean moveLeft() {
		if (col <= 0)
			return false;

		Rectangle rect = new Rectangle(row, col - 1, 32, 32);
		if (level.intersects(rect, TileType.WALL) == false) {
			col = col - 32;
			return true;
		}
		return false;
	}

	public void tick() {

	}

	public void render(Graphics g) {
		g.drawImage(textures.player, col, row, null);
	}
}
