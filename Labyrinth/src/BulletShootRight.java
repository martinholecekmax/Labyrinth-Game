import java.awt.Graphics;

public class BulletShootRight extends GameObject implements IEntityBullets{
	private Textures textures;
	
	@Override
	public int getRow() {
		return row;
	}
	
	@Override
	public int getCol() {
		return col;
	}
	
	public BulletShootRight(int row, int col, Textures textures) {
		super(row, col);
		this.textures = textures;
	}
	
	@Override
	public void tick() {
		col = col + 5;
		
//		if (Physics.Collision(this, entityB)) {
//			
//		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(textures.missile, col, row, null);
	}
}
