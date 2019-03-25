package game.tiles;
import java.awt.Graphics;

import game.enums.TileType;
import game.sprites.Textures;

public class GoalTile extends Tile{

	public GoalTile(int row, int col, int tileSize, Textures textures, boolean solid) {
		super(row, col, tileSize, textures, TileType.GOAL, solid);
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(textures.goal, col * tileSize, row * tileSize, null);
	}
}
