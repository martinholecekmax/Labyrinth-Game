package game.gun;

import java.awt.Graphics;

import game.engine.Level;
import game.entities.Entity;
import game.entities.Player;
import game.enums.Direction;
import game.sprites.Textures;

public class BulletShoot extends Entity implements IEntityBullets {
	private Textures textures;
	private Direction direction;

	public BulletShoot(Player player, Textures textures, Direction direction, Level level) {
		super(player.row, player.col, level);
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
		g.drawRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
	}
}
