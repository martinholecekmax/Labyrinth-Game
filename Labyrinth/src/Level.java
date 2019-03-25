import java.awt.Graphics;
import java.util.LinkedList;

public class Level {
	private Textures textures;

	private int[][] grid = new int[][] { 
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 }, 
		{ 4, 4, 4, 4, 4, 4, 4, 4, 0, 4 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
		{ 4, 4, 4, 0, 4, 4, 4, 4, 0, 4 }, 
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 4, 4, 4, 4, 4, 4, 4, 4, 0, 4 }, 
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 }, 
		{ 4, 4, 4, 0, 4, 4, 4, 4, 0, 4 },
		{ 1, 4, 0, 0, 0, 4, 0, 0, 0, 4 }, 
		{ 0, 0, 0, 4, 0, 0, 0, 4, 0, 4 } };
	
	private int tileSize = 32;
	
	private LinkedList<Tile> tiles;
	
	public Level(Textures textures) {
		this.textures = textures;
		tiles = new LinkedList<Tile>();
		generateLevel();
	}
	
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
			}
		}
	}
	
	public void render(Graphics g) {
		Tile tile;
		for (int index = 0; index < tiles.size(); index++) {
			tile = tiles.get(index);
			tile.render(g);
		}
	}
}
