package game.entities;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.engine.Level;
import game.gun.IEntityBullets;

public class Player extends Entity implements IEntityBullets {
	private BufferedImage texture;

	public Player(int row, int col, BufferedImage textures, Level level) {
		super(row, col, level);
		this.texture = textures;
	}
	
	public void tick() {

	}

	public void render(Graphics g) {
		g.drawImage(texture, col, row, null);
	}
}
