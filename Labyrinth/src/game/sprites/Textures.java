package game.sprites;
import java.awt.image.BufferedImage;

public class Textures {
	
	private SpriteSheet spriteSheet = null;
	public BufferedImage player;
	public BufferedImage missile;
	public BufferedImage enemy;
	public BufferedImage background;
	public BufferedImage wall;
	public BufferedImage goal;
	
	public Textures(BufferedImage spriteSheet) {
		this.spriteSheet = new SpriteSheet(spriteSheet);
		getTextures();
	}
	
	private void getTextures() {
		player = spriteSheet.grabImage(1, 3, 32, 32);
		missile = spriteSheet.grabImage(10, 12, 32, 32);
		background = spriteSheet.grabImage(1, 16, 32, 32);
		enemy = spriteSheet.grabImage(2, 4, 32, 32);
		wall = spriteSheet.grabImage(1, 15, 32, 32);
		goal = spriteSheet.grabImage(12, 23, 32, 32);
	}
}
