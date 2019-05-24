package game.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class handles loading of the textures
 * 
 * @author Martin Holecek
 *
 */
public class BufferedImageLoader {
	private BufferedImage image;

	/**
	 * Load images from the file
	 * 
	 * @param path - the path of the file
	 * @return - instance of the ImageIO object
	 * @throws IOException
	 */
	public BufferedImage loadImage(String path) throws IOException {
		image = ImageIO.read(getClass().getResource(path));
		return image;

	}
}
