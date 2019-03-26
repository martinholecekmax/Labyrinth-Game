package game.entities;
import java.awt.Graphics;

import game.engine.GameObject;
import game.engine.Level;
import game.gun.IEntityBullets;
import game.sprites.Textures;

public class Player extends GameObject implements IEntityBullets {
	private Textures textures;

	public Player(int row, int col, Textures textures, Level level) {
		super(row, col, level);
		this.textures = textures;
	}
	
	public void tick() {

	}

	public void render(Graphics g) {
		g.drawImage(textures.player, col, row, null);
	}
}
