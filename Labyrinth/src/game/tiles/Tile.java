package game.tiles;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.enums.TileType;
import game.sprites.Textures;

public class Tile {
	public int row;
	public int col;
	public Textures textures;
	public boolean solid;
	public int tileSize;
	public TileType tileType;

	public Tile(int row, int col, int tileSize, Textures textures, TileType tileType) {
		this.row = row;
		this.col = col;
		this.tileSize = tileSize;
		this.textures = textures;
		this.tileType = tileType;
	}

	public void render(Graphics g) {
	};


	public Rectangle getBounds() {
		return new Rectangle(col * tileSize, row * tileSize, tileSize, tileSize);
	}
}
