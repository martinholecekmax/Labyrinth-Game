package game.gun;

import java.awt.Graphics;

import game.engine.GameObject;
import game.enums.ShootDirection;
import game.sprites.Textures;

public class BulletShoot extends GameObject implements IEntityBullets {
	private Textures textures;
	private ShootDirection direction;

	public BulletShoot(int row, int col, Textures textures, ShootDirection direction) {
		super(row, col);
		this.textures = textures;
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
		g.drawImage(textures.missile, col, row, null);
	}
}
