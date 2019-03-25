package game.engine;
import java.awt.Rectangle;

public class GameObject {
	
	public int row, col;
	
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	public GameObject(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(row, col, 32, 32);
	}
}
