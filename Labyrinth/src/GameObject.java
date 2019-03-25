import java.awt.Rectangle;

public class GameObject {
	
	public int row, col;
	
	public GameObject(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(row, col, 32, 32);
	}
}
