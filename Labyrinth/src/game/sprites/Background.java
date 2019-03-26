package game.sprites;
import java.awt.Graphics;

import game.engine.Level;

public class Background {
	private Textures textures;
	private Level level;

	public Background(Textures textures, Level level) {
		this.textures = textures;
		this.level = level;
	}

	public void render(Graphics g) {
		for (int row = 0; row < level.getRowCount(); row++) {
			for (int col = 0; col < level.getColCount(); col++) {
				g.drawImage(textures.background, col * level.getTileSize(), row * level.getTileSize(), null);
			}
		}
	}
}
