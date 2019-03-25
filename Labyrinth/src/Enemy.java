import java.awt.Graphics;
import java.util.Random;

public class Enemy extends GameObject implements IEntityEnemy {
	private static final int STEPS = 1;
	private Textures textures;
	private Level level;
	private int posRow;
	private int posCol;
	private int direction = 0;
	private boolean moveLeft = false;

	private Random random = new Random();

	public Enemy(int row, int col, Textures textures, Level level) {
		super(row, col);
		this.textures = textures;
		this.level = level;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public boolean moveUp() {
		if (row <= 0)
			return false;

		posRow = (int) Math.floor((row - STEPS) / 32);
		posCol = (int) Math.floor(col / 32);

//		debugPosition();

		if (level.getLevelObject(posRow, posCol) != 4) {
			row = row - STEPS;
			return true;
		}
		return false;
	}

	public boolean moveDown() {
		if (row >= 288)
			return false;

		posRow = (int) Math.floor((row + 32) / 32);
		posCol = (int) Math.floor(col / 32);

//		debugPosition();

		if (level.getLevelObject(posRow, posCol) != 4) {
			row = row + STEPS;
			return true;
		}
		return false;
	}

	public boolean moveRight() {
		if (col >= 288)
			return false;

		posRow = (int) Math.floor(row / 32);
		posCol = (int) Math.floor((col + 32) / 32);

//		debugPosition();

		if (level.getLevelObject(posRow, posCol) != 4) {
			col = col + STEPS;
			return true;
		}
		return false;
	}

	public boolean moveLeft() {
		if (col <= 0)
			return false;

		posRow = (int) Math.floor(row / 32);
		posCol = (int) Math.floor((col - STEPS) / 32);

//		debugPosition();

		if (level.getLevelObject(posRow, posCol) != 4) {
			col = col - STEPS;
			return true;
		}
		return false;
	}

	public void tick() {
		if (direction == 0) {
			moveLeft = moveUp();
		} else if (direction == 1) {
			moveLeft = moveLeft();
		} else if (direction == 2) {
			moveLeft = moveDown();
		} else {
			moveLeft = moveRight();
		}

		if (!moveLeft) {
			direction = random.nextInt(4);
		}
	}

	public void render(Graphics g) {
		g.drawImage(textures.enemy, col, row, null);
	}

	public void debugPosition() {
		System.out.println("Row: " + row);
		System.out.println("Pos Row: " + posRow);
		System.out.println("Col: " + col);
		System.out.println("Pos Col: " + posCol);
	}
}
