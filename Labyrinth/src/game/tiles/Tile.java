package game.tiles;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.enums.GameObjectType;

/**
 * This class handles the tile objects
 * 
 * @author Martin Holecek
 *
 */
public class Tile {
	private int row;
	private int col;
	private BufferedImage texture;
	private boolean solid;
	private int tileSize;
	private GameObjectType tileType;

	/**
	 * Constructor
	 * 
	 * @param row      - row position of the tile
	 * @param col      - column position of the tile
	 * @param tileSize - tile size
	 * @param texture  - texture image
	 * @param tileType - type of the texture
	 * @param solid    - determines if the player and enemy can go through
	 */
	public Tile(int row, int col, int tileSize, BufferedImage texture, GameObjectType tileType, boolean solid) {
		this.row = row;
		this.col = col;
		this.tileSize = tileSize;
		this.texture = texture;
		this.tileType = tileType;
		this.solid = solid;
	}

	/**
	 * Set the texture of the tile
	 * 
	 * @param texture - image of the texture
	 */
	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	/**
	 * Get type of the tile
	 * 
	 * @return - type of the tile
	 */
	public GameObjectType getTileType() {
		return tileType;
	}

	/**
	 * Render the tile on the screen
	 * 
	 * @param g - instance of the Graphics object
	 */
	public void render(Graphics g) {
		g.drawImage(texture, col * tileSize, row * tileSize, null);
	}

	/**
	 * Get bounds of the tile to find collisions
	 * 
	 * @return
	 */
	public Rectangle getBounds() {
		return new Rectangle(col * tileSize, row * tileSize, tileSize, tileSize);
	}

	/**
	 * Check if the tile is solid
	 * 
	 * @return true if tile is solid, otherwise, return false.
	 */
	public boolean isSolid() {
		return solid;
	}
}
