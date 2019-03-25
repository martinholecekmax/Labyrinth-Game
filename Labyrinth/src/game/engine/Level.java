package game.engine;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import game.enums.TileType;
import game.sprites.Textures;
import game.tiles.GoalTile;
import game.tiles.Tile;
import game.tiles.WallTile;

public class Level {
	private Textures textures;

	private int[][] grid = new int[][] { 
		{ 0, 0, 0, 0, 0, 4, 0, 0, 0, 4 }, 
		{ 0, 4, 4, 4, 4, 4, 4, 4, 0, 4 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
		{ 4, 4, 4, 4, 4, 4, 4, 4, 0, 4 }, 
		{ 0, 0, 4, 0, 0, 0, 4, 0, 0, 0 },
		{ 4, 4, 4, 0, 0, 0, 4, 4, 0, 4 }, 
		{ 0, 0, 4, 0, 0, 0, 4, 0, 0, 4 }, 
		{ 4, 4, 4, 4, 4, 4, 4, 4, 0, 4 },
		{ 1, 4, 0, 0, 0, 4, 0, 0, 0, 4 }, 
		{ 0, 0, 0, 4, 0, 0, 0, 4, 0, 4 } };

	private int tileSize = 32;

	private LinkedList<Tile> tiles;

	public Level(Textures textures) {
		this.textures = textures;
		tiles = new LinkedList<Tile>();
		generateLevel();
	}

	@Deprecated
	public int getLevelObject(int row, int col) {
		return grid[row][col];
	}

	public int getRowSize() {
		return grid.length;
	}

	public int getColSize() {
		return grid[0].length;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void generateLevel() {
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				if (grid[row][col] == 4) {
					tiles.add(new WallTile(row, col, tileSize, textures, true));
				}
				if (grid[row][col] == 1) {
					tiles.add(new GoalTile(row, col, tileSize, textures, true));
				}
			}
		}
	}

	public boolean intersects(Rectangle bounds, TileType tileType) {
		Tile tile;
		for (int index = 0; index < tiles.size(); index++) {
			tile = tiles.get(index);
			if (tile.getBounds().intersects(bounds) && tile.tileType == tileType)
				return true;
		}
		return false;
	}

	@Deprecated
	public boolean intersect(int row, int col) {
		Tile tile;
		for (int index = 0; index < tiles.size(); index++) {
			tile = tiles.get(index);
			if (inRange(row, tile.row * tile.tileSize, tile.row * tile.tileSize + tile.tileSize)
					&& inRange(row, tile.col * tile.tileSize, tile.col * tile.tileSize + tile.tileSize))
				return true;
		}
		return false;
	}

	@Deprecated
	public boolean inRange(int value, int min, int max) {
		return (value >= min) && (value <= max);
	}

	public void render(Graphics g) {
		Tile tile;
		for (int index = 0; index < tiles.size(); index++) {
			tile = tiles.get(index);
			tile.render(g);
		}
	}
}
