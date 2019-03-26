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

	// private int[][] grid = new int[][] { { 0, 0, 0, 0, 0, 4, 0, 4 }, { 4, 0, 4,
	// 4, 4, 4, 4, 4 },
	// { 0, 0, 0, 0, 0, 0, 0, 0 }, { 4, 4, 4, 4, 4, 4, 4, 4 }, { 4, 0, 4, 0, 0, 0,
	// 4, 4 },
	// { 4, 4, 4, 0, 0, 0, 4, 4 }, { 4, 0, 4, 0, 0, 0, 4, 4 }, { 4, 1, 0, 0, 0, 0,
	// 0, 4 },
	// { 4, 0, 0, 4, 0, 0, 0, 4 } };
//	private int[][] grid = new int[][] { { 0, 0, 0, 0, 0, 4, 0, 0, 0, 4 }, { 4, 0, 4, 4, 4, 4, 4, 4, 0, 4 },
//			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 }, { 4, 4, 4, 4, 4, 4, 4, 4, 0, 4 }, { 4, 0, 4, 0, 0, 0, 4, 0, 0, 4 },
//			{ 4, 4, 4, 0, 0, 0, 4, 4, 0, 4 }, { 4, 0, 0, 0, 0, 0, 0, 0, 0, 4 }, { 4, 4, 4, 4, 4, 4, 4, 4, 0, 4 },
//			{ 4, 1, 0, 0, 0, 4, 0, 0, 0, 4 }, { 4, 0, 0, 4, 0, 0, 0, 4, 0, 4 } };

	private int[][] grid = new int[][] { { 0, 0, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 4 },
			{ 4, 0, 4, 4, 4, 4, 4, 4, 0, 4, 0, 0, 0, 0, 0, 4 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 4, 4, 4, 4, 4, 4, 4, 4, 0, 4, 0, 0, 0, 0, 0, 4 }, { 4, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 4 },
			{ 4, 4, 4, 0, 0, 0, 4, 4, 0, 4, 0, 0, 0, 0, 0, 4 }, { 4, 0, 4, 0, 0, 0, 4, 0, 0, 4, 0, 0, 0, 0, 0, 4 },
			{ 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 }, { 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 },
			{ 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 }, { 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 },
			{ 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 }, { 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 },
			{ 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 }, { 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 },
			{ 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 }, { 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 },
			{ 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 }, { 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 },
			{ 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 }, { 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 },
			{ 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 }, { 4, 4, 4, 4, 4, 4, 4, 4, 0, 4, 0, 0, 0, 0, 0, 4 },
			{ 4, 1, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 4 }, { 4, 0, 0, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 0, 0, 4 } };

	private int tileSize = 32;

	private LinkedList<Tile> tiles;

	public Level(Textures textures) {
		this.textures = textures;
		tiles = new LinkedList<Tile>();
		generateLevel();
	}

	// private void loadLevel(String path) {
	// InputStream inputStream = this.getClass().getResourceAsStream(path);
	// BufferedReader reader = new BufferedReader(new
	// InputStreamReader(inputStream));
	//
	// try {
	// String line;
	//
	// while ((line = reader.readLine()) != null) {
	// String[] tokens = line.split(",");
	//
	// }
	// } catch (IOException e) {
	// System.exit(1);
	// }
	// }

	public Rectangle getBounds() {
		return new Rectangle(0, 0, getColSize(), getRowSize());
	}

	public boolean contains(Rectangle rectangle) {
		return getBounds().contains(rectangle);
	}

	public int getRowCount() {
		return grid.length;
	}

	public int getColCount() {
		return grid[0].length;
	}

	public int getRowSize() {
		return getRowCount() * tileSize;
	}

	public int getColSize() {
		return getColCount() * tileSize;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void generateLevel() {
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				if (grid[row][col] == 4) {
					tiles.add(new WallTile(row, col, tileSize, textures));
				}
				if (grid[row][col] == 1) {
					tiles.add(new GoalTile(row, col, tileSize, textures));
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

	public void render(Graphics g) {
		Tile tile;
		for (int index = 0; index < tiles.size(); index++) {
			tile = tiles.get(index);
			tile.render(g);
		}
	}
}
