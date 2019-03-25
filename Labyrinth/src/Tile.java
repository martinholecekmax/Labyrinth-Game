import java.awt.Graphics;
import java.awt.Rectangle;

public class Tile {
	public int row;
	public int col;
	public Textures textures;
	public boolean solid;
	public int tileSize;

	public Tile(int row, int col, int tileSize,Textures textures, boolean solid) {
		this.row = row;
		this.col = col;
		this.tileSize = tileSize;
		this.textures = textures;
		this.solid = solid;
	}

	public void render(Graphics g) {
	};

	public boolean solid() {
		return solid;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(row * tileSize, col * tileSize, tileSize, tileSize);
	}
}
