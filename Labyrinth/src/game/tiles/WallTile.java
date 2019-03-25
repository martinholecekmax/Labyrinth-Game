package game.tiles;
import java.awt.Graphics;

import game.enums.TileType;
import game.sprites.Textures;

public class WallTile extends Tile {

	public WallTile(int row, int col, int tileSize, Textures textures, Boolean solid) {
		super(row, col, tileSize, textures, TileType.WALL, solid);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(textures.wall, col * tileSize, row * tileSize, null);
	}
}
