package game.sprites;

import java.awt.image.BufferedImage;

/**
 * This class handles the textures of game objects.
 * 
 * @author Martin Holecek
 *
 */
public class Textures {

	private SpriteSheet spriteSheet = null;
	public BufferedImage player;
	public BufferedImage missile;
	public BufferedImage enemy;
	public BufferedImage background;
	public BufferedImage wall;
	public BufferedImage goal;
	public BufferedImage doorClosed;
	public BufferedImage doorOpened;

	/**
	 * Constructor
	 * 
	 * @param spriteSheet - Image tile set of the game objects
	 */
	public Textures(BufferedImage spriteSheet) {
		this.spriteSheet = new SpriteSheet(spriteSheet);
		getTextures();
	}

	/**
	 * Store texture of the game objects
	 */
	private void getTextures() {
		player = spriteSheet.grabImage(1, 3, 32, 32);
		missile = spriteSheet.grabImage(10, 12, 32, 32);
		background = spriteSheet.grabImage(1, 16, 32, 32);
		enemy = spriteSheet.grabImage(2, 4, 32, 32);
		wall = spriteSheet.grabImage(1, 15, 32, 32);
		goal = spriteSheet.grabImage(24, 13, 32, 32);
		doorClosed = spriteSheet.grabImage(24, 12, 32, 32);
		doorOpened = spriteSheet.grabImage(28, 12, 32, 32);
	}
}
