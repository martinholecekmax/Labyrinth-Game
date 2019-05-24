package game.gun;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Interface for Bullets
 * 
 * @author Martin Holecek
 *
 */
public interface IEntityBullets {
	public void tick();

	public void render(Graphics g);

	public Rectangle getBounds();

	public int getRow();

	public int getCol();
}
