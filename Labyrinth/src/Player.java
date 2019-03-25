import java.awt.Graphics;

public class Player extends GameObject implements IEntityBullets {
	private Textures textures;
	private int posRow;
	private int posCol;
	private Level level;

	public Player(int row, int col, Textures textures, Level level) {
		super(row, col);
		this.textures = textures;
		this.level = level;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public boolean moveDown() {
		if (row >= 288)
			return false;

		posRow = (int) Math.floor(row / 32);
		posCol = (int) Math.floor(col / 32);

		if (level.getLevelObject(posRow + 1, posCol) != 4) {
			row = row + 32;
			return true;
		}
		return false;
	}

	public boolean moveUp() {
		if (row <= 0)
			return false;

		posRow = (int) Math.floor(row / 32);
		posCol = (int) Math.floor(col / 32);

		if (level.getLevelObject(posRow - 1, posCol) != 4) {
			row = row - 32;
			return true;
		}
		return false;
	}

	public boolean moveRight() {
		if (col >= 288)
			return false;

		posRow = (int) Math.floor(row / 32);
		posCol = (int) Math.floor(col / 32);

		if (level.getLevelObject(posRow, posCol + 1) != 4) {
			col = col + 32;
			return true;
		}
		return false;
	}

	public boolean moveLeft() {
		if (col <= 0)
			return false;

		posRow = (int) Math.floor(row / 32);
		posCol = (int) Math.floor(col / 32);

		if (level.getLevelObject(posRow, posCol - 1) != 4) {
			col = col - 32;
			return true;
		}
		return false;
	}

	public void tick() {

	}

	public void render(Graphics g) {
		g.drawImage(textures.player, col, row, null);
	}
}
