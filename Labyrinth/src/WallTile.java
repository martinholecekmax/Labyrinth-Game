import java.awt.Graphics;

public class WallTile extends Tile {

	public WallTile(int row, int col, int tileSize, Textures textures, Boolean solid) {
		super(row, col, tileSize, textures, solid);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(textures.wall, col * tileSize, row * tileSize, null);
	}
}
