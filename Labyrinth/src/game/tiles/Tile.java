package game.tiles;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.enums.GameObjectType;

public class Tile {
	private int row;
	private int col;
	private BufferedImage texture;
	private boolean solid;
	private int tileSize;
	private GameObjectType tileType;
	
	public Tile(int row, int col, int tileSize, BufferedImage texture, GameObjectType tileType, boolean solid) {
		this.row = row;
		this.col = col;
		this.tileSize = tileSize;
		this.texture = texture;
		this.tileType = tileType;
		this.solid = solid;
	}

	public GameObjectType getTileType() {
		return tileType;
	}

	public void render(Graphics g) {
		g.drawImage(texture, col * tileSize, row * tileSize, null);
	}


	public Rectangle getBounds() {
		return new Rectangle(col * tileSize, row * tileSize, tileSize, tileSize);
	}
	
	public boolean isSolid() {
		return solid;
	}
}
