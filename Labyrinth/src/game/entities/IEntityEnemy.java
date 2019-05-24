package game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Interface of Enemy entity
 * 
 * @author Martin Holecek
 *
 */
public interface IEntityEnemy {
	public void tick();

	public void render(Graphics g);

	public Rectangle getBounds();

	public int getRow();

	public int getCol();
}
