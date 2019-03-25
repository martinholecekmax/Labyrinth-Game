import java.awt.Graphics;

public class Background {
	private Textures textures;
	private Level level;

	public Background(Textures textures, Level level) {
		this.textures = textures;
		this.level = level;
	}

	public void render(Graphics g) {
		for (int row = 0; row < level.getRowSize(); row++) {
			for (int col = 0; col < level.getColSize(); col++) {
				g.drawImage(textures.background, col * level.getTileSize(), row * level.getTileSize(), null);
			}
		}
	}
}
