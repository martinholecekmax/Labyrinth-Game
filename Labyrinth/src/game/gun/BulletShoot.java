package game.gun;

import java.awt.Graphics;

import game.engine.Level;
import game.entities.Entity;
import game.entities.Player;
import game.enums.Direction;
import game.sprites.Textures;

/**
 * Single Bullet object
 * 
 * @author Martin Holecek
 *
 */
public class BulletShoot extends Entity implements IEntityBullets {
	private Textures texture;
	private Direction direction;

	/**
	 * Constructor
	 * 
	 * @param player    - instance of the player
	 * @param texture   - texture that will be rendered
	 * @param direction - direction in which the bullet travels
	 * @param level     - instance of the level object
	 */
	public BulletShoot(Player player, Textures texture, Direction direction, Level level) {
		super(player.row, player.col, level);
		this.texture = texture;
		this.direction = direction;
	}

	@Override
	public void tick() {
		switch (direction) {
		case UP:
			row = row - 5;
			break;
		case DOWN:
			row = row + 5;
			break;
		case LEFT:
			col = col - 5;
			break;
		case RIGHT:
			col = col + 5;
			break;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(texture.missile, col, row, null);
		// g.drawRect(getBounds().x, getBounds().y, getBounds().width,
		// getBounds().height);
	}
}
