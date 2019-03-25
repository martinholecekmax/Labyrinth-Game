import java.awt.image.BufferedImage;

public class Textures {
	
	private SpriteSheet spriteSheet = null;
	BufferedImage player;
	BufferedImage missile;
	BufferedImage enemy;
	BufferedImage background;
	BufferedImage wall;
	
	public Textures(GameWindow gameWindow) {
		spriteSheet = new SpriteSheet(gameWindow.getSpriteSheet());
		getTextures();
	}
	
	private void getTextures() {
		player = spriteSheet.grabImage(1, 3, 32, 32);
		missile = spriteSheet.grabImage(10, 12, 32, 32);
		background = spriteSheet.grabImage(1, 16, 32, 32);
		enemy = spriteSheet.grabImage(2, 4, 32, 32);
		wall = spriteSheet.grabImage(1, 15, 32, 32);
	}
}
