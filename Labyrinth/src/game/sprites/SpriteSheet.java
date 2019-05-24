package game.sprites;

import java.awt.image.BufferedImage;

/**
 * Handles image of the tile set which is used for setting up textures for the
 * game objects
 * 
 * @author Martin Holecek
 *
 */
public class SpriteSheet {
	private BufferedImage image;

	/**
	 * Constructor
	 * 
	 * @param image - instance of the BufferedImage object
	 */
	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}

	/**
	 * 
	 * @param col    - column position of the texture
	 * @param row    - row position of the texture
	 * @param width  - width of the texture
	 * @param height - height of the texture
	 * @return - the image of the texture
	 */
	public BufferedImage grabImage(int col, int row, int width, int height) {
		BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
		return img;
	}
}
