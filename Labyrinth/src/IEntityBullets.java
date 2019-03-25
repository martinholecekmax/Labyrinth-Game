import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Player and bullets entity
 * @author martinholecek
 *
 */
public interface IEntityBullets {
	public void tick();
	public void render(Graphics g);
	
	public Rectangle getBounds();
	
	public int getRow();
	public int getCol();
}
