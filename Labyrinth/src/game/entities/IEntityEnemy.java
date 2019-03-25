package game.entities;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Enemy entity
 * @author martinholecek
 *
 */
public interface IEntityEnemy {
	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();
	
	public int getRow();
	public int getCol();
}
